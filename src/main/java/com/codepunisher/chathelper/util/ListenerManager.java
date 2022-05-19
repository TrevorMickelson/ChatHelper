package com.codepunisher.chathelper.util;

import com.codepunisher.chathelper.ChatMain;
import com.codepunisher.chathelper.listeners.*;
import me.drepic.proton.common.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Handles all listeners in 1 class
 */
public class ListenerManager implements Listener {
    private final ChatCurse chatCurse = new ChatCurse();
    private final ChatSpam chatSpam = new ChatSpam();
    private final ChatCap chatCap = new ChatCap();
    private final CommandSpam commandSpam = new CommandSpam();
    private final ChatGlobal chatGlobal = new ChatGlobal();

    @EventHandler (priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!ChatHelper.bypassesEverything(player)) {
            if (chatSpam.didSpam(event))
                return;

            if (chatCap.isMaxCaps(event))
                return;

            if (chatCurse.isCursedChat(event))
                return;
        }

        // Global chat/formatting
        if (ChatMain.getInstance().getConfigManager().isGlobalChat()) {
            chatGlobal.handleGlobalChat(event);
        } else {
            event.setFormat(ChatHelper.getFormattedChatMessage(player, event.getMessage()));
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        commandSpam.handleCommandSpam(event);
    }
}
