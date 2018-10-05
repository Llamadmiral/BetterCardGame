package com.lamadmiralis.bettercardgame.animation.impl;

import com.lamadmiralis.bettercardgame.animation.Movement;
import com.lamadmiralis.bettercardgame.animation.MovementHelper;
import com.lamadmiralis.bettercardgame.renderer.Clickable;

import java.util.ArrayList;

public class MovementReArrange extends Movement {

    private float[] nextPosition;

    public MovementReArrange(final Clickable clickable, final float[] nextPosition) {
        this.clickable = clickable;
        this.nextPosition = nextPosition;
        initStepList();
    }

    @Override
    protected void initStepList() {
        this.stepList = new ArrayList<>();
        final float[] startingCoordinates = new float[]{clickable.getX(), clickable.getY()};
        final float[] endingCoordinates = new float[]{nextPosition[0], nextPosition[1]};
        stepList.add(MovementHelper.addStepBetweenTwoPoints(startingCoordinates, endingCoordinates, 6));
    }
}
