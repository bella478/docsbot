package com.bella478.docsbot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class Commands extends ListenerAdapter {
    static final String prefix = DocsBot.prefix;
    static final String[] commands = {prefix + "ping", prefix + "java"};
    static ArrayList<String> modulesList = new ArrayList<String>();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        boolean isCommand = false;

        // Check if message author is a bot to avoid infinitely executing the code below
        if (!event.getAuthor().isBot()){

            // Reading external list of packages into an ArrayList<String>
            try {
                Path dir = Paths.get("").toAbsolutePath();
                BufferedReader br = new BufferedReader(new FileReader(dir + "/modulesList.txt"));
                String line;
                while((line = br.readLine()) != null) {
                    modulesList.add(line.trim());
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Check if command exists
            for (int i = 0; i < commands.length; i++) {
                if (args[0].equalsIgnoreCase(commands[i])) {
                    isCommand = true;
                    break;
                }
            }

            // Check which command was entered only if command exists
            if (isCommand) {
                if (args[0].equalsIgnoreCase(prefix + "ping")) {
                    channel.sendTyping().queue();
                    channel.sendMessage("pong").queue();
                }

                if (args[0].equalsIgnoreCase(prefix + "java")) {
                    if (args.length < 2) {
                        channel.sendMessage("Not enough arguments supplied, check db!help java for correct usage").queue();
                    } else {
                        switch (args[1]) {
                            case "module":
                                if (args.length == 2) {
                                    EmbedBuilder usage = new EmbedBuilder();
                                    usage.setTitle("Usage");
                                    usage.addField("db!java module <package>", "Displays information about a specific module", false);
                                    usage.addField("db!java listmodules", "Lists modules from the java api", false);
                                    usage.setColor(Color.RED);
                                    usage.setFooter(Objects.requireNonNull(event.getMember()).getNickname(), event.getAuthor().getAvatarUrl());
                                    channel.sendMessage(usage.build()).queue();
                                    usage.clear();
                                }
                                break;

                            case "listmodules":
                                EmbedBuilder modules = new EmbedBuilder();
                                modules.setTitle("Java modules list");
                                for (int i = 0; i < modulesList.size(); i++) {
                                    modules.addField(modulesList.get(i), "", false);
                                }
                                modules.setColor(Color.CYAN);
                                modules.setFooter(Objects.requireNonNull(event.getMember()).getNickname(), event.getAuthor().getAvatarUrl());
                                channel.sendMessage(modules.build()).queue();
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
