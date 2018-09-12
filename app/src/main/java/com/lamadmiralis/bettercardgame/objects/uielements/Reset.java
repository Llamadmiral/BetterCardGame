package com.lamadmiralis.bettercardgame.objects.uielements;

import com.lamadmiralis.bettercardgame.objects.AbstractObject;
import com.lamadmiralis.bettercardgame.objects.card.ProjectConstants;
import com.lamadmiralis.bettercardgame.utility.ImageHolder;
import com.lamadmiralis.bettercardgame.utility.InterfaceContext;

/**
 * @author maczaka
 */
public class Reset extends AbstractObject {

    public Reset() {
        this.fullCardImage = ImageHolder.getResByIdAndResize(ProjectConstants.IMG_RESET, 3);
        this.setX(1100);
        this.setY(100);
    }

    @Override
    public void click() {
        InterfaceContext.getInstance().reset();
    }
}
