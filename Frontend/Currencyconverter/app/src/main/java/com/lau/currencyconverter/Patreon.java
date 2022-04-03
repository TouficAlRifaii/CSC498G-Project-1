package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Patreon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patreon);
    }
    public void change(View v){//takes us back to the conversion page
        Intent intent= new Intent(getApplicationContext(),Convert.class);
        startActivity(intent);
    }
}