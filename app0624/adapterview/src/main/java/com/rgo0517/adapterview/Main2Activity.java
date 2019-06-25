package com.rgo0517.adapterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    //리스트 뷰에 출력할 데이터 모임
    ArrayList<String> list;
    //Adapter
    ArrayAdapter<String> adapter;
    //ListView
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //출력할 데이터 생성
        list = new ArrayList<>();
        list.add("사과");

        //다중 선택을 위한 데이터 추가
        list.add("딸기");
        list.add("자몽");
        list.add("포도");
        list.add("수박");
        list.add("멜론");
        list.add("당근");

       //adapter 생성
        //단일 선택의 경우
//        adapter = new ArrayAdapter<>(
//                Main2Activity.this, android.R.layout.simple_list_item_single_choice, list);
        //다중 선택의 경우
        adapter = new ArrayAdapter<>(
                Main2Activity.this, android.R.layout.simple_list_item_multiple_choice, list);

        //listView 에 적용
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        //android의 listView는 adapter에 설정한 항목 뷰의 모양과
        //Choice 모드가 일치해야만 출력
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //삽입 버튼 클릭 이벤트 처리
        Button insertBtn = (Button)findViewById(R.id.insertbtn);
        insertBtn.setOnClickListener((view) -> {
            //입력한 내용 가져오기
            EditText edit = (EditText)findViewById(R.id.name);
            String content = edit.getText().toString();
            if(content.trim().length() == 0){
                Toast.makeText(
                        Main2Activity.this,
                        "입력할 내용을 작성하세요", Toast.LENGTH_LONG).show();
                return;
            }
            list.add(content);
            //데이터가 변경되었다는 것을 ListView에게 전달
            //ListView 가 데이터를 다시 출력
            adapter.notifyDataSetChanged();
            //입력 완료 이후 클어
            edit.setText("");
        });

        //삭제 버튼 이벤트 처리
        Button deleteBtn = (Button)findViewById(R.id.deletetbtn);
        deleteBtn.setOnClickListener((view) -> {
            //선택한 행 번호 가져오기
//            int pos = listView.getCheckedItemPosition();
//            if(pos < 0 || pos >= list.size()){
//                Toast.makeText(Main2Activity.this, "선택할 항목이 없습니다.", Toast.LENGTH_LONG).show();
//                return;
//            }
            //리스트에서 데이터를 삭제하고 리스트 뷰를 다시 출력
//            list.remove(pos);
//            adapter.notifyDataSetChanged();
            //선택된 항목들의 인덱스 가져오기
            //ListView의 모든 인덱스 들의 배열
            //선택된 것은 true 선택이 안된 것은 false를 저장
            SparseBooleanArray sba = listView.getCheckedItemPositions();
            //선택이 된 경우에만 동작
            if(sba.size() >= 0){
                //반복문 안에서 데이터의 집합(List, Set, Map)등의 삽입, 삭제 작업을 하는 경우의 데이터 개수 문제
                for(int i=0; i<sba.size(); i=i+1){
                    if(sba.get(sba.size()-i-1) == true){
                        list.remove(sba.size()-i-1);
                    }
                }
            }
            listView.clearChoices();
            adapter.notifyDataSetChanged();
        });

        //ListView의 아이템을 클릭했을 때 처리
        //첫번째 매개변수는 이벤트가 발생한 AdapterView
        //두번째 매개변수는 이벤트가 발생한 항목 뷰
        //세번째 매개변수는 선택한 항목의 인덱스
        //네번째 매개변수는 선택한 항목의 id
        listView.setOnItemClickListener(
                (parent, view, pos, id) -> {
                    String data = list.get(pos);
                    Toast.makeText(Main2Activity.this, data, Toast.LENGTH_LONG).show();
        });
    }
}
