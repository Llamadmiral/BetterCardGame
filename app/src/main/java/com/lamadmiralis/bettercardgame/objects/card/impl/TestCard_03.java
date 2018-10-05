package com.lamadmiralis.bettercardgame.objects.card.impl;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

public class TestCard_03 extends AbstractCard {
    public TestCard_03() {
        super(ProjectConstants.TESTCARD_NAME_03);
        setInitialAttackDamage(3);
        setInitialHealthPoints(2);
        setAttackDamage(3);
        setHealthPoints(2);
    }
}
