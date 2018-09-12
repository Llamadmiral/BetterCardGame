package com.lamadmiralis.bettercardgame.animation.impl;

import com.lamadmiralis.bettercardgame.animation.Movement;
import com.lamadmiralis.bettercardgame.animation.Step;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.ArrayList;

/**
 * @author maczaka
 */
public class MovementCardAttack extends Movement {
    public MovementCardAttack(final AbstractCard card) {
        super(card);
    }

    @Override
    protected void initStepList() {
        stepList = new ArrayList<>();
        final AbstractCard card = (AbstractCard) clickable;
        if (card.isOwnedByPlayer()) {
            stepList.add(new Step(new float[]{0, -40}, 4));
            stepList.add(new Step(new float[]{0, 20}, 8));
        } else {
            stepList.add(new Step(new float[]{0, 40}, 4));
            stepList.add(new Step(new float[]{0, -20}, 8));
        }
    }
}
