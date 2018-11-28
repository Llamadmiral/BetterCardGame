package com.lamadmiralis.bettercardgame.objects.uielements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

import com.lamadmiralis.bettercardgame.objects.AbstractObject;

public abstract class AbstractTextBox extends AbstractObject {

    protected static final Paint TEXT_PAINT = new Paint();
    protected static final Paint BORDER_PAINT = new Paint();
    protected static final int FONT_SIZE = 30;
    protected static final int PADDING = 5;

    static {
        TEXT_PAINT.setTypeface(Typeface.DEFAULT_BOLD);
        TEXT_PAINT.setTextSize(FONT_SIZE);
        TEXT_PAINT.setTextAlign(Paint.Align.CENTER);
        TEXT_PAINT.setColor(Color.BLACK);

        BORDER_PAINT.setStyle(Paint.Style.STROKE);
        BORDER_PAINT.setColor(Color.BLACK);
        BORDER_PAINT.setStrokeWidth(3f);
    }


    private String text;


    public AbstractTextBox(final String text) {
        super();
        this.text = text;
        createImage();
    }

    protected void createImage() {
        final int textLengt = FONT_SIZE * text.length();
        final Bitmap bitmap = Bitmap.createBitmap(textLengt + PADDING,
                FONT_SIZE + PADDING,
                Bitmap.Config.ARGB_8888
        );
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawText(this.text,
                ((textLengt + PADDING) / 2),
                (FONT_SIZE * 2 + PADDING) / 2,
                TEXT_PAINT);
        final RectF rectF = new RectF(textLengt + PADDING,
                FONT_SIZE + PADDING,
                0,
                0);

        canvas.drawRoundRect(rectF, 30F, 30F, BORDER_PAINT);
        this.fullImage = bitmap;
    }

    public String getText() {
        return this.text;
    }
}
