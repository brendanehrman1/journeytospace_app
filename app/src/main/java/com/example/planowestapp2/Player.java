package com.example.planowestapp2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

class Player {
    private static final int MAX_DASH = 8;

    private Context context;
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
    private boolean lastRight;
    private boolean isDashing;
    private boolean dashed;
    private int dashPoint;
    private int direction;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.speed = 16;
        this.jumpPoint = 0;
        this.lastRight = true;
        this.objects = new ArrayList<>();
        this.context = context;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isDashing() {
        return isDashing;
    }

    public boolean hasDashed() {
        return dashed;
    }

    public void jump() {
        this.isJumping = true;
    }

    public double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
        boolean canvasCreated = canvasWidth == 0;
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
    }

    public void update() {
        if (isDashing) {
            double changeBy = speed * 2;
            if (dashPoint == MAX_DASH) {
                isDashing = false;
                isJumping = true;
                jumpPoint = 8;
                direction = -1;
            } else {
                if ((direction == -1 && lastRight || direction == 0) && !moveRight(changeBy) ||
                        (direction == -1 && !lastRight || direction == 4) && !moveLeft(changeBy) ||
                        direction == 1 && (!moveRight(changeBy) || !moveUp(changeBy)) ||
                        direction == 2 && (!moveUp(changeBy)) ||
                        direction == 3 && (!moveLeft(changeBy) || !moveUp(changeBy)) ||
                        direction == 5 && (!moveLeft(changeBy) || !moveDown(changeBy)) ||
                        direction == 6 && (!moveDown(changeBy)) ||
                        direction == 7 && (!moveRight(changeBy) || !moveDown(changeBy))) {
                    isDashing = false;
                    isJumping = true;
                    jumpPoint = 8;
                    direction = -1;
                }
                dashPoint++;
            }
        }
        if (isJumping) {
            double changeBy = -8*jumpPoint + 64;
            jumpPoint++;
            if (changeBy > 0 && !moveUp(changeBy)) {
                jumpPoint = 8;
            } else if (changeBy < 0 && !moveDown(-changeBy)) {
                isJumping = false;
                dashed = false;
                jumpPoint = 0;
            }
        }
    }

    public void resetObstacles() {
        objects = new ArrayList<>();
        objects.add(new Rect(0, -speed, canvasWidth, 0));
        objects.add(new Rect(0, canvasHeight, canvasWidth, canvasHeight + speed));
        objects.add(new Rect(-speed, 0, 0, canvasHeight));
        objects.add(new Rect(canvasWidth, 0, canvasWidth + speed, canvasHeight));
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
        boolean hitRightWall = false;
        double defaultPosition = Integer.MAX_VALUE;
        int obsDirBelow = 0;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).top < positionY + radius && objects.get(i).bottom > positionY - radius) {
                if (objects.get(i).left >= positionX - radius && objects.get(i).left <= positionX + radius + changeBy && objects.get(i).left < canvasWidth && objects.get(i).right > 0) {
                    if (objects.get(i).left - radius < defaultPosition)
                        defaultPosition = objects.get(i).left - radius;
                    canMove = false;
                } else {
                    if (objects.get(i).left == canvasWidth && objects.get(i).left <= positionX - radius + changeBy) {
                        hitRightWall = true;
                        canMove = false;
                    }
                }
            }
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius && objects.get(i).top - (positionY + radius) >= 0 && objects.get(i).top - (positionY + radius) < changeBy) {
                obsDirBelow++;
            }
        }
        if (canMove)
            positionX += changeBy;
        else {
            if (hitRightWall) {
                Game.increaseLevel();
                setPosition(0, positionY);
            } else {
                positionX = defaultPosition;
                //System.out.println("HERE");
            }
        }
        if (!isDashing && !isJumping && obsDirBelow == 0) {
            isJumping = true;
            jumpPoint = 9;
        }
        lastRight = true;
        return canMove;
    }

    public boolean moveLeft(double changeBy) {
        boolean canMove = true;
        boolean hitLeftWall = false;
        double defaultPosition = -1;
        int obsDirBelow = 0;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).top < positionY + radius && objects.get(i).bottom > positionY - radius) {
                if (objects.get(i).right <= positionX - radius && objects.get(i).right >= positionX - radius - changeBy && objects.get(i).left < canvasWidth && (objects.get(i).right > 0 || Game.getLevel() == 0)) {
                    if (objects.get(i).right + radius > defaultPosition)
                        defaultPosition = objects.get(i).right + radius;
                    canMove = false;
                } else {
                    if (objects.get(i).right == 0 && objects.get(i).right >= positionX + radius - changeBy) {
                        hitLeftWall = true;
                        canMove = false;
                    }
                }
            }
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius && objects.get(i).top - (positionY + radius) >= 0 && objects.get(i).top - (positionY + radius) < changeBy) {
                obsDirBelow++;
            }
        }
        if (canMove) {
            positionX -= changeBy;
        } else {
            if (hitLeftWall && Game.getLevel() > 0) {
                Game.decreaseLevel();
                System.out.println("LEFT");
                setPosition(canvasWidth, positionY);
            } else {
                positionX = defaultPosition;
            }
        }
        if (!isDashing && !isJumping && obsDirBelow == 0) {
            isJumping = true;
            jumpPoint = 9;
        }
        lastRight = false;
        return canMove;
    }

    public boolean moveUp(double changeBy) {
        boolean canMove = true;
        boolean hitTopWall = false;
        double defaultPosition = -1;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius) {
                if (objects.get(i).bottom <= positionY - radius && objects.get(i).bottom >= positionY - radius - changeBy && objects.get(i).top < canvasHeight && objects.get(i).bottom > 0) {
                    if (objects.get(i).bottom + radius > defaultPosition)
                        defaultPosition = objects.get(i).bottom + radius;
                    canMove = false;
                } else {
                    if (objects.get(i).bottom == 0 && objects.get(i).bottom <= positionY + radius && objects.get(i).bottom >= positionY + radius - changeBy) {
                        hitTopWall = true;
                        canMove = false;
                    }
                }
            }
        }
        if (canMove) {
            positionY -= changeBy;
        } else {
            if (hitTopWall) {
                Game.increaseLevel();
                setPosition(positionX, canvasHeight);
            } else {
                //System.out.println("HERE");
                positionY = defaultPosition;
            }
        }
        return canMove;
    }

    public boolean moveDown(double changeBy) {
        boolean canMove = true;
        boolean hitBottomWall = false;
        double defaultPosition = Integer.MAX_VALUE;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).left < positionX + radius && objects.get(i).right > positionX - radius) {
                if (objects.get(i).top >= positionY + radius && objects.get(i).top <= positionY + radius + changeBy && objects.get(i).top < canvasHeight && objects.get(i).bottom > 0) {
                    if (objects.get(i).top - radius < defaultPosition)
                        defaultPosition = objects.get(i).top - radius;
                    canMove = false;
                } else {
                    if (objects.get(i).bottom == canvasHeight && objects.get(i).top >= positionY - radius && objects.get(i).top <= positionY - radius + changeBy) {
                        hitBottomWall = true;
                        canMove = false;
                    }
                }
            }
        }
        if (canMove) {
            positionY += changeBy;
        } else {
            if (hitBottomWall && Game.getLevel() > 0) {
                Game.decreaseLevel();
                setPosition(positionX, radius);
            } else {
                positionY = defaultPosition;
            }
        }
        return canMove;
    }

    public void dash(int direction) {
        this.dashPoint = 0;
        this.direction = direction;
        this.dashed = true;
        this.isDashing = true;
        this.isJumping = false;
    }
}
