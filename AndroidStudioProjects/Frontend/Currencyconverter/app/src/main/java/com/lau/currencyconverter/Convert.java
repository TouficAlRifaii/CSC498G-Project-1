package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.InputMismatchException;

public class Convert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    private EditText enter;
    private TextView result;
    private ImageView currencies;
    private boolean isNotImg;
    private int tag;

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

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text = parent.getItemAtPosition(position).toString();
        currencies= (ImageView) findViewById(R.id.imageView3);
        if (position==0){
            currencies.setImageResource(R.drawable.img);
            tag=R.drawable.img;
            isNotImg=false;
        }
        if (position==1){
            currencies.setImageResource(R.drawable.img3);
            tag=R.drawable.img3;
            isNotImg=false;
        }
        if (position==2){
            currencies.setImageResource(R.drawable.img2);
            tag=R.drawable.img2;
            isNotImg=false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void convert(View v){
        String s=spinner.getSelectedItem()+"";
        try{
            double input=Double.parseDouble(enter.getText().toString());
            if(enter.getText().length()!=0){
                if (s.equals("USD")){
                    result.setText(input*22000 +"LBP");
                    result.setAlpha(1.0F);
                }
                else if (s.equals("LBP")){
                    result.setText(input/22000 +"USD");
                    result.setAlpha(1.0F);
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


}