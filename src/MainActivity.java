package com.example.sinramyoun.object;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1,btn2;
    EditText id,pw;
    String loginResult=null;
    String[] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn1 = (Button)findViewById(R.id.join);
        btn2 = (Button)findViewById(R.id.login);

        id = (EditText)findViewById(R.id.editText);
        pw = (EditText)findViewById(R.id.editText2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.join:
                Intent joinIn = new Intent(MainActivity.this,join.class);
                startActivity(joinIn);
                break;
            case R.id.login:
                HttpPostData();
                if(result[0].equals("success")){

                    Intent loginIn = new Intent(MainActivity.this, mainpage.class);
                    loginIn.putExtra("sendId",id.getText().toString());
                    loginIn.putExtra("spermit",result[1]);
                    startActivity(loginIn);
                }else if(loginResult.equals("fail")){
                    Toast.makeText(this,"id,pw를 확인하세요",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(this,"id,pw를 입력하세요",Toast.LENGTH_SHORT);
                }
                break;
        }
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

            buffer.append("id").append("=").append(id.getText().toString().trim()).append("&");
            buffer.append("pw").append("=").append(pw.getText().toString().trim());

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

            loginResult = builder.toString();
            result=loginResult.split("&");

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
}
