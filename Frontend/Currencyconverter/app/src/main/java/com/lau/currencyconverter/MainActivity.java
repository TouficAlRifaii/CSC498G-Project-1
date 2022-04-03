package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

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

    public void start(View v){
        Intent intent= new Intent(getApplicationContext(),Convert.class);
        startActivity(intent);
    }
    public void play (View v){
        if (player == null){
            player = MediaPlayer.create(getApplicationContext(),R.raw.coindrop);
        }
        player.start();
    }
    public void fairyTail(View v){
        Intent intent= new Intent(getApplicationContext(),Support.class);
        startActivity(intent);
    }

}