package fr.sukikui.pmc_location_api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerLocation
{
    public static PlayerLocation getFromName(String name)
    {
        Player player = Bukkit.getPlayer(name);
        if (player != null)
        {
            Location loc = player.getLocation();
            return new PlayerLocation(Objects.requireNonNull(loc.getWorld()).getName(),
                    loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        }
        return null;
    }

    public String world;
    public int x;
    public int y;
    public int z;

    public PlayerLocation(String world, int blockX, int blockY, int blockZ)
    {
        this.world = world;
        this.x = blockX;
        this.y = blockY;
        this.z = blockZ;
    }
}
