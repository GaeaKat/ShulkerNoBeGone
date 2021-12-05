package net.newgaea.shulkerstay;

import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ShulkerStayPlugin extends JavaPlugin {
    private final Logger logger = this.getLogger();
    public boolean debug=false;
    private double distanceBetweenShulkers = 10;

    public double getDistanceBetweenShulkers() {
        return distanceBetweenShulkers;
    }

    public void setDistanceBetweenShulkers(double distanceBetweenShulkers) {
        this.distanceBetweenShulkers = distanceBetweenShulkers;
        getConfig().set("distanceBetweenShulkers", distanceBetweenShulkers);
        saveConfig();
    }

    public void logDebug(String message) {
        if(debug)
            logger.warning(message);
    }
    ShulkerListener listener;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        debug=getConfig().getBoolean("debug");
        distanceBetweenShulkers=getConfig().getDouble("distanceBetweenShulkers");
        PaperLib.suggestPaper(this);
        listener=new ShulkerListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(listener,this);
    }
}
