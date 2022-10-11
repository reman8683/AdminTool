package com.reman8683.admintool.discord.command;

import com.reman8683.admintool.discord.command.Manager.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;

import java.util.UUID;

import static com.reman8683.admintool.adminTool.ServerTickListener.server;
import static com.reman8683.admintool.ingame.Configuration.BroadCastGroup.BroadCastColor;
import static com.reman8683.admintool.ingame.Configuration.BroadCastGroup.BroadCastPrefer;

public class broadcast implements SlashCommand {
    @Override
    public void performCommand(SlashCommandEvent event, Member m, TextChannel channel) {
        String text = event.getOption("text").getAsString();
        event.reply("공지 게시 완료").setEphemeral(true).queue();
        String json = "[\"\",".concat(BroadCastPrefer.getValue()).concat(",{\"color\":\"").concat(BroadCastColor.getValue()).concat("\",\"text\":\" ").concat(text).concat("\"}]");
        server.getPlayerManager().broadcast(Text.Serializer.fromJson(json), MessageType.SYSTEM, UUID.randomUUID());
    }
}