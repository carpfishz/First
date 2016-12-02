package com.example.sinramyoun.object;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class addclass extends AppCompatActivity {
    String recId, id, stat;
    ArrayAdapter<String> m_Adapter;
    String ticket = "";
    EditText proom;
    String[] test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclass);

        proom = (EditText) findViewById(R.id.addedit);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Intent intentSs = getIntent();
        recId = intentSs.getExtras().getString("sendId");
        id = recId;





        Button add = (Button) findViewById(R.id.addclass);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();
                if(ticket.equals("success"))
                    Toast.makeText(addclass.this, "등록되었습니다.",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(addclass.this, "알수없는 오류가 발생하였습니다.",Toast.LENGTH_LONG).show();


            }
        });

    }

    public void HttpPostData() {
        try {

            URL url = new URL("http://chanwoo.hust.net/carpfish/addclassAnd.php");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            //"bname","mname"은 php에서 값을 받는 변수명과 같아야함//////////////////////////////////////////

            buffer.append("proom").append("=").append(proom.getText().toString());


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
                if (str == null) {

                } else
                    ticket += str;
                builder.append(str);

            }
            test=ticket.split("&");


            //Toast.makeText(this, ticket, Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}