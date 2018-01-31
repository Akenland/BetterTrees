package com.kylenanakdewa.bettertrees;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * TreeBrushListener
 */
final class TreeBrushListener implements Listener {

    @EventHandler
    public void onBrushUse(PlayerInteractEvent event){
        if(event.isCancelled() || !event.hasItem() || !event.getPlayer().hasPermission("bettertrees.brush")) return;

        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();

        // Make sure item is correct material, and has lore
        if(!item.getType().equals(BetterTreesPlugin.brushMaterial) || !meta.hasLore() || meta.getLore().size()<2) return;

        // Get lore lines
        String forestLine = meta.getLore().get(0);
        String expressionLine = meta.getLore().get(1);

        // Forest mode
        if(forestLine.contains("Forest")){
            event.getPlayer().sendMessage(BetterTreesPlugin.errorColor+"Not yet implemented.");
            return;
        } else if(forestLine.contains("Single Tree")){
            TreeExpression expression = new TreeExpression(expressionLine);

            boolean placed = expression.placeTree(event.getPlayer().getTargetBlock(null, BetterTreesPlugin.maxPlaceDistance).getLocation(), event.getPlayer());

            if(!placed) event.getPlayer().sendMessage(BetterTreesPlugin.errorColor+"Tree placement failed.");
        } else return;
    }

}