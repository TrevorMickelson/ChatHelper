package com.codepunisher.chathelper.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {
    private final static HashMap<UUID, Cooldown> cooldownUsers = new HashMap<>();

    private final Map<String, Range> cooldowns = new HashMap<>();

    public Cooldown(final UUID uuid, final String type, final int cooldown) {
        cooldowns.put(type, new Range(cooldown));

        if (!cooldownUsers.containsKey(uuid)) {
            cooldownUsers.put(uuid, this);
        }
    }

    public boolean noMoreCooldown(String type) { return getTimeLeft(type) <= 0; }
    public long getTimeLeft(String type) {
        Range newRange = cooldowns.get(type);
        return (newRange.getStartTime() + (newRange.getCooldown() * 1000)) - System.currentTimeMillis();
    }

    public static boolean isInCooldown(UUID uuid, String type) {
        // Checking if they're in the map first
        if (cooldownUsers.containsKey(uuid)) {
            // Now I know they're in the map
            // I need to see if the cooldown is expired
            // so I can remove them
            Cooldown cooldown = cooldownUsers.get(uuid);

            if (cooldown.cooldowns.containsKey(type)) {
                if (cooldown.noMoreCooldown(type)) {
                    cooldown.cooldowns.remove(type);
                    return false;
                } else {
                    return true;
                }
            }

            return false;
        }

        return false;
    }

    public static void startCooldown(UUID uuid, String type, int time) {
        if (cooldownUsers.containsKey(uuid)) {
            Cooldown cooldown = cooldownUsers.get(uuid);
            cooldown.cooldowns.put(type, new Range(time));
        } else {
            new Cooldown(uuid, type, time);
        }
    }
    public static void stopCooldown(UUID uuid, String type) {
        if (isInCooldown(uuid, type)) {
            Cooldown cooldown = cooldownUsers.get(uuid);
            cooldown.cooldowns.remove(type);
        }
    }
    public static long getTimeLeft(UUID uuid, String type) { return cooldownUsers.get(uuid).getTimeLeft(type); }

    // This is the range object used for each
    // Cooldown, because each cooldown needs
    // a different start time + cooldown time
    private final static class Range {
        private final long startTime;
        private final int cooldown;

        public Range(int cooldown) {
            this.startTime = System.currentTimeMillis();
            this.cooldown = cooldown;
        }

        public long getStartTime() { return this.startTime; }
        public int getCooldown() { return this.cooldown; }
    }
}
