package com.lamadmiralis.bettercardgame.events.impl;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.utility.BattleContext;

public class NextTurnEvent extends AbstractEvent {
    public NextTurnEvent() {
        super(200L); //always used as a finalizing Event.
    }

    @Override
    public void fire() {
        BattleContext.getInstance().nextTurn();
    }
}
