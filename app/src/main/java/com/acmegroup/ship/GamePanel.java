package com.acmegroup.ship;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;
    private SceneManager manager;
    private boolean mGameIsRunning;



    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
        manager = new SceneManager();
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (manager == null) return;
        thread = new MainThread(getHolder(), this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();

   //     if (!mGameIsRunning) {
    //        thread.start();
     //       mGameIsRunning = true;
     //       thread.setRunning(true);
    //    } else {
    //        thread.onResume();
     //   }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (Exception e) {e.printStackTrace();}

        }
    }
    

    @Override
    public boolean onTouchEvent(MotionEvent event) {

            manager.receiveTouch(event);
            return true;

       // return super.onTouchEvent(event);
    }
    public void update(){
        manager.update();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        manager.draw(canvas);

    }



}
