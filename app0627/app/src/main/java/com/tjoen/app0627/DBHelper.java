package com.tjoen.app0627;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //생성자 - Context 를 넘겨받아서 상위 클래스를 호출
    public DBHelper(Context context){
        super(context, "database.sqlite",null,1);
    }
    //처음 사용하려고 했을때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db){
        //테이블을 만들고 샘플 데이터를 삽입
        String sql = "create table soccer(" +
                "_id integer primary key autoincrement, nation text," +
                "player text)";
        //sql 실행
        db.execSQL(sql);

        //샘플 데이터 삽입
        db.execSQL("insert into soccer(nation, player)" +
                "values('영국','게리')");
        db.execSQL("insert into soccer(nation, player)" +
                "values('영국','앨런')");
        db.execSQL("insert into soccer(nation, player)" +
                "values('ko','son')");
        db.execSQL("insert into soccer(nation, player)" +
                "values('ko','cha')");

    }
    //데이터베이스 버전이 변경되었을 때 호출되는 메소드
    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion, int newVersion){
        if(newVersion == 1) {
            //테이블을 삭제하고 다시 생성
            db.execSQL("drop table soccer");
            onCreate(db);
        }
    }
}
