package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls){
            String rate = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while( data != -1){
                    char current = (char) data;
                    rate += current;
                    data = reader.read();

                }
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
            return rate;
        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s);
                String rate = json.getString("rate");
                Log.i("rate", rate);

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


    //i am creating my variables that will be used in the future methods
    private Button button;
    private ImageView image;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://192.168.11.108/CSC498G-Project-1/backend/api.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);
        button=(Button) findViewById(R.id.next);
        image = (ImageView) findViewById(R.id.imageView2);
    }

    public void start(View v){//this is an on click method which maks it so that when the button is clicked it switches page
        Intent intent= new Intent(getApplicationContext(),Convert.class);
        startActivity(intent);
    }
    public void play (View v){//on click method that makes it so that when the image is clicked an audio is played
        if (player == null){
            player = MediaPlayer.create(getApplicationContext(),R.raw.coindrop);
        }
        player.start();
    }
    public void fairyTail(View v){// on click method that takes the user to the thank you page
        Intent intent= new Intent(getApplicationContext(),Support.class);
        startActivity(intent);
    }

}