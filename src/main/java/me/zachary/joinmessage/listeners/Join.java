package me.zachary.joinmessage.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.zachary.joinmessage.JoinMessage;
import me.zachary.joinmessage.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Join implements Listener {

    private static JoinMessage plugin;

    public Join(JoinMessage plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
        Bukkit.getServer().getOnlinePlayers().toArray(players);
        for (Player player : players) {
            if(player.hasPermission("joinmessage.staff.join") && plugin.getConfig().getBoolean("Staff_join_message_enable") && p.hasPermission("joinmessage.staff.join")){
                player.sendMessage(Utils.color(plugin.getConfig().getString("Staff_join_message")).replace("<player>", p.getName()));
            }
        }
        if(hasPermission(p, "joinmessage.join.disable")){
            e.setJoinMessage("");
            return;
        }
        String JoinMessage = plugin.getConfig().getString("Join_message");
        String WelcomeMessage = plugin.getConfig().getString("Welcome_message");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            WelcomeMessage = PlaceholderAPI.setPlaceholders(p, WelcomeMessage);
            JoinMessage = PlaceholderAPI.setPlaceholders(p, JoinMessage);
        }
        if (plugin.getConfig().getBoolean("Join_enable")){
            if (p.hasPlayedBefore()){
                e.setJoinMessage(Utils.color(JoinMessage).replace("<player>", p.getName()));
            }else {
                if (plugin.getConfig().getBoolean("Welcome_message_enable")) {
                    e.setJoinMessage(Utils.color(WelcomeMessage).replace("<player>", p.getName()));
                } else {
                    e.setJoinMessage("");
                }
            }
        } else if(!p.hasPlayedBefore()){
            if (plugin.getConfig().getBoolean("Welcome_message_enable")) {
                if (!p.hasPlayedBefore()) {
                    e.setJoinMessage(Utils.color(WelcomeMessage).replace("<player>", p.getName()));
                }else{
                    e.setJoinMessage("");
                }
            }else {
                e.setJoinMessage("");
            }
        } else {
            e.setJoinMessage("");
        }
        if(plugin.getConfig().getBoolean("Send_Message_To_A_Player_On_First_Join") && !p.hasPlayedBefore()){
            for(String message : plugin.getConfig().getStringList("Message_To_A_Player_On_First_Join")){
                if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
                    message = PlaceholderAPI.setPlaceholders(p, message);
                }
                p.sendMessage(Utils.color(message).replace("<player>", p.getName()));
            }
        }else if(plugin.getConfig().getBoolean("Send_Message_To_A_Player_On_Join") && p.hasPlayedBefore()){
            for(String message : plugin.getConfig().getStringList("Message_To_A_Player_On_Join")){
                if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
                    message = PlaceholderAPI.setPlaceholders(p, message);
                }
                p.sendMessage(Utils.color(message).replace("<player>", p.getName()));
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent e){
        Player p = e.getPlayer();
        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
        Bukkit.getServer().getOnlinePlayers().toArray(players);
        for (Player player : players) {
            if(player.hasPermission("joinmessage.staff.quit") && plugin.getConfig().getBoolean("Staff_leave_message_enable") && p.hasPermission("joinmessage.staff.quit")){
                player.sendMessage(Utils.color(plugin.getConfig().getString("Staff_leave_message")).replace("<player>", p.getName()));
            }
        }
        if(hasPermission(p, "joinmessage.quit.disable")){
            e.setQuitMessage("");
            return;
        }
        String QuitMessage = plugin.getConfig().getString("Leave_message");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            QuitMessage = PlaceholderAPI.setPlaceholders(p, QuitMessage);
        }
        if (plugin.getConfig().getBoolean("Leave_enable")){
            if (p.hasPlayedBefore()){
                e.setQuitMessage(Utils.color(QuitMessage).replace("<player>", p.getName()));
            }else {
                e.setQuitMessage(Utils.color(QuitMessage).replace("<player>", p.getName()));
            }
        }else {
            e.setQuitMessage("");
        }
    }

    boolean hasPermission(Player player, String permission)
    {
        Permission p = new Permission(permission, PermissionDefault.FALSE);
        return player.hasPermission(p);
    }
}

