package com.lamadmiralis.bettercardgame.utility;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

abstract class AbstractCardHolder {
    protected Map<Integer, AbstractCard> cards = new TreeMap<>();

    /**
     * Normalizes indexes in hand. E.g.: 1 3 7 -> 1 2 3
     */
    static void collapseMap(final Map<Integer, AbstractCard> map, final Set<Integer> indexes) {
        int i = 0;
        for (final Integer index : new HashSet<>(indexes)) {
            map.put(i, map.remove(index));
            i++;
        }
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
