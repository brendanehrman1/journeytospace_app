package com.example.planowestapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private static final int LEVEL_WIDTH = 32;
    private static final int LEVEL_HEIGHT = 12;

    private static Context context;
    private Player player;
    private Canvas canvas;
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
    private Rect dashBtn;
    private MotionEvent event;
    private int dpadX;
    private int dpadY;
    private int direction;
    private boolean isLocked;
    private static String[] level;
    private static int screen;
    private static boolean screenTransition;
    private static boolean screenChanged;


    private int width;
    private int height;

    public Game(Context context, Intent intent) {
        super(context);

        getHolder().addCallback(this);

        screenTransition = false;

        player = new Player(getContext(), 0, 0, 30);

        thread = new GameThread(this, getHolder());

        isLocked = intent.getBooleanExtra("LOCK", false);

        this.context = context;

        this.screen = 0;

        screenChanged = true;

        this.level = Level.getLevel(intent.getIntExtra("LEVEL", 1));

        setFocusable(true);
    }

    public static void increaseLevel() {
        screen++;
        System.out.println(screen);
        if (screen == level.length) {
            context.startActivity(new Intent(context, MainActivity.class));
            screenTransition = true;
        } else {
            screenChanged = true;
        }
    }

    public static void decreaseLevel() {
        screen--;
        if (screen < 0) {
            screen = 0;
        } else {
            screenChanged = true;
        }
    }

    public static int getLevel() {
        return screen;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & 0xff;
        //System.out.println(Integer.toHexString(action) + " " + event.getActionIndex());
        //Down = 0, Up = 1, Move = 2, Pointer_Down = 5, Pointer-Up = 6
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            this.event = event;
        } else if (action == MotionEvent.ACTION_MOVE) {
            this.event = event;
        } else if (action == MotionEvent.ACTION_POINTER_UP) {
            this.event = event;
        } else if (action == MotionEvent.ACTION_UP) {
            this.event = null;
            direction = -1;
        }

        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        System.out.println("DESTROYED");
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (!screenTransition) {
            player.update();
            int numTouches = 0;
            if (event != null) {
                numTouches = event.getPointerCount();
            }
            boolean leftSideTouched = false;
            boolean isDashing = false;
            boolean goingUp = false;
            boolean goingDown = false;
            boolean goingLeft = false;
            boolean goingRight = false;
            for (int i = 0; i < numTouches; i++) {
                int id = event.getPointerId(i);
                if ((event.getAction() & 0xff) != MotionEvent.ACTION_POINTER_UP || event.getActionIndex() != event.findPointerIndex(id)) {
                    if (!isLocked && ((event.getAction() & 0xff) == MotionEvent.ACTION_DOWN || (event.getAction() & 0xff) == MotionEvent.ACTION_POINTER_DOWN) && event.getActionIndex() == event.findPointerIndex(id) && !leftSideTouched && (int) event.getX(event.findPointerIndex(id)) < width / 2) {
                        dpadX = (int) event.getX(event.findPointerIndex(id));
                        dpadY = (int) event.getY(event.findPointerIndex(id));
                        draw(canvas);
                        leftSideTouched = true;
                    } else if ((int) event.getX(event.findPointerIndex(id)) < width / 2) {
                        leftSideTouched = true;
                    }
                    if (!player.isDashing() && upBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        goingUp = true;
                    }
                    if (!player.isDashing() && downBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        goingDown = true;
                    }
                    if (!player.isDashing() && leftBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        player.moveLeft();
                        goingLeft = true;
                    }
                    if (!player.isDashing() && rightBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        player.moveRight();
                        goingRight = true;
                    }
                    if (jumpBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id))) && !player.isJumping())
                        player.jump();
                    if (dashBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id))) && !player.isDashing() && !player.hasDashed())
                        isDashing = true;
                }
            }
            if (goingUp && goingDown) {
                goingUp = false;
                goingDown = false;
            }
            if (goingLeft && goingRight) {
                goingLeft = false;
                goingRight = false;
            }
            direction = getDirection(goingUp, goingDown, goingLeft, goingRight);
            if (isDashing) {
                System.out.println(direction);
                player.dash(direction);
            }
        }
    }

    public int getDirection(boolean goingUp, boolean goingDown, boolean goingLeft, boolean goingRight) {
        int direction = -1;
        if (!goingUp && !goingDown && goingRight)
            direction = 0;
        else if (!goingUp && !goingDown && goingLeft)
            direction = 4;
        else if (!goingLeft && !goingRight && goingUp)
            direction = 2;
        else if (!goingLeft && !goingRight && goingDown)
            direction = 6;
        else if (goingUp && goingRight)
            direction = 1;
        else if (goingUp && goingLeft)
            direction = 3;
        else if (goingDown && goingRight)
            direction = 7;
        else if (goingDown && goingLeft)
            direction = 5;
        return direction;
    }

    @Override
    public void draw(Canvas canvas) {
        if (!screenTransition) {
            this.canvas = canvas;
            super.draw(canvas);
            boolean first = upBtn == null;

            instantiateValues(first);
            player.draw(canvas);
            drawObjects(first, screenChanged);
            drawScreen();


            screenChanged = false;
        } else {
            Paint paint = new Paint();
            int color = ContextCompat.getColor(getContext(), R.color.black);
            paint.setColor(color);
            canvas.drawRect(new Rect(0, 0, width, height), paint);
        }
    }

    public void drawScreen() {
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
        canvas.drawRect(dashBtn, paint);
        canvas.drawText("DIRECTION: " + direction, 100, 300, paint);
    }

    public void drawObjects(boolean first, boolean changed) {
        if (changed) {
            player.resetObstacles();
        }
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.green);
        paint.setColor(color);
        for (int i = 0; i < LEVEL_HEIGHT; i++) {
            for (int j = 0; j < LEVEL_WIDTH; j++) {
                int index = i * LEVEL_WIDTH + j;
                if (screen < level.length && level[screen].substring(index, index + 1).equals("B")) {
                    Rect obstacle = new Rect(j * (width / LEVEL_WIDTH), i * (height / LEVEL_HEIGHT), (j + 1) * (width / LEVEL_WIDTH), (i + 1) * (height / LEVEL_HEIGHT));
                    canvas.drawRect(obstacle, paint);
                    if (changed)
                        player.addObject(obstacle);
                }
            }
        }
        if (first) {
            player.setPosition(width / 2, height * (LEVEL_HEIGHT - 1) / LEVEL_HEIGHT - player.getRadius());
        }
    }

    private void instantiateValues(boolean first) {
        width = canvas.getWidth();
        height = canvas.getHeight();
        if (first) {
            dpadX = 25 + (DPAD_SIZE / 2);
            dpadY = height - 25 - (DPAD_SIZE / 2);
        }
        int leftSide = dpadX - (DPAD_SIZE / 2);
        int rightSide = leftSide + DPAD_SIZE;
        int downSide = dpadY + (DPAD_SIZE / 2);
        int upSide = downSide - DPAD_SIZE;
        upDisplay = new Rect(leftSide + (DPAD_SIZE / 3), upSide, rightSide - (DPAD_SIZE / 3), upSide + (DPAD_SIZE / 3));
        downDisplay = new Rect(leftSide + (DPAD_SIZE / 3), downSide - (DPAD_SIZE / 3), rightSide - (DPAD_SIZE / 3), downSide);
        leftDisplay = new Rect(leftSide, upSide + (DPAD_SIZE / 3), leftSide + (DPAD_SIZE / 3), downSide - (DPAD_SIZE / 3));
        rightDisplay = new Rect(rightSide - (DPAD_SIZE / 3), upSide + (DPAD_SIZE / 3), rightSide, downSide - (DPAD_SIZE / 3));
        upBtn = new Rect(0, 0, width / 2, upSide + (DPAD_SIZE / 3));
        downBtn = new Rect(0, downSide - (DPAD_SIZE / 3), width / 2, height);
        leftBtn = new Rect(0, 0, leftSide + (DPAD_SIZE / 3), height);
        rightBtn = new Rect(rightSide - (DPAD_SIZE / 3), 0, width / 2, height);
        jumpBtn = new Rect(width - DPAD_SIZE - 50, height - (DPAD_SIZE * 2 / 3) - 25, width - (DPAD_SIZE * 1 / 3) - 50, height - 25);
        dashBtn = new Rect(width - (DPAD_SIZE * 2 / 3) - 25, height - (DPAD_SIZE * 4 / 3) - 50, width - 25, height - (DPAD_SIZE * 2 / 3) - 50);
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
