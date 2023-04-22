
package com.example.ans_watch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
public class nodriveActivity  extends  Activity{

    public MyWearableListenerService service;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_nodrive);

        service = new MyWearableListenerService();

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
    protected void onDestroy() {
        super.onDestroy();
    }
}
