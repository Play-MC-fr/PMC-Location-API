package fr.sukikui.pmc_location_api;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        // Only /requests_count is implemented so no need to check the command
        Map<RequestResponse, Integer> countMap = new HashMap<>();

        for (RequestResponse value : Server.getRequests())
        {
            countMap.put(value, countMap.getOrDefault(value, 0) + 1);
        }

        sender.sendMessage("");
        sender.sendMessage("§lPMC-Location-API Requests");

        if (countMap.isEmpty())
            sender.sendMessage("§7No requests have been sent yet since the server reloaded.");

        for (Map.Entry<RequestResponse, Integer> entry : countMap.entrySet())
        {
            RequestResponse key = entry.getKey();
            int value = entry.getValue();
            sender.sendMessage("§7§l[§r" + key.color + key.code + "§7§l]§r§7 " + key.message + " -> " + value);
        }

        sender.sendMessage("");
        return true;
    }
}
