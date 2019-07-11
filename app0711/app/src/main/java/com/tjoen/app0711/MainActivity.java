package com.tjoen.app0711;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //액션바 가져오기
        ActionBar actionBar = getSupportActionBar();
        //actionBar.show();
        //actionBar.hide();
        //를 이용해서 액션 바를 보이고 숨기게 할 수 잇습니다.
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.icons8_trash_can_26);
    }
}
