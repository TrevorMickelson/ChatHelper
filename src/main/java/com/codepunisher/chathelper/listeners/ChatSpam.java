package com.codepunisher.chathelper.listeners;

import com.codepunisher.chathelper.ChatMain;
import com.codepunisher.chathelper.util.Cooldown;
import com.codepunisher.chathelper.util.ChatHelper;
import com.codepunisher.chathelper.util.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class ChatSpam {
    private final ConfigManager configManager = ChatMain.getInstance().getConfigManager();

    private boolean onChat(AsyncPlayerChatEvent event) {
        if (configManager.getChatCooldown() <= 0)
            return false;

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (Cooldown.isInCooldown(uuid, "chatspam")) {
            long timeLeft = Cooldown.getTimeLeft(uuid, "chatspam");

            // Cancelling event
            event.setCancelled(true);
            String message = configManager.getChatCooldownMessage();

            if (!message.isEmpty())
                player.sendMessage(message.replace("%time%", ChatHelper.formatTime(timeLeft)));
            return true;
        } else {
            Cooldown.startCooldown(uuid, "chatspam", configManager.getChatCooldown());
            return false;
        }
    }

    public boolean didSpam(AsyncPlayerChatEvent event) { return onChat(event); }
}
