package com.discordbot.commands;

import com.discordbot.json.walmartitem.WalmartItem;
import com.discordbot.json.walmartitem.data.Common;
import com.discordbot.json.walmartitem.data.Online;
import com.discordbot.json.walmartitem.data.online.Inventory;
import com.discordbot.utils.RandomGen;
import com.google.gson.Gson;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.HierarchyException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Handles the commands.
 *
 * @author Bananastreet
 */
public class CommandHandler implements Command {


    private String prefix = "!";

    private final RandomGen random = new RandomGen();

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

        Member member = event.getGuild().getSelfMember();

        try {
            normalCommands(event, command, parts);
            if (member.hasPermission(Permission.ADMINISTRATOR)) {
                administratorCommands(event, command, parts);
            } else {
                event.getTextChannel().sendMessage("You do not have permission to use this command!").queue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void normalCommands(MessageReceivedEvent event, String command, String[] parts) {
        if (parts[0].equalsIgnoreCase("ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        } else if (parts[0].equalsIgnoreCase("commands")) {
            event.getAuthor().openPrivateChannel().queue((channel) -> channel.sendMessage("Hey dood, how about some commands?").queue());
        } else if (parts[0].equalsIgnoreCase("hug")) {
            if (!event.getMessage().getMentionedUsers().isEmpty()) {
                User user = event.getMessage().getMentionedUsers().get(0);
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " has given " + user.getAsMention() + " a hug. :heart: AWWW!").queue();
            } else {
                event.getChannel().sendMessage("You need to mention a user first.").queue();
            }
        } else if (parts[0].equalsIgnoreCase("random")) {
            event.getChannel().sendMessage("Generating random numbers...").queue();

            EmbedBuilder embedBuilder = new EmbedBuilder();

            for (int i = 0; i < 5; i++) {
                embedBuilder.addField(random.inclusive(100) + ", ", "", true);
            }
            MessageEmbed embed = embedBuilder.setColor(Color.RED).build();

            event.getChannel().sendMessage(embed).queue();
        } else if (parts[0].equalsIgnoreCase("walmart")) {
            //Test UPC : 619659156442
            if (parts.length < 1) {
                event.getChannel().sendMessage("Please use \"!walmart help\" for more information about this command.").queue();
                return;
            }
            switch (parts[1]) {
                case "upc": //https://i.imgur.com/MyunjQL.png
                    String url = "https://search.mobile.walmart.com/v1/products-by-code/UPC/" + parts[2];
                    try (InputStream is = new URL(url).openStream();
                         Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {

                        Gson gson = new Gson();
                        WalmartItem walmartItem = gson.fromJson(reader, WalmartItem.class);

                        Common common = walmartItem.getData().getCommon();
                        Online online = walmartItem.getData().getOnline();
                        Inventory inventory = online.getInventory();

                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setColor(Color.BLUE);
                        embedBuilder.setAuthor("Walmart UPC Lookup", null, "https://upload.wikimedia.org/wikipedia/en/thumb/1/14/Walmart_Spark.svg/47px-Walmart_Spark.svg.png");
                        //embedBuilder.setThumbnail(common.getProductImageUrl());
                        embedBuilder.setTitle(common.getName(), common.getProductUrl());
                        float price = online.getPrice().getPriceInCents();
                        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
                        String priceToString = n.format(price / 100.0);
                        embedBuilder.addField("Price", priceToString + " " + online.getPrice().getCurrencyUnit(), false);
                        embedBuilder.addField("Item's Status", inventory.getStatus(), false);
                        embedBuilder.addField("Average Rating", common.getCustomerRating().getRating() + "", false);
                        embedBuilder.setImage(common.getProductImageUrl());

                        event.getChannel().sendMessage(embedBuilder.build()).queue();

                    } catch (Exception e) {
                        if (e instanceof FileNotFoundException) {
                            event.getChannel().sendMessage("Item was not found!").queue();
                        } else {
                            event.getChannel().sendMessage("Error looking up item.").queue();
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    event.getChannel().sendMessage("Please use \"!walmart help\" for more information about this command.").queue();
                    break;
            }
        }
    }

    public void administratorCommands(MessageReceivedEvent event, String command, String[] parts) {
        if (parts[0].equalsIgnoreCase("setprefix")) {
            String newPrefix = parts[1];
            setPrefix(newPrefix);
            event.getChannel().sendMessage("Your new prefix is " + getPrefix() + ".").queue();
        } else if (parts[0].equalsIgnoreCase("getprefix")) {
            event.getChannel().sendMessage("The prefix is " + getPrefix() + ".").queue();
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
//            event.getChannel().sendMessage("Hey big daddy " + member.getAsMention() + ", your new role is " + role.getAsMention() + ".").queue();
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
            try {
                event.getGuild().getController().removeSingleRoleFromMember(member, role).queue();
            } catch (Exception e) {
                if (e instanceof HierarchyException) {
                    event.getChannel().sendMessage("HierarchyException changing [").append(member.getEffectiveName()).append("]: ").append(e.getMessage()).queue();
                } else {
                    event.getChannel().sendMessage("Unknown error while changing role [")
                            .append(member.getEffectiveName())
                            .append("]: <").append(e.getClass().getSimpleName()).append(">: ")
                            .append(e.getMessage()).queue();
                }
            }
//            event.getChannel().sendMessage("Hey big daddy " + member.getAsMention() + ", you lost the role " + role.getAsMention() + ".").queue();
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
