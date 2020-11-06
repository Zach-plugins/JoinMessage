package me.zachary.joinmessage.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private static final Pattern regex = Pattern.compile("&#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3}).*?");

    public static String parseHexColors(String message) {
        final Matcher matcher = regex.matcher(message);
        while (matcher.find()) {
            final String color = matcher.group();
            final String hexcolor = color.substring(1);
            net.md_5.bungee.api.ChatColor c = null;
            try {
                c = net.md_5.bungee.api.ChatColor.of(hexcolor);
            } catch (final Exception ignored) {
            }
            if (c != null) {
                message = message.replaceAll(color, c.toString());
            }
        }
        return message;
    }

    public static String color(String message){
        if(Utils.VERSION.contains("1_16")){
            message = Utils.parseHexColors(Utils.chat(message));
        }else{
            message = Utils.chat(message);
        }
        Bukkit.getServer().getVersion();
        return message;
    }

}
