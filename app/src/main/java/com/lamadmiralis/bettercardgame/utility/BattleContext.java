package com.lamadmiralis.bettercardgame.utility;

import com.lamadmiralis.bettercardgame.R;
import com.lamadmiralis.bettercardgame.animation.impl.MovementCardDraw;
import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.objects.card.impl.TestCard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author maczaka
 */
public class BattleContext {

    public static final int CARD_HEIGHT = ImageHolder.getResById(R.drawable.img_placeholder).getHeight();
    public static final int ENEMY_HAND_HEIGHT = 60;
    private static final int CARD_WIDTH = ImageHolder.getResById(R.drawable.img_placeholder).getWidth();
    private static final int SMALL_CARD_WIDTH = ImageHolder.getResizedCardBack().getWidth();
    private static final int SMALL_CARD_HEIGHT = ImageHolder.getResizedCardBack().getHeight();
    public static final float PLAYER_HAND_HEIGHT = InterfaceContext.HEIGHT - ((SMALL_CARD_HEIGHT + 50));
    private static final int BASE_OFFSET = 30;
    private static final int PADDING = 15;
    private static BattleContext instance = null;
    private List<AbstractCard> playerHand = new ArrayList<>();
    private List<AbstractCard> enemyHand = new ArrayList<>();
    private List<AbstractCard> playerField = new ArrayList<>();
    private List<AbstractCard> enemyField = new ArrayList<>();
    private boolean isPlayersTrun = true;

    public static BattleContext getInstance() {
        if (instance == null) {
            instance = new BattleContext();
        }
        return instance;
    }

    public void nextTurn() {
        int i = 0;
        for (final AbstractCard card : (isPlayersTrun ? playerField : enemyField)) {
            if (!card.isInHand()) {
                card.activateSpecialEffect();
                card.attack(++i);
            }
        }
        EventHandler.dispatch();
        for (Iterator<AbstractCard> iterator = (isPlayersTrun ? enemyField : playerField).iterator();
             iterator.hasNext(); ) {
            final AbstractCard card = iterator.next();
            if (card.getHealthPoints() <= 0) {
                iterator.remove();
                InterfaceContext.getInstance().getObjectsToDraw().remove(card);
            }
        }
        isPlayersTrun = !isPlayersTrun;
    }

    public void activateCard(final AbstractCard card) {
        final List<AbstractCard> field = card.isOwnedByPlayer() ? playerField : enemyField;
        final List<AbstractCard> hand = card.isOwnedByPlayer() ? playerHand : enemyHand;
        if (card.isInHand()) {
            hand.remove(card);
            field.add(card);
        } else {
            field.remove(card);
            hand.add(card);
        }
    }

    public float[] getNextPositionForDrawnCard(final boolean isDrawnByPlayer) {
        int x = SMALL_CARD_WIDTH + PADDING;
        float y;
        if (isDrawnByPlayer) {
            x *= playerHand.size();
            y = PLAYER_HAND_HEIGHT;
        } else {
            x *= enemyHand.size();
            y = ENEMY_HAND_HEIGHT;
        }
        x += BASE_OFFSET;
        return new float[]{x, y};
    }

    public float[] getNextPositionForPlayedCard(final boolean isPlayedByPlayer) {
        int x = SMALL_CARD_WIDTH + PADDING;
        float y;
        if (isPlayedByPlayer) {
            x *= playerField.size();
            y = PLAYER_HAND_HEIGHT - (SMALL_CARD_HEIGHT * 1.4F);
        } else {
            x *= enemyField.size();
            y = ENEMY_HAND_HEIGHT + (SMALL_CARD_HEIGHT * 1.4F);
        }
        x += BASE_OFFSET;
        return new float[]{x, y};
    }

    public void drawCard(final boolean isDrawnByPlayer) {
        final AbstractCard card = new TestCard();
        card.setOwnedByPlayer(isDrawnByPlayer);
        card.setX(InterfaceContext.WIDTH * (7F / 12F));
        card.setY((isDrawnByPlayer ? InterfaceContext.HEIGHT : -2 * CARD_HEIGHT) + CARD_HEIGHT);
        card.setMovement(new MovementCardDraw(card));
        if (isDrawnByPlayer) {
            playerHand.add(card);
        } else {
            enemyHand.add(card);
        }
        InterfaceContext.getInstance().addToClickable(card);
    }

    public int getPositionOfCard(final boolean inHand, final boolean fromPlayer, final AbstractCard card) {
        return inHand ? (fromPlayer ? playerHand.indexOf(card) : enemyHand.indexOf(card))
                : (fromPlayer ? playerField.indexOf(card) : enemyField.indexOf(card));
    }

    public AbstractCard getCardByPosition(final boolean inHand, final boolean fromPlayer, final int index) {
        return inHand ? (fromPlayer ? playerHand.get(index) : enemyHand.get(index))
                : (fromPlayer ? playerField.get(index) : enemyField.get(index));
    }


    public void reset() {
        playerHand.clear();
        playerField.clear();
        enemyHand.clear();
        enemyField.clear();
    }

}
