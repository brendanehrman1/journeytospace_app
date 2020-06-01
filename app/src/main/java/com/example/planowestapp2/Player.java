package com.example.planowestapp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

class Player {
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private int speed;
    private int canvasWidth;
    private int canvasHeight;
    private ArrayList<Rect> objects;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.speed = 16;
        this.objects = new ArrayList<>();

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
        boolean canvasCreated = canvasWidth == 0;
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        if (canvasCreated) {
            objects.add(new Rect(0, -speed, canvasWidth, 0));
            objects.add(new Rect(0, canvasHeight, canvasWidth, canvasHeight + speed));
            objects.add(new Rect(-speed, 0, 0, canvasHeight));
            objects.add(new Rect(canvasWidth, 0, canvasWidth + speed, canvasHeight));
        }
    }

    public void update() {

    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void addObject(Rect r) {
        objects.add(r);
    }

    public void moveRight() {
        boolean canMove = true;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).contains((int)(positionX + radius + speed), (int)positionY))
                canMove = false;
        }
        if (canMove)
            positionX += speed;
    }

    public void moveLeft() {
        boolean canMove = true;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).contains((int)(positionX - radius - speed), (int)positionY))
                canMove = false;
        }
        if (canMove)
            positionX -= speed;
    }

    public void moveUp() {
        boolean canMove = true;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).contains((int)positionX, (int)(positionY - radius - speed)))
                canMove = false;
        }
        if (canMove)
            positionY -= speed;
    }

    public void moveDown() {
        boolean canMove = true;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).contains((int)positionX, (int)(positionY + radius + speed)))
                canMove = false;
        }
        if (canMove)
            positionY += speed;
    }
}
