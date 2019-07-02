package com.tjoen.app0627;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PalyerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palyer);

        //NationActivity 클래스에서 넘겨준 nation 키의 데이터를 찾아오기
        Intent intent = getIntent();
        String nation = intent.getStringExtra("nation");

        //soccer 테이블에서 nation 컬럼의 값이 nation 변수인 데이터를 찾아와서
        //ArrayList에 저장하기
        DBHelper dbHelper = new DBHelper(PalyerActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select player from soccer where nation = ? ",
                new String[]{nation});
        ArrayList<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        db.close();
        //ListView 에 데이터를 출력하기 위한 Adapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                PalyerActivity.this,
                android.R.layout.simple_list_item_1,
                list
        );
        //ListView에 adaptor 연결
        ListView playerlist = (ListView)findViewById(R.id.playerlist);
        playerlist.setAdapter(adapter);

        //텍스트 뷰에 넘겨받은 nation의 값을 출력
        TextView title = (TextView)findViewById(R.id.subtitle);
        title.setText(nation);

        //뒤로 버튼을 눌렀을 때 이전 화면으로 돌아가는 코드
        Button backbtn = (Button)findViewById(R.id.backbtn);
        backbtn.setOnClickListener((view) -> {
            finish();
        });

    }
}
