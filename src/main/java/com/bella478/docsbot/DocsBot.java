package com.bella478.docsbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class DocsBot {
    public static String prefix = "db!";

    public static void main(String[] args) {
        try{
            JDA jda = JDABuilder.createDefault("i almost forgot to remove my token before committing this lol").build();
            jda.getPresence().setStatus(OnlineStatus.ONLINE);
            jda.getPresence().setActivity(Activity.playing("db!help"));
            jda.addEventListener(new Commands());
        } catch (javax.security.auth.login.LoginException ex) {
            ex.printStackTrace();
        }

    }
}
