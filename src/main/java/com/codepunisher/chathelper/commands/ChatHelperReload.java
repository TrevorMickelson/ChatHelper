package com.codepunisher.chathelper.commands;

import com.codepunisher.chathelper.ChatMain;
import com.codepunisher.chathelper.util.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatHelperReload implements CommandExecutor {
    private final ConfigManager configManager = ChatMain.getInstance().getConfigManager();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        configManager.reloadConfig();
        sender.sendMessage(configManager.getReloadMessage());
        return false;
    }
}
