package io.github.quzacks.maoi.interaction;

import io.github.quzacks.maoi.interaction.slash_command.SlashCommandOptionType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents application command interaction option data.
 */
public class ApplicationCommandOptionData {
    /**
     * Name of parameter.
     */
    private final String name;
    /**
     * Type of data value.
     *
     * @see SlashCommandOptionType
     */
    private final SlashCommandOptionType type;
    /**
     * Option value.
     */
    private final Object value;
    /**
     * Options if the current option is a sub command group.
     */
    private final List<ApplicationCommandOptionData> options = new ArrayList<>();

    /**
     * Constructs option data from JSON.
     *
     * @param data JSON object.
     */
    public ApplicationCommandOptionData(JSONObject data) {
        this.name = data.getString("name");
        this.type = SlashCommandOptionType.fromValue(data.getInt("type"));
        this.value = data.isNull("value") ? null : data.get("value");

        if(!data.isNull("options")) {
            final JSONArray options = data.getJSONArray("options");

            for(int i = 0; i < options.length(); i++)
                this.options.add(new ApplicationCommandOptionData(options.getJSONObject(i)));
        }
    }

    /**
     * @return Name of object.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Option type.
     *
     * @see SlashCommandOptionType
     */
    public SlashCommandOptionType getType() {
        return type;
    }

    /**
     * @return Option value.
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return List of options.
     */
    public List<ApplicationCommandOptionData> getOptions() {
        return options;
    }
}
