package com.tjoen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리시버를 등록
        //오레오 버전 이우헤는 이렇게 직접 등록해야 합니다.
        registerReceiver(
                new MyReceiver(),
                new IntentFilter(
                        "com.example.sendbroadcast"
                )
        );

    }
}
