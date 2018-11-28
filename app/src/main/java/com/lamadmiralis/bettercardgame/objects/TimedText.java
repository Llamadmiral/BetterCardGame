package com.lamadmiralis.bettercardgame.objects;

import android.graphics.Canvas;

import com.lamadmiralis.bettercardgame.objects.uielements.AbstractTextBox;

public class TimedText extends AbstractTextBox {

    private long updatesToLast;

    public TimedText(final String text, final int secondsToLast) {
        super(text);
        updatesToLast = secondsToLast * 30L;
        createImage();
    }

    @Override
    public void click() {
        // do nothing
    }

    @Override
    public void render(final Canvas canvas) {
        if (updatesToLast > 0) {
            super.render(canvas);
            updatesToLast--;
        } else {
            this.removeObject();
        }
    }
}
