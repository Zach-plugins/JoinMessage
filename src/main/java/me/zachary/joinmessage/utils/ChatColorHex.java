package me.zachary.joinmessage.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColorHex {

    private static final Pattern regex = Pattern.compile("&#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3}).*?");

    public static String parseHexColors(String message) {
        final Matcher matcher = regex.matcher(message);
        while (matcher.find()) {
            final String color = matcher.group();
            final String hexcolor = color.substring(1);
            ChatColor c = null;
            try {
                c = ChatColor.of(hexcolor);
            } catch (final Exception ignored) {
            }
            if (c != null) {
                message = message.replaceAll(color, c.toString());
            }
        }
        return message;
    }
}
