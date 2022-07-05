package io.github.quzacks.maoi.entity.message;

import io.github.quzacks.maoi.entity.Attachment;
import io.github.quzacks.maoi.entity.channel.Channel;
import io.github.quzacks.maoi.entity.guild.Guild;
import io.github.quzacks.maoi.entity.message.activity.MessageActivity;
import io.github.quzacks.maoi.entity.message.embed.Embed;
import io.github.quzacks.maoi.entity.message.sticker.Sticker;
import io.github.quzacks.maoi.entity.user.User;
import org.json.JSONObject;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Represents message data from Discord.
 */
public class Message {
    /**
     * ID of the message.
     */
    private final String id;
    /**
     * Channel the message was sent in.
     *
     * @see Channel
     */
    private final Channel channel;
    /**
     * Guild the message was sent in.
     *
     * @see Guild
     */
    private final Guild guild;
    /**
     * Author of the message.
     *
     * @see User
     */
    private final User author;
    /**
     * Content of the message.
     */
     private final String content;
    /**
     * When the message was sent.
     */
    private final OffsetDateTime date;
    /**
     * Whether the message uses text to speech.
     */
    private final boolean tts;
    /**
     * Whether the message mentions everyone
     */
    private final boolean mentionsEveryone;
    /**
     * All mentions in the message.
     *
     * @see Mentions
     */
    private final Mentions mentions;
    /**
     * All attachments.
     *
     * @see Attachment
     */
    private final List<Attachment> attachments = new ArrayList<>();
    /**
     * All embed content.
     *
     * @see Embed
     */
    private final List<Embed> embeds = new ArrayList<>();
    /**
     * All reactions on the message.
     *
     * @see Reaction
     */
    private final List<Reaction> reactions = new ArrayList<>();
    /**
     * Whether the message was pinned.
     */
    private final boolean pinned;
    /**
     * Message type.
     *
     * @see MessageType
     */
    private final MessageType type;
    /**
     * Message activity.
     *
     * @see MessageActivity
     */
    private final MessageActivity activity;
    /**
     * Referenced message.
     */
    private final Message referencedMessage;
    /**
     * Message flags.
     *
     * @see MessageFlag
     */
    private final EnumSet<MessageFlag> flags = EnumSet.noneOf(MessageFlag.class);
    /**
     * Message interaction.
     *
     * @see Interaction
     */
    private final Interaction interaction;
    // TODO: Message components.
    /**
     * Stickers send with the message.
     *
     * @see Sticker
     */
    private final List<Sticker> stickerItems = new ArrayList<>();

    /**
     * Deserializes JSON to construct a Message object.
     *
     * @param data JSON object.
     */
    public Message(JSONObject data) {
        this.id = data.getString("id");
        // TODO: Create an actual instance.
        this.channel = new Channel();
        // TODO: Create guild instance.
        this.guild = data.isNull("guild_id") ? null : new Guild();
        this.author = !data.isNull("webhook_id") ? null : new User(data.getJSONObject("author"));
        this.content = data.getString("content");
        this.date = OffsetDateTime.parse(data.getString("timestamp"));
        this.tts = data.getBoolean("tts");
        this.mentionsEveryone = data.getBoolean("mention_everyone");
        this.mentions = new Mentions(data);

        for(int i = 0; i < data.getJSONArray("attachments").length(); i++)
            attachments.add(new Attachment(data.getJSONArray("attachments").getJSONObject(i)));

        for(int i = 0; i < data.getJSONArray("embeds").length(); i++)
            embeds.add(new Embed(data.getJSONArray("embeds").getJSONObject(i)));

        if(!data.isNull("reactions")) {
            for(int i = 0; i < data.getJSONArray("reactions").length(); i++)
                reactions.add(new Reaction(data.getJSONArray("reactions").getJSONObject(i)));
        }

        this.pinned = data.getBoolean("pinned");
        this.type = MessageType.fromId(data.getInt("type"));
        this.activity = data.isNull("activity") ? null : new MessageActivity();
        this.referencedMessage = data.isNull("referenced_message") ? null : new Message(data.getJSONObject("referenced_message"));

        if(!data.isNull("flags")) {
            final int flags = data.getInt("flags");

            for(MessageFlag flag : MessageFlag.values()) {
                if((flag.getRaw() & flags) == flag.getRaw()) this.flags.add(flag);
            }
        }

        // TODO: Interaction object.
        this.interaction = data.isNull("interaction") ? null : new Interaction();

        if(!data.isNull("sticker_items")) {
            for(int i = 0; i < data.getJSONArray("sticker_items").length(); i++)
                stickerItems.add(new Sticker(data.getJSONArray("sticker_items").getJSONObject(i)));
        }
    }

    /**
     * @return ID of the message.
     */
    public String getId() {
        return id;
    }

    /**
     * @return Channel message was sent in.
     *
     * @see Channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @return Guild message was sent in.
     *
     * @see Guild
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * @return Author of the message.
     *
     * @see User
     */
    public User getAuthor() {
        return author;
    }

    /**
     * @return Content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @return When the message was sent.
     */
    public OffsetDateTime getDate() {
        return date;
    }

    /**
     * @return Whether message uses text to speech.
     */
    public boolean tts() {
        return tts;
    }

    /**
     * @return Whether message mentions everyone.
     */
    public boolean mentionsEveryone() {
        return mentionsEveryone;
    }

    /**
     * @return Grouped mentions.
     *
     * @see Mentions
     */
    public Mentions getMentions() {
        return mentions;
    }

    /**
     * @return Attachments.
     *
     * @see Attachment
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * @return Message embeds.
     *
     * @see Embed
     */
    public List<Embed> getEmbeds() {
        return embeds;
    }

    /**
     * @return Reactions of the message.
     *
     * @see Reaction
     */
    public List<Reaction> getReactions() {
        return reactions;
    }

    /**
     * @return Whether message is pinned.
     */
    public boolean isPinned() {
        return pinned;
    }

    /**
     * @return Type of the message.
     *
     * @see MessageType
     */
    public MessageType getType() {
        return type;
    }

    /**
     * @return Message activity.
     *
     * @see MessageActivity
     */
    public MessageActivity getActivity() {
        return activity;
    }

    /**
     * @return Referenced message.
     */
    public Message getReferencedMessage() {
        return referencedMessage;
    }

    /**
     * @return Set of flags.
     *
     * @see MessageFlag
     */
    public EnumSet<MessageFlag> getFlags() {
        return flags;
    }

    /**
     * @return Interaction data.
     *
     * @see Interaction
     */
    public Interaction getInteraction() {
        return interaction;
    }

    /**
     * @return List of stickers.
     *
     * @see Sticker
     */
    public List<Sticker> getStickerItems() {
        return stickerItems;
    }
}