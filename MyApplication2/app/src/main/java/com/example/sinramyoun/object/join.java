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


public class join extends AppCompatActivity {
    EditText id,pw,name,major,position,phone;
    String result,ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        id = (EditText)findViewById(R.id.id);
        pw = (EditText)findViewById(R.id.pw);
        name = (EditText)findViewById(R.id.name);
        major = (EditText)findViewById(R.id.major);
        position = (EditText)findViewById(R.id.position);
        phone = (EditText)findViewById(R.id.phone);
        Button btn = (Button)findViewById(R.id.join_ok);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();

                if(id.getText().toString().equals("")){
                    Toast.makeText(join.this, "id를 입력하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    if(pw.getText().toString().equals("")){
                        Toast.makeText(join.this, "pw를 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else{
                        if(result.equals("success")) {
                            Toast.makeText(join.this, "가입되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(join.this, MainActivity.class);
                            startActivity(intent);
                        }else if(result.equals("fail")){
                            Toast.makeText(join.this, "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
    public void HttpPostData() {
        try {

            URL url = new URL("http://chanwoo.hust.net/carpfish/joinAnd.php");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            buffer.append("pid").append("=").append(id.getText().toString()).append("&");
            buffer.append("ppw").append("=").append(pw.getText().toString()).append("&");
            buffer.append("pmajor").append("=").append(major.getText().toString()).append("&");
            buffer.append("pname").append("=").append(name.getText().toString()).append("&");
            buffer.append("ppos").append("=").append(position.getText().toString()).append("&");
            buffer.append("pphone").append("=").append(phone.getText().toString());

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
            //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}
