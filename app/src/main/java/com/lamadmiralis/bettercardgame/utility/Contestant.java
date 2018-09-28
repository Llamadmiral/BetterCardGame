package com.lamadmiralis.bettercardgame.utility;

import com.lamadmiralis.bettercardgame.animation.impl.MovementPlayCard;
import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.lamadmiralis.bettercardgame.utility.BattleContext.BASE_OFFSET;
import static com.lamadmiralis.bettercardgame.utility.BattleContext.ENEMY_HAND_HEIGHT;
import static com.lamadmiralis.bettercardgame.utility.BattleContext.PADDING;
import static com.lamadmiralis.bettercardgame.utility.BattleContext.PLAYER_HAND_HEIGHT;
import static com.lamadmiralis.bettercardgame.utility.BattleContext.SMALL_CARD_HEIGHT;
import static com.lamadmiralis.bettercardgame.utility.BattleContext.SMALL_CARD_WIDTH;

public class Contestant {
    private Hand hand = new Hand(this);
    private Field field = new Field(this);
    private boolean isPlayer;


    public Contestant(final boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

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

    void finalizeTurn() {
        hand.finalizeTurn();
        field.finalizeTurn();
        EventHandler.dispatch();
    }

    void activateCard(final AbstractCard card) {
        card.setMovement(new MovementPlayCard(card));
        if (card.isInHand()) {
            hand.removeCard(card);
            hand.incPlayedCards();
            field.addCard(card);
        } else {
            field.removeCard(card);
            hand.addCard(card);
        }
    }

    public float[] getNextPositionForDrawnCard() {
        final int pos = hand.getFirstEmptySpace();
        //Log.i(Tag.MT, "getNextPositionForDrawnCard: " + pos);
        return getCoordinatesOfNthCardInHand(pos);
    }

    public float[] getNextPositionForPlayedCard() {
        final int pos = field.getFirstEmptySpace();
        return getCoordinatesOfNthCardInField(pos);
    }

    public float[] getCoordinatesOfNthCardInField(final int index) {
        int x = ((SMALL_CARD_WIDTH + PADDING) * index) + BASE_OFFSET;
        float y = isPlayer
                ? PLAYER_HAND_HEIGHT - (SMALL_CARD_HEIGHT * 1.4F)
                : ENEMY_HAND_HEIGHT + (SMALL_CARD_HEIGHT * 1.4F);
        return new float[]{x, y};
    }

    public float[] getCoordinatesOfNthCardInHand(final int index) {
        int x = ((SMALL_CARD_WIDTH + PADDING) * index) + BASE_OFFSET;
        float y = isPlayer ? PLAYER_HAND_HEIGHT : ENEMY_HAND_HEIGHT;
        return new float[]{x, y};
    }

    public void drawCard(final AbstractCard card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public Field getField() {
        return field;
    }

    public void reset() {
        hand.reset();
        field.reset();
    }
}
