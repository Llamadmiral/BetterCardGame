package com.lamadmiralis.bettercardgame.utility;

import com.lamadmiralis.bettercardgame.R;
import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.events.impl.NextTurnEvent;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.contestant.Contestant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maczaka
 */
public class BattleContext {

    public static final int CARD_HEIGHT = ImageHolder.getResById(R.drawable.img_placeholder).getHeight();
    public static final int ENEMY_HAND_HEIGHT = 60;
    public static final int SMALL_CARD_WIDTH = ImageHolder.getResizedCardBack().getWidth();
    public static final int SMALL_CARD_HEIGHT = ImageHolder.getResizedCardBack().getHeight();
    public static final float PLAYER_HAND_HEIGHT = InterfaceContext.HEIGHT - (SMALL_CARD_HEIGHT + 50);
    public static final int BASE_OFFSET = 30;
    public static final int PADDING = 15;
    private static BattleContext instance = null;

    private Contestant player = new Contestant(true);
    private Contestant enemy = new Contestant(false);

    private boolean wasPlayersTurn = false;

    public static BattleContext getInstance() {
        if (instance == null) {
            instance = new BattleContext();
        }
        return instance;
    }

    public void nextTurn() {
        if (wasPlayersTurn) {
            enemy.initializeTurn();
            enemyTurn();
        } else {
            player.initializeTurn();
        }
    }

    public void endTurn() {
        wasPlayersTurn = !wasPlayersTurn;
        if (wasPlayersTurn) {
            player.finalizeTurn();
        } else {
            enemy.finalizeTurn();
        }
        EventHandler.getInstance().addFinalEvent(new NextTurnEvent(), 250);
        EventHandler.getInstance().dispatch();
    }

    private void enemyTurn() {
        final List<AbstractCard> cardsToActivate = new ArrayList<>();
        for (final AbstractCard card : enemy.getHand().getCards().values()) {
            if (card.getCurrentWaitTime() == 0) {
                cardsToActivate.add(card); //to avoid concurrentModif. exception
            }
        }
        for (final AbstractCard card : cardsToActivate) {
            enemy.activateCard(card);
        }
        endTurn();
    }


    public void activateCard(final AbstractCard card) {
        if (!card.isOwnedByPlayer() || !wasPlayersTurn) {
            (card.isOwnedByPlayer() ? player : enemy).activateCard(card);
        }
    }

    public float[] getNextPositionForDrawnCard(final boolean isDrawnByPlayer) {
        return (isDrawnByPlayer ? player : enemy).getNextPositionForDrawnCard();
    }

    public float[] getNextPositionForPlayedCard(final boolean isPlayedByPlayer) {
        return (isPlayedByPlayer ? player : enemy).getNextPositionForPlayedCard();
    }

    public void drawCard(final boolean isDrawnByPlayer) {
        (isDrawnByPlayer ? player : enemy).drawCard();
    }

    public int getPositionOfCard(final boolean inHand, final boolean fromPlayer, final AbstractCard card) {
        final Contestant contestant = fromPlayer ? player : enemy;
        return inHand ? contestant.getHand().getPositionOfCard(card) : contestant.getField().getPositionOfCard(card);
    }

    public AbstractCard getCardByPosition(final boolean inHand, final boolean fromPlayer, final int index) {
        final Contestant contestant = fromPlayer ? player : enemy;
        return inHand ? contestant.getHand().getCards().get(index) : contestant.getField().getCards().get(index);
    }


    public void reset() {
        enemy.reset();
        player.reset();
    }

    public Contestant getContestant(final boolean isPlayer) {
        return isPlayer ? player : enemy;
    }
}
