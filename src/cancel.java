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


public class cancel extends AppCompatActivity {
    String recId,id,stat;
    ArrayAdapter<String>    m_Adapter;
    String ticket="";
    String result="";
    EditText room;
    String[] res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intentSs = getIntent();
        recId = intentSs.getExtras().getString("sendId");
        id=recId;


        ListView list = (ListView)findViewById(R.id.clistView);
        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        list.setAdapter(m_Adapter);
        Button btn = (Button) findViewById(R.id.cmycheck);
        Button btn2 = (Button) findViewById(R.id.cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();
                if( res[0] != null)
                {
                    m_Adapter.add("강의실 : "+res[0]);
                    m_Adapter.add("예약날자 : "+res[1]);
                    m_Adapter.add("시작시간 : "+res[2]);
                    m_Adapter.add("종료시간 : "+res[3]);
                    m_Adapter.add("고객 이름 : "+res[4]);
                    m_Adapter.add("예약 상태 : " + res[5]);

                }
                else{
                    Toast.makeText(getApplicationContext(), "예약된 강의실이 존재하지 않습니다", Toast.LENGTH_SHORT).show();

                }


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData2();
                if (result.equals("success")) {
                    Toast.makeText(getApplicationContext(), "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(cancel.this, mainpage.class);
                    intent.putExtra("sendId", recId);
                    startActivity(intent);

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

            buffer.append("id").append("=").append(id);


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
            res=ticket.split("&");



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

            buffer.append("id").append("=").append(id);


            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
            result = builder.toString();



        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}