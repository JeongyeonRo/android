package com.tjoen.app0712;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {
    //항목 뷰를 만들어주는 클래스
    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public MyViewHolder(View itemview){
            super (itemview);
            title = itemView.findViewById(android.R.id.text1);
        }
    }

    //RecyclerView 출력을 위한 Adapter 클래스
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        //출력하기 위한 데이터 목록
        List<String> list;
        public MyAdapter(List<String> list){
            this.list = list;
        }
        //화면에 출력될 뷰 홀더를 설정하는 메소드
        @Override
        public MyViewHolder onCreateViewHolder(
                ViewGroup group, int i
        ){
            View view = LayoutInflater.from(group.getContext())
                    .inflate(android.R.layout.simple_list_item_1, group, false);
            return new MyViewHolder(view);
        }
        //항목의 개수를 설정하는 메소드
        @Override
        public int getItemCount(){
            return list.size();
        }
        //뷰 홀더와 인덱스를 매핑시켜주는 메소드
        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, int i){
            String text = list.get(i);
            myViewHolder.title.setText(text);
        }
    }
    //RecyclerView가 화면에 출력되기 전이나 출력된 후에 호출되는 클래스
    //이 클래스는 그림을 그리기 위해서 사용
    class MyItemDecoration extends RecyclerView.ItemDecoration{
        //항목간의 거리를 만드는 메소드
        @Override
        public void getItemOffsets(
                Rect outRect, View view, RecyclerView parent,
                RecyclerView.State state){
            //인덱스를 1부터 시작시키기 위해서 +1
            int index = parent.getChildAdapterPosition(view) + 1;
            //3초에 한 번은 아래쪽 여백 늘리기
            if(index % 3 == 0){
                outRect.set(20,20,20,60);
            }else {
                outRect.set(20,20,20,20);
            }
            view.setBackgroundColor(0xFFE9E9E9);
            ViewCompat.setElevation(view, 20.0f);
        }
        //항목을 전부 표시하고 호출되는 메소드
        @Override
        public void onDraw(Canvas canvas, RecyclerView parent,
                               RecyclerView.State state){
            super.onDraw(canvas, parent, state);
            //RecyclerView 전체 크기를 가져오기
            int width = parent.getWidth();
            int height = parent.getHeight();
            //이미지 가져오기
            Drawable dr = ResourcesCompat.getDrawable(
                    getResources(), R.drawable.img2, null
            );
            //이미지 크기 가져오기
            int drWidth = dr.getIntrinsicWidth();
            int drHeigth = dr.getIntrinsicHeight();

            int left = width/2 - drWidth/2;
            int top = width/2 - drHeigth/2;

            //이미지 그리기
            canvas.drawBitmap(
                    BitmapFactory.decodeResource(
                            getResources(), R.drawable.img2
                    ), left, top, null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        //출력할 뷰를 찾아오기
        RecyclerView recyclerView = findViewById(R.id.recycle);
        //데이터 생성
        List<String> list = new ArrayList<>();
        for(int i=1; i<20; i=i+1){
            list.add("항목:" + i);
        }
        //출력 방식 설정
        recyclerView.setLayoutManager(
                new LinearLayoutManager(RecycleActivity.this)
        );
        //어댑터 설정
        recyclerView.setAdapter(
                new MyAdapter(list)
        );
        //데코레이터 설정
        recyclerView.addItemDecoration(new MyItemDecoration());
    }
    @Override
    public void onResume(){
        super.onResume();
        Toast.makeText(this, "ㅎㅇ", Toast.LENGTH_SHORT).show();
    }


}
