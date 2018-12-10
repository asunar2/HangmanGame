package project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LosingPage extends AppCompatActivity {
    String gameWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button lose;
        TextView word;
        setContentView(R.layout.activity_losing_page);
        lose = findViewById(R.id.losertohome);
        word = findViewById(R.id.losingword);
        this.gameWord = getIntent().getStringExtra("word").toUpperCase();
        word.setText("The word is " + gameWord);
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
