package com.rgo0517.app0620;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        String msg = event.getX() + ":" + event.getY();
        Toast.makeText(MainActivity.this, msg,  Toast.LENGTH_LONG).show();
        return true;
    }
}
