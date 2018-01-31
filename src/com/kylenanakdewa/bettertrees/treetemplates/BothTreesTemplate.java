package com.kylenanakdewa.bettertrees.treetemplates;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

import com.kylenanakdewa.bettertrees.BetterTreesPlugin;

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
        TreeTemplate template = pickRandom();
        boolean placed = template.placeTree(location, player);
        if(!placed && template instanceof CustomTreeTemplate) placed = vanilla.placeTree(location, player);
        return placed;
	}

	@Override
	public boolean placeTree(Location location) {
        TreeTemplate template = pickRandom();
        boolean placed = template.placeTree(location);
        if(!placed && template instanceof CustomTreeTemplate) placed = vanilla.placeTree(location);
        return placed;
	}

    /** Picks either the vanilla or custom tree template. */
    private TreeTemplate pickRandom(){
        return (ThreadLocalRandom.current().nextDouble(100.0) <= BetterTreesPlugin.customChances.get(treeType))
            ? custom : vanilla;
    }
}