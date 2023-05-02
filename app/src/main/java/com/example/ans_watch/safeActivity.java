package com.example.ans_watch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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
    private BroadcastReceiver dataReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_safe);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        timeDate = (TextView) findViewById(R.id.timetext);
        timeDate.setText(getTime());

        dataReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data = intent.getStringExtra(MyWearableListenerService.EXTRA_DATA);
                // 데이터를 처리하세요
                Log.d("nodriveActivity", "Data: " + data);
                Intent next;
                if ("warning".equals(data)) {
                    // 경고 액티비티를 실행
                    next = new Intent(getApplicationContext(), warningActivity.class);
                    startActivity(next);
                }
                if ("back".equals(data)) {
                    onBackPressed();
                }
            }
        };

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
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(
                dataReceiver, new IntentFilter(MyWearableListenerService.ACTION_MESSAGE_RECEIVED));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(dataReceiver);
        super.onPause();
    }
}
