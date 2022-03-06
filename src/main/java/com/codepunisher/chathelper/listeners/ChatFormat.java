package com.codepunisher.chathelper.listeners;

import com.codepunisher.chathelper.ChatMain;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat {
    private final ChatMain plugin = ChatMain.getInstance();

    private void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String format = getChatFormat(player, event.getMessage());

        if (format.isEmpty())
            return;

        // Place holder api support of the plugin is enabled
        if (plugin.isPlaceHolderAPI())
            format = PlaceholderAPI.setPlaceholders(player, format);

        event.setFormat(format);
    }

    public String getChatFormat(Player player, String msg) {
        String configFormat = plugin.getConfigManager().getChatFormat();

        if (configFormat.isEmpty())
            return configFormat;

        return configFormat.replace("%player%", player.getName()).replace("%message%", msg);
    }

    public void handleChatFormat(AsyncPlayerChatEvent event) { onChat(event); }
}
