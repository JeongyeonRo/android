package com.tjoen.app0708;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShareSystemApp extends AppCompatActivity {

    Button contactBtn, galleyBtn;
    LinearLayout content;

    //화면 전페 크기의 너비와 높이를 저장할 변수
    int reqWidth;
    int reqHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_system_app);

        contactBtn = (Button)findViewById(R.id.contact);
        galleyBtn = (Button)findViewById(R.id.gallery);
        content = (LinearLayout)findViewById(R.id.content);

        //현재 디바이스의 전체 화면 크기 가져오기
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        reqWidth = metrics.widthPixels;
        reqHeight = metrics.heightPixels;

        //동적으로 권한 요청하기
        if (ContextCompat.checkSelfPermission(
                ShareSystemApp.this,
                Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    ShareSystemApp.this,
                    new String[]{
                Manifest.permission.READ_CONTACTS
                    }, 100);
        }
        //연락처 버튼을 눌러서 연락처 화면을 출력하도록 작성
        contactBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                //연락처 Intent 를 생성
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(
                        Uri.parse("content://com.android.contacts/data/phones"
                ));
                //intent를 출력하고 10번으로 구분해서 데이터를 넘겨받을 수 있도록
                //Intent 를 출력 - startActivity 는 못넘겨 받음
                startActivityForResult(intent, 10);
            }
        });
    }
    @Override
    public void onActivityResult(
            int requestCode, int resultCode, Intent data
    ){
        //연락처 Intent 가 닫혔을 때
        if(requestCode == 10){
            //선택한 데이터의 id 찾아오기
            String id = Uri.parse(data.getDataString()).getLastPathSegment();
            //데이터 가져오기
            Cursor cusor = getContentResolver().query(
                    ContactsContract.Data.CONTENT_URI,
                    new String[]{ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.Data._ID+"="+id,null,null
            );
            cusor.moveToNext();
            String name = cusor.getString(0);
            String phone = cusor.getString(1);

            //텍스트 뷰를 동적으로 생성해서 content에 추가
            TextView textView = new TextView(ShareSystemApp.this);
            textView.setText(name+":"+phone);
            textView.setTextSize(25);
            //크기 설정
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            content.addView(textView);
        }
    }
}