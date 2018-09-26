package com.lamadmiralis.bettercardgame.animation;

import java.util.ArrayList;
import java.util.List;

public final class MovementHelper {
    private MovementHelper() {
        //nope
    }

    public static Step addStepBetweenTwoPoints(final float[] start, final float[] end, final int frames) {
        final float offsetX = (end[0] - start[0]) / frames;
        final float offsetY = (end[1] - start[1]) / frames;
        return new Step(new float[]{offsetX, offsetY}, frames);
    }

    public static List<Step> addStepBetweenMultiplePoints(final int totalFrames, final float[]... positions) {
        final List<Step> steps = new ArrayList<>();
        final int initFramePerPos = (totalFrames / positions.length) + 1;
        int frameDiff = totalFrames - (initFramePerPos * positions.length);
        for (int i = 0; i < positions.length - 1; i++) {
            int finalFramePerPos = initFramePerPos;
            if (frameDiff > 0) { //if the totalFrames are not dividable by the no. of positions
                finalFramePerPos++;
                frameDiff--;
            }
            steps.add(addStepBetweenTwoPoints(positions[i], positions[i + 1], finalFramePerPos));
        }
        return steps;
    }
}
