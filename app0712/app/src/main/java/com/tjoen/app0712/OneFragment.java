package com.tjoen.app0712;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class OneFragment extends ListFragment {

    @Override
    public void onResume(){
        super.onResume();
        Log.e("Fragment", "잘보이져?");
    }

    @Override
    //화면에 출력될 내용을 만드는 메소드
    public void onViewCreated(
            View view, Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Oracle");
        list.add("WebFrontEnd");
        list.add("Android");
        list.add("iOS");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1, list
                );
        setListAdapter(adapter);
    }
    //항목을 선택햇을 때 호출될 메소드
    @Override
    public void onListItemClick(
            ListView l, View v, int position, long id){
        Toast.makeText(getActivity(), (String)l.getAdapter().getItem(position),
                Toast.LENGTH_SHORT).show();
    }
}
