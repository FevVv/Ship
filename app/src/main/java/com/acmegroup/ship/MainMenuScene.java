package com.acmegroup.ship;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.acmegroup.ship.Scene;

public class MainMenuScene implements Scene {
    BitmapFactory bf = new BitmapFactory();
    Bitmap img1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.menu);
    private Background bg;
    private Rect rectangle;
    private Point rectanglePoint;
    private Button player;
    private Point playerPoint;
    private GameplayScene scene = new GameplayScene();




    public MainMenuScene() {
        int widthOfNewBitmap, heightOfNewBitmap;

        widthOfNewBitmap = Constants.SCREEN_WIDTH;//width of this view
        heightOfNewBitmap = Constants.SCREEN_HEIGHT;//height of this view

        Bitmap newBitmap = Bitmap.createScaledBitmap(img1, widthOfNewBitmap,heightOfNewBitmap,true);
        bg = new Background(newBitmap);
        bg.setVector(0);
        rectangle = new Rect(100, 100, 200, 200);
        player = new Button(new Rect(100,100,325,425), Color.TRANSPARENT);
        playerPoint = new Point(Constants.SCREEN_WIDTH/2 + 25, Constants.SCREEN_HEIGHT-340);
        player.update(playerPoint);



    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(rectangle, paint);
        bg.draw(canvas);
        player.draw(canvas);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (player.getRectangle().contains((int) event.getX(), (int) event.getY())) {
                   SceneManager.ACTIVE_SCENE = 1;
                //   scene.reset();
                //   scene.setGameOver();
                }
                break;

        }
    }
}
