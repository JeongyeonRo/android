package com.rgo0517.app0620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ExternClassActivity extends AppCompatActivity {
    private TextView display;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extern_class);

        //뷰 찾아오기
        display = (TextView)findViewById(R.id.display);
        btn = (Button)findViewById(R.id.btn);

        //버튼의 이벤트 처리 객체를 생성
        ExternEventHandler externEventHandler = new ExternEventHandler(display);
        //이벤트 처리를 위임
        btn.setOnClickListener(externEventHandler);

    }
}
