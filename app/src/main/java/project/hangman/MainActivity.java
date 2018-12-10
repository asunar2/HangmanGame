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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button beginnerButton;
    String gameWord = "not null";
    String gameDefinition = "Definition - N/A";
    String gamePOS = "Part of Speech - N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiCall();
        beginnerButton = findViewById(R.id.beginner); //change button to name of the beginner button thing
        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to a new android page thing
                Toast.makeText(getApplicationContext(), "Starting game", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                //beginnerWord hardcoded to String testing
                myIntent.putExtra("beginnerWord", gameWord);
                myIntent.putExtra("definition", gameDefinition);
                myIntent.putExtra("partOfSpeech", gamePOS);
                MainActivity.this.startActivity(myIntent);
            }
        });


    }

    public void apiCall() {
        final String url ="https://wordsapiv1.p.rapidapi.com/words/?random=true";
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {

                        try {
                            Log.d("Response11", response.toString());
                            Log.d("Response22", response.getString("word"));
                            gameWord = response.getString("word");
                            apiCallDefinition();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w("Response", error.toString());
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "CiQsyIyhApmshwVvH76egK5yYwYvp161sI2jsnEQ86QFhSqxhp");
                Log.d("Response", params.toString());
                return params;
            }
        };
        queue.add(request);
    }
    public void apiCallDefinition() {
        final String url ="https://wordsapiv1.p.rapidapi.com/words/" + gameWord + "/definitions";
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {

                        try {
                            Log.d("Response1", response.toString());
                            //Log.d("Response2", response.getString("word"));
                            JSONArray temp = response.getJSONArray("definitions");
                            if (temp.length() != 0) {
                                gameDefinition = temp.getJSONObject(0).getString("definition");
                                gamePOS = temp.getJSONObject(0).getString("partOfSpeech");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w("Response", error.toString());
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "CiQsyIyhApmshwVvH76egK5yYwYvp161sI2jsnEQ86QFhSqxhp");
                Log.d("Response", params.toString());
                return params;
            }
        };
        queue.add(request);
    }
}
