package com.example.planowestapp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

class Player {
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private int speed;
    private int canvasWidth;
    private int canvasHeight;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.speed = 16;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
    }

    public void update() {

    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void moveRight() {
        if (positionX + radius + speed < canvasWidth)
            positionX += speed;
    }

    public void moveLeft() {
        if (positionX - radius - speed > 0)
            positionX -= speed;
    }

    public void moveUp() {
        if (positionY - radius - speed > 0)
            positionY -= speed;
    }

    public void moveDown() {
        if (positionY + radius + speed < canvasHeight)
            positionY += speed;
    }
}
