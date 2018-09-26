package com.lamadmiralis.bettercardgame.utility;

import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Field {

    private final Contestant owner;
    private int removedCards = 0;
    private Map<Integer, AbstractCard> field = new TreeMap<>();

    public Field(final Contestant contestant) {
        this.owner = contestant;
    }

    public void finalizeTurn() {
        final List<Integer> cardsToRemove = new ArrayList<>();
        for (final Map.Entry<Integer, AbstractCard> entry : field.entrySet()) {
            final AbstractCard card = entry.getValue();
            card.setAlreadyPlayed(true);
            card.activateSpecialEffect();
            card.attack(entry.getKey());
            if (card.getHealthPoints() <= 0) {
                cardsToRemove.add(entry.getKey());
            }
        }
        for (final Integer index : cardsToRemove) {
            field.remove(index);
            removedCards++;
        }
        if (removedCards > 0) {
            Contestant.collapseMap(field, field.keySet());
            removedCards = 0;
        }
    }

    public Map<Integer, AbstractCard> getCards() {
        return field;
    }

    public void addCard(final AbstractCard card) {
        if (card.getPreviousPosInField() != -1) {
            field.put(card.getPreviousPosInField(), card);
        } else {
            field.put(field.size() + 1, card);
            card.setPreviousPosInField(field.size() + 1);
        }

    }


    public void removeCard(final AbstractCard card) {
        field.remove(card.getCurrentPosInHand());
    }

    public int getPositionOfCard(final AbstractCard card) {
        int pos = -1;
        for (final Map.Entry<Integer, AbstractCard> entry : field.entrySet()) {
            if (entry.getValue().getId() == card.getId()) {
                pos = entry.getKey();
                break;
            }
        }
        return pos;
    }

    public void reset() {
        field.clear();
        removedCards = 0;
    }
}
