package com.example.sinramyoun.object;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class board extends Activity {
    ListView userList;
    Ucustom userAdapter;
    ArrayList<Buser> userArray = new ArrayList<Buser>();
    String ticket="";
    String[] res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        HttpPostData();

        for(int i=0; i<res.length; i+=2){
            userArray.add(new Buser(res[i],res[i+1]));
        }


        userAdapter = new Ucustom(board.this, R.layout.board_row,userArray);
        userList = (ListView) findViewById(R.id.blist);
        userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                String Item = userAdapter.res[arg2];
                Intent read = new Intent(board.this, board_read.class);
                read.putExtra("num",Item);
                startActivity(read);
                //Toast.makeText(board.this, Item, Toast.LENGTH_SHORT).show();

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


            //서버에서 전송받기
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


            //Toast.makeText(this, res[7], Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }

}
