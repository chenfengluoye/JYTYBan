package com.example.jytyban;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DrawVIew ttt;
    ActionBar bar;
    Calendar calendar;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=getSupportActionBar();
        ttt=(DrawVIew) findViewById(R.id.ttt);
        calendar=Calendar.getInstance();
        Toast.makeText(MainActivity.this,"点击‘音量-’可以全屏绘画",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mian,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       String cc=item.getTitle().toString();
       Log.e("dy",String.valueOf(item.getTitle()));
        if(cc.equals("红色")){
           ttt.paint.setColor(Color.RED);
        }
        if(cc.equals("黄色")){
            ttt.paint.setColor(Color.YELLOW);
        }
        if(cc.equals("绿色")){
            ttt.paint.setColor(Color.GREEN);
        }if(cc.equals("蓝色")){
            ttt.paint.setColor(Color.BLUE);
        }

        if(cc.equals("超大")){
            ttt.paint.setStrokeWidth(60);
        }
        if(cc.equals("大")){
            ttt.paint.setStrokeWidth(40);
        }
        if(cc.equals("中")){
            ttt.paint.setStrokeWidth(20);
        }
        if(cc.equals("小")){
            ttt.paint.setStrokeWidth(10);
        }
        if(cc.equals("极小")){
            ttt.paint.setStrokeWidth(5);
        }
        if(cc.equals("橡皮擦")){
            ttt.clear();
        }
        if(cc.equals("开始绘图")){
            ttt.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        }
        if(cc.equals("保存绘图")){
            int xx=calendar.get(Calendar.YEAR);
            int xxx=calendar.get(Calendar.MONTH);
            int xxxx=calendar.get(Calendar.DATE);
            int xxxxx=calendar.get(Calendar.HOUR);
            int xxxxxx=calendar.get(Calendar.MINUTE);
            int xxxxxxx=calendar.get(Calendar.SECOND);
            ttt.saveBitmap("/sdcard/pictures/p"+xx+xxx+xxxx+xxxxx+xxxxx+xxxxxx+xxxxxxx+".png");
            AlertDialog dialog=new AlertDialog.Builder(this).create();
            dialog.setTitle("提示");
            dialog.setMessage("成功保存至/sdcard/pictures/文件夹目录下");
            dialog.show();
        }
        if(cc.equals("退出软件")){
            finish();
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU) {
            super.openOptionsMenu();
            return true;
        }

        else if(keyCode==KeyEvent.KEYCODE_HOME)
        {
            if(bar.isShowing())
            {
                bar.hide();
            }else
                bar.show();
            return true;
        }
        else if (keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            if(bar.isShowing())
            {
                bar.hide();
            }else
                bar.show();
            return true;
        }
        else
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {

        if(bar.isShowing())
        {
            bar.hide();
        }else
            bar.show();
        return true;
    }
}
