package com.rgo0517.app0619;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //레이아웃에 작성한 뷰들 중에서 코드로 수정할 뷰 변수
    private TextView result;
    private Button btn;

    private String [] gender = {"f","m"};
    private Button singledialog;

    private  String [] hobbies = {"a","b","c","d"};
    private Button multidialog;
    //인덱스들의 선택 여부를 저장하기 위한 배열 생성
    private boolean [] bSelect ={false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰 변수들에 참조를 대입
        result = (TextView)findViewById(R.id.result);
        btn =(Button)findViewById(R.id.btn);

        //버튼 눌렀을 때 이벤트 처리
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("좋아하는 동물을 선택하세요")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setItems(R.array.animal, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //리소스에 작성한 문자열 배열을 가져오기
                                String [] items = getResources().getStringArray(R.array.animal);
                                result.setText(items[i]);
                            }
                        })
                        .setNegativeButton("cancel",null)
                        .show();
            }
        });

        //단일 항목 선택 대화 상자 출력
        singledialog = (Button)findViewById(R.id.singledialog);
        singledialog.setOnClickListener(new Button.OnClickListener(){
            public  void onClick(View v){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("gender")
                        //.setItems() - 목록
                        //.setMessage() - 텍스트
                        //라디오 버튼 만들기
                        .setSingleChoiceItems(gender, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String item = gender[i];
                                Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
                            }
                        })
                        //취소버튼 만들기
                        .setNegativeButton("cancel",null)
                        .show();
            }
        });
        multidialog = (Button)findViewById(R.id.multidialog);
        multidialog.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("다중 선택 대화상자")
                        .setMultiChoiceItems(hobbies, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                //선택이 변경된 항목의 값으로 배열의 값을 변경
                                bSelect[i] = b;
                            }
                        })
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String hobby = "";
                                int idx = 0;
                                for(boolean b : bSelect){
                                    if(b)
                                        hobby = hobby + hobbies[idx];
                                    //선택 초기화
                                        bSelect[idx] = false;
                                    idx = idx + 1;
                                }
                                result.setText(hobby);
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
            }
        });
    }
}
