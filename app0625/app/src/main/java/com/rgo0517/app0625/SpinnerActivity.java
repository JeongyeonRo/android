package com.rgo0517.app0625;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity {

    String [] ar;
    Spinner spinner;
    TextView nation;
    ArrayAdapter<CharSequence> adapter;

    //처음화면에서 아무것도 선택되지 않기 위한 변수 생성
    //시작할 때 이벤트가 발생한 것인지 확인하기 위한 변수 - false를 가지고 있음
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        //스피너 출력
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setPrompt("여행가고 싶은 나라");

        adapter = ArrayAdapter.createFromResource(
                SpinnerActivity.this,
                R.array.nations,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //array.xml 에 만든 배열을 가져오기
        ar = getResources().getStringArray(R.array.nations);
        //spinner에서
        spinner.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    //선택했을 때 호출되는 메소드
                    //Overrid - 메소드 이름이 틀릴 경우 에러 발생
                    @Override
                    public void onItemSelected(
                            AdapterView parent, View view, int position, long id){
                        if(flag == false){
                            flag = true;
                            return;
                        }
                        Toast.makeText(SpinnerActivity.this, ar[position],
                                Toast.LENGTH_LONG).show();
                        nation = (TextView)findViewById(R.id.nation);
                        nation.setText(ar[position]);
                    }
                    @Override
                    public void onNothingSelected(AdapterView parent){

                    }

                });
    }
}
