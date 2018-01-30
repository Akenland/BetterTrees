package com.kylenanakdewa.customtrees;

import com.kylenanakdewa.customtrees.treetemplates.TreeTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

/**
 * An expression for the types (species) of placed trees.
 */
class TreeExpression {

    /** The selected trees, and the percentage chance they will be placed. */
    private Map<TreeTemplate,Double> trees = new HashMap<TreeTemplate,Double>();

    TreeExpression(String expression){
        String[] entries = expression.toUpperCase().split(",");
        for(String entry : entries){

            // Chance
            double chance;
            if(entry.contains("%")){
                String[] splitEntry = entry.split("%");
                chance = Double.parseDouble(splitEntry[0]);
                entry = splitEntry[1];
            } else chance = 100/entries.length;

            // Tree source
            TreeSource treeSource;
            if(entry.contains(":")){
                String[] splitEntry = entry.split(":");
                treeSource = TreeSource.valueOf(splitEntry[1]);
                entry = splitEntry[0];
            } else treeSource = TreeSource.BOTH;

            // Tree type
            TreeType treeType = TreeType.valueOf(entry);

            // Save the tree to the map
            trees.put(treeSource.getTreeTemplate(treeType), chance);
        }
    }

    /** Picks a random tree template. */
    private TreeTemplate getTree(){
        // Attempt 3 times to find a tree
        for(int i=0; i<3; i++){
            for(Entry<TreeTemplate,Double> tree : trees.entrySet()){
                if(tree.getValue() >= ThreadLocalRandom.current().nextDouble(100.0)){
                    return tree.getKey();
                }
            }
        }
        // If no tree found, return null
        return null;
    }


    /**
     * Places an appropriate tree at the specified location. 
     * @param location the location where the center of the tree should be
     * @param player the player who is placing this tree
     * @return true if tree generated successfully
     */
    public boolean placeTree(Location location, Player player){
        TreeTemplate tree = getTree();
        if(tree==null) return false;

        // If tree is not null, attempt to place tree
        return tree.placeTree(location, player);
    }

    /**
     * Places an appropriate tree at the specified location. 
     * @param location the location where the center of the tree should be
     * @return true if tree generated successfully
     */
    public boolean placeTree(Location location){
        TreeTemplate tree = getTree();
        if(tree==null) return false;

        // If tree is not null, attempt to place tree
        return tree.placeTree(location);
    }
}