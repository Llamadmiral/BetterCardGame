package com.lamadmiralis.bettercardgame.utility;

public class Tag {
    public static final String MT = "CardGame";

    public static String getFormattedExceptionMessage(final Exception exception) {
        final StringBuilder builder = new StringBuilder(exception.getCause().getLocalizedMessage());
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
