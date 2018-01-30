package com.kylenanakdewa.customtrees;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

/**
 * SaplingListener
 */
final class SaplingListener implements Listener {

    @EventHandler
    public void onSaplingGrowth(StructureGrowEvent event){
        if(event.isCancelled()) return;

        
    }
}