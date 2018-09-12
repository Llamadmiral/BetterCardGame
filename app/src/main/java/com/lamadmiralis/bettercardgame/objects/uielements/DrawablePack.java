package com.lamadmiralis.bettercardgame.objects.uielements;

import com.lamadmiralis.bettercardgame.objects.AbstractObject;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;
import com.lamadmiralis.bettercardgame.utility.BattleContext;
import com.lamadmiralis.bettercardgame.utility.ImageHolder;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;

/**
 * @author maczaka
 */
public class DrawablePack extends AbstractObject {

    public DrawablePack() {
        this.fullCardImage = ImageHolder.getResByIdAndResize(ProjectConstants.DEFAULT_CARD_BACK, 3);
        this.setX(InterfaceContext.WIDTH - this.fullCardImage.getWidth() - 50);
        this.setY(InterfaceContext.HEIGHT - this.fullCardImage.getHeight() - 50);
    }

    @Override
    public void click() {
        BattleContext.getInstance().drawCard(true);
    }
}
