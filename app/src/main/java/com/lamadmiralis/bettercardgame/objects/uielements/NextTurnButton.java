package com.lamadmiralis.bettercardgame.objects.uielements;

import com.lamadmiralis.bettercardgame.objects.AbstractObject;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;
import com.lamadmiralis.bettercardgame.utility.BattleContext;
import com.lamadmiralis.bettercardgame.utility.ImageHolder;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;

/**
 * @author maczaka
 */
public class NextTurnButton extends AbstractObject {

    public NextTurnButton() {
        super();
        this.fullImage = ImageHolder.getResByIdAndResize(ProjectConstants.IMG_NEXT_TURN_BUTTON, 3);
        this.setX(InterfaceContext.WIDTH - this.fullImage.getWidth() - 300);
        this.setY(InterfaceContext.HEIGHT - this.fullImage.getHeight() - 100);
    }

    @Override
    public void click() {
        BattleContext.getInstance().nextTurn();
    }
}
