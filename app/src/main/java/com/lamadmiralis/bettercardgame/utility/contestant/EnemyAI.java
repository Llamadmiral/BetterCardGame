package com.lamadmiralis.bettercardgame.utility.contestant;

import android.util.Log;

import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.events.impl.EventAIStartAttackPhase;
import com.lamadmiralis.bettercardgame.events.impl.EventPlayEnemyCard;
import com.lamadmiralis.bettercardgame.events.impl.NextTurnEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.Tag;

public class EnemyAI {
    private Contestant enemy;

    public EnemyAI(final Contestant enemy) {
        this.enemy = enemy;
    }

    public void startTurn() {
        enemy.getHand().initializeTurn();
        playCards();
        Log.i(Tag.MT, "Started Enemy Turn");
    }


    public void attackWithCards() {
        enemy.finalizeTurn();
        EventHandler.getInstance().addFinalEvent(new NextTurnEvent());
        EventHandler.getInstance().dispatch();
    }

    private void playCards() {
        int i = 0;
        for (final AbstractCard card : enemy.getHand().getCards().values()) {
            if (card.getCurrentWaitTime() == 0) {
                i++;
                EventHandler.getInstance().addEvent(new EventPlayEnemyCard(200 * i, card));
            }
        }
        EventHandler.getInstance().addFinalEvent(new EventAIStartAttackPhase());
        EventHandler.getInstance().dispatch();
    }

}
