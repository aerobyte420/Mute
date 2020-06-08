package me.aerobyte.mute.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String chat(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> chat(List<String> s) { return s.stream().map(Utils::chat).collect(Collectors.toList());
    }

}
