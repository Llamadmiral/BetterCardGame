package com.lamadmiralis.bettercardgame.objects.card;

import com.lamadmiralis.bettercardgame.R;

/**
 * @author maczaka
 */
public final class ProjectConstants {

    public static final int DEFAULT_BANNER = R.drawable.card_banner;
    public static final int DEFAULT_FRAME = R.drawable.card_frame;
    public static final int DEFAULT_FRONT_BG = R.drawable.card_front_bg;
    public static final int DEFAULT_CARD_BACK = R.drawable.card_back_bg;
    public static final int PLACEHOLDER_IMG = R.drawable.img_placeholder;
    public static final int IMG_RESET = R.drawable.reset;
    public static final int IMG_NEXT_TURN_BUTTON = R.drawable.next_turn_button;

    //Card names
    public static final String TESTCARD_NAME_01 = "Shieldbearer";
    public static final String TESTCARD_NAME_02 = "Footman";
    public static final String TESTCARD_NAME_03 = "Archer";
    public static final String TESTCARD_NAME_04 = "Mage";
    public static final String TESTCARD_NAME_05 = "Knight";


    //for rendering
    public static final int IMG_CARD_PIC_WIDTH = 365;
    public static final int IMG_CARD_PIC_HEIGHT = 245;
    public static final int FULL_CARD_WIDTH = 350;
    public static final int FULL_CARD_HEIGHT = 500;
    public static final int CARD_IMAGE_SIZE = 400;
    public static final int IMG_CARD_TITLE_SIZE = 40;
    public static final int FULL_CARD_TEMP = R.drawable.full_card_temp;
    //colors
    public static int COLOR_DEFAULT_TEXT = R.color.colorDefaultText;
    public static int COLOR_DAMAGED = R.color.colorDamaged;
    public static int COLOR_ABOVE_ORIGINAL = R.color.colorAboveOriginal;
    //Health bar stuff
    public static int COLOR_HEALTH_BAR_BG = R.color.colorHealtBarBackGround;
    public static int HEALTH_BAR_WIDTH = 240;
    public static int HEALT_BAR_HEIGHT = 60;


    private ProjectConstants() {
        //nope.
    }
}
