package project.hangman;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;



public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    //The game word
    public String gameWord;
    //game word in a char[]
    public char[] gameWordArray;
    //The wrong letters
    private String wrongLettersString = "";
    //already guessed letters
    private String alreadyGuessed = "";
    //number of body parts
    private int numBodyParts = 6;
    //current body part (0 = head, 2 = body... 5 = one leg)
    private int currentBodyPart = -1;
    //array of body parts images
    private ImageView[] bodyParts;
    private LinearLayout wordLayout;
    private TextView[] wordTextView;
    private MyKeyboard keyboard;
    private EditText editText;
    private String ltr;
    private int numCharsCorrect = 0;
    TextView correctWord;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //display and connect the keyboard
        editText = (EditText) findViewById(R.id.editText);
        keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        homeButton = findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Goes back to the home page
                Toast.makeText(getApplicationContext(),"Going home", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(GameActivity.this, MainActivity.class);
                GameActivity.this.startActivity(myIntent);
            }
        });

        //array of body part so images can be displayed one by one when user guesses wrong
        bodyParts = new ImageView[numBodyParts];
        bodyParts[0] = findViewById(R.id.head);
        bodyParts[1] = findViewById(R.id.body);
        bodyParts[2] = findViewById(R.id.arm1);
        bodyParts[3] = findViewById(R.id.arm2);
        bodyParts[4] = findViewById(R.id.leg1);
        bodyParts[5] = findViewById(R.id.leg2);

        //set the game word from main activity
        this.gameWord = getIntent().getStringExtra("beginnerWord").toUpperCase();

        //account for any whitespace or hyphens in the game word
        checkWhitespace();

        //set the word layout in activity_game.xml
        wordLayout = findViewById(R.id.correctWord);

        //array of TextViews of each character in the word
        wordTextView = new TextView[gameWord.length()];
        wordLayout.removeAllViews();
        for (int i = 0; i < gameWord.length(); i++) {
            wordTextView[i] = new TextView(this);
            wordTextView[i].setText("" + gameWord.charAt(i));

            wordTextView[i].setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            wordTextView[i].setGravity(Gravity.CENTER);
            wordTextView[i].setTextColor(Color.WHITE);
            wordTextView[i].setBackgroundResource(R.drawable.letter_dash_background);

            //add to wordLayout
            wordLayout.addView(wordTextView[i]);
        }
        //Makes all body parts invisible at first
        for(int i = 0; i < numBodyParts; i++) {
            bodyParts[i].setVisibility(View.INVISIBLE);
        }
    }
    private void displayWrongLetters() {
        if (this.wrongLettersString == null) {
            return;
        }
        //Change to uppercase
        String toDisplay = this.wrongLettersString.toUpperCase();
        //Change to an char[], sort by alphabetical
        char[] toSort = toDisplay.toCharArray();
        Arrays.sort(toSort);
        //Convert back to a String and add spaces between letters to display
        toDisplay = new String(toSort);
        toDisplay = toDisplay.replace("", " ");
        //Update the text in the TextView box
        TextView wrongLetters;
        wrongLetters = findViewById(R.id.wrongLetters);
        wrongLetters.setText(toDisplay);
    }
    //Helper, call after input to check if it's in the word
    private boolean checkInWord(final String input) {
        if (input.length() > 1) {
            return input.equals(this.gameWord);
        }
        if (this.gameWord.contains(input)) {
            return true;
        } else {
            addWrongLetter(input);
            return false;
        }

    }
    //Helper, adds the wrong letters to the wrongLettersString
    private void addWrongLetter(final String input) {
        this.wrongLettersString += input;
    }

    //accounts for whitespace or hyphens in
    public void checkWhitespace() {
        gameWordArray = gameWord.toCharArray();
        for (int i = 0; i < gameWord.length(); i++) {
            if (gameWordArray[i] == '-') {
                numCharsCorrect++;
            }
            if (gameWordArray[i] == ' ') {
                numCharsCorrect++;
            }
        }
    }

    //method called to play the game when letter from keyboard is clicked
    public void playGame() {
        Log.d(TAG, "current wrongLettersString " + wrongLettersString);
        ltr = keyboard.getValue();
        if (!alreadyGuessed.contains(ltr)) {
            alreadyGuessed += ltr;
            if (wrongLettersString.length() == numBodyParts) {
                gameWin(false);
            }
        } else {
            Toast.makeText(getApplicationContext(), "You've already guessed that", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkInWord(ltr)) {
            //make that letter visible and check for game win
            for (int i = 0; i < gameWord.length(); i++) {
                if (String.valueOf(gameWord.charAt(i)).equals(ltr)) {
                    wordTextView[i].setTextColor(Color.BLACK);
                    numCharsCorrect++;
                }
            }
            if(numCharsCorrect == gameWord.length()) {
                gameWin(true);
            }
        } else {
            displayWrongLetters();
            currentBodyPart++;
            bodyParts[currentBodyPart].setVisibility(View.VISIBLE);
            if (currentBodyPart == numBodyParts - 1) {
                gameWin(false);
            }

        }

    }

    //checks to see if game has been won
    private void gameWin(boolean win) {
        //Should send to a different activity page that says either win or loss
        if (win) {
            Toast.makeText(getApplicationContext(), "You win!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(GameActivity.this, WinningPage.class);
            GameActivity.this.startActivity(myIntent);
        } else {
            Toast.makeText(getApplicationContext(), "You Lose. Correct word is " + gameWord, Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(GameActivity.this, LosingPage.class);
            myIntent.putExtra("word", gameWord);
            GameActivity.this.startActivity(myIntent);
        }

    }
}