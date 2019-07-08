package com.tjoen.app0708;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    //모든 곳에서 사용할 데이터베이스 변수
    SQLiteDatabase db;

    public MyContentProvider() {
    }

    //데이터를 삭제하는 메소드
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
       int row =  db.delete("tb_data", selection, selectionArgs);
        return row;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //데이터를 삽입하는 메소드
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db.insert("tb_data",null, values);
        return uri;
    }

    //onCreate 메소드 - 맨 처음 한 번만 호출되는 메소드로 가장 먼저 호출됩니다.
    @Override
    public boolean onCreate() {
        //데이터베이스 사용을 위한 인스턴스를 생성
        DBHelper helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
      Cursor cursor = db.query("tb_data",projection,selection,selectionArgs,
              null,null, sortOrder);
      return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int row = db.update("tb_data", values, selection, selectionArgs);
        return row;
    }
}
