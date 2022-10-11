package com.reman8683.admintool.discord.command;
import com.reman8683.admintool.discord.command.Manager.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import static com.reman8683.admintool.adminTool.ServerTickListener.server;

public class kick implements SlashCommand {
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        String target = event.getOption("target").getAsString();
        String reason = event.getOption("reason").getAsString();
        event.reply(target.concat("을(를) ").concat(reason).concat("의 사유로 킥되었습니다")).setEphemeral(true).queue();
        server.getCommandManager().execute(server.getCommandSource(), "kick ".concat(target).concat(" ").concat(reason));
    }
}