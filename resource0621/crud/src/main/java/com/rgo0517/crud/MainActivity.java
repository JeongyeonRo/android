package com.rgo0517.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

//SQLite를 사용하기 위한 클래스 생성
class WorldDBHelper extends SQLiteOpenHelper{
    //생성자에서 데이터베이스를 생성
    public WorldDBHelper(Context context) {
        //데이터베이스 파일 생성
        //EngWord.db 를 파일 이름으로 하고 Version 은 1
        //factory는 표준 커서 이동
        super(context, "EngWord.db", null,1);
    }
    //데이터베이스가 만들어 질 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db){
        //테이블 만들기
        db.execSQL("create table dic(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "eng TEXT," +
                "han TEXT);");
    }
    //데이터베이스 버전이 변경될 때 호출되는 메소드
    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion, int newVersion){
        //테이블을 삭제하고 새로 생성
        //기존 데이터가 존재하고 데이터를 보존할 필요성이 있을 때는 데이터를 옮기고
        //테이블을 다시 생
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

        //버튼 찾아오기
        Button create = (Button)findViewById(R.id.create);
        //저장 버튼을 클릭했을 때 처리
        create.setOnClickListener((view) -> {
            //데이터베이스 찾아오기
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //삽입할 데이터를 생성
            ContentValues row = new ContentValues();
            row.put("han","남자");
            row.put("eng","man");
            //데이터 삽입
            db.insert("dic", null, row);

            //sql을 이용해서 삽입
            db.execSQL("insert into dic values(null,'woman','여자');");
            //로그 출력
            Log.e("데이터삽입","성공");

            //데이터베이스 닫기
            dbHelper.close();
        });

        //읽기 버튼 가져오기
        Button read = (Button)findViewById(R.id.read);
        //읽기 버튼을 눌렀을 때 수행할 내용
        read.setOnClickListener((view) -> {
            //Edit 찾아오기
            EditText display = (EditText)findViewById(R.id.sqldisplay);
            //dic 테이블의 모든 데이터 찾아오기
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(
                    "select eng, han from dic", null);
            //모든 데이터 읽기
            String r  = "";
            while (cursor.moveToNext()){
                String eng = cursor.getString(0);
                String han = cursor.getString(1);
                r += eng + ":" + han +"\n";
            }
            display.setText(r);
            cursor.close();
            dbHelper.close();
        });

        Button update = (Button)findViewById(R.id.update);
        update.setOnClickListener((view) -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //수정할 내용을 작성 - set 절의 내용
            ContentValues row = new ContentValues();
            row.put("han","남성");
            //수정
            db.update("dic", row, "eng='man'", null);
            db.close();
        });

        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener((view) -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("dic","eng='man'", null);
            db.close();
        });

    }
}
