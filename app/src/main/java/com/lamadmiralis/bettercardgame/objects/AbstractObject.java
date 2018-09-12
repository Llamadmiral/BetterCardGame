package com.lamadmiralis.bettercardgame.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.lamadmiralis.bettercardgame.animation.Movement;
import com.lamadmiralis.bettercardgame.renderer.Clickable;

/**
 * @author maczaka
 */
public abstract class AbstractObject extends Clickable {
    private static final String TAG = "AbstractObject";
    private static long count = 0;
    protected Bitmap fullCardImage;
    protected Bitmap smallImage;
    protected int zIndex = 0;

    private long id;
    private boolean isHidden = false;
    private Movement movement;

    protected AbstractObject() {
        id = count;
        count++;
    }

    @Override
    public Bitmap getCurrentImage() {
        return fullCardImage;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(final Movement movement) {
        this.setZIndex(10);
        this.setInAnimation(true);
        this.setClickable(false);
        this.movement = movement;
    }

    public long getId() {
        return id;
    }

    public boolean isHidden() {
        return isHidden;
    }

    @Override
    public void update() {
        if (isInAnimation()) {
            final Movement movement = getMovement();
            if (movement.hasNext()) {
                movement.move(this);
            } else {
                setInAnimation(false);
                setClickable(true);
                setZIndex(0);
            }
        }
    }

    @Override
    public void render(final Canvas canvas) {
        if (fullCardImage != null) {
            canvas.drawBitmap(fullCardImage, getX(), getY(), null);
        }
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(final int zIndex) {
        this.zIndex = zIndex;
    }

    public void activateSpecialEffect() {
        //do nothing by default
    }
}
