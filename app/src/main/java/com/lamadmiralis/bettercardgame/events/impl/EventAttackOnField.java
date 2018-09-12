package com.lamadmiralis.bettercardgame.events.impl;

import android.util.Log;
import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.BattleContext;

/**
 * @author maczaka
 */
public class EventAttackOnField extends AbstractEvent {

    private AbstractCard attacker;

    public EventAttackOnField(final long offset) {
        super(offset);
    }

    public void setAttacker(final AbstractCard attacker) {
        this.attacker = attacker;
    }

    @Override
    public void fire() {
        final boolean ownedByPlayer = attacker.isOwnedByPlayer();
        int pos = BattleContext.getInstance().getPositionOfCard(false, ownedByPlayer, attacker);
        Log.e("EventAttackOnField", Integer.toString(pos));
        final AbstractCard attackedCard = BattleContext
                .getInstance()
                .getCardByPosition(false, !ownedByPlayer, pos);
        if (attackedCard != null) {
            Log.e("EventAttackOnField", "Found enemy card");
            attackedCard.subtractFromHealth(attacker.getAttackDamage());
        } else {
            //TODO: Do enemy player damaging.
        }
    }
}
