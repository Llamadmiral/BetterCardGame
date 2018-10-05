package com.lamadmiralis.bettercardgame.objects.card.impl;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;

public class TestCard_02 extends AbstractCard {
    public TestCard_02() {
        super(ProjectConstants.TESTCARD_NAME_02);
        setInitialAttackDamage(2);
        setInitialHealthPoints(3);
        setAttackDamage(2);
        setHealthPoints(3);
    }
}
