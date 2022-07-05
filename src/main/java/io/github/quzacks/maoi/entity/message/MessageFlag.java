package io.github.quzacks.maoi.entity.message;

/**
 * Enumerates all message flags on Discord.
 */
public enum MessageFlag {
    ;

    /**
     * Raw value of the flag.
     */
    private final int raw;

    /**
     * Constructor.
     *
     * @param offset Offset of the flag.
     */
    MessageFlag(int offset) {
        this.raw = 1 << offset;
    }

    /**
     * @return Raw value of the flag.
     */
    public int getRaw() {
        return raw;
    }
}