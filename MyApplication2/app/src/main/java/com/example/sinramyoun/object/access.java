package com.example.sinramyoun.object;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class access extends Activity {
    ListView userList;
    UserCustomAdapter userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    String ticket="";
    String[] res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access);

        HttpPostData();

        for(int i=0; i<res.length; i+=6){
            userArray.add(new User(res[i],res[i+1],res[i+2],res[i+3],res[i+4],res[i+5]));
        }


        userAdapter = new UserCustomAdapter(access.this, R.layout.row,userArray);
        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);




    }
    public void HttpPostData() {
        try {

            URL url = new URL("http://chanwoo.hust.net/carpfish/reservationaccessAnd1.php");
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
