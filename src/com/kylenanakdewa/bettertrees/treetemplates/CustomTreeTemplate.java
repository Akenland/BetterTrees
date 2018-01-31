package com.kylenanakdewa.bettertrees.treetemplates;

import com.kylenanakdewa.bettertrees.BetterTreesPlugin;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.entity.Player;

/**
 * Represents a custom tree, from a WorldEdit schematic.
 */
@SuppressWarnings("deprecation")
public class CustomTreeTemplate extends TreeTemplate {

    public CustomTreeTemplate(TreeType treeType){
        super(treeType);
    }

	@Override
	public boolean placeTree(Location location, Player player) {
        // Get the WE plugin
        WorldEditPlugin wePlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");

        // Get the player's WE edit session
        EditSession editSession = player!=null ? wePlugin.createEditSession(player) : wePlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), -1);

        // Get the location of the warpstone's center
        Vector loc = BukkitUtil.toVector(location.add(0, 1, 0));

        // Load a random custom tree
        CuboidClipboard schem = getSchematic();
        if(schem==null){
            if(player!=null && player.hasPermission("bettertrees.generate")) player.sendMessage(BetterTreesPlugin.errorColor+"No custom tree schematics found for "+treeType);
            return false;
        }

        // Randomly rotate the schematic
        schem.rotate2D(Arrays.asList(0, 90, 180, 270).get(ThreadLocalRandom.current().nextInt(3)));

        // Paste the schematic
        try {
            schem.paste(editSession, loc, true);
        } catch(MaxChangedBlocksException e){
            if(player!=null) player.sendMessage(BetterTreesPlugin.errorColor+"Unable to generate custom tree, too many blocks changed.");
            return false;
        }
        editSession.redo(editSession);
        return true;
	}

    @Override
	public boolean placeTree(Location location){
		return placeTree(location, null);
    }

    /** Gets a random custom tree schematic. */
    private CuboidClipboard getSchematic(){
        // Get a random file from this treeType's folder
        File[] schemFiles = new File(BetterTreesPlugin.plugin.getDataFolder(), treeType.name()).listFiles();
        if(schemFiles==null || schemFiles.length<1) return null;

        File chosenSchem = schemFiles[ThreadLocalRandom.current().nextInt(schemFiles.length)];

        try {
            return SchematicFormat.MCEDIT.load(chosenSchem);
        } catch(IOException | DataException e){
            BetterTreesPlugin.plugin.getLogger().warning(("An error occured loading schematic "+chosenSchem));
            return null;
        }
    }
}