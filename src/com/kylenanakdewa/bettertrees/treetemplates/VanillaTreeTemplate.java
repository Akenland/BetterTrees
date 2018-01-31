package com.kylenanakdewa.bettertrees.treetemplates;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

import com.kylenanakdewa.bettertrees.BetterTreesPlugin;

/**
 * Represents a vanilla Minecraft tree.
 */
public class VanillaTreeTemplate extends TreeTemplate {

    public VanillaTreeTemplate(TreeType treeType){
        super(treeType);
    }

	@Override
	public boolean placeTree(Location location, Player player) {
		return placeTree(location);
    }

	@Override
	public boolean placeTree(Location location){
		boolean placed = location.getWorld().generateTree(location, treeType);
		if(!placed) BetterTreesPlugin.plugin.getLogger().warning("Failed to generate vanilla tree.");
		return placed;
    }

}