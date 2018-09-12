package com.lamadmiralis.bettercardgame.events.impl;

import com.lamadmiralis.bettercardgame.animation.Movement;
import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

/**
 * @author maczaka
 */
public class EventMovementEvent extends AbstractEvent {
    private AbstractCard card;
    private Movement movement;

    public EventMovementEvent(final long offset, final AbstractCard card, final Movement movement) {
        super(offset);
        this.card = card;
        this.movement = movement;
    }

    @Override
    public void fire() {
        card.setMovement(movement);
    }
}
