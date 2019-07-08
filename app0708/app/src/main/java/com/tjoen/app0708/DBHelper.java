package com.tjoen.app0708;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "datadb", null, 1);
    }

    //처음 사용할 때 한 번 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db){
        //테이블을 생성하고 데이터 초기화하는 작업
        //서버에 기본 데이터가 있으면 가져와서 저장
        String tableSql = "create table tb_data(" +
                "_id integer primary key autoincrement," +
                "name text," +
                "phone text)";
        //SQL 실행
        db.execSQL(tableSql);
        //기본데이터 삽입
        db.execSQL("insert into tb_data(name, phone) values('강감찬','01012345678')");
        db.execSQL("insert into tb_data(name, phone) values('사과','01012345678')");
        db.execSQL("insert into tb_data(name, phone) values('수박','01012345678')");
        db.execSQL("insert into tb_data(name, phone) values('딸기','01012345678')");
        db.execSQL("insert into tb_data(name, phone) values('자몽','01012345678')");
        db.execSQL("insert into tb_data(name, phone) values('레몬','01012345678')");
        db.execSQL("insert into tb_data(name, phone) values('참외','01012345678')");
    }
    //데이터베이스 버전이 변경된 경우 호출되는 메소드
    //기존 데이터를 삭제하고 테이블을 변경
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion){
        db.execSQL("drop table tb_data");
        onCreate(db);
    }
}
