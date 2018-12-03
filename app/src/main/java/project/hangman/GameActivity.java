package project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridView;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    //The game word
    private String gameWord;
    //The wrong letters
    private String wrongLettersString;
    //number of characters in the word
    private int numCharacters;
    //number of correct guesses
    private int numCorrect;
    //number of body parts
    private int numBodyParts = 6;
    //current body part (0 = head, 2 = body... 5 = one leg)
    private int currentBodyPart;
    //array of body parts images
    private ImageView[] bodyParts;


    Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        EditText editText = (EditText) findViewById(R.id.editText);
        MyKeyboard keyboard = (MyKeyboard) findViewById(R.id.keyboard);
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

        //Get the word for the game from MainActivity
        this.gameWord = getIntent().getStringExtra("beginnerWord");
        this.numCharacters = gameWord.length();
        this.numCorrect = 0;
        Log.d(TAG, "word: " + gameWord + " length: " + numCharacters);
        //Remove this line later, update text on button press in future
        this.displayWrongLetters("Wrong Letters");
    }
    private void displayWrongLetters(final String letters) {
        if (letters == null) {
            return;
        }
        //Change to uppercase
        String toDisplay = letters.toUpperCase();
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
        String letters = wrongLettersString + input;
        this.wrongLettersString = letters;
    }


}
