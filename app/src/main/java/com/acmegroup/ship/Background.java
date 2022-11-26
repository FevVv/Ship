package com.acmegroup.ship;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Background {
    private Bitmap image;
    private int x, dy;
    private int y =0;

    public Background(Bitmap res) {
        image = res;

    }
    public  void update() {
        y+=dy;
        if(y>Constants.SCREEN_HEIGHT){
            y=0;
        }
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y,null);
        if(y>0)
        {
            canvas.drawBitmap(image, x,  y - Constants.SCREEN_HEIGHT, null);
        }

    }
    public void setVector(int dy)
    {
        this.dy = dy;
    }
}
