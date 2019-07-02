package com.tjoen.app0627;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class CallActivity extends AppCompatActivity {
    //전화 안됨 - 에러

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Button callPhone = (Button)findViewById(R.id.callphone);
        callPhone.setOnClickListener((view) -> {
            //실시간 권한 체크(6.0부터 추가)
            if(ContextCompat.checkSelfPermission(
                    CallActivity.this,
                    Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:010-9880-2279"));
                startActivity(intent);
            }
        });
    }
}
