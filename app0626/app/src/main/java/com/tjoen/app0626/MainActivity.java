package com.tjoen.app0626;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView display;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView)findViewById(R.id.display);
        seekBar = (SeekBar)findViewById(R.id.seek);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //메소드가 한 개가 아니기 때문에 람다를 사용할 수 없음
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //RAM이 작은 컴퓨터에서는 과부하가 걸릴 수 있음
                //display.setText(seekBar.getProgress() + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                display.setText(seekBar.getProgress() + "");
            }
        });

    }
}
