package com.tjoen.app0711;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ContextMenuActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        imageView = (ImageView)findViewById(R.id.imageview);
        //imageView 가 컨텍스트 메뉴를 사용할 수 있도록 설정
        registerForContextMenu(imageView);
    }

    //문자열을 받아서 토스트로 출력해주는 메소드
    private void toast(String message){
        /*
        Toast toast =
                Toast.makeText(ContextMenuActivity.this,
                        message, Toast.LENGTH_LONG);
        toast.show();
        */
        //set 메소드가 자기 자신을 리턴
        Toast.makeText(ContextMenuActivity.this,
                message, Toast.LENGTH_LONG).show();
    }

    //컨텍스트 메뉴를 만들어주는 메소드를 재정의
    @Override
    public void onCreateContextMenu(
            //두번째 매개변수가 컨텍스트 메뉴를 사용하는 뷰
            ContextMenu menu, View view,
            ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add(0,0,0,"서버전송");
        menu.add(0,1,0,"로컬에 저장");
        menu.add(0,2,0,"삭제");
    }
    //컨텍스트 메뉴를 눌렀을 때 호출될 메소드
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //메뉴의 id를 이용해서 분기
        switch (item.getItemId()) {
            case 0:
                toast("서버에 전송합니다.");
                break;
            case 1:
                toast("로컬에 저장합니다.");
                break;
            case 2:
                toast("delete");
                break;
        }
        return true;
    }

    //메뉴를 만들어주는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lab, menu);
        return true;
    }
    //메뉴를 선택했을 때 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //toast(item.getItemId() + "");
        switch (item.getItemId()){
            case R.id.menu1:
                toast("select menu1");
                break;
            case R.id.menu2:
                toast("select menu2");
                break;
            case R.id.menu3:
                toast("select menu3");
                break;
            case R.id.sub1:
                toast("select sub1");
                break;
            case R.id.sub2:
                toast("select sub2");
                break;
        }
        return true;
    }
}
