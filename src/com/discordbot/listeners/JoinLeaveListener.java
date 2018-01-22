package com.discordbot.listeners;

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

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
        event.getGuild().getDefaultChannel().sendMessage("See ya later then " + event.getMember().getAsMention() + ", I guess...").queue();
    }
}
