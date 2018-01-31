package com.kylenanakdewa.bettertrees;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class for BetterTrees.
 */
public final class BetterTreesPlugin extends JavaPlugin {

	public static BetterTreesPlugin plugin;


	/** Percentage chances that custom trees will generate, when BOTH are allowed to generate. */
	public static Map<TreeType,Double> customChances;
	/** The sources of trees that can generate from saplings. */
	public static Map<TreeType,TreeSource> saplingTrees;
	/** The modifier to the custom chance, when bonemeal is used to grow the sapling. */
	public static double bonemealModifier;

	/** The material to use for the tree brush. */
	public static Material brushMaterial;

	/** The maximum distance players can place trees from. */
	public static int maxPlaceDistance;


	/** The accent color for this plugin's messages. */
	public static ChatColor accentColor = ChatColor.DARK_GREEN;
	/** The main color for this plugin's messages. */
	public static ChatColor messageColor = ChatColor.GRAY;
	/** The secondary color for this plugin's non-important messages. */
	public static ChatColor infoColor = ChatColor.DARK_GRAY;
	/** The color for this plugin's error messages. */
	public static ChatColor errorColor = ChatColor.RED;


	@Override
	public void onEnable(){
		plugin = this;

		// Main command
		getCommand("bettertrees").setExecutor(new BetterTreesCommands());
		getCommand("tree").setExecutor(new TreeCommand());
		getCommand("treebrush").setExecutor(new TreeBrushCommand());

		// Run all reload tasks
		reload();

		// Register event listeners
		getServer().getPluginManager().registerEvents(new SaplingListener(), plugin);
		getServer().getPluginManager().registerEvents(new TreeBrushListener(), plugin);
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

		bonemealModifier = getConfig().getDouble("bonemeal-modifier");

		brushMaterial = Material.valueOf(getConfig().getString("brush-material"));

		maxPlaceDistance = getConfig().getInt("max-place-distance");
	}

}