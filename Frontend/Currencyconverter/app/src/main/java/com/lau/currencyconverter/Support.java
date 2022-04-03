package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Support extends AppCompatActivity {

    MediaPlayer player;
    private boolean isPlaying;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        isPlaying=false;
        logo=(ImageView) findViewById(R.id.logo);
    }
    public void change(View v){//this takes us back to the conversion page
        Intent intent= new Intent(getApplicationContext(),Convert.class);
        startActivity(intent);
    }
    public void patreon(View v){//this takes us to the placeholder patreon page
        Intent intent= new Intent(getApplicationContext(),Patreon.class);
        startActivity(intent);
    }
    public void ft(View v) {//this method is used when we click on the small image in the lower corner to activate the easter egg
        if (player == null) {
            player = MediaPlayer.create(getApplicationContext(), R.raw.fairytail);
        }
        if (isPlaying){
            player.stop();
            isPlaying = false;
            player=null;
            
        }
        else {

            player.start();
            isPlaying=true;

        }

    }
}