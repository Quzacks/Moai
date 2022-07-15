package io.github.quzacks.maoi.interaction.slash_command;

/**
 * Represents choices for slash command option.
 *
 * @see SlashCommandOption
 */
public class SlashCommandOptionChoice<T> {
    /**
     * Option choice name.
     */
    private final String name;
    /**
     * Option choice value.
     * This is dependant on the command option type.
     */
    private final T value;

    /**
     * @param name Choice name.
     * @param value Choice value.
     */
    public SlashCommandOptionChoice(String name, T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return Choice name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Choice value.
     */
    public T getValue() {
        return value;
    }
}