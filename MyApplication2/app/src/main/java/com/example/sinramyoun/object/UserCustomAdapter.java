package com.example.sinramyoun.object;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class UserCustomAdapter extends ArrayAdapter<User> {
    Context context;
    int layoutResourceId;
    ArrayList<User> data = new ArrayList<User>();
    String ticket="";
    String[] res;
    String id, room;


    public UserCustomAdapter(Context context, int layoutResourceId,
                             ArrayList<User> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.textRoom = (TextView) row.findViewById(R.id.room);
            holder.textWtime = (TextView) row.findViewById(R.id.w_time);
            holder.textStime = (TextView) row.findViewById(R.id.s_time);
            holder.textEtime = (TextView) row.findViewById(R.id.e_time);
            holder.textId = (TextView) row.findViewById(R.id.id);
            holder.textRtime = (TextView) row.findViewById(R.id.r_time);
            holder.access = (Button) row.findViewById(R.id.access);
            holder.deny = (Button) row.findViewById(R.id.deny);
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        final User user = data.get(position);
        holder.textRoom.setText(user.getRoom());
        holder.textWtime.setText(user.getWtime());
        holder.textStime.setText(user.getStime());
        holder.textEtime.setText(user.getEtime());
        holder.textId.setText(user.getId());
        holder.textRtime.setText(user.getRtime());

        holder.access.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                id = user.getId();
                room = user.getRoom();
                HttpPostData();
                if (ticket.equals("success"))
                    Toast.makeText(context, "승인 되었습니다.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "알수없는 오류가 발생하였습니다.", Toast.LENGTH_LONG).show();

                //Toast.makeText(context, room, Toast.LENGTH_SHORT).show();
            }
        });

        holder.deny.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                id = user.getId();
                room = user.getRoom();
                HttpPostData2();
                if(ticket.equals("success"))
                    Toast.makeText(context, "거절하였습니다.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context, "알수없는 오류가 발생하였습니다.",Toast.LENGTH_LONG).show();

                //Toast.makeText(context, room, Toast.LENGTH_SHORT).show();
            }
        });


        return row;

    }
    public void HttpPostData() {
        try {

            URL url = new URL("http://chanwoo.hust.net/carpfish/reservationaccessAnd2.php");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            //"bname","mname"은 php에서 값을 받는 변수명과 같아야함//////////////////////////////////////////
            buffer.append("pid").append("=").append(id).append("&");
            buffer.append("proom").append("=").append(room);

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



            //Toast.makeText(context, ticket, Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }
    public void HttpPostData2() {
        try {

            URL url = new URL("http://chanwoo.hust.net/carpfish/reservationaccessAnd3.php");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            //"bname","mname"은 php에서 값을 받는 변수명과 같아야함//////////////////////////////////////////
            buffer.append("pid").append("=").append(id).append("&");
            buffer.append("proom").append("=").append(room.toString());

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
            res=ticket.split("&");


            //Toast.makeText(this, ticket, Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
    }

    static class UserHolder {
        TextView textRoom;
        TextView textWtime;
        TextView textStime;
        TextView textEtime;
        TextView textId;
        TextView textRtime;
        Button access;
        Button deny;
    }

}



