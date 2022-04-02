package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.InputMismatchException;

public class Convert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    private EditText enter;
    private TextView result;
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

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