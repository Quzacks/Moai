package io.github.quzacks.maoi.interaction;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the data of an interaction object.
 *
 * @see Interaction
 */
public class InteractionData {
    /**
     * Interaction name.
     */
    private final String name;
    /**
     * Application command type.
     *
     * @see ApplicationCommandType
     */
    private final ApplicationCommandType type;
    /**
     * Options.
     *
     * @see ApplicationCommandOptionData
     */
    private final List<ApplicationCommandOptionData> options = new ArrayList<>();

    /**
     * Construct interaction data from JSON.
     *
     * @param data JSON object.
     */
    public InteractionData(JSONObject data) {
        this.name = data.isNull("name") ? null : data.getString("name");
        this.type = data.isNull("type") ? null : ApplicationCommandType.fromValue(data.getInt("type"));

        if(!data.isNull("options")) {
            final JSONArray options = data.getJSONArray("options");

            for(int i = 0; i < options.length(); i++)
                this.options.add(new ApplicationCommandOptionData(options.getJSONObject(i)));
        }
    }

    /**
     * @return Name of interaction.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Type of interaction.
     *
     * @see InteractionType
     */
    public ApplicationCommandType getType() {
        return type;
    }

    /**
     * @return Command options.
     *
     * @see ApplicationCommandOptionData
     */
    public List<ApplicationCommandOptionData> getOptions() {
        return options;
    }
}