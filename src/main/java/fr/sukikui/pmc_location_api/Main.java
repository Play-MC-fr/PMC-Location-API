package fr.sukikui.pmc_location_api;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        this.getCommand("requests").setExecutor(new CommandManager());
        Server.start();
    }

    @Override
    public void onDisable()
    {
        Server.stop();
    }
}
