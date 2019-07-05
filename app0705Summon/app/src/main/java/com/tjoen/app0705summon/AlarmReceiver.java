package com.tjoen.app0705summon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "알람호출",
                Toast.LENGTH_LONG).show();
        //토스트 대신에 음악, 어플이 뜨는 걸로 도전!해보기
        //4섹션때 노래재싱되기 함
        MediaPlayer player = MediaPlayer.create(
                context, R.raw.sample);
        player.start();
    }
}
