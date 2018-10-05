package com.lamadmiralis.bettercardgame.objects.card.impl;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

public class TestCard_05 extends AbstractCard {

    public TestCard_05() {
        super(ProjectConstants.TESTCARD_NAME_05);
        setInitialAttackDamage(2);
        setInitialHealthPoints(4);
        setAttackDamage(2);
        setHealthPoints(4);
    }
}
