package com.rgo0517.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.widget.TabHost;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        //탭을 생성하기 위해서 TabHost를 가져오기
        TabHost host = (TabHost)findViewById(R.id.host);
        host.setup();
        //하나의 탭 추가
        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(null,
                ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon1,null));
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        spec = host.newTabSpec("tab1");
        spec.setIndicator(null,
                ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon2,null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("tab1");
        spec.setIndicator(null,
                ResourcesCompat.getDrawable(getResources(), R.drawable.tab_icon3,null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

    }
}
