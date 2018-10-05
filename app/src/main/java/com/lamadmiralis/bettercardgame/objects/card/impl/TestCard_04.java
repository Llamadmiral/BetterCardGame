package com.lamadmiralis.bettercardgame.objects.card.impl;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

public class TestCard_04 extends AbstractCard {
    public TestCard_04() {
        super(ProjectConstants.TESTCARD_NAME_04);
        setInitialAttackDamage(4);
        setInitialHealthPoints(1);
        setAttackDamage(4);
        setHealthPoints(1);
    }
}
