package com.example.planowestapp2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import static com.example.planowestapp2.Game.LEVEL_WIDTH;
import static com.example.planowestapp2.Game.LEVEL_HEIGHT;

class Player {
    private static final int MAX_DASH = 6;

    private Context context;
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private int speed;
    private int canvasWidth;
    private int canvasHeight;
    private ArrayList<Obstacle> objects;
    private boolean isJumping;
    private int jumpPoint;
    private boolean lastRight;
    private boolean isDashing;
    private boolean dashed;
    private int dashPoint;
    private int direction;
    private boolean isClimbing;
    private int checkPointX;
    private int checkPointY;

    public Player(Context context, double positionX, double positionY, double radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.speed = 10;
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

    public boolean isClimbing() {
        return isClimbing;
    }

    public void jump() {
        this.isJumping = true;
        this.isClimbing = false;
        this.jumpPoint = 0;
    }

    public double getRadius() {
        return radius;
    }

    public void setCheckPoint(int x, int y) {
        checkPointX = x;
        checkPointY = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
    }

    public void update() {
        if (isTouchingSpikes()) {
            setPosition(checkPointX, checkPointY);
        } else {
            if (isDashing) {
                double changeBy = speed * 4;
                if (direction % 2 == 1 && dashPoint >= MAX_DASH * 2 / 3 || dashPoint >= MAX_DASH) {
                    isDashing = false;
                    isJumping = true;
                    jumpPoint = 8;
                    direction = -1;
                } else {
                    boolean up = false;
                    boolean down = false;
                    boolean left = false;
                    boolean right = false;
                    if ((direction == -1 && lastRight || direction == 0) && !moveRight(changeBy) ||
                            (direction == -1 && !lastRight || direction == 4) && !moveLeft(changeBy) ||
                            direction == 1 && ((right = !moveRight(changeBy)) || (up = !moveUp(changeBy))) ||
                            direction == 2 && (!moveUp(changeBy)) ||
                            direction == 3 && ((left = !moveLeft(changeBy)) || (up = !moveUp(changeBy))) ||
                            direction == 5 && ((left = !moveLeft(changeBy)) || (down = !moveDown(changeBy))) ||
                            direction == 6 && (!moveDown(changeBy)) ||
                            direction == 7 && ((right = !moveRight(changeBy)) || (down = !moveDown(changeBy)))) {
                        if (!isClimbing) {
                            if (direction == 1) {
                                if (right)
                                    direction = 2;
                                else
                                    direction = 0;
                                dashPoint = MAX_DASH - 2;
                            } else if (direction == 3) {
                                if (left)
                                    direction = 2;
                                else
                                    direction = 4;
                                dashPoint = MAX_DASH - 2;
                            } else if (direction == 5) {
                                if (left)
                                    direction = 6;
                                else
                                    direction = 4;
                                dashPoint = MAX_DASH - 2;
                            } else if (direction == 7) {
                                if (right)
                                    direction = 6;
                                else
                                    direction = 0;
                                dashPoint = MAX_DASH - 2;
                            } else {
                                isDashing = false;
                                isJumping = true;
                                jumpPoint = 8;
                                direction = -1;
                            }
                        }
                    }
                    dashPoint++;
                }
            }
            if (isJumping) {
                double changeBy = -4 * jumpPoint + 32;
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
    }

    public void resetObstacles() {
        objects = new ArrayList<>();
        objects.add(new Obstacle(new Rect(0, -speed, canvasWidth, 0), "B"));
        objects.add(new Obstacle(new Rect(0, canvasHeight, canvasWidth, canvasHeight + speed), "B"));
        objects.add(new Obstacle(new Rect(-speed, 0, 0, canvasHeight), "B"));
        objects.add(new Obstacle(new Rect(canvasWidth, 0, canvasWidth + speed, canvasHeight), "B"));
    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void addObject(Obstacle o) {
        objects.add(o);
    }

    public boolean isTouchingSpikes() {
        boolean touchSpikes = false;
        for (int i = 0; i < objects.size(); i++) {
            Rect rect = objects.get(i).getRect();
            if (objects.get(i).getType().equals("S") && (
                    rect.left > positionX - radius && rect.left < positionX + radius && positionY + radius - rect.top >= 0 && positionY + radius - rect.top == 0 ||
                            rect.left > positionX - radius && rect.left < positionX + radius && rect.bottom - (positionY - radius) >= 0 && rect.bottom - (positionY - radius) == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && positionX - radius - rect.right >= 0 && positionX - radius - rect.right == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && rect.left - (positionX + radius) >= 0 && rect.left - (positionX + radius) == 0)) {
                touchSpikes = true;
                //System.out.println("TOUCH");
            } else if (objects.get(i).getType().equals("S")) {
                System.out.println((rect.left > positionX - radius) + " " + (rect.left < positionX + radius));
            }
        }
        return touchSpikes;
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
        boolean touchSpikes = false;
        double defaultPosition = Integer.MAX_VALUE;
        Obstacle defaultObstacle = null;
        int obsDirBelow = 0;
        for (int i = 0; i < objects.size(); i++) {
            Rect rect = objects.get(i).getRect();
            if (!objects.get(i).getType().equals("P") && rect.top < positionY + radius && rect.bottom > positionY - radius) {
                if (rect.left >= positionX - radius && rect.left <= positionX + radius + changeBy && rect.left < canvasWidth && rect.right > 0) {
                    if (rect.left - radius < defaultPosition) {
                        defaultPosition = rect.left - radius;
                        defaultObstacle = objects.get(i);
                    }
                    canMove = false;
                } else {
                    if (rect.left == canvasWidth && rect.left <= positionX - radius + changeBy) {
                        hitRightWall = true;
                        canMove = false;
                    }
                }
            }
            if (rect.left < positionX + radius && rect.right > positionX - radius && rect.top - (positionY + radius) >= 0 && rect.top - (positionY + radius) < changeBy) {
                obsDirBelow++;
            }
            if (objects.get(i).getType().equals("S") && (
                    rect.left > positionX - radius && rect.left < positionX + radius && positionY + radius - rect.top == 0 ||
                            rect.left > positionX - radius && rect.left < positionX + radius && rect.bottom - (positionY - radius) == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && positionX - radius - rect.right == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && rect.left - (positionX + radius) == 0)) {
                touchSpikes = true;
                //System.out.println("TOUCH");
            } else if (objects.get(i).getType().equals("S")) {
                //System.out.println((rect.left > positionX - radius) + " " + (rect.left < positionX + radius));
            }
        }
        if (touchSpikes) {
            setPosition(checkPointX, checkPointY);
            return false;
        } else {
            if (canMove)
                positionX += changeBy;
            else {
                if (hitRightWall) {
                    //System.out.println("RIGHT");
                    Game.increaseLevel();
                    setPosition(-radius + changeBy, positionY);
                    Point newCheck = Level.getStart(Game.getLevel(), Game.getScreen());
                    setCheckPoint((int) ((double) (newCheck.x) / LEVEL_WIDTH * canvasWidth), (int) ((1 - ((double) (newCheck.y) / LEVEL_HEIGHT)) * canvasHeight - radius));
                } else if (obsDirBelow == 0 && defaultObstacle.getType().equals("C")) {
                    positionX = defaultPosition;
                    isJumping = false;
                    isDashing = false;
                    isClimbing = true;
                    //System.out.println("HERE");
                } else if (obsDirBelow == 0) {
                    positionX = defaultPosition;
                }
            }
            if (!isClimbing && !isDashing && !isJumping && obsDirBelow == 0) {
                isJumping = true;
                jumpPoint = 9;
                //System.out.println("HERE");
            }
            lastRight = true;
            return canMove;
        }
    }

    public boolean moveLeft(double changeBy) {
        boolean canMove = true;
        boolean hitLeftWall = false;
        double defaultPosition = -1;
        Obstacle defaultObstacle = null;
        int obsDirBelow = 0;
        boolean touchSpikes = false;
        for (int i = 0; i < objects.size(); i++) {
            Rect rect = objects.get(i).getRect();
            if (!objects.get(i).getType().equals("P") && rect.top < positionY + radius && rect.bottom > positionY - radius) {
                if (rect.right <= positionX - radius && rect.right >= positionX - radius - changeBy && rect.left < canvasWidth && (rect.right > 0 || Game.getScreen() == 0)) {
                    if (rect.right + radius > defaultPosition) {
                        defaultPosition = rect.right + radius;
                        defaultObstacle = objects.get(i);
                    }
                    canMove = false;
                } else {
                    if (rect.right == 0 && rect.right >= positionX + radius - changeBy) {
                        hitLeftWall = true;
                        canMove = false;
                    }
                }
            }
            if (rect.left < positionX + radius && rect.right > positionX - radius && rect.top - (positionY + radius) >= 0 && rect.top - (positionY + radius) < changeBy) {
                obsDirBelow++;
            }
            if (objects.get(i).getType().equals("S") && (
                    rect.left > positionX - radius && rect.left < positionX + radius && positionY + radius - rect.top == 0 ||
                            rect.left > positionX - radius && rect.left < positionX + radius && rect.bottom - (positionY - radius) == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && positionX - radius - rect.right == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && rect.left - (positionX + radius) == 0)) {
                touchSpikes = true;
                //System.out.println("TOUCH");
            } else if (objects.get(i).getType().equals("S")) {
                //System.out.println((rect.left > positionX - radius) + " " + (rect.left < positionX + radius));
            }
        }
        if (touchSpikes) {
            setPosition(checkPointX, checkPointY);
            return false;
        } else {
            if (canMove) {
                positionX -= changeBy;
            } else {
                if (hitLeftWall && Game.getScreen() > 0) {
                    //System.out.println("LEFT");
                    Game.decreaseLevel();
                    setPosition(canvasWidth + radius - changeBy, positionY);
                    Point newCheck = Level.getStart(Game.getLevel(), Game.getScreen());
                    setCheckPoint((int) ((double) (newCheck.x) / LEVEL_WIDTH * canvasWidth), (int) ((1 - ((double) (newCheck.y) / LEVEL_HEIGHT)) * canvasHeight - radius));
                } else if (obsDirBelow == 0 && defaultObstacle.getType().equals("C")) {
                    positionX = defaultPosition;
                    isJumping = false;
                    isDashing = false;
                    isClimbing = true;
                } else if (obsDirBelow == 0) {
                    positionX = defaultPosition;
                }
            }
            if (!isClimbing && !isDashing && !isJumping && obsDirBelow == 0) {
                isJumping = true;
                jumpPoint = 9;
                //System.out.println("HERE");
            }
            lastRight = false;
            return canMove;
        }
    }

    public boolean moveUp(double changeBy) {
        boolean canMove = true;
        boolean hitTopWall = false;
        boolean touchSpikes = false;
        boolean touchWall = false;
        double defaultPosition = -1;
        for (int i = 0; i < objects.size(); i++) {
            Rect rect = objects.get(i).getRect();
            if (!objects.get(i).getType().equals("P") && rect.left < positionX + radius && rect.right > positionX - radius) {
                if (rect.bottom <= positionY - radius && rect.bottom >= positionY - radius - changeBy && rect.top < canvasHeight && rect.bottom > 0) {
                    if (rect.bottom + radius > defaultPosition)
                        defaultPosition = rect.bottom + radius;
                    canMove = false;
                } else {
                    if (rect.bottom == 0 && rect.bottom >= positionY + radius - changeBy) {
                        hitTopWall = true;
                        canMove = false;
                    }
                }
            }
            if (objects.get(i).getType().equals("C") && rect.top > positionY - radius && rect.top < positionY + radius && positionX - radius - rect.right == 0 ||
                    rect.top > positionY - radius && rect.top < positionY + radius && rect.left - (positionX + radius) == 0) {
                touchWall = true;
            }
            if (objects.get(i).getType().equals("S") && (
                    rect.left > positionX - radius && rect.left < positionX + radius && positionY + radius - rect.top == 0 ||
                            rect.left > positionX - radius && rect.left < positionX + radius && rect.bottom - (positionY - radius) == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && positionX - radius - rect.right == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && rect.left - (positionX + radius) == 0)) {
                touchSpikes = true;
                //System.out.println("TOUCH");
            } else if (objects.get(i).getType().equals("S")) {
                //System.out.println((rect.left > positionX - radius) + " " + (rect.left < positionX + radius));
            }
        }
        if (canMove) {
            if (!isClimbing || touchWall)
                positionY -= changeBy;
            else if (isClimbing && !touchWall) {
                isClimbing = false;
                isJumping = true;
                jumpPoint = 9;
            }
        } else {
            if (hitTopWall) {
                //System.out.println("UP");
                Game.increaseLevel();
                setPosition(positionX, canvasHeight * 15 / 16 - radius);
                Point newCheck = Level.getStart(Game.getLevel(), Game.getScreen());
                setCheckPoint((int) ((double) (newCheck.x) / LEVEL_WIDTH * canvasWidth), (int) ((1 - ((double) (newCheck.y) / LEVEL_HEIGHT)) * canvasHeight - radius));
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
        boolean touchWall = false;
        boolean touchSpikes = false;
        double defaultPosition = Integer.MAX_VALUE;
        for (int i = 0; i < objects.size(); i++) {
            Rect rect = objects.get(i).getRect();
            if (rect.left < positionX + radius && rect.right > positionX - radius) {
                if (rect.top >= positionY + radius && rect.top <= positionY + radius + changeBy && rect.top < canvasHeight && rect.bottom > 0) {
                    if (rect.top - radius < defaultPosition)
                        defaultPosition = rect.top - radius;
                    canMove = false;
                } else {
                    if (rect.top == canvasHeight && rect.top <= positionY - radius + changeBy) {
                        hitBottomWall = true;
                        canMove = false;
                    }
                }
            }
            if (objects.get(i).getType().equals("C") && rect.top > positionY - radius && rect.top < positionY + radius && positionX - radius - rect.right == 0 ||
                    rect.top > positionY - radius && rect.top < positionY + radius && rect.left - (positionX + radius) == 0) {
                touchWall = true;
            }
            if (objects.get(i).getType().equals("S") && (
                    rect.left > positionX - radius && rect.left < positionX + radius && positionY + radius - rect.top == 0 ||
                            rect.left > positionX - radius && rect.left < positionX + radius && rect.bottom - (positionY - radius) == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && positionX - radius - rect.right == 0 ||
                            rect.top > positionY - radius && rect.top < positionY + radius && rect.left - (positionX + radius) == 0)) {
                touchSpikes = true;
                //System.out.println("TOUCH");
            } else if (objects.get(i).getType().equals("S")) {
                System.out.println((rect.left > positionX - radius) + " " + (rect.left < positionX + radius));
            }
        }
        if (canMove) {
            if (!isClimbing || touchWall)
                positionY += changeBy;
            else if (isClimbing && !touchWall) {
                isClimbing = false;
                isJumping = true;
                jumpPoint = 9;
            }
        } else {
            if (hitBottomWall && Game.getScreen() > 0) {
                //System.out.println("DOWN");
                Game.decreaseLevel();
                setPosition(positionX, radius);
                Point newCheck = Level.getStart(Game.getLevel(), Game.getScreen());
                setCheckPoint((int) ((double) (newCheck.x) / LEVEL_WIDTH * canvasWidth), (int) ((1 - ((double) (newCheck.y) / LEVEL_HEIGHT)) * canvasHeight - radius));
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
        this.isClimbing = false;
    }
}
