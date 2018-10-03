package com.lamadmiralis.bettercardgame.animation;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.events.InstantEvent;


/**
 * A class representing a single InstantEvent so it will be in-synch with the animation.
 */
public class EventStep extends Step {

    private static final float[] NO_STEP = new float[]{0, 0};

    private AbstractEvent event;

    public EventStep(final AbstractEvent abstractEvent) {
        this.event = abstractEvent;
    }

    @Override
    public float[] getSteps() {
        new InstantEvent<>(event).fire();
        return NO_STEP;
    }

    @Override
    public boolean finished() {
        return true;
    }
}
