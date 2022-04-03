package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //i am creating my variables that will be used in the future methods
    private Button button;
    private ImageView image;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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