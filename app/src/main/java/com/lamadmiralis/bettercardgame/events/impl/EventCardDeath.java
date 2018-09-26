package com.lamadmiralis.bettercardgame.events.impl;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;

public class EventCardDeath extends AbstractEvent {
    private AbstractCard card;

    public EventCardDeath(final AbstractCard abstractCard) {
        super(0);
        this.card = abstractCard;
    }

    @Override
    public void fire() {
        InterfaceContext.getInstance().getObjectsToDraw().remove(card);
    }
}
