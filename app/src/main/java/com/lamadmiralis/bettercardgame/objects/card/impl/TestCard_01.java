package com.lamadmiralis.bettercardgame.objects.card.impl;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

/**
 * @author maczaka
 */
public class TestCard_01 extends AbstractCard {
    public TestCard_01() {
        super(ProjectConstants.TESTCARD_NAME_01);
        setInitialAttackDamage(1);
        setInitialHealthPoints(6);
        setAttackDamage(1);
        setHealthPoints(6);
        setInitialWaitTime(2);
        setCurrentWaitTime(2);
    }
}
