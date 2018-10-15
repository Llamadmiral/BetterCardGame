package com.lamadmiralis.bettercardgame.utility.contestant;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractCardHolder {
    protected Map<Integer, AbstractCard> cards = new LinkedHashMap<>();

    /**
     * Normalizes indexes in a map. E.g.: 1 3 7 -> 1 2 3.
     * Use TreeMap, or order will be lost.
     */
    void collapseCards() {
        final Map<Integer, AbstractCard> newOrder = new LinkedHashMap<>();
        int i = 0;
        for (final Map.Entry<Integer, AbstractCard> card : cards.entrySet()) {
            newOrder.put(i, card.getValue());
            i++;
        }
        cards = newOrder;
    }

    public int getFirstEmptySpace() {
        int index = 0;
        if (!cards.isEmpty()) {
            boolean foundAny = false;
            for (int i = 0; i < cards.size(); i++) {
                if (!cards.containsKey(i)) {
                    index = i;
                    foundAny = true;
                    break;
                }
            }
            if (!foundAny) {
                index = cards.size();
            }
        }
        return index;
    }

    public Map<Integer, AbstractCard> getCards() {
        return this.cards;
    }

    public int getPositionOfCard(final AbstractCard card) {
        int pos = -1;
        for (final Map.Entry<Integer, AbstractCard> entry : cards.entrySet()) {
            if (entry.getValue().getId() == card.getId()) {
                pos = entry.getKey();
                break;
            }
        }
        return pos;
    }

    public abstract void addCard(AbstractCard card);

    public abstract void removeCard(AbstractCard card);
}
