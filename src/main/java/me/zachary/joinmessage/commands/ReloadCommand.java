package me.zachary.joinmessage.commands;

import me.zachary.joinmessage.JoinMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    private JoinMessage plugin;

    public ReloadCommand(JoinMessage plugin){
        this.plugin = plugin;
        plugin.getCommand("joinmessagereload").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("joinmessage.reload")) {
            plugin.saveDefaultConfig();
            plugin.reloadConfig();
            player.sendMessage("§4You have successful reload config!");
            return true;
        }else{
            player.sendMessage("§4You don't have permission");
        }

        return false;
    }
}
