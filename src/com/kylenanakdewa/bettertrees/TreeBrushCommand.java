package com.kylenanakdewa.bettertrees;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Command to generate a tree or forest, where the sender is looking.
 */
public class TreeBrushCommand implements TabExecutor {

    @Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(args.length>=1 && sender instanceof Player){
            // Create brush item
            ItemStack brush = new ItemStack(BetterTreesPlugin.brushMaterial);
            ItemMeta meta = brush.getItemMeta();

            // Set item name
            meta.setDisplayName(BetterTreesPlugin.accentColor+"Tree Brush");

            // Check if placing forest
            String forestLine = args.length>=2 && args[1].equalsIgnoreCase("forest") ?
             BetterTreesPlugin.infoColor+"Forest" : BetterTreesPlugin.infoColor+"Single Tree";
 
            // Place expresion in lore
            String expression = args[0];

            // Set lore
            meta.setLore(Arrays.asList(forestLine, expression));
            brush.setItemMeta(meta);

            // Give brush to player
            ((Player)sender).getInventory().addItem(brush);
            sender.sendMessage(BetterTreesPlugin.infoColor+"Tree Brush added to inventory.");
			return true;
        }

        // Invalid command
        sender.sendMessage(BetterTreesPlugin.errorColor+"Invalid arguments.");
        return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // Return nothing
        return Arrays.asList("");
    }
}