package com.kylenanakdewa.bettertrees;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

/**
 * Commands for the BetterTrees plugin.
 */
public class BetterTreesCommands implements TabExecutor {

    @Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Version command
		if(args.length==0 || args[0].equalsIgnoreCase("version")){
			sender.sendMessage(BetterTreesPlugin.accentColor+"BetterTrees "+BetterTreesPlugin.plugin.getDescription().getVersion()+" by Kyle Nanakdewa");
            sender.sendMessage(BetterTreesPlugin.messageColor+"- Generate custom trees and forests, with saplings and commands");
            sender.sendMessage(BetterTreesPlugin.messageColor+"- Website: http://Akenland.com/plugins");
			return true;
        }

		// Reload command
		if(args.length==1 && args[0].equalsIgnoreCase("reload")){
			BetterTreesPlugin.plugin.reload();
            sender.sendMessage(BetterTreesPlugin.messageColor+"BetterTrees reloaded.");
            return true;
		}

        // Invalid command
        sender.sendMessage(BetterTreesPlugin.errorColor+"Invalid arguments.");
        return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // Main command - return each sub-command
        if(args.length<=1) return Arrays.asList("version", "reload");
        // Otherwise return nothing
        return Arrays.asList("");
    }
}