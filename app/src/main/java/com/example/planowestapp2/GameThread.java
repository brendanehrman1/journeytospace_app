package com.example.planowestapp2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1000/MAX_UPS;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;
    private Game game;

    public GameThread(Game game, SurfaceHolder surfaceHolder) {
        super();
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    @Override
    public void run() {
        super.run();

        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while (isRunning) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            updateCount++;
            frameCount++;

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (elapsedTime / 1000);
                averageFPS = frameCount / (elapsedTime / 1000);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }

    }
}
