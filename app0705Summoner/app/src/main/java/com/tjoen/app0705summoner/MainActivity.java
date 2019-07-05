package com.tjoen.app0705summoner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendbraoscast = (Button)findViewById(R.id.sendbroadcast);

        sendbraoscast.setOnClickListener((view) -> {
            //com.example.sendbroadcast를 호출하도록 방송을 발송
            Intent intent = new Intent();
            intent.setAction("com.example.sendbroadcast");
            //한번도 화면에 보여진 적이 없는 경우에도 출력하도록 설정
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            //방송 발송
            sendBroadcast(intent);
        });

    }
}
