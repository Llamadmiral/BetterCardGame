package com.lamadmiralis.bettercardgame.renderer;

import android.graphics.Canvas;

/**
 * @author maczaka
 */
public interface Renderable {

    void render(final Canvas canvas);

    void update();

    int getZIndex();
}
