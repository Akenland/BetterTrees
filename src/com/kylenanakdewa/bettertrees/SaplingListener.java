package com.kylenanakdewa.bettertrees;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

import com.kylenanakdewa.bettertrees.treetemplates.CustomTreeTemplate;

/**
 * Provides control over sapling growth, including custom trees, and disabling particular trees.
 */
final class SaplingListener implements Listener {

    @EventHandler
    public void onSaplingGrowth(StructureGrowEvent event){
        if(event.isCancelled()) return;

        TreeSource saplingTreeSource = BetterTreesPlugin.saplingTrees.get(event.getSpecies());

        // If this sapling just does vanilla trees, do nothing
        if(saplingTreeSource.equals(TreeSource.VANILLA)) return;

        // If this sapling is disabled, cancel event
        if(saplingTreeSource.equals(TreeSource.NONE)){
            event.setCancelled(true);
            return;
        }

        // Chance of custom tree growing, when TreeSource is BOTH
        double customTreeChance = BetterTreesPlugin.customChances.get(event.getSpecies());

        // If sapling is growing with bone meal, add the modifier
        if(event.isFromBonemeal()) customTreeChance += BetterTreesPlugin.bonemealModifier;

        // If this sapling just does custom trees, or chance is high enough, cancel event and grow a custom tree
        if(saplingTreeSource.equals(TreeSource.CUSTOM) || ThreadLocalRandom.current().nextDouble(100.0) < customTreeChance){
            event.setCancelled(true);
            new CustomTreeTemplate(event.getSpecies()).placeTree(event.getLocation().subtract(0, 1, 0), event.getPlayer());
            return;
        }
    }

}