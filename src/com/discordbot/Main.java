package com.discordbot;

import com.discordbot.listeners.CommandListener;
import com.discordbot.listeners.JoinLeaveListener;
import com.discordbot.listeners.RoleChangeListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

/**
 * The main method for the server.
 * @author Bananastreet
 */
public class Main {

    //Initialize the server.
    public static void main(String[] args) throws LoginException {
        //TODO: Use a config file next time.
        if (args.length < 1) {
            throw new IllegalArgumentException("Your need an argument for the bot token.");
        }
        String token = args[0];
        JDA jda = new JDABuilder(AccountType.BOT).setToken(token).buildAsync();

        //TODO: Have some kind of loader for the listeners
        jda.addEventListener(new CommandListener());
        jda.addEventListener(new JoinLeaveListener());
        jda.addEventListener(new RoleChangeListener());
    }
}
