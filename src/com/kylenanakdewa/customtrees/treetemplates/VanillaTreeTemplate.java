package com.kylenanakdewa.customtrees.treetemplates;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

/**
 * Represents a vanilla Minecraft tree.
 */
public class VanillaTreeTemplate extends TreeTemplate {

    VanillaTreeTemplate(TreeType treeType){
        super(treeType);
    }

	@Override
	public boolean placeTree(Location location, Player player) {
		return placeTree(location);
    }

	@Override
	public boolean placeTree(Location location){
		return location.getWorld().generateTree(location, treeType);
    }

}