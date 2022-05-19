package com.codepunisher.chathelper.listeners;

import com.codepunisher.chathelper.ChatMain;
import com.codepunisher.chathelper.util.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatCurse {
    private final ConfigManager configManager = ChatMain.getInstance().getConfigManager();

    private boolean onChat(AsyncPlayerChatEvent event) {
        if (!shouldCheck())
            return false;

        if (hasBadWord(event.getMessage())) {
            Player player = event.getPlayer();

            event.setCancelled(true);
            String message = configManager.getAntiCurseMessage();

            if (!message.isEmpty())
                player.sendMessage(message);

            return true;
        }

        return false;
    }

    private boolean hasBadWord(String message) {
        // The idea here is to replace certain
        // chat bypasses to simply check for
        // the message itself
        String initialMessage = message
                .replace("1", "i")
                .replace("!", "i")
                .replace("l", "i")
                .replace("j", "i")
                .replace("3", "e")
                .replace("4", "a")
                .replace("@", "a")
                .replace("5", "s")
                .replace("$", "s")
                .replace("0", "o").toLowerCase();

        // This full message removes all spaces so that
        // it's one jumbled up message (This avoids the user doing this "F U C K)"
        String fullMessage = initialMessage.replace(" ", "");

        // Checking for ass by itself, because
        // of words like "grass" and "class"
        if (configManager.isAssCheck()) {
            for (String word : initialMessage.split(" ")) {
                if (word.startsWith("ass"))
                    return true;
            }
        }

        // Simply checking if the message contains a bad word
        if (!configManager.getCurseWords().isEmpty()) {
            for (String badWord : configManager.getCurseWords()) {
                if (fullMessage.contains(badWord.toLowerCase()))
                    return true;
            }
        }

        return false;
    }

    /**
     * If the plugin should even check
     * if there's a curse word or not
     */
    private boolean shouldCheck() {
        return !configManager.getCurseWords().isEmpty() || configManager.isAssCheck();
    }

    /**
     * Returning a boolean so that I can
     * easily return in the other method
     * to avoid unnecessary further checks
     */
    public boolean isCursedChat(AsyncPlayerChatEvent event) { return onChat(event); }
}
