package es.meriland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messager {

    private final static String prefix = colorizar("&7[&9ETSISI&7]&a ");

    public static String colorizar(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


    public static void sendMessage(Player player, String msg) {
        player.sendMessage(prefix + colorizar(msg));
    }

    public static void broadcastMessage(String msg) {
        Bukkit.broadcastMessage(prefix + colorizar(msg));
    }
}
