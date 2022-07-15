package io.github.quzacks.maoi.entity.application;

import org.json.JSONObject;

import java.util.EnumSet;

/**
 * Represents a Discord application.
 */
public class Application {
    /**
     * ID of the application.
     */
    private final String id;
    /**
     * Set of application flags.
     *
     * @see ApplicationFlag
     */
    private final EnumSet<ApplicationFlag> flags = EnumSet.noneOf(ApplicationFlag.class);

    /**
     * Construct application from JSON.
     *
     * @param data JSON object.
     */
    public Application(JSONObject data) {
        this.id = data.getString("id");
    }

    /**
     * @return ID of application.
     */
    public String getId() {
        return id;
    }

    /**
     * @return Application flags.
     *
     * @see ApplicationFlag
     */
    public EnumSet<ApplicationFlag> getFlags() {
        return flags;
    }
}
