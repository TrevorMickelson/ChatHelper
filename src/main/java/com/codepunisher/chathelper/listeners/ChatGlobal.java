package com.codepunisher.chathelper.listeners;

import com.codepunisher.chathelper.ChatMain;
import com.codepunisher.chathelper.util.ChatHelper;
import me.drepic.proton.common.ProtonManager;
import me.drepic.proton.common.message.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatGlobal {
    @EventHandler
    private void onChat(AsyncPlayerChatEvent event) {
        ProtonManager manager = ChatMain.getInstance().getManager();

        // Plugin isn't enabled
        if (manager == null) return;

        event.setCancelled(true);
        manager.send("networkChat", "chat", ChatHelper.getFormattedChatMessage(event.getPlayer(), event.getMessage()), "network");
    }

    @MessageHandler(namespace = "networkChat", subject = "chat")
    public void onTest(String formattedMessage) {
        Bukkit.broadcastMessage(formattedMessage);
    }

    public void handleGlobalChat(AsyncPlayerChatEvent event) { onChat(event); }
}
