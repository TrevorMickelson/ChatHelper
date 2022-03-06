package com.codepunisher.chathelper.util;

import org.bukkit.entity.Player;

public class ChatHelper {
    /**
     * This method formats the time
     * until a specific time/date
     *
     * Makes it human readable for in-game
     */
    public static String formatTime(long millis) {
        long totalSeconds = (millis + 1000) / 1000;

        // Setting up times
        long days = totalSeconds / 86400;
        long hours = (totalSeconds / 3600) - (days * 24);
        long minutes = (totalSeconds / 60) - (days * 1440) - (hours * 60);
        long seconds = totalSeconds - (days * 86400) - (hours * 3600) - (minutes * 60);

        // Adding values to string
        String formatted = (days <= 0 ? "" : days + "d ") + (hours <= 0 ? "" : hours + "h ") +
                (minutes <= 0 ? "" : minutes + "m ") + (seconds <= 0 ? "" : seconds + "s");

        return formatted.isEmpty() ? "1s" : formatted;
    }

    /**
     * If the user bypasses all of the checks
     */
    public static boolean bypassesEverything(Player player) {
        return player.hasPermission("chathelper.bypass");
    }
}
