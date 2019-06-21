package com.rgo0517.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

class WorldDBHelper extends SQLiteOpenHelper{
    //생성자에서 데이터베이스를 생성
    public WorldDBHelper(Context context){
        super(context, "EngWord.db", null,1);
    }
    //데이터베이스가 만들어 질 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db){
        //테이블 만들기
        db.execSQL("create table dic(" +
                "_id INTERGER PRIMARY KEY AUTOINCREMENT," +
                "eng TEXT, han TEXT);");
    }
    //데이터베이스 버전이 변경될 때 호출되는 메소드
    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion, int newVersion){
        //테이블을 삭제하고 새로 생성
        db.execSQL("drop table if exists dic");
        onCreate(db);
    }
}

public class MainActivity extends AppCompatActivity {

    //데이터베이스 변수
    WorldDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터베이스 생성
        dbHelper = new WorldDBHelper(MainActivity.this);
    }
}
