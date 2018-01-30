package com.kylenanakdewa.customtrees.treetemplates;

import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

/**
 * Represents a tree, either vanilla or custom.
 */
public abstract class TreeTemplate {

    protected TreeType treeType;

    /**
     * Gets a template for a custom tree.
     * @param treeType the type/species of tree to use
     */
    public TreeTemplate(TreeType treeType){
        this.treeType = treeType;
    }

    /**
     * Gets the type (species) of the tree.
     * @return the tree type
     */
    public TreeType getTreeType(){
        return treeType;
    }

    /**
     * Places an appropriate tree at the specified location. 
     * @param location the location where the center of the tree should be
     * @param player the player who is placing this tree
     * @return true if tree generated successfully
     */
    public abstract boolean placeTree(Location location, Player player);

    /**
     * Places an appropriate tree at the specified location. 
     * @param location the location where the center of the tree should be
     * @return true if tree generated successfully
     */
    public abstract boolean placeTree(Location location);
}