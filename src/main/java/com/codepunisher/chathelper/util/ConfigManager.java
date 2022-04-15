package com.codepunisher.chathelper.util;

import com.codepunisher.chathelper.ChatMain;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public class ConfigManager {
    private final ChatMain plugin = ChatMain.getInstance();
    private final File dataFile = new File(plugin.getDataFolder(), "config.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(dataFile);

    private List<String> curseWords;
    private final Map<String, Integer> cmdCooldowns = new HashMap<>();
    private int maxCaps;
    private int chatCooldown;
    private boolean assCheck;
    private boolean globalChat;
    private String chatFormat;
    private String antiCurseMessage;
    private String maxCapsMessage;
    private String chatCooldownMessage;
    private String cmdCooldownMessage;
    private String reloadMessage;

    public List<String> getCurseWords() { return curseWords; }
    public Map<String, Integer> getCmdCooldowns() { return cmdCooldowns; }
    public int getMaxCaps() { return maxCaps; }
    public int getChatCooldown() { return chatCooldown; }
    public boolean isAssCheck() { return assCheck; }
    public boolean isGlobalChat() { return globalChat; }
    public String getChatFormat() { return chatFormat; }
    public String getAntiCurseMessage() { return antiCurseMessage; }
    public String getMaxCapsMessage() { return maxCapsMessage; }
    public String getChatCooldownMessage() { return chatCooldownMessage; }
    public String getCmdCooldownMessage() { return cmdCooldownMessage; }
    public String getReloadMessage() { return reloadMessage; }

    public void initConfigValues() {
        cmdCooldowns.clear();
        curseWords = config.getStringList("AntiCurse");

        config.getStringList("CommandCooldowns").forEach(cmd -> {
            String command = cmd.substring(0, cmd.indexOf(":"));
            int cooldown = Integer.parseInt(cmd.substring(cmd.lastIndexOf(":") + 1));
            cmdCooldowns.put(command, cooldown);
        });

        maxCaps = config.getInt("MaxCaps");
        chatCooldown = config.getInt("ChatCooldown");
        assCheck = config.getBoolean("AssCheck");
        globalChat = config.getBoolean("GlobalChat");
        chatFormat = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("ChatFormat")));
        antiCurseMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("AntiCurseMessage")));
        maxCapsMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("MaxCapsMessage")));
        chatCooldownMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("ChatCooldownMessage")));
        cmdCooldownMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("CommandCooldownMessage")));
        reloadMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("ReloadMessage")));
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(dataFile);
        initConfigValues();
    }
}
