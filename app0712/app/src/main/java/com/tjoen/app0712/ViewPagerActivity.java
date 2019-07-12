package com.tjoen.app0712;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ViewPager pager = findViewById(R.id.pager);
        MyPagerAdapter adapter =
                new MyPagerAdapter(
                        getSupportFragmentManager()
                );
        pager.setAdapter(adapter);

    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        //Fragment 들을 저장할 List
        ArrayList <Fragment> fragments;
        public MyPagerAdapter(FragmentManager manager){
            super(manager);
            fragments = new ArrayList<>();
            fragments.add(new OneFragment());
            fragments.add(new ThreeFragment());
        }
        //화면 개수를 설정하는 메소드
        @Override
        public int getCount(){
            return fragments.size();
        }
        //출력할 Fragment를 설정하는 메소드
        @Override
        public Fragment getItem(int porition){
            return fragments.get(porition);
        }
    }

}
