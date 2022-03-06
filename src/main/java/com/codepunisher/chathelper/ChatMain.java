package com.codepunisher.chathelper;

import com.codepunisher.chathelper.commands.ChatHelperReload;
import com.codepunisher.chathelper.util.ConfigManager;
import com.codepunisher.chathelper.util.ListenerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ChatMain extends JavaPlugin {
    private static ChatMain chatMain;
    public static ChatMain getInstance() { return chatMain; }

    private ConfigManager configManager;
    public ConfigManager getConfigManager() { return configManager; }

    private boolean placeHolderAPI;
    public boolean isPlaceHolderAPI() { return placeHolderAPI; }

    @Override
    public void onEnable() {
        chatMain = this;
        registerConfig();
        registerListeners();
        registerCommand();
        checkForPlaceHolderAPI();
    }

    private void registerConfig() {
        saveDefaultConfig();
        configManager = new ConfigManager();
        configManager.initConfigValues();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ListenerManager(), this);
    }

    private void registerCommand() {
        Objects.requireNonNull(getCommand("chathelperreload")).setExecutor(new ChatHelperReload());
    }

    private void checkForPlaceHolderAPI() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            placeHolderAPI = true;
            return;
        }

        placeHolderAPI = false;
    }
}
