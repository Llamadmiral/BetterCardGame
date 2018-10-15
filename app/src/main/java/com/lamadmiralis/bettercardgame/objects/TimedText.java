package com.lamadmiralis.bettercardgame.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.lamadmiralis.bettercardgame.utility.InterfaceContext;
import com.lamadmiralis.bettercardgame.utility.Tag;

public class TimedText extends AbstractObject {

    private static final Paint TEXT_PAINT = new Paint();
    private static final int FONT_SIZE = 30;
    private static final int PADDING = 5;

    static {
        TEXT_PAINT.setTypeface(Typeface.DEFAULT_BOLD);
        TEXT_PAINT.setTextSize(FONT_SIZE);
        TEXT_PAINT.setTextAlign(Paint.Align.CENTER);
        TEXT_PAINT.setColor(Color.BLACK);
    }

    private String text;
    private long updatesToLast;

    public TimedText(final String text, final int secondsToLast) {
        super();
        this.text = text;
        updatesToLast = secondsToLast * 30L;
        createImage();
    }

    private void createImage() {
        Log.i(Tag.MT, "TimedText:createImage called");
        final int textLengt = FONT_SIZE * text.length();
        final float centerXPosition = (InterfaceContext.WIDTH / 2) - (textLengt / 2);
        this.setX(centerXPosition);
        this.setY(InterfaceContext.HEIGHT / 2);
        final Bitmap bitmap = Bitmap.createBitmap(textLengt + PADDING,
                FONT_SIZE + PADDING,
                Bitmap.Config.ARGB_8888
        );
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawText(this.text,
                ((textLengt + PADDING) / 2),
                (FONT_SIZE * 2 + PADDING) / 2,
                TEXT_PAINT);
        this.fullImage = bitmap;
        Log.i(Tag.MT, "Pos: " + this.getX() + "-" + this.getY());
        Log.i(Tag.MT, "TimedText:createImage finished!");
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
