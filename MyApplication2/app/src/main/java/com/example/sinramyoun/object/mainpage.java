package com.example.sinramyoun.object;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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

       // Toast.makeText(this, recId, Toast.LENGTH_SHORT);

        if(per.equals("2")){
            LinearLayout layout = (LinearLayout) findViewById(R.id.hidden);
            layout.setVisibility(View.VISIBLE);
            Button access = (Button)findViewById(R.id.access);
            Button peoplecheck = (Button)findViewById(R.id.peoplecheck);
            Button addclass = (Button)findViewById(R.id.addclass);

            access.setOnClickListener(this);
            peoplecheck.setOnClickListener(this);
            addclass.setOnClickListener(this);
            LinearLayout layout2 = (LinearLayout) findViewById(R.id.hidden2);

            layout2.setVisibility(View.VISIBLE);

            Button addboard = (Button)findViewById(R.id.addboard);
            Button delboard = (Button)findViewById(R.id.delboard);


            addboard.setOnClickListener(this);
            delboard.setOnClickListener(this);
        }

        Button roomcheck = (Button)findViewById(R.id.roomcheck);
        Button reserv = (Button)findViewById(R.id.reserv);
        Button cancel = (Button)findViewById(R.id.cancel);
        Button mycheck = (Button)findViewById(R.id.mycheck);
        Button board = (Button)findViewById(R.id.board);

        board.setOnClickListener(this);
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
                roomcheckn.putExtra("spermit", per);
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
                myIn.putExtra("spermit", per);
                startActivity(myIn);
                break;
            case R.id.mycheck:
                Intent homeIn = new Intent(mainpage.this, myreservation.class);
                homeIn.putExtra("sendId",recId);
                homeIn.putExtra("spermit", per);
                startActivity(homeIn);
                break;
            case R.id.access:
                Intent access = new Intent(mainpage.this, access.class);
                access.putExtra("sendId",recId);
                access.putExtra("spermit", per);
                startActivity(access);
                break;
            case R.id.peoplecheck:
                Intent people = new Intent(mainpage.this, peoplecheck.class);
                people.putExtra("sendId",recId);
                people.putExtra("spermit", per);
                startActivity(people);
                break;
            case R.id.addclass:
                Intent addc = new Intent(mainpage.this, addclass.class);
                addc.putExtra("sendId",recId);
                addc.putExtra("spermit", per);
                startActivity(addc);
                break;
            case R.id.board:
                Intent board = new Intent(mainpage.this, board.class);
                board.putExtra("sendId",recId);
                board.putExtra("spermit", per);
                startActivity(board);
                break;
            case R.id.delboard:
                Intent board_del = new Intent(mainpage.this, board_del.class);
                startActivity(board_del);
                break;
            case R.id.addboard:
                Intent board_add = new Intent(mainpage.this, board_add.class);
                board_add.putExtra("sendId",recId);
                startActivity(board_add);
                break;
        }
    }
}