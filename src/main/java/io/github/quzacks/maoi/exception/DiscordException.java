package io.github.quzacks.maoi.exception;

/**
 * Discord exceptions.
 */
public abstract class DiscordException extends Exception {
    /**
     * Initializes an instance of the exception.
     *
     * @param reason Reason.
     */
    public DiscordException(String reason) {
        super(reason);
    }
}