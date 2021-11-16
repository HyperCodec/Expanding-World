package me.tristandasavage.expandingworld.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

import static me.tristandasavage.expandingworld.Main.plugin;

public class SetExpandSize implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("setexpandsize")) {
            if(!sender.hasPermission("expandingworld.setsize")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                return true;
            }
            if(args.length < 1) {
                sender.sendMessage(ChatColor.RED + "Missing required argument size");
                return true;
            }
            try {
                for (World world : Bukkit.getWorlds()) {
                    world.getWorldBorder().setSize(Integer.parseInt(args[0]));
                }
            }
            catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Provided value " + args[0] + " is not a number!");
                plugin.getLogger().log(Level.WARNING, "Player did not enter an int into setexpandsize", e);
                return true;
            }
            sender.sendMessage(ChatColor.GREEN + "Set the world size to " + args[0] + " (in all worlds)");
        }
        return true;
    }
}
