package com.example.sinramyoun.object;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class reservation extends AppCompatActivity {

    private int mYear;
    private int mMonth;
    private int mDay;
    EditText resdate,resroom;
    static final int DATE_DIALOG_ID = 0;
    String recId;
    String per;
    String starttime="";
    String endtime="";
    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        Intent intentSs = getIntent();
        recId = intentSs.getExtras().getString("sendId");

        Button complete = (Button) findViewById(R.id.complete);
        Button resdateb = (Button) findViewById(R.id.resdateb);
        resdate = (EditText) findViewById(R.id.resdate);
        resroom = (EditText) findViewById(R.id.resroom);


        final String[] start = {"18:00","19:00","20:00","21:00","22:00"};
        final String[] end = {"18:00","19:00","20:00","21:00","22:00"};

        final Spinner spinner1 = (Spinner) findViewById(R.id.startp);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, start);
        spinner1.setAdapter(adapter);
        spinner1.setSelection(1);
        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                starttime= (String) spinner1.getSelectedItem();

            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        final Spinner spinner2 = (Spinner) findViewById(R.id.endp);
        ArrayAdapter<String> adapter2;
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, end);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(1);
        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                endtime = (String) spinner2.getSelectedItem();

            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        resdateb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(reservation.this, listener, 2016, 5, 17);
                dialog.show();
                //showDialog(DATE_DIALOG_ID);
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpPostData();
                if (result.equals("success")) {
                    Toast.makeText(getApplicationContext(), "예약 되었습니다.", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(reservation.this, mainpage.class);
                    //intent.putExtra("sendId", recId);
                    //intent.putExtra("spermit", per);
                    //startActivity(intent);
                    //Toast.makeText(getApplicationContext(),res[0] , Toast.LENGTH_SHORT).show();

                }

            }
        });

    }


    public void HttpPostData() {
        try {

            URL url = new URL("http://chanwoo.hust.net/carpfish/reservationAnd.php");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");

            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();

            //"bname","mname"은 php에서 값을 받는 변수명과 같아야함//////////////////////////////////////////
            buffer.append("id").append("=").append(recId).append("&");
            buffer.append("proom").append("=").append(resroom.getText().toString()).append("&");
            buffer.append("pstarttime").append("=").append(starttime).append("&");
            buffer.append("pendtime").append("=").append(endtime).append("&");
            buffer.append("restade").append("=").append(resdate.getText().toString());


            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            //서버에서 전송받기
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;

            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
            result = builder.toString();

            //loginResult = builder.toString();


        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //Toast.makeText(getApplicationContext(), year + "년" + monthOfYear + "월" + dayOfMonth +"일", Toast.LENGTH_SHORT).show();
            resdate.setText(year + "-" + (monthOfYear+1) + "-" + dayOfMonth );
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            //updateDisplay();
        }
    };
    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }
}

