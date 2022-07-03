package io.github.quzacks.maoi.gateway;

import org.json.JSONObject;

/**
 * Represents general response from the Discord gateway.
 */
public class Payload {
    /**
     * The OP code response.
     */
    private final int op;
    /**
     * Data from the response.
     */
     private final JSONObject data;
    /**
     * Sequence from response.
     * Used to resume sessions and heartbeats.
     */
    private final int sequence;
    /**
     * The event name from the response.
     */
    private final String name;

    /**
     * De-structures response from gateway to construct a payload object.
     *
     * @param response JSON object from Discord gateway.
     */
    Payload(final JSONObject response) {
        this.op = response.getInt("op");
        this.data = response.isNull("d") ? null : response.getJSONObject("d");
        this.sequence = response.isNull("s") ? -10 : response.getInt("s");
        this.name = response.isNull("t") ? "null" : response.getString("t");
    }

    /**
     * @return OP code.
     */
    public int op() {
        return op;
    }

    /**
     * @return Name of the event.
     */
    public String name() {
        return name;
    }

    /**
     * @return Data from response.
     */
    public JSONObject data() {
        return data;
    }

    /**
     * @return Sequence number.
     */
    public int sequence() {
        return sequence;
    }
}
