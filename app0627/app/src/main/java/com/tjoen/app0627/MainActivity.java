package com.tjoen.app0627;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //startActivityForResult 메소드로 하위 액티비티를 출력한 경우
    //하위 액티비티가 소멸될 때 호출되는 메소드
    @Override
    public void onActivityResult(
            int requestCode, int resultCode, Intent intent){
        String data = intent.getStringExtra("data");
        Toast.makeText(MainActivity.this,
                data,
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(
                MainActivity.this,
                "메인 액티비티 생성",
                Toast.LENGTH_LONG
        ).show();

        Button subbtn = (Button)findViewById(R.id.subbtn);
        subbtn.setOnClickListener((view) -> {
            //SubActivity 를 호출할 수 있는 Intent 만들기
            //부모가 될 Activity의 참조와 출력될 activity 의 클래스이름을 가지고 생성
            Intent intent = new Intent(
                    MainActivity.this,
                    SubActivity.class
            );

            //하위 Activity 에게 전달할 데이터 만들기
            EditText editText = (EditText)findViewById(R.id.message);
            String data = editText.getText().toString();
            intent.putExtra("message",data);

            //새로운 인텐트 출력
            //startActivity(intent);

            //데이터를 넘겨받을 수 있고록 인텐트를 출력
            //두번째로 넘겨주는 정수는 하위 액티비티들을 구분하기 위한 코드
            startActivityForResult(intent, 1);
        });
    }
    //액티비티가 종료되었다가(사라졌다) 다시 실행되는 경우 호출되는 메소드
    //= 액티비티가 화면에 보여지면 무조건 호출되는 메소드
    @Override
    public void onResume(){
        super.onResume();
        Toast.makeText(
                MainActivity.this,
                "메인 액티비티 계속",
                Toast.LENGTH_LONG
        ).show();
    }
}
