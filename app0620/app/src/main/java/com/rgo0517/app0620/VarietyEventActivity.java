package com.rgo0517.app0620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class VarietyEventActivity extends AppCompatActivity {
    private LinearLayout mainview;
    private EditText edit;
    private Button hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variety_event);

        mainview = (LinearLayout)findViewById(R.id.mainview);
        edit = (EditText)findViewById(R.id.edit);
        hide = (Button)findViewById(R.id.hide);

        //키보드 관리 객체를 생성
        final InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        //버튼을 눌렀을 때의 이벤트 처리
       hide.setOnClickListener((view) -> {imm.hideSoftInputFromWindow(
               edit.getWindowToken(), 0);
       });

       //메인 뷰 영역을 터치했을 때의 이벤트 처리
        mainview.setOnTouchListener((view, event) -> {
            imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
            return true;
        });
    }
}
