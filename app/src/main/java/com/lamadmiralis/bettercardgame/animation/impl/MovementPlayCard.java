package com.lamadmiralis.bettercardgame.animation.impl;

import com.lamadmiralis.bettercardgame.animation.Movement;
import com.lamadmiralis.bettercardgame.animation.MovementHelper;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.BattleContext;

import java.util.ArrayList;

/**
 * @author maczaka
 */
public class MovementPlayCard extends Movement {

    public MovementPlayCard(final AbstractCard card) {
        super(card);
    }

    @Override
    protected void initStepList() {
        final AbstractCard card = (AbstractCard) clickable;
        stepList = new ArrayList<>();
        float[] finalPosition;
        if (card.isInHand()) {
            finalPosition = BattleContext.getInstance().getNextPositionForPlayedCard(card.isOwnedByPlayer());
            card.saveCurrentCoordinates();
        } else {
            finalPosition = card.getPreviousCoordinates();
        }
        stepList.add(MovementHelper.addStepBetweenTwoPoints(new float[]{card.getX(), card.getY()},
                finalPosition,
                5));
    }
}
