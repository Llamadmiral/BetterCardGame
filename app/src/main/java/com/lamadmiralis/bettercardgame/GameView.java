package com.lamadmiralis.bettercardgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.lamadmiralis.bettercardgame.objects.card.CardTemplate;
import com.lamadmiralis.bettercardgame.renderer.Clickable;
import com.lamadmiralis.bettercardgame.renderer.Renderable;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;
import com.lamadmiralis.bettercardgame.utility.ResourceInitializer;
import com.lamadmiralis.bettercardgame.utility.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author maczaka
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread mainThread;

    public GameView(final MainActivity mainActivity) {
        super(mainActivity);
        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(), this);

        ResourceInitializer.initResources(mainActivity.getApplicationContext());
        CardTemplate.initCardImages();
        InterfaceContext.getInstance().setContext(getContext());
        InterfaceContext.getInstance().test();

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                final int action = event.getAction();
                if (action == MotionEvent.ACTION_UP) {
                    final Clickable clickable = findClickedObject(event.getX(), event.getY());
                    if (clickable != null) {
                        clickable.click();
                    }

                }
                return true;
            }
        });
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
        //do nothing.
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                Log.e(Tag.MT, Tag.getFormattedExceptionMessage(e));
            }
            retry = false;
        }
    }

    private Clickable findClickedObject(final float x, final float y) {
        Clickable clickedObject = null;
        for (final Clickable clickable : InterfaceContext.getInstance().getClickables()) {
            if (clickable.isClickable() && clickable.isClicked(x, y)) {
                clickedObject = clickable;
                break;
            }
        }
        return clickedObject;
    }

    public void update() {
        try {
            InterfaceContext.getInstance().update();
            for (final Renderable renderable : InterfaceContext.getInstance().getObjectsToDraw()) {
                renderable.update();
            }
        } catch (final Exception e) {
            Log.e(Tag.MT, Tag.getFormattedExceptionMessage(e));
        }
    }

    @Override
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        final List<Renderable> objectsToDraw = InterfaceContext.getInstance().getObjectsToDraw();
        Collections.sort(objectsToDraw, new Comparator<Renderable>() {
            @Override
            public int compare(final Renderable o1, final Renderable o2) {
                return o1.getZIndex() - o2.getZIndex();
            }
        });
        for (final Renderable renderable : new ArrayList<>(objectsToDraw)) {
            renderable.render(canvas);
        }
        InterfaceContext.getInstance().removeObjects();
    }

}
