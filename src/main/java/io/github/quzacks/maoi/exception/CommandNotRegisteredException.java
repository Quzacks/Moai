package io.github.quzacks.maoi.exception;

/**
 * Called when a command that has not been registered has been fired.
 */
public class CommandNotRegisteredException extends DiscordException {
    /**
     * @param commandName Name of the command.
     */
    public CommandNotRegisteredException(String commandName) {
        super("Command \"" + commandName + "\" has not been registered");
    }
}