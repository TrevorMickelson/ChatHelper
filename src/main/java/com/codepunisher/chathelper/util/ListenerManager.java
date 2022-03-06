package com.codepunisher.chathelper.util;

import com.codepunisher.chathelper.listeners.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Handles all listeners in 1 class
 */
public class ListenerManager implements Listener {
    private final ChatFormat chatFormat = new ChatFormat();
    private final ChatCurse chatCurse = new ChatCurse();
    private final ChatSpam chatSpam = new ChatSpam();
    private final ChatCap chatCap = new ChatCap();
    private final CommandSpam commandSpam = new CommandSpam();

    @EventHandler (priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        chatFormat.handleChatFormat(event);

        if (ChatHelper.bypassesEverything(event.getPlayer()))
            return;

        if (chatSpam.didSpam(event))
            return;

        if (chatCap.isMaxCaps(event))
            return;

        chatCurse.handleChatCurse(event);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        commandSpam.handleCommandSpam(event);
    }
}
