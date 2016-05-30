package com.example.sinramyoun.object;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class roomcheck extends AppCompatActivity {
    String recId,id,stat;
    ArrayAdapter<String>    m_Adapter;
    String ticket="";
    EditText room;
    String[] test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomcheck);

        room = (EditText)findViewById(R.id.roomedit);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intentSs = getIntent();
        recId = intentSs.getExtras().getString("sendId");
        id=recId;
        HttpPostData();

        ListView list = (ListView)findViewById(R.id.listView);
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        list.setAdapter(m_Adapter);

        Button btn2 = (Button) findViewById(R.id.btnall);
        Button btn = (Button) findViewById(R.id.some);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();
                if (test[1].length() == 4) {
                    m_Adapter.add("강의실 : " + test[1]);
                } else {
                    Toast.makeText(getApplicationContext(), "강의실이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();

                }


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();
                for(int i=1; i<11; i++){
                    m_Adapter.add("강의실 : "+test[i]);
                }
            }
        });


    }
    public void HttpPostData() {
        try {

            URL url = new URL("#");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            buffer.append("id").append("=").append(id).append("&");
            buffer.append("room").append("=").append(room.getText().toString());


            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;

            while ((str = reader.readLine()) != null) {
                if(str==null){

                }
                else
                    ticket+=str;
                builder.append(str);

            }
            test=ticket.split(" ");


        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}