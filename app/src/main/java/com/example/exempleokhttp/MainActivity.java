package com.example.exempleokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.txtReponse);
        Button btn = findViewById(R.id.btnWebService);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncCallWS asyncCallWS = new AsyncCallWS();
                asyncCallWS.execute();
            }
        });
    }

    private class AsyncCallWS extends AsyncTask<String, Integer,String>{



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return callServiceWeb();
        }

        @Override
        protected void onPostExecute(String retourSW) {
            super.onPostExecute(retourSW);
            textView.setText(retourSW);
        }
    }

    private String callServiceWeb(){
        //On saisir l'url
        String url = "http://claudehenri.fr/serviceweb/bonjour";

        //String a retourner
        String retourSW = "";

        //on instencie le client OkHTTP
        OkHttpClient client = new OkHttpClient();

        //on créé la requete grace à l'url.
        Request request = new Request.Builder()
                .url(url)
                .build();
        try{
            Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    retourSW =  response.body().string();
                }
        }
        catch (Exception ex){
            Log.e("ErrorSW",ex.getMessage()) ;
            retourSW= ex.getMessage();
        }
        return retourSW;
    }
}
