package com.lamadmiralis.bettercardgame.animation;

/**
 * @author maczaka
 */
public class Step {
    private float[] step;
    private int count;

    public Step(final float[] step, final int count) {
        this.step = step;
        this.count = count - 1;
    }

    public Step() {

    }

    public float[] getStep() {
        count--;
        return step;
    }

    public boolean finished() {
        return count == 0;
    }
}
