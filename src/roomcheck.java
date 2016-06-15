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


        ListView list = (ListView)findViewById(R.id.listView);
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        list.setAdapter(m_Adapter);

        Button all = (Button) findViewById(R.id.btnall);
        Button some = (Button) findViewById(R.id.some);
        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();
                ListView list = (ListView)findViewById(R.id.listView);
                m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
                list.setAdapter(m_Adapter);

                for(int i=0; i<test.length; i++){
                    m_Adapter.add(test[i]);
                }
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData2();
                ListView list = (ListView)findViewById(R.id.listView);
                m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
                list.setAdapter(m_Adapter);
                for(int i=0; i<test.length; i++){
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

            //"bname","mname"은 php에서 값을 받는 변수명과 같아야함//////////////////////////////////////////

            buffer.append("proom").append("=").append(room.getText().toString());


            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            //서버에서 전송받기
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            ticket="";

            while ((str = reader.readLine()) != null) {
                if(str==null){

                }
                else
                    ticket+=str;
                builder.append(str);

            }
            test=ticket.split("&");

            //Toast.makeText(this, test[0], Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }

    public void HttpPostData2() {
        try {

            URL url = new URL("#");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            //"bname","mname"은 php에서 값을 받는 변수명과 같아야함//////////////////////////////////////////
            //buffer.append("om").append("=").append(room.getText().toString());


            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            //서버에서 전송받기
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            ticket="";

            while ((str = reader.readLine()) != null) {
                if(str==null){

                }
                else
                    ticket+=str;
                builder.append(str);

            }

            test=ticket.split("&");
            //Toast.makeText(this, test[0], Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}