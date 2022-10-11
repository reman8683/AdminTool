package com.reman8683.admintool.ingame;

import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.util.List;

import static java.util.List.of;

public class Configuration extends Config {
    public static final ConfigItemGroup mainGroup = new ConfigGroup();

    public static final List<ConfigItemGroup> configs = of(mainGroup);

    public Configuration() {
        super(configs, new File(FabricLoader.getInstance().getConfigDir().toFile(), "adminTool.json"), "adminTool");
    }

    public static class ConfigGroup extends ConfigItemGroup {
        public static final ConfigItem<String> Token = new ConfigItem<>("discord_bot_token", "", "discord_bot_token");

        public ConfigGroup() {
            super(of(Token, new BroadCastGroup()), "config");
        }
    }

    public static class BroadCastGroup extends ConfigItemGroup {
        public static final ConfigItem<String> BroadCastPrefer = new ConfigItem<>("prefer", "{\"text\":\"[\\uacf5\\uc9c0]\",\"color\":\"yellow\"}", "broadcast_prefer");
        public static final ConfigItem<String> BroadCastColor = new ConfigItem<>("color", "#FFFFFF", "broadcast_color");

        public BroadCastGroup() {
            super(of(BroadCastPrefer, BroadCastColor), "broadcast");
        }
    }
}
