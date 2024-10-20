package fr.sukikui.pmc_location_api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerLocation
{
    public static PlayerLocation getFromName(String name)
    {
        Player player = Bukkit.getPlayer(name);
        return player != null ?
                new PlayerLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY()) : null;
    }

    public int x;
    public int y;

    public PlayerLocation(int blockX, int blockY)
    {
        this.x = blockX;
        this.y = blockY;
    }
}
