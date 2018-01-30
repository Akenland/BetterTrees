package com.kylenanakdewa.customtrees;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

/**
 * Command to generate a tree or forest, where the sender is looking.
 */
public class TreeCommand implements TabExecutor {

    @Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(args.length>=1 && sender instanceof Player){
            // Use the expression to place a tree
            TreeExpression expression = new TreeExpression(args[0]);

            // Check if placing forest
            if(args[1].equalsIgnoreCase("forest")){
                sender.sendMessage("Not yet implemented.");
                return false;
            }

            Player player = (Player)sender;

            boolean placed = expression.placeTree(player.getTargetBlock(null, CustomTreesPlugin.maxPlaceDistance).getLocation(), player);

            if(!placed) sender.sendMessage("Tree placement failed.");
			return placed;
        }

        // Invalid command
        sender.sendMessage("Invalid arguments.");
        return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // Return nothing
        return Arrays.asList("");
    }
}