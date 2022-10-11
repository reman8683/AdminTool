package com.reman8683.admintool;

import com.oroarmor.config.Config;
import com.reman8683.admintool.discord.bot;
import com.reman8683.admintool.ingame.Configuration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class adminTool implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("admintool");
	public static Config CONFIG = new Configuration();

	@Override
	@Environment(EnvType.SERVER)
	public void onInitialize() {
		CONFIG.readConfigFromFile();
		CONFIG.saveConfigToFile();
		ServerLifecycleEvents.SERVER_STOPPED.register(instance -> CONFIG.saveConfigToFile());

		ServerTickEvents.END_SERVER_TICK.register(new ServerTickListener());

		LOGGER.info("Hello Fabric world!");
		new Thread(() -> {
			try {
				bot.bot();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public class ServerTickListener implements ServerTickEvents.EndTick {
		public static MinecraftServer server;
		@Override
		@Environment(EnvType.SERVER)
		public void onEndTick(MinecraftServer server){
			ServerTickListener.server = server;
		}
	}
}
