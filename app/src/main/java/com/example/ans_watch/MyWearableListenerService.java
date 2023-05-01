package com.example.ans_watch;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import java.nio.charset.StandardCharsets;

public class MyWearableListenerService extends WearableListenerService {

    private static final String TAG = "from Android";
    private static final String DATA_PATH = "/data";
    public static final String ACTION_DATA_RECEIVED = "com.example.ans_watch.DATA_RECEIVED";
    public static final String EXTRA_DATA = "extra_data";

    public MyWearableListenerService() {
        Log.d(TAG, "객체가 여기서 생성했음");
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        Log.d(TAG, "데이터가 바뀌었다!!");

        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED && dataEvent.getDataItem().getUri().getPath().equals(DATA_PATH)) {
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                String message = dataMap.getString("message");
                Log.d("수신 성공!!", "받은 데이터는" + message);

                Intent localIntent = new Intent(ACTION_DATA_RECEIVED);
                localIntent.putExtra(EXTRA_DATA, message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
            }
        }
    }
}
