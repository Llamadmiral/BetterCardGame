package com.lamadmiralis.bettercardgame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.lamadmiralis.bettercardgame.utility.Tag;

/**
 * @author maczaka
 */
public class MainThread extends Thread {
    private static final long FPS = 60;
    private static final long TIME_BETWEEN_FRAMES = 1000 / FPS;

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    private Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(final boolean running) {
        this.running = running;
    }


    @Override
    public void run() {
        while (running) {
            long renderStart = System.currentTimeMillis();
            stepThenDraw();
            long renderEnd = System.currentTimeMillis();
            sleepThread(TIME_BETWEEN_FRAMES - (renderEnd - renderStart));
        }
    }

    private void stepThenDraw() {
        canvas = null;

        try {
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                this.gameView.update();
                this.gameView.draw(canvas);
            }
        } catch (final Exception e) {
            Log.e(Tag.MT, Tag.getFormattedExceptionMessage(e));
        } finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (final Exception e) {
                    Log.e(Tag.MT, Tag.getFormattedExceptionMessage(e));
                }
            }
        }
    }

    private void sleepThread(long time) {
        //If we need to sleep, cause its too fast
        try {
            if (time > 0) {
                //Sleep it to make it synchronous
                sleep(time);
            } else {
                //Sleep it to 5ms to let GC do his work etc. ---->> less memory use
                sleep(5);
            }
        } catch (final InterruptedException e) {
            Log.e(Tag.MT, "InterruptedException");
        }
    }

}
