package com.lamadmiralis.bettercardgame.renderer;

import android.graphics.Bitmap;

/**
 * @author maczaka
 */
public abstract class Clickable implements Renderable {

    private float x;
    private float y;
    private float previousX;
    private float previousY;
    private boolean isClickable = true;
    private boolean inAnimation = false;

    public boolean isClicked(final float clickX, final float clickY) {
        boolean isClicked = false;
        final int x = (int) getX();
        final int y = (int) getY();
        final Bitmap image = getCurrentImage();
        if (x < clickX && clickX < x + image.getWidth() && y < clickY && clickY < y + image.getHeight()) {
            isClicked = true;
        }
        return isClicked;
    }

    public void saveCurrentCoordinates() {
        setPreviousX(getX());
        setPreviousY(getY());
    }

    public float[] getPreviousCoordinates() {
        return new float[]{getPreviousX(), getPreviousY()};
    }

    public float getPreviousX() {
        return previousX;
    }

    public void setPreviousX(final float previousX) {
        this.previousX = previousX;
    }

    public float getPreviousY() {
        return previousY;
    }

    public void setPreviousY(final float previousY) {
        this.previousY = previousY;
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
