package io.github.quzacks.maoi.entity.channel;

import io.github.quzacks.maoi.entity.channel.thread.ThreadMetadata;
import io.github.quzacks.maoi.entity.guild.Guild;
import io.github.quzacks.maoi.entity.permission.Overwrite;
import io.github.quzacks.maoi.entity.user.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a DM or guild channel on Discord.
 */
public class Channel {
    /**
     * Channel id.
     */
    private final String id;
    /**
     * Type of the channel.
     *
     * @see ChannelType
     */
    private final ChannelType type;
    /**
     * Guild ID.
     *
     * @see Guild
     */
    private final Guild guild;
    /**
     * Sorting position of the channel.
     */
    private final int position;
    /**
     * Channel name.
     */
    private final String name;
    /**
     * Channel topic.
     */
    private final String topic;
    /**
     * Whether channel is tagged as NSFW.
     */
    private final boolean nsfw;
    /**
     * Bit rate for voice channels.
     */
    private final int bitRate;
    /**
     * User limit of the voice channel.
     */
    private final int userLimit;
    /**
     * Channel slow mode.
     */
    private final int rateLimit;
    /**
     * Recipients of the DM.
     *
     * @see User
     */
    private final List<User> recipients = new ArrayList<>();
    /**
     * Icon hash of the group DM.
     */
    private final String iconHash;
    /**
     * Owner of the thread or group DM.
     */
    private final User owner;
    /**
     * Permission overwrites of the channel.
     *
     * @see Overwrite
     */
    private final List<Overwrite> permissions = new ArrayList<>();
    /**
     * Thread message count.
     * Stops counting at 50.
     */
    private final int messageCount;
    /**
     * Thread member count.
     * Stops counting at 50.
     */
    private final int memberCount;
    /**
     * Thread metadata.
     *
     * @see ThreadMetadata
     */
    private final ThreadMetadata threadMetadata;

    /**
     * Constructs a Channel object from a JSON.
     *
     * @param data JSON object.
     */
    public Channel(JSONObject data) {
        this.id = data.getString("id");
        this.type = ChannelType.fromId(data.getInt("type"));
        this.guild = data.isNull("guild_Id") ? null : new Guild();
        this.position = data.isNull("position") ? -1 : data.getInt("position");

        /*
        if(!data.isNull("permission_overwrites")) {
            JSONArray permissionOverwrites = data.getJSONArray("permission_overwrites");

            for(int i = 0; i < permissionOverwrites.length(); i++)
                this.permissions.add(new Overwrite(permissionOverwrites.getJSONObject(i)));
        }
         */

        this.name = data.isNull("name") ? "" : data.getString("name");
        this.topic = data.isNull("topic") ? "" : data.getString("topic");
        this.nsfw = !data.isNull("nsfw") && data.getBoolean("nsfw");
        this.bitRate = data.isNull("bitrate") ? -1 : data.getInt("bitrate");
        this.userLimit = data.isNull("user_limit") ? -1 : data.getInt("user_limit");
        this.rateLimit = data.isNull("rate_limit_per_user") ? -1 : data.getInt("rate_limit_per_user");

        if(!data.isNull("recipients")) {
            JSONArray recipients = data.getJSONArray("recipients");

            for(int i = 0; i < recipients.length(); i++)
                this.recipients.add(new User(recipients.getJSONObject(i)));
        }

        this.iconHash = data.isNull("icon") ? null : data.getString("icon");
        this.owner = null;
        this.messageCount = data.isNull("message_count") ? -1 : data.getInt("message_count");
        this.memberCount = data.isNull("member_count") ? -1 : data.getInt("member_count");
        this.threadMetadata = data.isNull("thread_metadata?") ? null : new ThreadMetadata(data.getJSONObject("thread_metadata"));
    }

    /**
     * @return ID of the channel.
     */
    public String getId() {
        return id;
    }

    /**
     * @return Type of the channel.
     *
     * @see ChannelType
     */
    public ChannelType getType() {
        return type;
    }

    /**
     * @return Guild the channel resides in.
     *
     * @see Guild
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * @return Position of the channel.
     */
    public int getPosition() {
        return position;
    }

    /**
     * @return Channel name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Channel topic.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @return Whether channel is marked as NSFW.
     */
    public boolean isNsfw() {
        return nsfw;
    }

    /**
     * @return Channel bit rate.
     */
    public int getBitRate() {
        return bitRate;
    }

    /**
     * @return Voice channel user limit.
     */
    public int getUserLimit() {
        return userLimit;
    }

    /**
     * @return Channel slow mode.
     */
    public int getRateLimit() {
        return rateLimit;
    }

    /**
     * @return Recipients of DM.
     *
     * @see User
     */
    public List<User> getRecipients() {
        return recipients;
    }

    /**
     * @return Group icon hash.
     */
    public String getIconHash() {
        return iconHash;
    }

    /**
     * @return Owner of the group.
     *
     * @see User
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @return Overwritten permissions.
     *
     * @see Overwrite
     */
    public List<Overwrite> getPermissions() {
        return permissions;
    }

    /**
     * @return Total message count in a thread.
     */
    public int getMessageCount() {
        return messageCount;
    }

    /**
     * @return Total member count in a thread.
     */
    public int getMemberCount() {
        return memberCount;
    }

    /**
     * @return Thread metadata.
     *
     * @see ThreadMetadata
     */
    public ThreadMetadata getThreadMetadata() {
        return threadMetadata;
    }
}