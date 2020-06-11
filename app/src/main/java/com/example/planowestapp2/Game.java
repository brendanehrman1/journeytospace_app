package com.example.planowestapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;
import static com.example.planowestapp2.MainActivity.PREF_NAME;

import java.util.ArrayList;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private static final int DPAD_SIZE = 200;
    public static final int LEVEL_WIDTH = 32;
    public static final int LEVEL_HEIGHT = 16;

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
    private Rect backBtn;
    private MotionEvent event;
    private int dpadX;
    private int dpadY;
    private int direction;
    private static boolean isLocked;
    private static double startTime;
    private static String[] level;
    private static int levelNum;
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

        isLocked = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean("LOCK", true);

        Game.context = context;

        screen = 0;

        startTime = System.currentTimeMillis();

        screenChanged = true;

        levelNum = intent.getIntExtra("LEVEL", 1);

        level = Level.getLevel(levelNum);

        setFocusable(true);
    }

    public static void increaseLevel() {
        screen++;
        //System.out.println(screen);
        if (screen == level.length) {
            Intent intent = new Intent(context, LevelActivity.class);
            double diffTime = System.currentTimeMillis() - startTime;
            String time = Double.toString(diffTime / 1000.0);
            SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
            if (levelNum == 1) {
                String oldTime = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("LEVEL_ONE_TIME", null);
                if (oldTime == null || Double.parseDouble(oldTime) * 1000 > diffTime) {
                    edit.putString("LEVEL_ONE_TIME", time);
                }
                edit.putBoolean("LEVEL_ONE_COMPLETE", true);
            } else if (levelNum == 2) {
                String oldTime = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("LEVEL_TWO_TIME", null);
                if (oldTime == null || Double.parseDouble(oldTime) * 1000 > diffTime) {
                    edit.putString("LEVEL_TWO_TIME", time);
                }
                edit.putBoolean("LEVEL_TWO_COMPLETE", true);
            } else if (levelNum == 3) {
                String oldTime = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("LEVEL_THREE_TIME", null);
                if (oldTime == null || Double.parseDouble(oldTime) * 1000 > diffTime) {
                    edit.putString("LEVEL_THREE_TIME", time);
                }
                edit.putBoolean("LEVEL_THREE_COMPLETE", true);
            } else {
                String oldTime = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString("LEVEL_FOUR_TIME", null);
                if (oldTime == null || Double.parseDouble(oldTime) * 1000 > diffTime) {
                    edit.putString("LEVEL_FOUR_TIME", time);
                }
                edit.putBoolean("LEVEL_FOUR_COMPLETE", true);
            }
            edit.apply();
            context.startActivity(intent);
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

    public static int getLevel() { return levelNum; }

    public static int getScreen() {
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
        //System.out.println("DESTROYED");
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
                        if (player.isClimbing())
                            player.moveUp();
                    }
                    if (!player.isDashing() && downBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        goingDown = true;
                        if (player.isClimbing())
                            player.moveDown();
                    }
                    if (!player.isDashing() && leftBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        if (!player.isClimbing())
                            player.moveLeft();
                        goingLeft = true;
                    }
                    if (!player.isDashing() && rightBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        if (!player.isClimbing())
                            player.moveRight();
                        goingRight = true;
                    }
                    if (jumpBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id))) && !player.isJumping())
                        player.jump();
                    if (levelNum > 2 && dashBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id))) && !player.isDashing() && !player.hasDashed())
                        isDashing = true;
                    if (backBtn.contains((int) event.getX(event.findPointerIndex(id)), (int) event.getY(event.findPointerIndex(id)))) {
                        Intent intent = new Intent(context, LevelActivity.class);
                        intent.putExtra("LOCK", isLocked);
                        context.startActivity(intent);
                        screenTransition = true;
                    }
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
                //System.out.println(direction);
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

            canvas.drawColor(0, PorterDuff.Mode.CLEAR);

            boolean first = upBtn == null;

            instantiateValues(first);
            player.draw(canvas);
            drawObjects(first, screenChanged);
            drawScreen();

            screenChanged = false;
        } else {
            canvas.drawColor(Color.BLACK);
        }
    }

    public void drawScreen() {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.grey);
        paint.setColor(color);
        paint.setTextSize(35);
        canvas.drawRect(upDisplay, paint);
        canvas.drawRect(downDisplay, paint);
        canvas.drawRect(leftDisplay, paint);
        canvas.drawRect(rightDisplay, paint);
        canvas.drawRect(jumpBtn, paint);
        canvas.drawRect(backBtn, paint);
        if (levelNum > 2)
            canvas.drawRect(dashBtn, paint);
    }

    public void drawObjects(boolean first, boolean changed) {
        if (changed) {
            player.resetObstacles();
        }
        Paint paint = new Paint();
        for (int i = 0; i < LEVEL_HEIGHT; i++) {
            for (int j = 0; j < LEVEL_WIDTH; j++) {
                int index = i * LEVEL_WIDTH + j;
                if (screen < level.length && !level[screen].substring(index, index + 1).equals(" ")) {
                    Rect rect = new Rect(j * (width / LEVEL_WIDTH), i * (height / LEVEL_HEIGHT), (j + 1) * (width / LEVEL_WIDTH), (i + 1) * (height / LEVEL_HEIGHT));
                    int color = ContextCompat.getColor(getContext(), R.color.black);
                    Obstacle obstacle = new Obstacle(rect, level[screen].substring(index, index + 1));
                    if (obstacle.getType().equals("B")) {
                        color = ContextCompat.getColor(getContext(), R.color.green);
                        if (levelNum == 2) {
                            color = ContextCompat.getColor(getContext(), R.color.white);
                        } else if (levelNum == 3) {
                            color = ContextCompat.getColor(getContext(), R.color.light_blue);
                        } else if (levelNum == 4) {
                            color = ContextCompat.getColor(getContext(), R.color.dark_grey);
                        }
                    } else if (obstacle.getType().equals("S"))
                        color = ContextCompat.getColor(getContext(), R.color.red);
                    else if (obstacle.getType().equals("C"))
                        color = ContextCompat.getColor(getContext(), R.color.orange);
                    else if (obstacle.getType().equals("P"))
                        color = ContextCompat.getColor(getContext(), R.color.blue);
                    paint.setColor(color);
                    canvas.drawRect(rect, paint);
                    if (changed)
                        player.addObject(obstacle);
                }
            }
        }
        if (first) {
            Point start = Level.getStart(levelNum, screen);
            player.setPosition((double)(start.x) / LEVEL_WIDTH * width, (1 - ((double)(start.y) / LEVEL_HEIGHT)) * height - player.getRadius());
            player.setCheckPoint((int)((double)(start.x) / LEVEL_WIDTH * width), (int)((1 - ((double)(start.y) / LEVEL_HEIGHT)) * height - player.getRadius()));
        }
    }

    private void instantiateValues(boolean first) {
        width = 1220;
        height = 720;
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
        jumpBtn = new Rect(width - DPAD_SIZE - 70, height - (DPAD_SIZE * 2 / 3) - 25, width - (DPAD_SIZE * 1 / 3) - 70, height - 25);
        if (levelNum < 3)
            jumpBtn = new Rect(width - (DPAD_SIZE * 2 / 3) - 45, height - (DPAD_SIZE * 2 / 3) - 25, width - 45, height - 25);
        else
            dashBtn = new Rect(width - (DPAD_SIZE * 2 / 3) - 45, height - (DPAD_SIZE * 4 / 3) - 50, width - 45, height - (DPAD_SIZE * 2 / 3) - 50);
        backBtn = new Rect(width - (DPAD_SIZE * 2 / 3) - 45, 25, width - 45, (DPAD_SIZE / 3) + 25);
    }
}
