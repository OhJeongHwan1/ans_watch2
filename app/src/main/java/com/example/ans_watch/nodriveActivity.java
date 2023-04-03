package com.example.ans_watch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;
import android.annotation.*;
import android.view.View;
import android.widget.Button;

public class nodriveActivity  extends  Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_nodrive);
        Button developer_info_btn = (Button) findViewById(R.id.button);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), safeActivity.class);
                startActivity(intent);
            }
        });
        Button developer_info_btn2 = (Button) findViewById(R.id.button_setting);
        developer_info_btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), settingActivity.class);
                startActivity(intent);
            }
        });
    }
}
