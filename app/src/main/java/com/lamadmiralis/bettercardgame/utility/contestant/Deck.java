package com.lamadmiralis.bettercardgame.utility.contestant;

import android.util.Log;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.impl.TestCard_01;
import com.lamadmiralis.bettercardgame.objects.card.impl.TestCard_02;
import com.lamadmiralis.bettercardgame.objects.card.impl.TestCard_03;
import com.lamadmiralis.bettercardgame.objects.card.impl.TestCard_04;
import com.lamadmiralis.bettercardgame.objects.card.impl.TestCard_05;
import com.lamadmiralis.bettercardgame.utility.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static final Random RAND = new Random();
    private List<Class<? extends AbstractCard>> cardList = new ArrayList<>();

    public void test() {
        addToCardList(TestCard_01.class, 4);
        addToCardList(TestCard_02.class, 3);
        addToCardList(TestCard_03.class, 2);
        addToCardList(TestCard_04.class, 2);
        addToCardList(TestCard_05.class, 2);
    }

    private void addToCardList(final Class<? extends AbstractCard> cardType, final int amount) {
        for (int i = 0; i < amount; i++) {
            cardList.add(cardType);
        }
    }

    public AbstractCard drawCard() {
        AbstractCard card = null;
        try {
            card = cardList.isEmpty()
                    ? null
                    : cardList.remove(RAND.nextInt(cardList.size())).newInstance();
        } catch (final Exception e) {
            Log.e(Tag.MT, Tag.getFormattedExceptionMessage(e));
        }
        return card;
    }
}
