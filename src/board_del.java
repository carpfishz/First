package com.example.sinramyoun.object;

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


public class board_del extends AppCompatActivity {
    String recId,id;

    String ticket="";
    EditText num;
    String[] test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_del);

        num = (EditText)findViewById(R.id.bnum);

        Button some = (Button) findViewById(R.id.bdel);
        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();
                //Toast.makeText(board_del.this, ticket, Toast.LENGTH_LONG).show();
                if(ticket.equals("success"))
                    Toast.makeText(board_del.this, "삭제되었습니다.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(board_del.this, "알수없는 오류가 발생하였습니다.",Toast.LENGTH_LONG).show();


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

            buffer.append("pnum").append("=").append(num.getText().toString());


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


            //Toast.makeText(this, test[0], Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }


}