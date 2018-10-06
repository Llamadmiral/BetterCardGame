package com.lamadmiralis.bettercardgame.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.lamadmiralis.bettercardgame.animation.Movement;
import com.lamadmiralis.bettercardgame.renderer.Clickable;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;
import com.lamadmiralis.bettercardgame.utility.Tag;

/**
 * @author maczaka
 */
public abstract class AbstractObject extends Clickable {
    private static long count = 0;
    protected Bitmap fullImage;
    protected Bitmap smallImage;
    protected int zIndex = 0;

    private long id;
    private boolean isHidden = false;
    private Movement movement;

    protected AbstractObject() {
        id = count;
        count++;
        InterfaceContext.getInstance().addToClickable(this);
        Log.d(Tag.MT, "Created: " + this.getClass().getSimpleName() + ":" + this.id);
    }

    @Override
    public Bitmap getCurrentImage() {
        return fullImage;
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
            final Movement mvmnt = getMovement();
            if (mvmnt.hasNext()) {
                mvmnt.move(this);
            } else {
                setInAnimation(false);
                setClickable(true);
                setZIndex(0);
            }
        }
    }

    @Override
    public void render(final Canvas canvas) {
        if (fullImage != null) {
            canvas.drawBitmap(fullImage, getX(), getY(), null);
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
