package com.lamadmiralis.bettercardgame.objects.uielements;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import com.lamadmiralis.bettercardgame.objects.AbstractObject;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;
import com.lamadmiralis.bettercardgame.utility.Contestant;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;

public class HealthBar extends AbstractObject {

    private static final Paint BG_FILL_PAINT = new Paint();
    private static final Paint HP_FILL_PAINT = new Paint();
    private static final Paint STROKE_PAINT = new Paint();
    private static final Paint TEXT_PAINT = new Paint();
    private static final RectF RECT_F = new RectF(
            ProjectConstants.HEALTH_BAR_WIDTH,
            ProjectConstants.HEALT_BAR_HEIGHT,
            0f,
            0f);
    private static final float CORNER_RADIUS = 50f;

    static {
        BG_FILL_PAINT.setStyle(Paint.Style.FILL);
        BG_FILL_PAINT.setColor(ContextCompat.getColor(InterfaceContext.getInstance().getContext(),
                ProjectConstants.COLOR_HEALTH_BAR_BG));

        HP_FILL_PAINT.setStyle(Paint.Style.FILL);
        HP_FILL_PAINT.setColor(ContextCompat.getColor(InterfaceContext.getInstance().getContext(),
                ProjectConstants.COLOR_DAMAGED));

        STROKE_PAINT.setStyle(Paint.Style.STROKE);
        STROKE_PAINT.setColor(Color.BLACK);
        STROKE_PAINT.setStrokeWidth(3f);

        TEXT_PAINT.setTypeface(Typeface.DEFAULT_BOLD);
        TEXT_PAINT.setTextSize(ProjectConstants.HEALT_BAR_HEIGHT * (2.0f / 3.0f));
        TEXT_PAINT.setTextAlign(Paint.Align.CENTER);
        TEXT_PAINT.setColor(Color.WHITE);
    }

    private Contestant contestant;

    public HealthBar(final Contestant contestant) {
        this.contestant = contestant;
        createImage();
    }

    @Override
    public void click() {
        //do nothing.
    }

    public void createImage() {
        final Bitmap bitmap = Bitmap.createBitmap(ProjectConstants.HEALTH_BAR_WIDTH,
                ProjectConstants.HEALT_BAR_HEIGHT,
                Bitmap.Config.ARGB_8888
        );
        final Canvas canvas = new Canvas(bitmap);
        //Draw the round background
        canvas.drawRoundRect(RECT_F, CORNER_RADIUS, CORNER_RADIUS, BG_FILL_PAINT);
        float maxHp = contestant.getMaxHealth();
        float currentHp = contestant.getCurrentHealth();
        float percentageDiff = currentHp / maxHp;
        canvas.drawRoundRect(percentageDiff * ProjectConstants.HEALTH_BAR_WIDTH,
                ProjectConstants.HEALT_BAR_HEIGHT,
                0f,
                0f,
                CORNER_RADIUS,
                CORNER_RADIUS,
                HP_FILL_PAINT
        );
        //Draw the text
        canvas.drawText(this.getText(),
                ProjectConstants.HEALTH_BAR_WIDTH / 2,
                (ProjectConstants.HEALT_BAR_HEIGHT / 2) * 1.4f, TEXT_PAINT);
        //Draw the round edge
        canvas.drawRoundRect(RECT_F, CORNER_RADIUS, CORNER_RADIUS, STROKE_PAINT);
        this.fullImage = bitmap;
    }

    private String getText() {
        return contestant.getCurrentHealth() + " / " + contestant.getMaxHealth();
    }

}
