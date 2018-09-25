package com.lamadmiralis.bettercardgame.events;

import java.util.Objects;

/**
 * @author maczaka
 */
public abstract class AbstractEvent {
    private static long count = 0L;

    private long createTimeStamp = System.currentTimeMillis();
    private Long offset;
    private long id;

    public AbstractEvent(final long offset) {
        this.offset = offset;
        this.id = count;
        count++;
    }

    public boolean isFireable() {
        return System.currentTimeMillis() - createTimeStamp >= offset;
    }

    public abstract void fire();

    public Long getOffset() {
        return offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEvent that = (AbstractEvent) o;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
