package com.example.ans_watch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;
import android.annotation.*;
import android.os.VibrationEffect;
import android.os.Vibrator;


public class warningActivity  extends  Activity{
    private Handler handler;
    private Vibrator vibrator;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_warning1);

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

}
