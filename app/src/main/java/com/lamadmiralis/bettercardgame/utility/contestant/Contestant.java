package com.lamadmiralis.bettercardgame.utility.contestant;

import android.util.Log;

import com.lamadmiralis.bettercardgame.animation.impl.MovementCardDraw;
import com.lamadmiralis.bettercardgame.animation.impl.MovementPlayCard;
import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.uielements.HealthBar;
import com.lamadmiralis.bettercardgame.utility.BattleContext;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;
import com.lamadmiralis.bettercardgame.utility.Tag;

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
    private int currentHealth;
    private int maxHealth;
    private HealthBar healthBar;
    private Deck deck;


    public Contestant(final boolean isPlayer) {
        this.isPlayer = isPlayer;
        this.currentHealth = 20;
        this.maxHealth = 20;
        init();
    }


    public void finalizeTurn() {
        hand.finalizeTurn();
        field.finalizeTurn();
    }

    public void activateCard(final AbstractCard card) {
        card.setMovement(new MovementPlayCard(card));
        if (card.isInHand()) {
            hand.removeCard(card);
            hand.incPlayedCards();
            field.addCard(card);
            card.setInHand(false);
        } else {
            field.removeCard(card);
            hand.addCard(card);
            card.setInHand(true);
        }
    }

    public float[] getNextPositionForDrawnCard() {
        final int pos = hand.getFirstEmptySpace();
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

    public AbstractCard drawCard() {
        final AbstractCard card = this.deck.drawCard();
        if (card != null) {
            card.setOwnedByPlayer(this.isPlayer);
            card.setX(InterfaceContext.WIDTH * (7F / 12F));
            card.setY((this.isPlayer
                    ? InterfaceContext.HEIGHT
                    : -2 * BattleContext.CARD_HEIGHT)
                    + BattleContext.CARD_HEIGHT);
            card.setMovement(new MovementCardDraw(card));
            hand.addCard(card);
        }
        return card;
    }

    public Hand getHand() {
        return hand;
    }

    public Field getField() {
        return field;
    }

    public void reset() {
        Log.i(Tag.MT, "Reset called!");
        hand.reset();
        field.reset();
        init();
    }

    private void init() {
        this.healthBar = new HealthBar(this);
        this.healthBar.setX(300);
        this.healthBar.setY(this.isPlayer ? 600 : 300);
        this.deck = new Deck();
        this.deck.test();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(final int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(final int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void addToHealth(final int amount) {
        this.currentHealth += amount;
        this.healthBar.createImage();
    }

    public Deck getDeck() {
        return deck;
    }

    public void initializeTurn() {
        hand.initializeTurn();
    }
}
