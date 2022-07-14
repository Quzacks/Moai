package io.github.quzacks.maoi.gateway;

/**
 * Enumerates all Discord OP codes.
 */
public enum OpCode {
    /**
     * Event was dispatched.
     */
    DISPATCH(0),
    /**
     * Heartbeat was sent or received.
     */
    HEARTBEAT(1),
    /**
     * New session during initial handshake.
     */
    IDENTIFY(2),
    /**
     * Update client's presence.
     */
    PRESENCE_UPDATE(3),
    /**
     * Join and leave voice channels.
     */
    VOICE_STATE_UPDATE(4),
    /**
     * Client resumed a session.
     */
    RESUME(6),
    /**
     * Attempt to reconnect.
     */
    RECONNECT(7),
    /**
     * Requesting information on a large guild.
     */
    REQUEST_GUILD_MEMBERS(8),
    /**
     * Session has been invalidated.
     */
    INVALID_SESSION(9),
    /**
     * Sent immediately after connection.
     */
    HELLO(10),
    /**
     * Sent in response to receiving a heartbeat.
     */
    HEARTBEAT_RES(11);

    /**
     * OP code.
     */
    private final int code;

    OpCode(int code) {
        this.code = code;
    }

    /**
     * @return OP code.
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code OP code value.
     * @return OP code that matches the value.
     */
    public static OpCode fromCode(int code) {
        for(OpCode opCode : OpCode.values())
            if(opCode.getCode() == code) return opCode;
        return null;
    }
}
