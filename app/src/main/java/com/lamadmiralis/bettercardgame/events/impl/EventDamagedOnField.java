package com.lamadmiralis.bettercardgame.events.impl;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;

/**
 * @author maczaka
 */
public class EventDamagedOnField extends AbstractEvent {

    public EventDamagedOnField(final long offset) {
        super(offset);
    }

    @Override
    public void fire() {

    }
}
