package com.lau.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Convert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //i am creating my variables that will be used in the future methods
    Spinner spinner;
    private EditText enter;
    private TextView result;
    private Button button;
    private boolean isPlaying;
    MediaPlayer player;
    String rate;
    TextView rateDisplay;
    public class DownloadTask extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... urls){
            rate = "" ;
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
                rate = json.getString("rate");
                rateDisplay.setText("Current rate: 1 USD = " + rate + " LBP");
                //Log.i("rate", rate);

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {//in here we should add lines of code that change the view that shows the lira rate so that it updates in real time
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        rateDisplay = (TextView) findViewById(R.id.displayRate);
        String url = "http://192.168.11.108/CSC498G-Project-1/backend/api.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);

        //Log.i("rate", rate);

        //create the spinner that holds the two currencies
        spinner =   findViewById(R.id.value);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.value, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        result= findViewById(R.id.result);
        enter= findViewById(R.id.input);
        button=  findViewById(R.id.convert);
        enter.addTextChangedListener(new TextWatcher() {//this allows me to keep track of whether or not the user inputed a value
            @Override
            //this is executed when the text has not changed
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (spinner.getSelectedItem().equals("USD") || spinner.getSelectedItem().equals("LBP")){
                    button.setEnabled(true);
                }
                else
                    button.setEnabled(false);
            }

            @Override
            //this is executed when the text is being changed
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (spinner.getSelectedItem().equals("USD") || spinner.getSelectedItem().equals("LBP")){
                    button.setEnabled(true);
                }
                else
                    button.setEnabled(false);

            }

            @Override
            //this is executed after the text was changed
            public void afterTextChanged(Editable editable) {

                if (spinner.getSelectedItem().equals("USD") || spinner.getSelectedItem().equals("LBP")){
                    button.setEnabled(true);
                }
                else
                    button.setEnabled(false);
                if (enter.getText().toString().equals("")){

                    button.setEnabled(false);
                }
                else{

                    button.setEnabled(true);
                }

            }
        });

    }
    @Override
    //this is executed on selecting items in the spinner
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        ImageView currencies =  findViewById(R.id.imageView3);
        if (position==0){
            currencies.setImageResource(R.drawable.img);
            button.setEnabled(false);
        }
        if (position==1){
            currencies.setImageResource(R.drawable.img3);
            if (enter.getText().toString().equals("")){

                button.setEnabled(false);
            }
            else{

                button.setEnabled(true);
            }

        }
        if (position==2){
            currencies.setImageResource(R.drawable.img2);
            if (enter.getText().toString().equals("")){
                button.setEnabled(false);
            }
            else{
                button.setEnabled(true);
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        button.setEnabled(false);
    }

    //this method is to be modified after the completion of the api since we need to send it there compute it and get it back
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


    public void rick(View v) {//this method is used when we click on the small image in the lower corner to activate the easter egg
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
    public void fairyTail(View v) {//this method takes us to the thank you page
        if (player != null){
            player.stop();
            isPlaying = false;
            player=null;
        }
        Intent intent = new Intent(getApplicationContext(), Support.class);
        startActivity(intent);
    }
}