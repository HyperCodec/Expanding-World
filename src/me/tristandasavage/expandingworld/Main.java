package me.tristandasavage.expandingworld;

import me.tristandasavage.expandingworld.commands.SetExpandCenter;
import me.tristandasavage.expandingworld.commands.SetExpandSize;
import me.tristandasavage.expandingworld.commands.StartExpand;
import me.tristandasavage.expandingworld.commands.StopExpand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class Main extends JavaPlugin {
    public static Plugin plugin;

    public static BukkitRunnable expander = new BukkitRunnable() {
        @Override
        public void run() {
            if(Bukkit.getWorlds().get(0).getTime() == 1) {
                for(World world : Bukkit.getWorlds()) {
                    world.getWorldBorder().setSize(world.getWorldBorder().getSize() + plugin.getConfig().getInt("increaseby"));
                }
                Bukkit.broadcastMessage(ChatColor.GREEN + "The border has expanded!");
            }
        }
    };

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Expanding World plugin unloaded");
    }

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        this.getServer().getPluginCommand("startexpand").setExecutor(new StartExpand());
        this.getServer().getPluginCommand("stopexpand").setExecutor(new StopExpand());
        this.getServer().getPluginCommand("setexpandcenter").setExecutor(new SetExpandCenter());
        this.getServer().getPluginCommand("setexpandsize").setExecutor(new SetExpandSize());
        if(this.getConfig().getBoolean("started")) {
            expander.runTaskTimer(this, 1L, 1L);
            this.getLogger().log(Level.INFO, "Started border expander");
        }
        this.getLogger().log(Level.INFO, "Expanding World v1.0 loaded");
    }

}
