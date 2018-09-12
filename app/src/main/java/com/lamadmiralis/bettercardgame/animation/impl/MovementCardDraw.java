package com.lamadmiralis.bettercardgame.animation.impl;

import com.lamadmiralis.bettercardgame.animation.Movement;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.BattleContext;

import java.util.ArrayList;

/**
 * @author maczaka
 */
public class MovementCardDraw extends Movement {
    public MovementCardDraw(final AbstractCard card) {
        super(card);
    }

    /**
     * Start out of screen, go up to the owner hand's height, then go to the next position available in hand.
     */
    @Override
    protected void initStepList() {
        final AbstractCard card = (AbstractCard) clickable;
        stepList = new ArrayList<>();
        stepList.addAll(addStepBetweenMultiplePoints(27,
                new float[]{clickable.getX(), clickable.getY()},
                new float[]{clickable.getX(),
                        card.isOwnedByPlayer() ? BattleContext.PLAYER_HAND_HEIGHT : BattleContext.ENEMY_HAND_HEIGHT},
                BattleContext.getInstance().getNextPositionForDrawnCard(card.isOwnedByPlayer())));
    }
}
