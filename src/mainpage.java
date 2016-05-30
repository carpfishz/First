package com.example.sinramyoun.object;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class mainpage extends AppCompatActivity  implements View.OnClickListener{

    String recId;
    String per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        Intent intentSs = getIntent();
        recId = intentSs.getExtras().getString("sendId");
        per =  intentSs.getExtras().getString("spermit");

        Toast.makeText(this, recId, Toast.LENGTH_SHORT);

        if(per.equals("2")){
            LinearLayout layout = (LinearLayout) findViewById(R.id.hidden);
            layout.setVisibility(View.VISIBLE);
            Button access = (Button)findViewById(R.id.access);
            Button peoelccheck = (Button)findViewById(R.id.peoelccheck);
            Button addclass = (Button)findViewById(R.id.addclass);

            access.setOnClickListener(this);
            peoelccheck.setOnClickListener(this);
            addclass.setOnClickListener(this);
        }

        Button roomcheck = (Button)findViewById(R.id.roomcheck);
        Button reserv = (Button)findViewById(R.id.reserv);
        Button cancel = (Button)findViewById(R.id.cancel);
        Button mycheck = (Button)findViewById(R.id.mycheck);

        roomcheck.setOnClickListener(this);
        reserv.setOnClickListener(this);
        cancel.setOnClickListener(this);
        mycheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.roomcheck:
                Intent roomcheckn = new Intent(mainpage.this, roomcheck.class);
                roomcheckn.putExtra("sendId", recId);
                startActivity(roomcheckn);
                break;
            case R.id.reserv:
                Intent reservationIn= new Intent(mainpage.this, reservation.class);
                reservationIn.putExtra("sendId", recId);
                startActivity(reservationIn);
                break;
            case R.id.cancel:
                Intent myIn = new Intent(mainpage.this, cancel.class);
                myIn.putExtra("sendId", recId);
                startActivity(myIn);
                break;
            case R.id.mycheck:
                Intent homeIn = new Intent(mainpage.this, myreservation.class);
                homeIn.putExtra("sendId",recId);
                startActivity(homeIn);
                break;
            case R.id.access:
                Intent access = new Intent(mainpage.this, myreservation.class);
                access.putExtra("sendId",recId);
                startActivity(access);
                break;
            case R.id.peoelccheck:
                Intent people = new Intent(mainpage.this, myreservation.class);
                people.putExtra("sendId",recId);
                startActivity(people);
                break;
            case R.id.addclass:
                Intent addc = new Intent(mainpage.this, myreservation.class);
                addc.putExtra("sendId",recId);
                startActivity(addc);
                break;
        }
    }
}