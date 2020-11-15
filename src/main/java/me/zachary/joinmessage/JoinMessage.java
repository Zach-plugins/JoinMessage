package me.zachary.joinmessage;

import me.zachary.joinmessage.commands.ReloadCommand;
import me.zachary.joinmessage.listeners.Join;
import me.zachary.zachcore.ZachCorePlugin;
import me.zachary.zachcore.utils.Metrics;

public final class JoinMessage extends ZachCorePlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new Join(this);
        new ReloadCommand(this);

        int pluginId = 7870; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        preEnable();
    }
}

