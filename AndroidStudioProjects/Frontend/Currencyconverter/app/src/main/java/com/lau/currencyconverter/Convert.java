package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Convert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    private EditText enter;
    private TextView result;
    private Button button;
    private boolean isPlaying;

    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        spinner = (Spinner)  findViewById(R.id.value);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.value, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        result=(TextView) findViewById(R.id.result);
        enter=(EditText) findViewById(R.id.input);
        button= (Button) findViewById(R.id.convert);
        enter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (spinner.getSelectedItem().equals("USD") || spinner.getSelectedItem().equals("LBP")){
                    button.setEnabled(true);
                }
                else
                    button.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (spinner.getSelectedItem().equals("USD") || spinner.getSelectedItem().equals("LBP")){
                    button.setEnabled(true);
                }
                else
                    button.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (spinner.getSelectedItem().equals("USD") || spinner.getSelectedItem().equals("LBP")){
                    button.setEnabled(true);
                }
                else
                    button.setEnabled(false);

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        ImageView currencies = (ImageView) findViewById(R.id.imageView3);
        if (position==0){
            currencies.setImageResource(R.drawable.img);
            button.setEnabled(false);

        }
        if (position==1){
            currencies.setImageResource(R.drawable.img3);


        }
        if (position==2){
            currencies.setImageResource(R.drawable.img2);


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        button.setEnabled(false);
    }
    public void convert(View v){
        String s=spinner.getSelectedItem()+"";
        try{
            double input=Double.parseDouble(enter.getText().toString());
            if(enter.getText().length()!=0){
                if (s.equals("USD")){
                    String concatenated=input*22000 +"LBP";
                    result.setText(concatenated);
                    result.animate().alpha(1.0F);
                }
                else if (s.equals("LBP")){
                    String concatenated=input/22000 +"USD";
                    result.setText(concatenated);
                    result.animate().alpha(1.0F);
                }
                else{
                    Toast.makeText(this, "Enter a currency", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show();
            }
        }catch (NumberFormatException e){
            Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show();
        }
    }


    public void rick(View v) {
        if (player == null) {
            player = MediaPlayer.create(getApplicationContext(), R.raw.rick);
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
    public void fairyTail(View v){
        Intent intent= new Intent(getApplicationContext(),Support.class);
        startActivity(intent);
    }


}