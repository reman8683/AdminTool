package com.reman8683.admintool.discord;

import com.reman8683.admintool.discord.command.Manager.SlashCommandManager;
import com.reman8683.admintool.ingame.Configuration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class bot {
    public static JDA jda;
    public static SlashCommandManager slashCmdManager;

    public static void bot() throws Exception {
        JDABuilder builder = JDABuilder.createDefault(Configuration.ConfigGroup.Token.getValue());
        builder.enableCache(CacheFlag.VOICE_STATE);
        builder.setRawEventsEnabled(true);

        jda = builder.build();
        jda.addEventListener(slashCmdManager = new SlashCommandManager());
    }
}
