package com.discordbot.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Handles the commands.
 * @author Bananastreet
 */
public class CommandHandler implements Command {

    private String prefix = "!";

    @Override
    public void handleCommand(MessageReceivedEvent event) {
        String command = event.getMessage().getContentRaw().toLowerCase();

        if (!command.startsWith(getPrefix())) {
            return;
        }

        command = command.substring(1, command.length());

        String[] parts = command.split(" ");

        System.out.println(command);

        if (event.getAuthor().isBot() || event.getAuthor() == null) {
            return;
        }

        try {
            normalCommands(event, command, parts);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void normalCommands(MessageReceivedEvent event, String command, String[] parts) {
        if (parts[0].equalsIgnoreCase("ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        } else if (parts[0].equalsIgnoreCase("addrole")) {
            Member member;
            if (event.getMessage().getMentionedUsers().isEmpty()) {
                member = event.getGuild().getMemberById(parts[1]);
            } else {
                member = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
            }
            Role role;
            if (event.getMessage().getMentionedRoles().isEmpty()) {
                role = event.getGuild().getRoleById(parts[2]);
            } else {
                role = event.getMessage().getMentionedRoles().get(0);
            }
            event.getGuild().getController().addSingleRoleToMember(member, role).queue();
            event.getChannel().sendMessage("Hey big daddy " + member.getAsMention() + ", your new role is " + role.getAsMention() + ".").queue();
        } else if (parts[0].equalsIgnoreCase("removerole")) {
            Member member;
            if (event.getMessage().getMentionedUsers().isEmpty()) {
                member = event.getGuild().getMemberById(parts[1]);
            } else {
                member = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
            }
            Role role;
            if (event.getMessage().getMentionedRoles().isEmpty()) {
                role = event.getGuild().getRoleById(parts[2]);
            } else {
                role = event.getMessage().getMentionedRoles().get(0);
            }
            event.getGuild().getController().removeSingleRoleFromMember(member, role).queue();
            event.getChannel().sendMessage("Hey big daddy " + member.getAsMention() + ", you lost the role " + role.getAsMention() + ".").queue();
        } else if (parts[0].equalsIgnoreCase("commands")) {
            event.getAuthor().openPrivateChannel().queue((channel) -> channel.sendMessage("Hey dood, how about some commands?").queue());
        } else if (parts[0].equalsIgnoreCase("hug")) {
            if (!event.getMessage().getMentionedUsers().isEmpty()) {
                User user = event.getMessage().getMentionedUsers().get(0);
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " has given " + user.getAsMention() + " a hug. :heart: AWWW!").queue();
            } else {
                event.getChannel().sendMessage("You need to mention a user first.").queue();
            }
        } else if (parts[0].equalsIgnoreCase("setprefix")) {
            String newPrefix = parts[1];
            setPrefix(newPrefix);
            event.getChannel().sendMessage("Your new prefix is " + getPrefix() + ".").queue();
        } else if (parts[0].equalsIgnoreCase("getprefix")) {
            event.getChannel().sendMessage("The prefix is " + getPrefix() + ".").queue();
        }
    }


    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

}
