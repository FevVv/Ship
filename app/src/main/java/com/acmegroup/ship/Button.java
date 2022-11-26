package com.acmegroup.ship;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Button implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;

    private int color;



    public Rect getRectangle() {
        return rectangle;
    }

    public Button(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }
    @Override
    public void draw(Canvas canvas) {

           Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawRect(rectangle,paint);

        // canvas.drawBitmap(IDLEIMG,null,rectangle,null);

    }

    @Override
    public void update() {

    }

    public void update(Point point){
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2,point.y - rectangle.height()/2,point.x + rectangle.width()/2,point.y + rectangle.height()/2);

        int state = 0;
        if(rectangle.left - oldLeft > 5)
            state = 1;
        else if(rectangle.left - oldLeft < -5)
            state = 2;
    }
}

