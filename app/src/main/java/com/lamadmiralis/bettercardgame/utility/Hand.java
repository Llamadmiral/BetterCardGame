package com.lamadmiralis.bettercardgame.utility;

import com.lamadmiralis.bettercardgame.animation.impl.MovementReArrange;
import com.lamadmiralis.bettercardgame.events.InstantEvent;
import com.lamadmiralis.bettercardgame.events.impl.EventMovementEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.Map;
import java.util.TreeMap;

public class Hand {

    private Contestant owner;
    /**
     * Indexed from 1 on purpose.
     */
    private Map<Integer, AbstractCard> hand = new TreeMap<>();
    private int playedCards = 0;


    public Hand(final Contestant contestant) {
        this.owner = contestant;
    }

    public void finalizeTurn() {
        if (playedCards > 0) {
            Contestant.collapseMap(hand, hand.keySet());
            for (final Map.Entry<Integer, AbstractCard> entry : hand.entrySet()) {
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

    public void addCard(final AbstractCard card) {
        if (card.getPreviousPosInHand() != -1) {
            hand.put(card.getPreviousPosInHand(), card);
            playedCards--;
        } else {
            final int newPosition = hand.size();
            hand.put(newPosition, card);
            card.setCurrentPosInHand(newPosition);
        }
    }


    public void removeCard(final AbstractCard card) {
        card.setPreviousPosInHand(card.getCurrentPosInHand());
        hand.remove(card.getCurrentPosInHand());
    }

    public Map<Integer, AbstractCard> getCards() {
        return hand;
    }

    public int getPositionOfCard(final AbstractCard card) {
        int pos = -1;
        for (final Map.Entry<Integer, AbstractCard> entry : hand.entrySet()) {
            if (entry.getValue().getId() == card.getId()) {
                pos = entry.getKey();
                break;
            }
        }
        return pos;
    }

    public void reset() {
        hand.clear();
        playedCards = 0;
    }
}
