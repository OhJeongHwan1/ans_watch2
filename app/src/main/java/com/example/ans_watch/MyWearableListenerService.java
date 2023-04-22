package com.example.ans_watch;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import java.nio.charset.StandardCharsets;

public class MyWearableListenerService extends WearableListenerService {

    private static final String TAG = "from Android";
    private static final String MESSAGE_PATH = "/message";

    public MyWearableListenerService(){
        Log.d(TAG, "객체가 생성했음");
    }
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyWearableListenerService 객체가 생성되었습니다.");
    }
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "데이터 수신은 성공" + messageEvent.getPath());
        if (messageEvent.getPath().equals(MESSAGE_PATH)) {
            String message = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent("my-event");
            intent.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            Toast.makeText(this, "Hello, world!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "받은 데이터는" + message);
        } else {
            super.onMessageReceived(messageEvent);
        }
    }
}
