package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
    }
    public void change(View v){//this takes us back to the conversion page
        Intent intent= new Intent(getApplicationContext(),Convert.class);
        startActivity(intent);
    }
    public void patreon(View v){//this takes us to the placeholder patreon page
        Intent intent= new Intent(getApplicationContext(),Patreon.class);
        startActivity(intent);
    }
}