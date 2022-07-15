package io.github.quzacks.maoi.interaction;

/**
 * Represents a Discord application command.
 */
public abstract class ApplicationCommand {
    /**
     * Name of the command.
     */
    private final String name;
    /**
     * Command description.
     */
    private final String description;

    /**
     * @param name Name of the command.
     * @param description Description of the command.
     */
    public ApplicationCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Return the command type.
     *
     * @return Command type.
     *
     * @see ApplicationCommandType
     */
    public abstract ApplicationCommandType getType();

    /**
     * @return Name of the command.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Description of the command.
     */
    public String getDescription() {
        return description;
    }
}