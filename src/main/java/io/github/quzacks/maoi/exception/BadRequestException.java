package io.github.quzacks.maoi.exception;

/**
 * Called when HTTP code is 400 or above.
 */
public class BadRequestException extends DiscordException {
    /**
     * Error code.
     */
    private final int code;

    public BadRequestException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * @return Error code.
     */
    public int getCode() {
        return code;
    }
}
