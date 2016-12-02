package com.example.sinramyoun.object;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


public class board_add extends AppCompatActivity {
    EditText name, content;
    String recId,result, ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_add);

        Intent intentSs = getIntent();
        recId = intentSs.getExtras().getString("sendId");

        name = (EditText)findViewById(R.id.name);
        content = (EditText)findViewById(R.id.content);

        Button btn = (Button)findViewById(R.id.add);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();

                if(name.getText().toString().equals("")){
                    Toast.makeText(board_add.this, "제목을 입력하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    if(content.getText().toString().equals("")){
                        Toast.makeText(board_add.this, "내용 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else{
                        if(ticket.equals("success")) {
                            Toast.makeText(board_add.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(board_add.this, "알수없는 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
    public void HttpPostData() {
        try {

            URL url = new URL("http://chanwoo.hust.net/carpfish/board_add.php");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            buffer.append("pid").append("=").append(recId.toString()).append("&");
            buffer.append("pname").append("=").append(name.getText().toString()).append("&");
            buffer.append("pcontent").append("=").append(content.getText().toString());


            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

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

            //Toast.makeText(this, ticket, Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}
