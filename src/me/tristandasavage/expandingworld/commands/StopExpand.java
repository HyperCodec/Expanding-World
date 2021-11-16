package me.tristandasavage.expandingworld.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.tristandasavage.expandingworld.Main.expander;
import static me.tristandasavage.expandingworld.Main.plugin;

public class StopExpand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("stopexpand")) {
            if(!sender.hasPermission("expandingworld.stop")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                return true;
            }
            if(!plugin.getConfig().getBoolean("started")) {
                sender.sendMessage(ChatColor.RED + "The world is not expanding right now");
                return true;
            }
            expander.cancel();
            plugin.getConfig().set("started", false);
            plugin.saveConfig();
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Stopped border expansion");
        }
        return true;
    }
}
