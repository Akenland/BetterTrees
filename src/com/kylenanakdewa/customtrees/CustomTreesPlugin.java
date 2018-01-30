package com.kylenanakdewa.customtrees;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class for CustomTrees.
 */
public final class CustomTreesPlugin extends JavaPlugin {

	public static CustomTreesPlugin plugin;

	/** Percentage chances that custom trees will generate, when BOTH are allowed to generate. */
	public static Map<TreeType,Double> customChances;

	/** The sources of trees that can generate from saplings. */
	public static Map<TreeType,TreeSource> saplingTrees;

	/** The material to use for the tree brush. */
	public static Material brushMaterial;

	/** The maximum distance players can place trees from. */
	public static int maxPlaceDistance;


	@Override
	public void onEnable(){
		plugin = this;

		// Main command
		getCommand("customtrees").setExecutor(new CustomTreesCommands());
		getCommand("tree").setExecutor(new TreeCommand());
		getCommand("treebrush").setExecutor(new TreeBrushCommand());

		// Run all reload tasks
		reload();

		// Register event listener
		getServer().getPluginManager().registerEvents(new SaplingListener(), plugin);
	}


	/** Reloads the plugin. */
	public void reload(){
		// Load config
		saveDefaultConfig();
		loadConfig();
	}

	/** Retrieve values from config. */
	private void loadConfig(){
		reloadConfig();

		customChances = new HashMap<TreeType,Double>();
		for(String treeType : getConfig().getConfigurationSection("chance").getKeys(false))
			customChances.put(TreeType.valueOf(treeType), Double.parseDouble(getConfig().getString("chance."+treeType)));

		saplingTrees = new HashMap<TreeType,TreeSource>();
		for(String treeType : getConfig().getConfigurationSection("saplings").getKeys(false))
			saplingTrees.put(TreeType.valueOf(treeType), TreeSource.valueOf(getConfig().getString("saplings."+treeType)));

		brushMaterial = Material.valueOf(getConfig().getString("brush-material"));

		maxPlaceDistance = getConfig().getInt("max-place-distance");
	}

}