package com.lamadmiralis.bettercardgame.events.impl;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.BattleContext;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;
import com.lamadmiralis.bettercardgame.utility.contestant.Contestant;

public class EventCardDeath extends AbstractEvent {
    private AbstractCard card;

    public EventCardDeath(final AbstractCard abstractCard) {
        super(0);
        this.card = abstractCard;
    }

    @Override
    public void fire() {
        final Contestant cont = BattleContext.getInstance().getContestant(card.isOwnedByPlayer());
        cont.getField().removeCard(card);
        cont.getField().rearrangeCards();
        InterfaceContext.getInstance()
                .getObjectsToDraw()
                .remove(card);
    }
}
