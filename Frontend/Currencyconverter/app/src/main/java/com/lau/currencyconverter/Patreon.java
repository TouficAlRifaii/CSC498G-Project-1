package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

public class Patreon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patreon);
        ImageView wendy= (ImageView) findViewById(R.id.wendy);

        wendy.animate().alpha(1.0F).setDuration(2000);
    }
    public void change(View v){//takes us back to the conversion page
        Intent intent= new Intent(getApplicationContext(),Convert.class);
        startActivity(intent);
    }

}