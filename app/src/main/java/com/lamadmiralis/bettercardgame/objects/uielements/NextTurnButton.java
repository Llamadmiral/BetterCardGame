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
        this.fullCardImage = ImageHolder.getResByIdAndResize(ProjectConstants.IMG_NEXT_TURN_BUTTON, 3);
        this.setX(InterfaceContext.WIDTH - this.fullCardImage.getWidth() - 300);
        this.setY(InterfaceContext.HEIGHT - this.fullCardImage.getHeight() - 100);
    }

    @Override
    public void click() {
        BattleContext.getInstance().nextTurn();
    }
}
