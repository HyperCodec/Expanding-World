package me.tristandasavage.expandingworld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetExpandCenter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("setexpandcenter")) {
            if(!sender.hasPermission("expandingworld.setcenter")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                return true;
            }
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Console does not have a position to set it to");
            }
            Player plr = (Player) sender;
            for(World world : Bukkit.getWorlds()) {
                Location loc = new Location(world, plr.getLocation().getX(), plr.getLocation().getY(), plr.getLocation().getZ());
                world.getWorldBorder().setCenter(loc);
                world.setSpawnLocation(loc);
            }
            sender.sendMessage(ChatColor.GREEN + "Set the center to your location (in all worlds)");
        }
        return true;
    }
}
