package com.discordbot.listeners;

import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class RoleChangeListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        event.getGuild().getDefaultChannel().sendMessage(event.getMember().getAsMention() + " has received role " + event.getRoles().get(0).getAsMention()).queue();
     }

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        event.getGuild().getDefaultChannel().sendMessage(event.getMember().getAsMention() + " has been demoted from role " + event.getRoles().get(0).getAsMention()).queue();
    }
}
