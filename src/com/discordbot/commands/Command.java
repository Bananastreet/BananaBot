package com.discordbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {
    void handleCommand(MessageReceivedEvent event);
}
