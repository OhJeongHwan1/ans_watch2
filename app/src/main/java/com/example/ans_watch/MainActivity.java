package com.example.ans_watch;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.os.Handler;

import com.example.ans_watch.databinding.ActivityMainBinding;



public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, nodriveActivity.class);
                startActivity(intent);
            }
        }, 2000);

    }
}