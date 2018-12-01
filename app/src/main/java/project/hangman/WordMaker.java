package project.hangman;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordMaker extends AsyncTask<String, Void, String> {
    private static final String TAG = "WordMaker";

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "Finding the word");
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... strings) {
        String wordForGame = null;
        getWordOfDay();
        return wordForGame;
    }
    @Override
    protected void onPostExecute(String msg) {

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
                Log.d(TAG, "OUTPUTTING: \n" + output);
            } else {
                Log.d(TAG, "http request failed, response code: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            Log.d(TAG, "oops: " + e);
        }
        //return wordOfDay;
    }
}