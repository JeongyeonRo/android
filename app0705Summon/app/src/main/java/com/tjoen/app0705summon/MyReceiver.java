package com.tjoen.app0705summon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        //토스트 출력
        Toast.makeText(context, "Event Happend", Toast.LENGTH_LONG).show();
        //자신의 MainActivity를 화면에 출력
        Intent myIntent = new Intent(context, MainActivity.class);
        //MainActivity가 화면에 없을 경우 대비 생성
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(myIntent);
    }
}
