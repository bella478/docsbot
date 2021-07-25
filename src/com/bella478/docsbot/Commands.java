package com.bella478.docsbot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(DocsBot.prefix + "ping")) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("pong").queue();
        }
    }
}
