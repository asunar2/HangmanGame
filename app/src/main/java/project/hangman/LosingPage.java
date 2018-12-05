package project.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LosingPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button lose;
        setContentView(R.layout.activity_losing_page);
        lose = findViewById(R.id.losertohome);
        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to a new android page thing
                Toast.makeText(getApplicationContext(), "Going Home", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LosingPage.this, MainActivity.class);
                //beginnerWord hardcoded to String testing
                //myIntent.putExtra("beginnerWord", testing);
                LosingPage.this.startActivity(myIntent);
            }
        });
    }
}
