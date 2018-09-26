package com.lamadmiralis.bettercardgame.animation;

import com.lamadmiralis.bettercardgame.renderer.Clickable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maczaka
 */
public abstract class Movement {
    protected List<Step> stepList;
    protected Clickable clickable;

    public Movement(final Clickable clickable) {
        this.clickable = clickable;
        initStepList();
    }

    protected Movement() {
    }

    protected abstract void initStepList();

    private float[] getNextStep() {
        final Step step = stepList.get(0);
        if (step.finished()) {
            stepList.remove(0);
        }
        return step.getStep();
    }

    public boolean hasNext() {
        return !stepList.isEmpty();
    }

    public void move(final Clickable clickable) {
        final float[] move = getNextStep();
        clickable.setX(clickable.getX() + move[0]);
        clickable.setY(clickable.getY() + move[1]);
    }
}
