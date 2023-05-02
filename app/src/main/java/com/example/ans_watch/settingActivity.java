package com.example.ans_watch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class settingActivity extends Activity{
    private Switch switch1;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 스위치 상태를 저장한다.
        outState.putBoolean("switch_state", switch1.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // 스위치 상태를 복원한다.
        switch1.setChecked(savedInstanceState.getBoolean("switch_state"));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_setting);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        MyApplication myApp = (MyApplication) getApplication();
        switch1 = findViewById(R.id.switch_vibrate);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("switch_state", isChecked);
                editor.apply();
                if (isChecked) {
                    switch1.setText("진동 ON  ");
                    myApp.setMyData(true);
                } else {
                    switch1.setText("진동 OFF");
                    myApp.setMyData(false);
                }
            }
        });
        // 저장된 스위치 상태 가져오기
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("switch_state", false);
        switch1.setChecked(switchState);

        Button developer_info_btn = (Button) findViewById(R.id.button_back);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
    }
    protected void onStart() {
        super.onStart();
        // 액티비티가 화면에 나타날 때 저장된 스위치 상태를 복원한다.
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("switch_state", false);
        switch1.setChecked(switchState);
    }
}
