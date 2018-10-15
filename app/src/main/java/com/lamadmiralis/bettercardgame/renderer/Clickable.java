package com.lamadmiralis.bettercardgame.renderer;

import android.graphics.Bitmap;

import com.lamadmiralis.bettercardgame.utility.InterfaceContext;

/**
 * @author maczaka
 */
public abstract class Clickable implements Renderable {

    private float x;
    private float y;
    private boolean isClickable = true;
    private boolean inAnimation = false;

    public boolean isClicked(final float clickX, final float clickY) {
        boolean isClicked = false;
        final int coordX = (int) getX();
        final int coordY = (int) getY();
        final Bitmap image = getCurrentImage();
        if (coordX < clickX && clickX < coordX + image.getWidth() && coordY < clickY && clickY < coordY + image.getHeight()) {
            isClicked = true;
        }
        return isClicked;
    }

    public void removeObject() {
        InterfaceContext.getInstance().removeObject(this);
    }

    public boolean isInAnimation() {
        return inAnimation;
    }

    public void setInAnimation(final boolean inAnimation) {
        this.inAnimation = inAnimation;
    }

    public float getX() {
        return x;
    }

    public void setX(final float positionX) {
        this.x = positionX;
    }

    public float getY() {
        return y;
    }

    public void setY(final float positionY) {
        this.y = positionY;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public void setClickable(final boolean clickable) {
        isClickable = clickable;
    }

    public abstract void click();

    public abstract Bitmap getCurrentImage();

    public String getPrintablePosition() {
        return "x: " + getX() + ", y: " + getY();
    }

}
