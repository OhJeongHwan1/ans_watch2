package com.example.ans_watch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;
import android.annotation.*;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class warningActivity  extends  Activity{
    private Handler handler;
    private Vibrator vibrator;
    private BroadcastReceiver dataReceiver;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_warning1);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        dataReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data = intent.getStringExtra(MyWearableListenerService.EXTRA_DATA);
                // 데이터를 처리하세요
                Log.d("nodriveActivity", "Data: " + data);
                if ("back".equals(data)) {
                    onBackPressed();
                }
            }
        };

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), warning2Activity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        Button developer_info_btn = (Button) findViewById(R.id.toSafe1);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
        MyApplication myApp = (MyApplication) getApplication();
        boolean a = myApp.getIsChecked();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if(a == true){
            vibrator.vibrate(VibrationEffect.createOneShot(2700, 255));
            Log.d("read", "진동 울림");
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        vibrator.cancel();
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
