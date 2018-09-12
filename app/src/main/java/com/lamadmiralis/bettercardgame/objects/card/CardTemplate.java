package com.lamadmiralis.bettercardgame.objects.card;

import com.lamadmiralis.bettercardgame.utility.ImageHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maczaka
 */
public class CardTemplate {

    private static final Map<String, CardTemplate> CACHE = new HashMap<>();
    private static final Map<String, Integer> NAME_MAP = new HashMap<>();

    static {
        NAME_MAP.put(ProjectConstants.TEST_CARD_NAME, ProjectConstants.FULL_CARD_TEMP);
    }

    private int banner;
    private int frame;
    private int front;
    private int imageId;
    private String name;

    private CardTemplate(final int imageId, final String name) {
        this.imageId = imageId;
        this.name = name;
        CACHE.put(name, this);
    }

    public static CardTemplate createTemplate(final String name, final int id) {
        final CardTemplate template = new CardTemplate(id, name);
        template.initDefaultLook();
        return template;
    }

    public static void initCardImages() {
        for (final Map.Entry<String, Integer> card : NAME_MAP.entrySet()) {
            ImageHolder.initCardImage(CardTemplate.createTemplate(card.getKey(), card.getValue()));
        }
    }

    private void initDefaultLook() {
        this.banner = ProjectConstants.DEFAULT_BANNER;
        this.frame = ProjectConstants.DEFAULT_FRAME;
        this.front = ProjectConstants.DEFAULT_FRONT_BG;
    }

    private void initCustomLook(final int banner, final int frame, final int front) {
        this.banner = banner;
        this.frame = frame;
        this.front = front;
    }

    public int getBanner() {
        return banner;
    }

    public int getFrame() {
        return frame;
    }

    public int getFront() {
        return front;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
