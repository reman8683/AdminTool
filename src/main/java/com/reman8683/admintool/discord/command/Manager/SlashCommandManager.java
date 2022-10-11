package com.reman8683.admintool.discord.command.Manager;

import com.reman8683.admintool.discord.command.ban;
import com.reman8683.admintool.discord.command.broadcast;
import com.reman8683.admintool.discord.command.kick;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.reman8683.admintool.discord.bot.jda;

public class SlashCommandManager extends ListenerAdapter {

    public Map<String, SlashCommand> commandsMap;
    public SlashCommandManager() {
        commandsMap = new ConcurrentHashMap<>();
        CommandListUpdateAction commands = jda.updateCommands();
        //--------------------------------------------------------------------------------------------------------------
        commandsMap.put("kick", new kick());
        commands.addCommands(new CommandData("kick", "킥").addOptions(
                new OptionData(OptionType.STRING, "target", "대상").setRequired(true),
                new OptionData(OptionType.STRING, "reason", "사유").setRequired(true)));

        commandsMap.put("ban", new ban());
        commands.addCommands(new CommandData("ban", "밴").addOptions(
                new OptionData(OptionType.STRING, "target", "대상").setRequired(true),
                new OptionData(OptionType.STRING, "reason", "사유").setRequired(true)));

        commandsMap.put("broadcast", new broadcast());
        commands.addCommands(new CommandData("broadcast", "공지").addOptions(
                new OptionData(OptionType.STRING, "text", "공지").setRequired(true)));

        //--------------------------------------------------------------------------------------------------------------
        commands.queue();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String commandName = event.getName();

        SlashCommand command;

        if((command = commandsMap.get(commandName)) != null) {
            try {
                command.performCommand(event, event.getMember(), event.getTextChannel());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
