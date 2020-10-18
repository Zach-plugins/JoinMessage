package me.zachary.joinmessage;

import me.zachary.joinmessage.Listeners.Join;
import me.zachary.joinmessage.Utils.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new Join(this);

        int pluginId = 7870; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

    }
}

