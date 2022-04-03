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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        public String rateInside = "" ;


        protected String doInBackground(String... urls){
            String getting = "" ;
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
                    getting += current;
                    data = reader.read();

                }
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
            return getting;
        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s);
                rate = json.getString("rate");
                rateDisplay.setText("Current rate: 1 USD = " + rate + " LBP");
                rateInside = rate;

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
        Log.i("rateInside",task.rateInside);
        rate = task.rateInside;

        Log.i("rate", rate);

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
        Log.i("rate" , rate);
        String s=spinner.getSelectedItem()+"";
        try{
            double input=Double.parseDouble(enter.getText().toString());
            if(enter.getText().length()!=0){
                if (s.equals("USD")){
                   // String concatenated=input * Integer.parseInt(rate)  +"LBP";
                    sendPostRequest(Double.toString(input) , rate ,s.toLowerCase(Locale.ROOT));
//                    result.setText(concatenated);
//                    result.animate().alpha(1.0F);

                }
                else if (s.equals("LBP")){
                  //  String concatenated=input/Integer.parseInt(rate) +"USD";
                    sendPostRequest(Double.toString(input) , rate ,s.toLowerCase(Locale.ROOT));
//                    result.setText(concatenated);
//                    result.animate().alpha(1.0F);
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
        String result = "" ;
        String url = "http://192.168.11.108/CSC498G-Project-1/backend/post.php";


    }

    private void sendPostRequest(String givenAmount, String givenRate , String givenCurrency) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {

                String paramAmount = params[0];
                String paramRate = params[1];
                String paramCurrency = params[2];


                HttpClient httpClient = new DefaultHttpClient();

                // In a POST request, we don't pass the values in the URL.
                //Therefore we use only the web page URL as the parameter of the HttpPost argument
                HttpPost httpPost = new HttpPost("http://192.168.11.108/CSC498G-Project-1/backend/post.php");

                // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
                //uniquely separate by the other end.
                //To achieve that we use BasicNameValuePair
                //Things we need to pass with the POST request
                BasicNameValuePair amountBasicNameValuePair = new BasicNameValuePair("paramAmount", paramAmount);
                BasicNameValuePair rateBasicNameValuePAir = new BasicNameValuePair("paramRate", paramRate);
                BasicNameValuePair currencyBasicNameValuePAir = new BasicNameValuePair("paramCurrency", paramCurrency);

                // We add the content that we want to pass with the POST request to as name-value pairs
                //Now we put those sending details to an ArrayList with type safe of NameValuePair
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(amountBasicNameValuePair);
                nameValuePairList.add(rateBasicNameValuePAir);
                nameValuePairList.add(currencyBasicNameValuePAir);


                try {
                    // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                    //This is typically useful while sending an HTTP POST request.
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                    // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                    httpPost.setEntity(urlEncodedFormEntity);

                    try {
                        // HttpResponse is an interface just like HttpPost.
                        //Therefore we can't initialize them
                        HttpResponse httpResponse = httpClient.execute(httpPost);

                        // According to the JAVA API, InputStream constructor do nothing.
                        //So we can't initialize InputStream although it is not an interface
                        InputStream inputStream = httpResponse.getEntity().getContent();

                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        StringBuilder stringBuilder = new StringBuilder();

                        String bufferedStrChunk = null;

                        while((bufferedStrChunk = bufferedReader.readLine()) != null){
                            stringBuilder.append(bufferedStrChunk);
                        }

                        return stringBuilder.toString();

                    } catch (ClientProtocolException cpe) {
                        System.out.println("First Exception case of HttpResponese :" + cpe);
                        cpe.printStackTrace();
                    } catch (IOException ioe) {
                        System.out.println("Second Exception case of HttpResponse :" + ioe);
                        ioe.printStackTrace();
                    }

                } catch (UnsupportedEncodingException uee) {
                    System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                    uee.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);

                try{
                    JSONObject json = new JSONObject(s);
                    String result = json.getString("result");
                    Log.i("result" , result);


                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(givenAmount, givenRate , givenCurrency);
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