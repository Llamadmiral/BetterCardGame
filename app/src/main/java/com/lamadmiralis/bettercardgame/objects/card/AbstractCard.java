package com.lamadmiralis.bettercardgame.objects.card;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.lamadmiralis.bettercardgame.animation.impl.MovementCardAttack;
import com.lamadmiralis.bettercardgame.animation.impl.MovementPlayCard;
import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.events.impl.EventAttackOnField;
import com.lamadmiralis.bettercardgame.events.impl.EventMovementEvent;
import com.lamadmiralis.bettercardgame.objects.AbstractObject;
import com.lamadmiralis.bettercardgame.utility.BattleContext;
import com.lamadmiralis.bettercardgame.utility.ImageHolder;

/**
 * @author maczaka
 */
public class AbstractCard extends AbstractObject {

    private String name;
    private boolean inHand = true;
    private boolean ownedByPlayer = true;
    private Bitmap cardBack = ImageHolder.getResizedCardBack();
    private Integer initialAttackDamage = 0;
    private Integer initialHealthPoints = 0;
    private Integer attackDamage = 0;
    private Integer healthPoints = 0;
    private boolean canAttack = true;

    public AbstractCard(final String name) {
        super();
        this.name = name;
        this.fullCardImage = ImageHolder.getFullCardImageByName(name);
        this.smallImage = ImageHolder.getSmallCardImageByName(name);
    }

    @Override
    public void click() {
        this.setMovement(new MovementPlayCard(this));
        BattleContext.getInstance().activateCard(this);
        inHand = !inHand;
    }

    public Bitmap getCurrentImage() {
        Bitmap bitmap;
        if (inHand) {
            if (ownedByPlayer) {
                bitmap = smallImage;
            } else {
                bitmap = cardBack;
            }
        } else {
            bitmap = fullCardImage;
        }
        return bitmap;
    }

    @Override
    public void render(final Canvas canvas) {
        canvas.drawBitmap(getCurrentImage(), getX(), getY(), null);
        if (!inHand || ownedByPlayer) {
            canvas.drawText(attackDamage.toString(),
                    getX() + (70 / 4.5F),
                    getY() + (480 / 4.5F),
                    ImageHolder.getRecolouredNumberText(attackDamage, initialAttackDamage)
            );
            canvas.drawText(healthPoints.toString(),
                    getX() + (270 / 4.5F),
                    getY() + (480 / 4.5F),
                    ImageHolder.getRecolouredNumberText(healthPoints, initialHealthPoints)
            );
        }
    }


    public boolean isInHand() {
        return inHand;
    }

    public void setInHand(final boolean inHand) {
        this.inHand = inHand;
    }

    public boolean isOwnedByPlayer() {
        return ownedByPlayer;
    }

    public void setOwnedByPlayer(final boolean ownedByPlayer) {
        this.ownedByPlayer = ownedByPlayer;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(final int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void subtractFromHealth(final int healthPoints) {
        this.healthPoints -= healthPoints;
    }

    public Integer getInitialAttackDamage() {
        return initialAttackDamage;
    }

    public void setInitialAttackDamage(final Integer initialAttackDamage) {
        this.initialAttackDamage = initialAttackDamage;
    }

    public Integer getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public void setInitialHealthPoints(final Integer initialHealthPoints) {
        this.initialHealthPoints = initialHealthPoints;
    }

    public void attack(final int position) {
        if (this.canAttack()) {
            final EventAttackOnField attackOnBattlefield = new EventAttackOnField(480);
            attackOnBattlefield.setAttacker(this);
            EventHandler.addEvent(attackOnBattlefield);
            EventHandler.addEvent(new EventMovementEvent(position * 500, this, new MovementCardAttack(this)));
        }
    }

    private boolean canAttack() {
        return canAttack;
    }
}
