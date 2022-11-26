package com.acmegroup.ship;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.SystemClock;
import android.service.autofill.FieldClassification;
import android.view.MotionEvent;

public class GameplayScene implements Scene {
    BitmapFactory bf = new BitmapFactory();
    Bitmap IMG1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rock1);
    Bitmap img1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gameover);
    Bitmap points = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.points);
    Bitmap background = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bg1);




    private Rect r = new Rect();

    private Obstacle obstacle;

    private Button button1;
    private Button button2;
    private Point button1Point;
    private Point button2Point;

    private RectPlayer player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private ObstacleManager obstacleManager1;
    private Background bg;

    private boolean movingPlayer = false;

    private boolean  gameOver = true;
    private long gameOverTime;

    private OrientationData orientationData;
    private long frameTime;
    TimeWatch watch = TimeWatch.start();
    long passedTimeInMs;
    int start = 0;
    public void setGameOver(){
        gameOver = false;
    }



    public GameplayScene(){
        int widthOfNewBitmap, heightOfNewBitmap;

        widthOfNewBitmap = Constants.SCREEN_WIDTH;//width of this view
        heightOfNewBitmap = Constants.SCREEN_HEIGHT;//height of this view

        Bitmap newBitmap = Bitmap.createScaledBitmap(background, widthOfNewBitmap,heightOfNewBitmap,true);

        obstacleManager = new ObstacleManager(300, 750,IMG1.getHeight(), Color.BLACK);

        bg = new Background(newBitmap);
        bg.setVector(7);
        player = new RectPlayer(new Rect(100,100,200,200), Color.rgb(255,0,0));
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/4 + Constants.SCREEN_HEIGHT/4 + Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        orientationData = new OrientationData();
        orientationData.register();
        frameTime = System.currentTimeMillis();
        button1 = new Button(new Rect(100,100,225,225), Color.TRANSPARENT);
        button1Point = new Point(Constants.SCREEN_WIDTH/3 + 50, Constants.SCREEN_HEIGHT/3 + Constants.SCREEN_HEIGHT/3-75);
        button1.update(button1Point);
        button2 = new Button(new Rect(100,100,225,225), Color.TRANSPARENT);
        button2Point = new Point(Constants.SCREEN_WIDTH/3 +Constants.SCREEN_WIDTH/3 - 50, Constants.SCREEN_HEIGHT/3 + Constants.SCREEN_HEIGHT/3-75);
        button2.update(button2Point);
    }

    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/4 + Constants.SCREEN_HEIGHT/4 + Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(300, 750,IMG1.getHeight(),Color.BLACK);
        movingPlayer = false;
        watch = TimeWatch.start();
        start++;
    }


    @Override
    public void update() {
            if (!gameOver) {
                if (frameTime < Constants.INIT_TIME)
                    frameTime = Constants.INIT_TIME;
                int elapsedTime = (int) (System.currentTimeMillis() - frameTime);
                frameTime = System.currentTimeMillis();
                if (orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
                    float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                    float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                    float xSpeed = 2 * roll * Constants.SCREEN_WIDTH / 1000f;
                    float ySpeed = pitch * Constants.SCREEN_HEIGHT / 1000f;

                    playerPoint.x += Math.abs(xSpeed * elapsedTime) > 5 ? xSpeed * elapsedTime : 0;
                    playerPoint.y -= Math.abs(ySpeed * elapsedTime) > 5 ? ySpeed * elapsedTime : 0;
                }

                if (playerPoint.x < 0)
                    playerPoint.x = 0;
                else if (playerPoint.x > Constants.SCREEN_WIDTH)
                    playerPoint.x = Constants.SCREEN_WIDTH;
                if (playerPoint.y < 0)
                    playerPoint.y = 0;
                else if (playerPoint.y > Constants.SCREEN_HEIGHT)
                    playerPoint.y = Constants.SCREEN_HEIGHT;

                player.update(playerPoint);
                obstacleManager.update();
                if (obstacleManager.playerCollide(player)) {
                    gameOver = true;
                    gameOverTime = System.currentTimeMillis();

                }
                bg.update();
                passedTimeInMs = watch.time();


            }


        }

    @Override
    public void draw(Canvas canvas) {
     //   canvas.drawBitmap(background,0,0,null);
        Paint paint = new Paint();
        paint.setTextSize(75);
        paint.setColor(Color.WHITE);
        Paint p = new Paint();
        p.setTextSize(75);
        p.setColor(Color.BLACK);

        bg.draw(canvas);
        player.draw(canvas);
        obstacleManager.draw(canvas);


        canvas.drawBitmap(points,0,0,null);
        canvas.drawText("" + passedTimeInMs, Constants.SCREEN_WIDTH/4,Constants.SCREEN_HEIGHT/22 + paint.descent() - paint.ascent(),paint);
        if(start==0) {

            start++;
            }
          else if (start==1){
            drawCenterText(canvas, paint, "Tap the screen to start");
        }
        else if (start>1) {
            if (gameOver) {
                canvas.drawBitmap(img1, Constants.SCREEN_WIDTH / 12, Constants.SCREEN_HEIGHT / 3, null);

                //  long passedTimeInMs = watch.time();
                //  long vreme = passedTimeInMs;
                drawCenterText(canvas, p, "" + passedTimeInMs);
            }
        }
        button1.draw(canvas);
        button2.draw(canvas);

    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 1;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY()))
                    movingPlayer = true;
                if (gameOver && passedTimeInMs == 0) {
                    reset();
                    gameOver = false;
                    orientationData.newGame();
                }
                if(gameOver && passedTimeInMs !=0 && button2.getRectangle().contains((int) event.getX(), (int) event.getY()))
                {
                    reset();
                    gameOver = false;
                    orientationData.newGame();
                }
                if(gameOver && passedTimeInMs !=0 && button1.getRectangle().contains((int) event.getX(), (int) event.getY()))
                {
                    reset();
                    SceneManager.ACTIVE_SCENE = 0;
                    start = 0;
                    passedTimeInMs = watch.time();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!gameOver && movingPlayer)
                    playerPoint.set((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }
        private void drawCenterText(Canvas canvas, Paint paint, String text) {
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.getClipBounds(r);
            int cHeight = r.height();
            int cWidth = r.width();
            paint.getTextBounds(text, 0, text.length(), r);
            float x = cWidth / 2f - r.width() / 2f - r.left;
            float y = cHeight / 2f + r.height() / 2f - r.bottom;
            canvas.drawText(text, x, y, paint);

        }
}
