package com.lamadmiralis.bettercardgame.events;

/**
 * @author maczaka
 */
public abstract class AbstractEvent {

    private long createTimeStamp = System.currentTimeMillis();
    private Long offset;

    public AbstractEvent(final long offset) {
        this.offset = offset;
    }

    public boolean isFireable() {
        return System.currentTimeMillis() - createTimeStamp >= offset;
    }

    public abstract void fire();

    public Long getOffset() {
        return offset;
    }

    public void setOffset(final long aOffset) {
        this.offset = aOffset;
    }
}
