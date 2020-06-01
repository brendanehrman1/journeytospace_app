package com.example.planowestapp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private static final int DPAD_SIZE = 200;

    private Player player;
    private GameThread thread;
    private Rect upDisplay;
    private Rect downDisplay;
    private Rect leftDisplay;
    private Rect rightDisplay;
    private Rect upBtn;
    private Rect downBtn;
    private Rect leftBtn;
    private Rect rightBtn;
    private Rect jumpBtn;
    private MotionEvent event;
    boolean isUp;
    boolean isDown;
    int numTouches;

    private int width;
    private int height;

    public Game(Context context) {
        super(context);

        getHolder().addCallback(this);

        player = new Player(getContext(), 0, 0, 30);

        thread = new GameThread(this, getHolder());
        
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & 0xff;
        //System.out.println(Integer.toHexString(action) + " " + event.getActionIndex());
        //Down = 0, Up = 1, Move = 2, Pointer_Down = 5, Pointer-Up = 6
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            this.event = event;
            numTouches++;
            return true;
        } else if (action == MotionEvent.ACTION_MOVE) {
            this.event = event;
            return true;
        } else if (action == MotionEvent.ACTION_POINTER_UP) {
            this.event = event;
            numTouches--;
            return true;
        }else if (action == MotionEvent.ACTION_UP) {
            this.event = null;
            isUp = false;
            isDown = false;
            numTouches = 0;
            return false;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void update() {
        player.update();
        for (int i = 0; i < numTouches; i++) {
            if (upBtn.contains((int) event.getX(event.getPointerId(i)), (int) event.getY(event.getPointerId(i))))
                isUp = true;
            else
                isUp = false;
            if (downBtn.contains((int) event.getX(event.getPointerId(i)), (int) event.getY(event.getPointerId(i))))
                isDown = true;
            else
                isDown = false;
            if (leftBtn.contains((int) event.getX(event.getPointerId(i)), (int) event.getY(event.getPointerId(i))))
                player.moveLeft();
            if (rightBtn.contains((int) event.getX(event.getPointerId(i)), (int) event.getY(event.getPointerId(i))))
                player.moveRight();
            if (jumpBtn.contains((int) event.getX(event.getPointerId(i)), (int) event.getY(event.getPointerId(i))) && !player.isJumping())
                player.setJumping(true);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean first = false;
        if (upBtn == null) {
            instantiateValues(canvas);
            first = true;
        }
        drawObjects(canvas, first);
        drawScreen(canvas);

        player.draw(canvas);
    }

    public void drawScreen(Canvas canvas) {
        drawUPS(canvas);
        drawFPS(canvas);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(35);
        canvas.drawRect(upDisplay, paint);
        canvas.drawRect(downDisplay, paint);
        canvas.drawRect(leftDisplay, paint);
        canvas.drawRect(rightDisplay, paint);
        canvas.drawRect(jumpBtn, paint);
        canvas.drawText("UP: " + isUp, 100, 300, paint);
        canvas.drawText("DOWN: " + isDown, 100, 400, paint);
        canvas.drawText("TOUCHES: " + numTouches, 100, 500, paint);
    }

    public void drawObjects(Canvas canvas, boolean first) {
        Rect ground = new Rect(0, height * 7 / 8, width, height);
        Rect obstacle = new Rect(0, height * 2 / 3 - 50, width / 2, height * 2 / 3);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.green);
        paint.setColor(color);
        canvas.drawRect(ground, paint);
        canvas.drawRect(obstacle, paint);
        if (first) {
            player.addObject(ground);
            player.addObject(obstacle);
            player.setPosition(width / 2, height * 7 / 8 - player.getRadius());
        }
    }

    private void instantiateValues(Canvas c) {
        width = c.getWidth();
        height = c.getHeight();
        int leftSide = 25;
        int rightSide = leftSide + DPAD_SIZE;
        int downSide = height - 25;
        int upSide = downSide - DPAD_SIZE;
        upDisplay = new Rect(leftSide + (DPAD_SIZE / 3), upSide, rightSide - (DPAD_SIZE / 3), upSide + (DPAD_SIZE / 3));
        downDisplay = new Rect(leftSide + (DPAD_SIZE / 3), downSide - (DPAD_SIZE / 3), rightSide - (DPAD_SIZE / 3), downSide);
        leftDisplay = new Rect(leftSide, upSide + (DPAD_SIZE / 3), leftSide + (DPAD_SIZE / 3), downSide - (DPAD_SIZE / 3));
        rightDisplay = new Rect(rightSide - (DPAD_SIZE / 3), upSide + (DPAD_SIZE / 3), rightSide, downSide - (DPAD_SIZE / 3));
        upBtn = new Rect(leftSide, upSide, rightSide, upSide + (DPAD_SIZE / 3));
        downBtn = new Rect(leftSide, downSide - (DPAD_SIZE / 3), rightSide, downSide);
        leftBtn = new Rect(leftSide, upSide, leftSide + (DPAD_SIZE / 3), downSide);
        rightBtn = new Rect(rightSide - (DPAD_SIZE / 3), upSide, rightSide, downSide);
        jumpBtn = new Rect(width - DPAD_SIZE - 25, upSide, width - 25, downSide);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(thread.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(35);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(thread.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(35);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }
}
