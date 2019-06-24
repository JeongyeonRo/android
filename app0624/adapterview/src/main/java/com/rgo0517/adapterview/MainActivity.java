package com.rgo0517.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //출력할 데이터 만들기
        ArrayList<String> list = new ArrayList<>();
        list.add("사과");
        list.add("수박");
        list.add("딸기");
        list.add("포도");
        list.add("망고");
        list.add("자몽");

        //리스트 뷰를 찾아오기
        ListView listView = (ListView)findViewById(R.id.listView);

        //리스트 뷰에 데이터를 출력하기 위한 Adapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, list);
        //Adapter를 listView에 주입
        listView.setAdapter(adapter);

        //선택 모드 설정
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //선 관련 옵션 수정
        listView.setDivider(new ColorDrawable(Color.RED));
        listView.setDividerHeight(3);

    }
}
