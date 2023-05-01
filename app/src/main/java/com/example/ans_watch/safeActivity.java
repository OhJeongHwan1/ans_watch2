package com.example.ans_watch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.google.android.gms.wearable.DataClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class safeActivity  extends  Activity{
    private DataClient mDataClient;
    TextView timeDate;
    long StartTime = 0L;
    long MillisecondTime = 0L;
    long startTime = SystemClock.uptimeMillis();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_safe);

        timeDate = (TextView) findViewById(R.id.timetext);
        timeDate.setText(getTime());

        Button developer_info_btn = (Button) findViewById(R.id.button2);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), warningActivity.class);
                startActivity(intent);
            }
        });

        Button developer_info_btn2 = (Button) findViewById(R.id.button_setting2);
        developer_info_btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), settingActivity.class);
                startActivity(intent);
            }
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                timeDate.setText(getTime());
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(runnable, 1000);
    }
    private String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH : mm : ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        long now = SystemClock.uptimeMillis() - startTime;
        Date date = new Date(now);
        String getTime = dateFormat.format(date);
        return getTime;
    }
    public void onCreate(){

    }
}
