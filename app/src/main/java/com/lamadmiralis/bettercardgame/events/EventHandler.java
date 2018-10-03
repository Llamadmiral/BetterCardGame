package com.lamadmiralis.bettercardgame.events;

import android.util.Log;

import com.lamadmiralis.bettercardgame.utility.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author maczaka
 */
public class EventHandler {

    private static final List<AbstractEvent> EVENTS = new ArrayList<>();
    private static final List<AbstractEvent> EVENTS_TO_FIRE = new ArrayList<>();

    private EventHandler() {
    }

    public static void addEvent(final AbstractEvent event) {
        Log.d(Tag.MT, "Event added: " + event.getClass().getSimpleName());
        EVENTS.add(event);
    }

    public static void dispatch() {
        Log.d(Tag.MT, "Dispatch called, size: " + EVENTS.size());
        Collections.sort(EVENTS, new EventComparator());
        EVENTS_TO_FIRE.addAll(EVENTS);
        EVENTS.clear();
    }

    public static List<AbstractEvent> getFireableEvents() {
        final List<AbstractEvent> events = new ArrayList<>();
        if (!EVENTS_TO_FIRE.isEmpty()) {
            for (final AbstractEvent abstractEvent : EVENTS_TO_FIRE) {
                if (abstractEvent.isFireable()) {
                    events.add(abstractEvent);
                }
            }
            EVENTS_TO_FIRE.removeAll(events);
        }
        return events;
    }

    private static class EventComparator implements Comparator<AbstractEvent> {

        @Override
        public int compare(final AbstractEvent o1, final AbstractEvent o2) {
            return o2.getOffset().compareTo(o1.getOffset());
        }
    }

}
