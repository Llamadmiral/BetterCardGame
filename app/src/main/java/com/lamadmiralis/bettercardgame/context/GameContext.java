package com.lamadmiralis.bettercardgame.context;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maczaka
 */
public class GameContext {
    private static final List<AbstractCard> CARDS = new ArrayList<>();

    public static List<AbstractCard> getCards() {
        return CARDS;
    }


}
