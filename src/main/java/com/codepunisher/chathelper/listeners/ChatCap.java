package com.codepunisher.chathelper.listeners;

import com.codepunisher.chathelper.ChatMain;
import com.codepunisher.chathelper.util.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatCap {
    private final ConfigManager configManager = ChatMain.getInstance().getConfigManager();

    private boolean onChat(AsyncPlayerChatEvent event) {
        if (hasTooManyCaps(event.getMessage())) {
            Player player = event.getPlayer();

            event.setCancelled(true);
            String message = configManager.getMaxCapsMessage();

            if (!message.isEmpty())
                player.sendMessage(message);
            return true;
        }

        return false;
    }

    private boolean hasTooManyCaps(String message) {
        int maxCaps = configManager.getMaxCaps();

        if (maxCaps <= 0)
            return false;

        int capCounter = 0;
        for (int i = 0; i < message.length(); i++) {
            if (Character.isUpperCase(message.charAt(i)))
                capCounter++;
        }

        return capCounter >= maxCaps;
    }

    public boolean isMaxCaps(AsyncPlayerChatEvent event) { return onChat(event); }
}
