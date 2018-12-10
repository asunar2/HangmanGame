package project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LosingPage extends AppCompatActivity {
    private String gameWord;
    private String definition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button lose;
        TextView word;
        TextView definitionText;
        setContentView(R.layout.activity_losing_page);
        lose = findViewById(R.id.losertohome);
        word = findViewById(R.id.losingword);
        definitionText = findViewById(R.id.definition);
        this.gameWord = getIntent().getStringExtra("word").toUpperCase();
        word.setText("The word is " + gameWord);
        this.definition = getIntent().getStringExtra("partOfSpeech").toUpperCase() + ": "
                + getIntent().getStringExtra("definition");
        definitionText.setText(this.definition);
        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to a new android page thing
                Toast.makeText(getApplicationContext(), "Going Home", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LosingPage.this, MainActivity.class);
                LosingPage.this.startActivity(myIntent);
            }
        });
    }
}
