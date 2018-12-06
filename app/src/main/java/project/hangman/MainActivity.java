package project.hangman;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button beginnerButton;
    Button advancedButton;
    //FOR TESTING, hardcoded word
    String testing = "testing";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        beginnerButton = findViewById(R.id.beginner); //change button to name of the beginner button thing
        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to a new android page thing
                Toast.makeText(getApplicationContext(), "Starting beginner game", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                //beginnerWord hardcoded to String testing
                myIntent.putExtra("beginnerWord", testing);
                GameActivity.setDifficultyLevel(0);
                MainActivity.this.startActivity(myIntent);
            }
        });

        advancedButton = findViewById(R.id.advanced);
        advancedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to a new android page thing
                Toast.makeText(getApplicationContext(), "Starting advanced game", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                //beginnerWord hardcoded to String testing
                myIntent.putExtra("advancedWord", testing);
                GameActivity.setDifficultyLevel(1);
                MainActivity.this.startActivity(myIntent);
            }
        });

        //Needs to be run as an AsyncTask bc it requires network access
        new WordMaker().execute();

    }
}
