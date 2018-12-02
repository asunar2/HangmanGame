package project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

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

    Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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
    private void addWrongLetter(String input) {
        StringBuilder letters = new StringBuilder(wrongLettersString);
        letters.append(input);
        this.wrongLettersString = letters.toString();
    }

}
