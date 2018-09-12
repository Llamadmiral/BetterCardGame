package com.lamadmiralis.bettercardgame.events.impl;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.utility.BattleContext;

/**
 * @author maczaka
 */
public class EventDrawCard extends AbstractEvent {
    private boolean drawForPlayer;

    public EventDrawCard(final long offset, final boolean drawForPlayer) {
        super(offset);
        this.drawForPlayer = drawForPlayer;
    }

    @Override
    public void fire() {
        BattleContext.getInstance().drawCard(drawForPlayer);
    }
}
