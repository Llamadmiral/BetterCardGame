package com.lamadmiralis.bettercardgame.utility;

import android.util.Log;

import com.lamadmiralis.bettercardgame.R;
import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.events.impl.NextTurnEvent;
import com.lamadmiralis.bettercardgame.objects.TimedText;
import com.lamadmiralis.bettercardgame.objects.card.AbstractCard;
import com.lamadmiralis.bettercardgame.utility.contestant.Contestant;
import com.lamadmiralis.bettercardgame.utility.contestant.EnemyAI;

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
    private EnemyAI enemyAI = new EnemyAI(enemy);

    private boolean isPlayerTurn = true;

    public static BattleContext getInstance() {
        if (instance == null) {
            instance = new BattleContext();
        }
        return instance;
    }

    public void nextTurn() {
        if (isPlayerTurn) {
            new TimedText("ENEMY'S TURN!", 2);
            enemyAI.startTurn();
        } else {
            new TimedText("YOUR TURN!", 2);
            player.initializeTurn();
        }
        isPlayerTurn = !isPlayerTurn;
    }

    public void endTurn() {
        if (isPlayerTurn) {
            player.finalizeTurn();
            EventHandler.getInstance().addFinalEvent(new NextTurnEvent());
            EventHandler.getInstance().dispatch();
        }
    }


    public void activateCard(final AbstractCard card) {
        if (!card.isOwnedByPlayer() || isPlayerTurn) {
            (card.isOwnedByPlayer() ? player : enemy).activateCard(card);
        } else {
            Log.i(Tag.MT, "Can't activate card");
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

    public Contestant getEnemy() {
        return enemy;
    }

    public EnemyAI getEnemyAI() {
        return this.enemyAI;
    }
}
