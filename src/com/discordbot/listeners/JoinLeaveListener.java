package com.discordbot.listeners;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * This handles sending a message in the channel when a player joins and leaves, it may also send a private message to the player that has joined.
 * TODO: Add more features to make this more useful.
 */
public class JoinLeaveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        if (event.getMember().getUser().isBot()) {
            return;
        }
        event.getMember().getUser().openPrivateChannel().queue((channel) -> channel.sendMessage("I hope you love me long time.").queue());
        event.getGuild().getDefaultChannel().sendMessage("Welcome to BananaRama, " + event.getMember().getAsMention() + "!").queue();
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        if (event.getMember().getUser().isBot()) {
            return;
        }
        event.getGuild().getDefaultChannel().sendMessage("See ya later then " + event.getMember().getEffectiveName() + ", I guess...").queue();
    }
}
