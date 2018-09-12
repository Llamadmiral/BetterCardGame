package com.lamadmiralis.bettercardgame.objects.card.impl;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

/**
 * @author maczaka
 */
public class TestCard extends AbstractCard {
    public TestCard() {
        super(ProjectConstants.TEST_CARD_NAME);
        setInitialAttackDamage(1);
        setInitialHealthPoints(5);
        setAttackDamage(2);
        setHealthPoints(3);
    }
}
