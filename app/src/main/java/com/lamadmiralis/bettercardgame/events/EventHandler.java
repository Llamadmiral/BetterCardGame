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

    private static final Comparator<AbstractEvent> COMPARATOR = new EventComparator();
    private static final EventHandler instance = new EventHandler();

    private final List<AbstractEvent> events = new ArrayList<>();
    private final List<AbstractEvent> eventsToFire = new ArrayList<>();

    private long lastFireTS = 0L;

    private EventHandler() {
    }

    public static EventHandler getInstance() {
        return instance;
    }

    public void addEvent(final AbstractEvent event) {
        Log.d(Tag.MT, "Event added: " + event.getClass().getSimpleName());
        lastFireTS = event.getOffset() > lastFireTS ? event.getOffset() : lastFireTS;
        events.add(event);
    }

    public void dispatch() {
        Log.d(Tag.MT, "Dispatch called, size: " + events.size());
        Collections.sort(events, COMPARATOR);
        eventsToFire.addAll(events);
        events.clear();
    }

    /**
     * Adds an event to be fired after the latest event with offset.
     */
    public void addFinalEvent(final AbstractEvent event, final int offset) {
        event.setOffset(lastFireTS + offset);
        events.add(event);
    }

    public List<AbstractEvent> getFireableEvents() {
        final List<AbstractEvent> events = new ArrayList<>();
        if (!eventsToFire.isEmpty()) {
            for (final AbstractEvent abstractEvent : eventsToFire) {
                if (abstractEvent.isFireable()) {
                    events.add(abstractEvent);
                }
            }
            eventsToFire.removeAll(events);
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
