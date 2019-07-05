import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.tjoen.myapplication.MainActivity;

public class MyReceiver extends BroadcastReceiver {
    //이벤트가 발생했을 때 호출되는 메소드
    @Override
    public void onReceive(Context context, Intent intent){
        //토스토 출력
        Toast.makeText(context, "이벤트 발생", Toast.LENGTH_LONG).show();
        //자신의 MainActivity를 화면에 출력하기
        Intent myIntent = new Intent(
                context, MainActivity.class
        );
        //메인액티비티가 화면에 없을 경우를 대비해서 없으면 생성하는 옵션을 추가
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
