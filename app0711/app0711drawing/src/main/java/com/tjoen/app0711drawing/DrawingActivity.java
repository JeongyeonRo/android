package com.tjoen.app0711drawing;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DrawingActivity extends AppCompatActivity {

    //터치한 곳의 정보를 가지는 클래스
    class Vertex{
        float x;
        float y;
        boolean isDraw;

        Vertex(float x, float y, boolean isDraw){
            this.x = x;
            this.y = y;
            this.isDraw = isDraw;
        }
    }

    ArrayList<Vertex> list;

    //화면에서 터치를 하고 드래그를 하면 선을 그려주는 뷰
    class MyCustomView extends View {
        //그리기 정보를 저장할 변수
        Paint paint;
        //생성자
        MyCustomView(Context context){
            super(context);
            //그리기 정보를 저장할 객체를 생성하고 옵션을 설정
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
        }
        //화면에 보여질 때 호출되는 메소드 - 그림
        @Override
        public void onDraw(Canvas canvas) {
            //모든 점들을 순회하면서 홀수번째 좌표에서 짝수 번째 좌표로 선을 그리면 됩니다.
            //isDraw 값을 확인해서 true인 경우 이전 좌표에서 현재 좌표까지 선을 그려도 됩니다.
            /*
            int len = list.size();
            for(int i=0; i<len; i=i+1){
                if(list.get(i).isDraw == true){
                    canvas.drawLine(
                            list.get(i-1).x, list.get(i-1).y,
                            list.get(i).x, list.get(i).y, paint
                    );
                }
            }
            */

            /*
            //이미지를 반대로 출력하기
            Paint paint = new Paint();
            Resources resources = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(
                    resources, R.drawable.image1
            );
            //위의 비트맵을 출력
            //canvas.drawBitmap(bitmap, 10,10,null);

            //크기는 곱하기 1 또
            Matrix matrix = new Matrix();
            matrix.setScale(0.5f, 0.5f);
            Bitmap editBitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                    matrix, false
            );
            canvas.drawBitmap(editBitmap, 10, 10, paint);

            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            //패스 만들기
            Path path = new Path();
            path.moveTo(10, 400);
            path.cubicTo(80, 330, 150, 400, 220, 360);
            canvas.drawPath(path, paint);

            //패스 위에 글자 출력
            paint.setTextSize(30);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath("글자를 패스 위에 출력", path, 0, 0, paint);
            */


            Paint paint = new Paint();
            //이미지 생성
            Bitmap bitmap = BitmapFactory.decodeResource(
                    getResources(), R.drawable.image1
            );
            //컬러필터 생성
            ColorFilter filter = new LightingColorFilter(
                    0x33ffff,0x000080
            );
            paint.setColorFilter(filter);
            //이미지 출력
            canvas.drawBitmap(bitmap, 10,10, paint);
        }

        //터치 이벤트가 발생했을 때 호출되는 메소드
        @Override
        public boolean onTouchEvent(MotionEvent event){
            //터치가 시잘한 경우에는 좌표만 저장
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                list.add(new Vertex(event.getX(), event.getY(), false));
                return true;
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                list.add(new Vertex(event.getX(), event.getY(), true));
                //다시 출력하도록 초기화
                invalidate();
                return true;
            }
            return false;
        }
    }

    class MyView extends View {
        //기본 생성자가 없기 때문에 생성자를 만들어서 상위 클래스의 생성자를
        //직접 호출해야 합니다.
        public MyView(Context context){
            super(context);
        }
        //뷰가 화면에 보여져야 할 때 뷰를 그려주는 메소드
        @Override
        public void onDraw(Canvas canvas){
            //그림을 그릴 때는 그리기 전보를 저장할 Paint 객체가 필요
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            canvas.drawCircle(200,200,100,paint);
            //canvas.drawCircle(800,200,100,paint);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_drawing);
        //원그리기
        /*
        MyView view = new MyView(DrawingActivity.this);
        setContentView(view);
        */
        //그림그리기
        MyCustomView view = new MyCustomView(DrawingActivity.this);
        setContentView(view);
        list = new ArrayList<>();
    }
}
