package es.meriland;

import es.meriland.cmds.MaskCMD;
import es.meriland.listeners.PlayerClickListener;
import es.meriland.listeners.PlayerJoinListener;
import es.meriland.listeners.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpaghettiCode extends JavaPlugin {

    private static SpaghettiCode instance;


    public void onEnable() {
        System.out.println("Spaghetti Code despierta");
        instance = this;
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getServer().getPluginCommand("mask").setExecutor(new MaskCMD());
    }

    public void onDisable() {
        System.out.println("Spaghetti Code se va a dormir");
    }

    public static SpaghettiCode getInstance() {
        return instance;
    }

}
