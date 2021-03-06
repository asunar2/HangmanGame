package project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WinningPage extends AppCompatActivity {

    String defText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button win;
        TextView definition;
        setContentView(R.layout.activity_winning_page);
        win = findViewById(R.id.winnertohome);
        definition = findViewById(R.id.definitionText);
        defText = getIntent().getStringExtra("word") + " - "
                + getIntent().getStringExtra("partOfSpeech")
                + ": " + getIntent().getStringExtra("definition");
        definition.setText(defText);
        win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to a new android page thing
                Toast.makeText(getApplicationContext(), "Going Home", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(WinningPage.this, MainActivity.class);
                //beginnerWord hardcoded to String testing
                //myIntent.putExtra("beginnerWord", testing);
                WinningPage.this.startActivity(myIntent);
            }
        });
    }
}
