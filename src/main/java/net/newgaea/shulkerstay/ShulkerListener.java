package net.newgaea.shulkerstay;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.HashMap;
import java.util.Map;

public class ShulkerListener implements Listener {

    private ShulkerStayPlugin plugin;
    public Map<Location,Integer> deaths;

    public ShulkerListener(ShulkerStayPlugin plugin) {
        deaths=new HashMap<>();
        this.plugin=plugin;
    }


    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if(entity instanceof Enderman) {
            if(entity.getWorld().getEnvironment()== World.Environment.THE_END) {
                if(entity.getLocation().getBlock().getBiome()== Biome.END_HIGHLANDS || entity.getLocation().getBlock().getBiome()== Biome.END_MIDLANDS) {
                    if(entity.getLocation().subtract(0,1,0).getBlock().getType().toString().contains("PURPUR") || entity.getLocation().getBlock().getType().toString().contains("PURPUR") ||
                            entity.getLocation().subtract(0,1,0).getBlock().getType().toString().contains("BRICKS") || entity.getLocation().getBlock().getType().toString().contains("BRICKS")) {
                        Location location = entity.getLocation();
                        World    world    = entity.getWorld();
                        for(Entity ent:event.getEntity().getNearbyEntities(plugin.getDistanceBetweenShulkers()/2, plugin.getDistanceBetweenShulkers()/2,plugin.getDistanceBetweenShulkers()/2 )) {
                            if (ent instanceof Shulker)
                                return;
                        }
                        event.setCancelled(true);
                        plugin.logDebug("Spawning at location "+location);
                        world.spawn(location,Shulker.class);
                    }


                }
            }
        }
    }
}
