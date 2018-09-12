package com.lamadmiralis.bettercardgame.utility;

import android.content.Context;
import android.content.res.Resources;
import com.lamadmiralis.bettercardgame.events.AbstractEvent;
import com.lamadmiralis.bettercardgame.events.EventHandler;
import com.lamadmiralis.bettercardgame.events.impl.EventDrawCard;
import com.lamadmiralis.bettercardgame.objects.uielements.DrawablePack;
import com.lamadmiralis.bettercardgame.objects.uielements.NextTurnButton;
import com.lamadmiralis.bettercardgame.objects.uielements.Reset;
import com.lamadmiralis.bettercardgame.renderer.Clickable;
import com.lamadmiralis.bettercardgame.renderer.Renderable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maczaka
 */
public class InterfaceContext {

    public static final int WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    private static final String TAG = "InterfaceContext";
    private static InterfaceContext instance;
    private Context context;

    private List<Renderable> objectsToDraw = new ArrayList<>();
    private List<Clickable> clickables = new ArrayList<>();

    public static InterfaceContext getInstance() {
        if (instance == null) {
            instance = new InterfaceContext();
        }
        return instance;
    }


    public void drawCard(final int count, final boolean drawForPlayer) {
        for (int i = 0; i < count; i++) {
            EventHandler.addEvent(new EventDrawCard(1000 + (i * 300), drawForPlayer));
        }
    }

    public List<Renderable> getObjectsToDraw() {
        return objectsToDraw;
    }

    public List<Clickable> getClickables() {
        return clickables;
    }

    public void addToClickable(final Clickable clickable) {
        objectsToDraw.add(clickable);
        clickables.add(clickable);
    }

    public void test() {
        InterfaceContext.getInstance().addToClickable(new DrawablePack());
        InterfaceContext.getInstance().addToClickable(new Reset());
        InterfaceContext.getInstance().addToClickable(new NextTurnButton());
        InterfaceContext.getInstance().drawCard(3, true);
        InterfaceContext.getInstance().drawCard(3, false);
        EventHandler.dispatch();
    }

    public void reset() {
        BattleContext.getInstance().reset();
        objectsToDraw.clear();
        clickables.clear();
        test();
    }

    public void update() {
        for (final AbstractEvent abstractEvent : EventHandler.getFireableEvents()) {
            abstractEvent.fire();
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(final Context context) {
        this.context = context;
    }
}
