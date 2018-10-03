package com.lamadmiralis.bettercardgame.utility;

import android.content.Context;

import com.lamadmiralis.bettercardgame.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maczaka
 */
public class ResourceInitializer {

    private static final List<Integer> RESOURCES_TO_LOAD = new ArrayList<>();

    static {
        RESOURCES_TO_LOAD.add(R.drawable.card_banner);
        RESOURCES_TO_LOAD.add(R.drawable.card_frame);
        RESOURCES_TO_LOAD.add(R.drawable.card_front_bg);
        RESOURCES_TO_LOAD.add(R.drawable.card_back_bg);
        RESOURCES_TO_LOAD.add(R.drawable.img_placeholder);
        RESOURCES_TO_LOAD.add(R.drawable.reset);
        RESOURCES_TO_LOAD.add(R.drawable.next_turn_button);
        RESOURCES_TO_LOAD.add(R.drawable.full_card_temp);
        RESOURCES_TO_LOAD.add(R.drawable.healthbar);
    }

    private ResourceInitializer() {
        //nope.
    }

    public static void initResources(final Context context) {
        for (final Integer id : RESOURCES_TO_LOAD) {
            ImageHolder.initResource(context, id);
        }
    }

}
