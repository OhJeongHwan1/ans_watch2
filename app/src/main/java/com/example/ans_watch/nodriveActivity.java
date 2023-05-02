
package com.example.ans_watch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;

import java.nio.charset.StandardCharsets;

public class nodriveActivity  extends  Activity{
    private BroadcastReceiver dataReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_nodrive);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        dataReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String data = intent.getStringExtra(MyWearableListenerService.EXTRA_DATA);
                // 데이터를 처리하세요
                Log.d("nodriveActivity", "Data: " + data);
                Intent next;
                if ("start".equals(data)) {
                    // 경고 액티비티를 실행
                    next = new Intent(getApplicationContext(), safeActivity.class);
                    startActivity(next);
                }
            }
        };

        Intent serviceIntent = new Intent(this, MyWearableListenerService.class);
        startService(serviceIntent);

        Button developer_info_btn = (Button) findViewById(R.id.button);
        developer_info_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), safeActivity.class);
                startActivity(intent);
            }
        });
        final TextView text = (TextView) findViewById(R.id.textView5);

        Button developer_info_btn2 = (Button) findViewById(R.id.button_setting);
        developer_info_btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), settingActivity.class);
                startActivity(intent);
            }
        });
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
