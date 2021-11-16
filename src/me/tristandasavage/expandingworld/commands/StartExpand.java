package me.tristandasavage.expandingworld.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.tristandasavage.expandingworld.Main.expander;
import static me.tristandasavage.expandingworld.Main.plugin;

public class StartExpand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("startexpand")) {
            if(!sender.hasPermission("expandingworld.start")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                return true;
            }
            if(plugin.getConfig().getBoolean("started")) {
                sender.sendMessage(ChatColor.RED + "The border is already expanding");
                return true;
            }
            expander.runTaskTimer(plugin, 1L, 1L);
            plugin.getConfig().set("started", true);
            plugin.saveConfig();
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Started the border expansion");
        }
        return true;
    }
}
