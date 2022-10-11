package com.reman8683.admintool.discord.command.Manager;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.io.IOException;
import java.net.URISyntaxException;

public interface SlashCommand {
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) throws IOException, URISyntaxException;
}
