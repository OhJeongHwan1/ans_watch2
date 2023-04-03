package com.example.ans_watch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;
import android.annotation.*;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class warning2Activity  extends  Activity{
    private Handler handler;
    private Vibrator vibrator;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_warning2);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), warningActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1200);

        Button developer_info_btn = (Button) findViewById(R.id.toSafe2);
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
            vibrator.vibrate(VibrationEffect.createOneShot(1500, 255));
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        vibrator.cancel();
    }
}
