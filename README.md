# Moai
Java implementation of the Discord library wrapper. Wraps around the most recent API version, which as of this writing is [v10](https://discord.com/developers/docs/intro).

Not well-made or applicable to actual projects. If you're working on something similar, feel free to poke around the spaghetti code.

The [`gateway`](src/main/java/io/github/quzacks/maoi/gateway) directory handles the Discord gateway, which is what allows us to listen to events. Gateway intents are required to properly initialise your bot as of [v10](https://discord.com/developers/docs/intro). When you create a client instance, you must specify its intents.

There is no official documentation for the project, and I have no plans to create one.

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

Use the `client#listen` to listen to events.

```java
public static void main(String[] args) {
  final DiscordClient client = ...;

  // Fires on initialize handshake with the Discord gateway.
  client.listen(ClientReadyEvent.class, e -> 
    System.out.println(e.getClient().getUser().getUsername())
  );
}
```

You can have multiple listeners of the same type.
