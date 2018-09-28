package com.lamadmiralis.bettercardgame.utility;

import com.lamadmiralis.bettercardgame.animation.impl.MovementPlayCard;
import com.lamadmiralis.bettercardgame.animation.impl.MovementReArrange;
import com.lamadmiralis.bettercardgame.events.InstantEvent;
import com.lamadmiralis.bettercardgame.events.impl.EventMovementEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.Map;
import java.util.TreeMap;

public class Hand extends AbstractCardHolder{

    private Contestant owner;
    /**
     * Indexed from 1 on purpose.
     */
    private int playedCards = 0;


    public Hand(final Contestant contestant) {
        this.owner = contestant;
    }

    public void finalizeTurn() {
        if (playedCards > 0) {
            Contestant.collapseMap(cards, cards.keySet());
            for (final Map.Entry<Integer, AbstractCard> entry : cards.entrySet()) {
                final float nextPosition[] = owner.getCoordinatesOfNthCardInHand(entry.getKey());
                new InstantEvent<>(new EventMovementEvent(0, entry.getValue(), new MovementReArrange(entry.getValue(), nextPosition))).fire();
            }
            playedCards = 0;
        }
    }


    public void incPlayedCards() {
        playedCards++;
    }

    public void decPlayedCards() {
        playedCards--;
    }

    @Override
    public void addCard(final AbstractCard card) {
        if (card.getPreviousPosInHand() != -1 && cards.get(card.getPreviousPosInHand()) == null) {
            cards.put(card.getPreviousPosInHand(), card);
            playedCards--;
        } else {
            final int newPosition = cards.size();
            cards.put(newPosition, card);
            card.setCurrentPosInHand(newPosition);
        }
    }


    @Override
    public void removeCard(final AbstractCard card) {
        card.setPreviousPosInHand(card.getCurrentPosInHand());
        cards.remove(card.getCurrentPosInHand());
    }

    public void reset() {
        cards.clear();
        playedCards = 0;
    }
}
