# Moai
Java implementation of the Discord library. Wraps around the most recent API version, which as of this writing is [v10](https://discord.com/developers/docs/intro).

Not well-made or applicable to actual projects. If you're working on something similar, feel free to poke around the spaghetti code.

The [`gateway`](src/main/java/io/github/quzacks/maoi/gateway) directory handles the Discord gateway, which is what allows us to listen to events. Gateway intents are required to properly initialise your bot as of [v10](https://discord.com/developers/docs/intro). When you create a client instance, you must specify its intents.

# Usage

## Client instance

Creating a [`DiscordClient`](src/main/java/io/github/quzacks/maoi/DiscordClient.java) instance is done via the [`ClientBuilder`](src/main/java/io/github/quzacks/maoi/ClientBuilder.java).

```java
public static void main(String[] args) {
  final DiscordClient client = ClientBuilder.create("TOKEN-HERE")
    .setIntents(
      GatewayIntent.MESSAGE_CONTENT,
      GatewayIntent.GUILD_MESSAGES
    )
    .build(); // <-- Constructs the actual client instance.
}
```

Additionally, you can set the client's presence using `setPresence(UserPresence)`.

## Events

Use the `client#listen` method to listen to events.

```java
public static void main(String[] args) {
  final DiscordClient client = ...;

  // Fires on initial handshake with the Discord gateway.
  client.listen(ClientReadyEvent.class, e -> 
    System.out.println(e.getClient().getUser().getUsername())
  );
}
```

You can have multiple listeners of the same type.

## Commands

### Slash commands

Slash commands are the native way of creating chat input commands on Discord. Moai provides an easy interface to build your slash commands. Let's create our own slash command.

* Step 1: Create a class that extends [`SlashCommand`](src/main/java/io/github/quzacks/interaction/slash_command/SlashCommand).

```java
public class LogCommand extends SlashCommand { }
```

* Step 2: Create a constructor for the command and provide necessary information.

```java
public LogCommand() {
  super(
    "log",
    "Logs a message in the console.",
    new SlashCommandOption(
      SlashCommandOptionType.STRING,
      "message",
      "Message you want to log.",
      true
    )
  );
}
```

* Step 3: Override the `run()` method from the super class.

```java
@Override
public void run(Interaction interaction, Object[] options) {
  final String message = (String) options[0];

  System.out.println("Logged: " message);
}
```

You should've ended up with something like this.

```java
public class LogCommand extends SlashCommand {
  public LogCommand() {
    super(
      "log",
      "Logs a message in the console.",
      new SlashCommandOption(
        SlashCommandOptionType.STRING,
        "message",
        "Message you want to log.",
        true
      )
    );
  }

  @Override
  public void run(Interaction interaction, Object[] options) {
    final String message = (String) options[0];

    System.out.println("Logged: " message);
  }
}
```

* Step 5: Finally, register the command:

```java
client.getCommandRegistry().register(new LogCommand()); // <-- This registers it globally.
```

You can register the command in a singular guild using the `CommandRegistry#register(SlashCommand command, String guildId)` method.
