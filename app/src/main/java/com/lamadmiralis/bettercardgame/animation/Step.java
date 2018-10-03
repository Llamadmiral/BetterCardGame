package com.lamadmiralis.bettercardgame.animation;

/**
 * @author maczaka
 */
public class Step {
    private float[] steps;
    private int count;

    public Step(final float[] steps, final int count) {
        this.steps = steps;
        this.count = count - 1;
    }

    public Step() {

    }

    public float[] getSteps() {
        count--;
        return steps;
    }

    public boolean finished() {
        return count == 0;
    }
}
