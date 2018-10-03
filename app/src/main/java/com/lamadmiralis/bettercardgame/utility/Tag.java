package com.lamadmiralis.bettercardgame.utility;

public class Tag {

    public static final String MT = "CardGame";

    private Tag() {
        //nope.
    }

    public static String getFormattedExceptionMessage(final Exception exception) {
        final StringBuilder builder = new StringBuilder(exception.toString());
        for (final StackTraceElement stackTraceElement : exception.getStackTrace()) {
            builder.append(stackTraceElement.getFileName())
                    .append(".")
                    .append(stackTraceElement.getMethodName())
                    .append("() line: ")
                    .append(stackTraceElement.getLineNumber()).append("\n");
        }
        return builder.toString();
    }
}
