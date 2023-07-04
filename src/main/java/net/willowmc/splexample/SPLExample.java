package net.willowmc.splexample;

import org.bukkit.plugin.java.JavaPlugin;

public final class SPLExample extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        // Moved spl startup elsewhere for cleaner main class
        Features.init(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
