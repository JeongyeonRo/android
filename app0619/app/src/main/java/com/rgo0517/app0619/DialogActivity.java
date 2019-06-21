package com.rgo0517.app0619;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button loginbtn = (Button)findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Log.e("tag","버튼클릭");
                //login.xml 로 디자인한 뷰 찾아오기
                final LinearLayout login = (LinearLayout)View.inflate(DialogActivity.this,
                        R.layout.login,null);
                new AlertDialog.Builder(DialogActivity.this)
                        .setTitle("Login")
                        .setView(login)
                        //버튼 설정
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText id = (EditText)login.findViewById(R.id.id);
                                EditText pw = (EditText)login.findViewById(R.id.pw);
                                String inputId = id.getText().toString();
                                String inputPw = pw.getText().toString();
                                Toast.makeText(DialogActivity.this, inputId+":"+inputPw,
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
            }
        });
    }
}
