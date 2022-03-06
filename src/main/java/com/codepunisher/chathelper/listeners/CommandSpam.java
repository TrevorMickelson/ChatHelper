package com.codepunisher.chathelper.listeners;

import com.codepunisher.chathelper.ChatMain;
import com.codepunisher.chathelper.util.Cooldown;
import com.codepunisher.chathelper.util.ChatHelper;
import com.codepunisher.chathelper.util.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.UUID;

public class CommandSpam {
    private final ConfigManager configManager = ChatMain.getInstance().getConfigManager();

    private void onCommand(PlayerCommandPreprocessEvent event) {
        String command = getCommand(event.getMessage());

        // If the command is stored in the commands list
        if (!isCooldownCommand(command))
            return;

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        // If in cooldown
        if (Cooldown.isInCooldown(uuid, command)) {
            long timeLeft = Cooldown.getTimeLeft(uuid, command);

            // Cancelling event
            event.setCancelled(true);
            String message = configManager.getCmdCooldownMessage();

            if (!message.isEmpty())
                player.sendMessage(message.replace("%time%", ChatHelper.formatTime(timeLeft)).replace("%command%", command));
        } else {
            int cooldown = configManager.getCmdCooldowns().get(command);
            Cooldown.startCooldown(uuid, command, cooldown);
        }
    }

    /**
     * Gets the command from the message
     */
    private String getCommand(String message) {
        return message.split(" ")[0].replace("/", "").toLowerCase();
    }

    /**
     * If the specified command is stored
     * in the config list of command cooldowns
     */
    private boolean isCooldownCommand(String command) {
        if (configManager.getCmdCooldowns().isEmpty())
            return false;

        return configManager.getCmdCooldowns().get(command) != null;
    }

    public void handleCommandSpam(PlayerCommandPreprocessEvent event) { onCommand(event); }
}
