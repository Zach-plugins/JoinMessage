package me.zachary.joinmessage.Listeners;

import me.zachary.joinmessage.JoinMessage;
import me.zachary.joinmessage.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Join implements Listener {

    private static JoinMessage plugin;

    public Join(JoinMessage plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (plugin.getConfig().getBoolean("Join_enable")){
            if (p.hasPlayedBefore()){
                e.setJoinMessage(Utils.chat(plugin.getConfig().getString("Join_message").replace("<player>", p.getName())));
                //p.sendTitle("test", "test2", 1, 5, 1);
            }else {
                if (plugin.getConfig().getBoolean("Welcome_message_enable")) {
                    e.setJoinMessage(Utils.chat(plugin.getConfig().getString("Welcome_message").replace("<player>", p.getName())));
                } else {
                    e.setJoinMessage("");
                }
            }
        } else if(!p.hasPlayedBefore()){
            if (plugin.getConfig().getBoolean("Welcome_message_enable")) {
                if (!p.hasPlayedBefore()) {
                    e.setJoinMessage(Utils.chat(plugin.getConfig().getString("Welcome_message").replace("<player>", p.getName())));
                }else{
                    e.setJoinMessage("");
                }
            }else {
                e.setJoinMessage("");
            }
        } else {
            e.setJoinMessage("");
        }
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (plugin.getConfig().getBoolean("Leave_enable")){
            if (p.hasPlayedBefore()){
                e.setQuitMessage(Utils.chat(plugin.getConfig().getString("Leave_message").replace("<player>", p.getName())));
            }else {
                e.setQuitMessage(Utils.chat(plugin.getConfig().getString("Leave_message").replace("<player>", p.getName())));
            }
        }else {
            e.setQuitMessage("");
        }
    }
}

