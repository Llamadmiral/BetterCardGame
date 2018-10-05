package com.lamadmiralis.bettercardgame.objects.card.impl;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

public class TestCard_05 extends AbstractCard {

    public TestCard_05() {
        super(ProjectConstants.TESTCARD_NAME_05);
        setInitialAttackDamage(3);
        setInitialHealthPoints(5);
        setAttackDamage(3);
        setHealthPoints(5);
        setInitialWaitTime(3);
        setCurrentWaitTime(3);
    }
}
