package com.kylenanakdewa.customtrees.treetemplates;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

import com.kylenanakdewa.customtrees.CustomTreesPlugin;

/**
 * Represents both vanilla and custom trees, picked at random.
 */
public class BothTreesTemplate extends TreeTemplate {

    private final VanillaTreeTemplate vanilla;
    private final CustomTreeTemplate custom;

    public BothTreesTemplate(TreeType treeType){
        super(treeType);
        vanilla = new VanillaTreeTemplate(treeType);
        custom = new CustomTreeTemplate(treeType);
    }

	@Override
	public boolean placeTree(Location location, Player player) {
		return pickRandom().placeTree(location, player);
	}

	@Override
	public boolean placeTree(Location location) {
		return pickRandom().placeTree(location);
	}

    /** Picks either the vanilla or custom tree template. */
    private TreeTemplate pickRandom(){
        return (ThreadLocalRandom.current().nextDouble(100.0) <= CustomTreesPlugin.customChances.get(treeType))
            ? custom : vanilla;
    }
}