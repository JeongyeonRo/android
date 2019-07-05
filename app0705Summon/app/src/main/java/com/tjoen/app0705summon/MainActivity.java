package com.tjoen.app0705summon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    BoundService myService;
    boolean isBound;

    ServiceConnection myConnection = new ServiceConnection() {
        //서비스를 생성
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyLocalBinder binder =
                    (BoundService.MyLocalBinder)iBinder;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultView = (TextView)findViewById(R.id.resultview);
        Button boundservice = (Button)findViewById(R.id.boundservice);
        Intent intent1 = new Intent(
                MainActivity.this,
                BoundService.class
        );
        bindService(intent1, myConnection, Context.BIND_AUTO_CREATE);
        boundservice.setOnClickListener((view) -> {
            //myService가 가진 메소드를 호출
            //이 Activity 가 종료되더라도 서비스의 메소드는 수행
            String currentTime = myService.getCurrentTime();
            resultView.setText(currentTime);
        });

        //리시버를 등록 - 오레오 이후로는 강제 등록
        registerReceiver(new MyReceiver(),new IntentFilter("com.example.sendbroadcast"));

        Button startIntentService = (Button)findViewById(R.id.startintentservice);
        startIntentService.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this,
                    MyIntentService.class);
            startService(intent);
        });

        Button startservice = (Button)findViewById(R.id.startservice);
        startservice.setOnClickListener((view) -> {
                Intent intent = new Intent(
                        MainActivity.this,
                        MyService.class
                );
                startService(intent);
        });

        Button alarmService = (Button)findViewById(R.id.alermservice);
        alarmService.setOnClickListener((view) -> {
            //일림 관리 객체 생성
            AlarmManager am =
                    (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            //알람에 사용할 인텐트 만들기
            Intent intent = new Intent(MainActivity.this,
                    AlarmReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this,
                    0, intent, 0);
            //현재 시간에서 30초 후 만들기
            //long amtime = System.currentTimeMillis() + 30000;
            //위와 같은 방법은 시간 생성에는 편하지만 하루 뒤와 같은 알람 제작에는 어려우
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.SECOND, 10);

            //알람설정
            am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), sender);
        });
    }
}
