package com.rgo0517.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        //버튼의 경우 이벤트처리 이후 다른 변화를 줄 일이 없기 때문에 바로 생성
        Button vib = (Button)findViewById(R.id.vib);
        Button syssound = (Button)findViewById(R.id.syssound);
        Button ressound = (Button)findViewById(R.id.ressound);

        vib.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                //진동
                vibrator.vibrate(10000);
            }
        });
        syssound.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                //재생가능한 사운드 만들기
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), noti);
                //재생
                ringtone.play();
            }
        });

        ressound.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                MediaPlayer player = MediaPlayer.create(
                        getApplicationContext(), R.raw.buttoneffect);
                player.start();
            }
        });

        Button stop = (Button)findViewById(R.id.stop);

        Button toast = (Button)findViewById(R.id.toast);
        toast.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "이삭토스트", Toast.LENGTH_LONG).show();
            }
        });



    }
}
