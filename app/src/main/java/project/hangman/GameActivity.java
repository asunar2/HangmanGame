package project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
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
        //Remove this line later, update text on button press in future
        this.displayWrongLetters("Wrong Letters");
        getWordOfDay();
    }
    private void displayWrongLetters(final String letters) {
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
    private static void getWordOfDay() {
        String wordOfDay = null;
        try {
            //Open http connection
            final URL url = new URL("https://en.oxforddictionaries.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //If the connection was successful, get the page and put it in a string for now
            if (connection.getResponseCode() == 200) {
                InputStream response = connection.getInputStream();
                int charByte;
                StringBuilder stringBuilder = new StringBuilder();
                //Go until the end of the InputStream, then close the connection
                while ((charByte = response.read()) != -1) {
                    stringBuilder.append((char) charByte);
                }
                String output = stringBuilder.toString();
                connection.disconnect();
                Log.d(TAG, "OUTPUTTING: " + output);
            } else {
                Log.d(TAG, "http request failed, response code: " + connection.getResponseCode());
            }


        } catch(Exception e) {
            Log.d(TAG, "oops: " + e);
        }
        //return wordOfDay;
    }

}
