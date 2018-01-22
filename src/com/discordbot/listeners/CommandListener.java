package com.discordbot.listeners;

import com.discordbot.commands.CommandHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    private final CommandHandler commandHandler = new CommandHandler();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        commandHandler.handleCommand(event);
    }
}
