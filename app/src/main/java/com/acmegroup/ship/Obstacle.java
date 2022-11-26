package com.acmegroup.ship;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.view.Display;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class Obstacle implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;
    private Rect rectangle3;
    private Rect rectangle4;
    private Rect rectangle5;
    private Rect rectangle6;
    private int color;
    int random;
    private RandomNumber randomNumber = new RandomNumber(random);
    private boolean running;

    BitmapFactory bf = new BitmapFactory();
   final Bitmap IMG1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rock1);
    Bitmap img2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rock2);
    Bitmap img3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rock3);
    Bitmap img4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rock4);
    Bitmap img5 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rock5);

  //  int nh = (int) (IMG1.getHeight() * (1024.0 / IMG1.getWidth()));
   // Bitmap scaled = Bitmap.createScaledBitmap(IMG1, 1024, nh, true);




    public Rect getRectangle() {

        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
      rectangle2.top += y;
       rectangle2.bottom += y;
        rectangle3.top += y;
        rectangle3.bottom += y;
        rectangle4.top += y;
        rectangle4.bottom += y;
        rectangle5.top += y;
        rectangle5.bottom += y;
        rectangle6.top += y;
        rectangle6.bottom += y;

    }


    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap) {
        this.color = color;
        rectangle = new Rect(0, startY+172, startX, 172+startY + rectHeight);
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
        rectangle3 = new Rect(0, startY+70+172, startX-60, 172+startY + rectHeight-30);
        rectangle4 = new Rect(0, startY+172+25, startX-150, 172+startY + rectHeight-30);
        rectangle5 = new Rect(startX + playerGap+150, startY+25, Constants.SCREEN_WIDTH, startY + rectHeight-30);
        rectangle6 = new Rect(startX + playerGap+60, startY+70, Constants.SCREEN_WIDTH, startY + rectHeight-30);



    }
    public  boolean playerCollide(RectPlayer player){
        return Rect.intersects(rectangle3,player.getRectangle()) || Rect.intersects(rectangle4,player.getRectangle()) || Rect.intersects(rectangle5,player.getRectangle()) || Rect.intersects(rectangle6,player.getRectangle());

    }

  /*  public boolean playerCollide(RectPlayer player,Bitmap bitmap1, int x1, int y1,
                                 Bitmap bitmap2, int x2, int y2) {
            if (Rect.intersects(rectangle, player.getRectangle()) || Rect.intersects(rectangle2, player.getRectangle())) {
                Rect collisionBounds = getCollisionBounds(rectangle, player.getRectangle());
                for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
                    for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
                        int bitmap1Pixel = bitmap1.getPixel(i-x1, j-y1);
                        int bitmap2Pixel = bitmap2.getPixel(i-x2, j-y2);
                        if (isFilled(bitmap1Pixel) && isFilled(bitmap2Pixel)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
*/



    @Override
    public void draw(Canvas canvas) {
    /*        Paint paint = new Paint();
        paint.setColor(color);
           canvas.drawRect(rectangle3, paint);
              canvas.drawRect(rectangle4, paint);
        canvas.drawRect(rectangle5, paint);
        canvas.drawRect(rectangle6, paint); */


        canvas.drawBitmap(IMG1,null,rectangle,null);
        canvas.drawBitmap(img2,null,rectangle2,null);

     //   Paint paint = new Paint();
     //   paint.setTextSize(100);
    //    paint.setColor(Color.MAGENTA);
    //    canvas.drawText("" + random, 450, 50 + paint.descent() - paint.ascent(), paint);
    }

    @Override
    public void update() {

    }
    public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth,
                                   int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight,
                true);
    }

  /*  public static boolean isCollisionDetected(Bitmap bitmap1, int x1, int y1,
                                              Bitmap bitmap2, int x2, int y2) {

        Rect bounds1 = new Rect(x1, y1, x1+bitmap1.getWidth(), y1+bitmap1.getHeight());
        Rect bounds2 = new Rect(x2, y2, x2+bitmap2.getWidth(), y2+bitmap2.getHeight());

        if (Rect.intersects(bounds1, bounds2)) {
            Rect collisionBounds = getCollisionBounds(bounds1, bounds2);
            for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
                for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
                    int bitmap1Pixel = bitmap1.getPixel(i-x1, j-y1);
                    int bitmap2Pixel = bitmap2.getPixel(i-x2, j-y2);
                    if (isFilled(bitmap1Pixel) && isFilled(bitmap2Pixel)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private static Rect getCollisionBounds(Rect rect1, Rect rect2) {
        int left = Math.max(rect1.left, rect2.left);
        int top = Math.max(rect1.top, rect2.top);
        int right = Math.min(rect1.right, rect2.right);
        int bottom = Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }
    private static boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }*/
 /* public static boolean isCollisionDetected(View view1, int x1, int y1,
                                            View view2, int x2, int y2) {

      Bitmap bitmap1 = getViewBitmap(view1);
      Bitmap bitmap2 = getViewBitmap(view2);

      if (bitmap1 == null || bitmap2 == null) {
          throw new IllegalArgumentException("bitmaps cannot be null");
      }

      Rect bounds1 = new Rect(x1, y1, x1 + bitmap1.getWidth(), y1 + bitmap1.getHeight());
      Rect bounds2 = new Rect(x2, y2, x2 + bitmap2.getWidth(), y2 + bitmap2.getHeight());

      if (Rect.intersects(bounds1, bounds2)) {
          Rect collisionBounds = getCollisionBounds(bounds1, bounds2);
          for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
              for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
                  int bitmap1Pixel = bitmap1.getPixel(i - x1, j - y1);
                  int bitmap2Pixel = bitmap2.getPixel(i - x2, j - y2);
                  if (isFilled(bitmap1Pixel) && isFilled(bitmap2Pixel)) {
                      bitmap1.recycle();
                      bitmap1 = null;
                      bitmap2.recycle();
                      bitmap2 = null;
                      return true;
                  }
              }
          }
      }
      bitmap1.recycle();
      bitmap1 = null;
      bitmap2.recycle();
      bitmap2 = null;
      return false;
  }
    private static Bitmap getViewBitmap(View v) {
        if (v.getMeasuredHeight() <= 0) {
            int specWidth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            v.measure(specWidth, specWidth);
            Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            v.draw(c);
            return b;
        }
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }
    private static Rect getCollisionBounds(Rect rect1, Rect rect2) {
        int left = Math.max(rect1.left, rect2.left);
        int top = Math.max(rect1.top, rect2.top);
        int right = Math.min(rect1.right, rect2.right);
        int bottom = Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }
    private static boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }*/
}
