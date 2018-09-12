package com.lamadmiralis.bettercardgame.events;

import android.util.Log;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author maczaka
 */
public class EventHandler {

    private static final String TAG = "EventHandler";
    private static final Set<AbstractEvent> EVENTS = new TreeSet<>(new EventComparator());
    private static final Set<AbstractEvent> EVENTS_TO_FIRE = new TreeSet<>(new EventComparator());

    private EventHandler() {
    }

    public static void addEvent(final AbstractEvent event) {
        Log.e(TAG, "Event added: " + event.getClass().getSimpleName());
        EVENTS.add(event);
    }

    public static void dispatch() {
        Log.e("EventHandler", "Dispatch called, size: " + EVENTS.size());
        EVENTS_TO_FIRE.addAll(EVENTS);
        EVENTS.clear();
    }

    public static Set<AbstractEvent> getFireableEvents() {
        final Set<AbstractEvent> events = new TreeSet<>(new EventComparator());
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
            return o1.getOffset().compareTo(o2.getOffset());
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof EventComparator;
        }
    }

}
