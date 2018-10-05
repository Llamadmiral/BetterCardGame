package com.lamadmiralis.bettercardgame.utility.contestant;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Field extends AbstractCardHolder {

    private final Contestant owner;
    private int removedCards = 0;

    public Field(final Contestant contestant) {
        this.owner = contestant;
    }

    public void finalizeTurn() {
        final List<Integer> cardsToRemove = new ArrayList<>();
        for (final Map.Entry<Integer, AbstractCard> entry : this.cards.entrySet()) {
            final AbstractCard card = entry.getValue();
            card.setAlreadyPlayed(true);
            card.activateSpecialEffect();
            card.attack(entry.getKey());
            if (card.getHealthPoints() <= 0) {
                cardsToRemove.add(entry.getKey());
            }
        }
        for (final Integer index : cardsToRemove) {
            this.cards.remove(index);
            removedCards++;
        }
        if (removedCards > 0) {
            collapseMap(this.cards, this.cards.keySet());
            removedCards = 0;
        }
    }

    public void addCard(final AbstractCard card) {
        final int pos = this.getFirstEmptySpace();
        this.cards.put(pos, card);
    }


    public void removeCard(final AbstractCard card) {
        int indexToRemove = -1;
        for (final Map.Entry<Integer, AbstractCard> entry : cards.entrySet()) {
            if (entry.getValue().getId() == card.getId()) {
                indexToRemove = entry.getKey();
                break;
            }
        }
        this.cards.remove(indexToRemove);
    }

    public void reset() {
        this.cards.clear();
        removedCards = 0;
    }
}
