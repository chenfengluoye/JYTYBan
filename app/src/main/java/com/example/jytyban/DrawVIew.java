package com.example.jytyban;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class DrawVIew extends View {
    private int screenW=0;
    private int screenH=0;
    private float perX;
    private float preY;
    private Path path;
    public Paint paint=null;
    Bitmap bitmap=null;
    Canvas canvasx;
    public DrawVIew(Context context, AttributeSet attrs) {
        super(context,attrs);
        screenW=context.getResources().getDisplayMetrics().widthPixels;
        screenH=context.getResources().getDisplayMetrics().heightPixels;
        bitmap=Bitmap.createBitmap(screenW,screenH, Bitmap.Config.ARGB_8888);
        canvasx=new Canvas();
        path=new Path();
        canvasx.setBitmap(bitmap);
        paint=new Paint(Paint.DEV_KERN_TEXT_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFFFFFFFF);
        Paint bumbpaint=new Paint();
        canvas.drawBitmap(bitmap,0,0,bumbpaint);
        canvas.drawPath(path,paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                perX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx=Math.abs(x-perX);
                float dy=Math.abs(y-preY);
                if(dx>=5||dy>=5)
               {
                   path.quadTo(perX,preY,x,y);
                   perX=x;
                   preY=y;
                }
                break;
            case MotionEvent.ACTION_UP:
                canvasx.drawPath(path,paint);
                path.reset();
                break;
        }
        path.moveTo(x,y);
        invalidate();
        return true;
    }
    public void clear(){
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setStrokeWidth(60);
    }
    public void sava(String name){
        try{
            saveBitmap(name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void saveBitmap(String src){
        File file=new File(src);
        try {
            file.createNewFile();
            FileOutputStream fileout=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileout);
            fileout.flush();
            fileout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
