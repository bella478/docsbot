package com.bella478.docsbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class DocsBot {
    public static String prefix = "db!";

    public static void main(String[] args) {
        try{
            JDA jda = JDABuilder.createDefault("ODY4OTM4ODg5NTY4NTg3Nzc3.YP28Zw.aj4SL-vPvocEtUv0LTX-UCTChrM").build();
            jda.getPresence().setStatus(OnlineStatus.ONLINE);
            jda.getPresence().setActivity(Activity.playing("db!help"));

            jda.addEventListener(new Commands());
        } catch (javax.security.auth.login.LoginException ex) {
            ex.printStackTrace();
        }

    }
}
