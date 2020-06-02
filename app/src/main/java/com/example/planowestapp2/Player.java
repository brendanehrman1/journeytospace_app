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
    private boolean isJumping;
    private int jumpPoint;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.speed = 16;
        this.jumpPoint = 0;
        this.objects = new ArrayList<>();

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
        boolean canvasCreated = canvasWidth == 0;
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        if (canvasCreated) {
            objects.add(new Rect(0, -speed, canvasWidth, 0));
            objects.add(new Rect(0, canvasHeight, canvasWidth, canvasHeight +speed));
            objects.add(new Rect(-speed, 0, 0, canvasHeight));
            objects.add(new Rect(canvasWidth, 0, canvasWidth + speed, canvasHeight));
        }
    }

    public void update() {
        if (isJumping) {
            double changeBy = -8*jumpPoint + 64;
            jumpPoint++;
            if (changeBy > 0 && !moveUp(changeBy)) {
                jumpPoint = 8;
            } else if (changeBy < 0 && !moveDown(-changeBy)) {
                isJumping = false;
                jumpPoint = 0;
            }
        }
    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void addObject(Rect r) {
        objects.add(r);
    }

    public boolean moveRight() {
        return moveRight(speed);
    }

    public boolean moveLeft() {
        return moveLeft(speed);
    }

    public boolean moveUp() {
        return moveUp(speed);
    }

    public boolean moveDown() {
        return moveDown(speed);
    }

    public boolean moveRight(double changeBy) {
        boolean canMove = true;
        double defaultPosition = Integer.MAX_VALUE;
        int obsDirBelow = 0;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).top < positionY + radius && objects.get(i).bottom > positionY - radius && objects.get(i).left >= positionX - radius && objects.get(i).left <= positionX + radius + changeBy) {
                if (objects.get(i).left - radius < defaultPosition)
                    defaultPosition = objects.get(i).left - radius;
                canMove = false;
            }
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius && objects.get(i).top - (positionY + radius) >= 0 && objects.get(i).top - (positionY + radius) < changeBy) {
                obsDirBelow++;
            }
        }
        if (canMove)
            positionX += changeBy;
        else
            positionX = defaultPosition;
        if (!isJumping && obsDirBelow == 0) {
            isJumping = true;
            jumpPoint = 9;
        }
        return canMove;
    }

    public boolean moveLeft(double changeBy) {
        boolean canMove = true;
        double defaultPosition = -1;
        int obsDirBelow = 0;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).top < positionY + radius && objects.get(i).bottom > positionY - radius && objects.get(i).right <= positionX - radius && objects.get(i).right >= positionX - radius - changeBy) {
                if (objects.get(i).right + radius > defaultPosition)
                    defaultPosition = objects.get(i).right + radius;
                canMove = false;
            }
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius && objects.get(i).top - (positionY + radius) >= 0 && objects.get(i).top - (positionY + radius) < changeBy) {
                obsDirBelow++;
            }
        }
        if (canMove)
            positionX -= changeBy;
        else
            positionX = defaultPosition;
        if (!isJumping && obsDirBelow == 0) {
            isJumping = true;
            jumpPoint = 9;
        }
        return canMove;
    }

    public boolean moveUp(double changeBy) {
        boolean canMove = true;
        double defaultPosition = -1;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius && objects.get(i).bottom <= positionY - radius && objects.get(i).bottom >= positionY - radius - changeBy) {
                if (objects.get(i).bottom + radius > defaultPosition)
                    defaultPosition = objects.get(i).bottom + radius;
                canMove = false;
            }
        }
        if (canMove)
            positionY -= changeBy;
        else
            positionY = defaultPosition;
        return canMove;
    }

    public boolean moveDown(double changeBy) {
        boolean canMove = true;
        double defaultPosition = Integer.MAX_VALUE;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius && objects.get(i).top >= positionY + radius && objects.get(i).top <= positionY + radius + changeBy) {
                if (objects.get(i).top - radius < defaultPosition)
                    defaultPosition = objects.get(i).top - radius;
                canMove = false;
            }
        }
        if (canMove)
            positionY += changeBy;
        else
            positionY = defaultPosition;
        return canMove;
    }
}
