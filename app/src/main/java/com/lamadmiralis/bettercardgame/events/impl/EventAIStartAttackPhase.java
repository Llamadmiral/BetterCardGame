package com.lamadmiralis.bettercardgame.events.impl;

import android.util.Log;

import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.utility.BattleContext;
import com.lamadmiralis.bettercardgame.utility.Tag;

public class EventAIStartAttackPhase extends AbstractEvent {
    public EventAIStartAttackPhase() {
        super(300L);
    }

    @Override
    public void fire() {
        Log.i(Tag.MT, "Started attack phase for AI");
        BattleContext.getInstance().getEnemyAI().attackWithCards();
    }
}
