package com.lamadmiralis.bettercardgame.events.impl;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.BattleContext;

public class EventPlayEnemyCard extends AbstractEvent {
    private AbstractCard card;

    public EventPlayEnemyCard(final long offset, final AbstractCard card) {
        super(offset);
        this.card = card;
    }

    @Override
    public void fire() {
        BattleContext.getInstance().getEnemy().activateCard(this.card);
    }
}
