package com.emiv.awesomeenchantcommands;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class fortunePlayer implements Listener {
	
	Main plugin;
	public fortunePlayer(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (plugin.fortunePlayers.containsKey(p.getName())) {
			Random rand = new Random(); 
			int amount = rand.nextInt(plugin.fortunePlayers.get(p.getName())) + plugin.fortunePlayers.get(p.getName());
			String[] blockTypes = {"DIAMOND_ORE", "COAL_ORE", "REDSTONE_ORE", "EMERALD_ORE", "LAPIS_ORE"};
			String[] itemTypes = {"DIAMOND", "COAL", "REDSTONE", "EMERALD", "LAPIS_LAZULI"};
			for (int i = 0; i < blockTypes.length; i++) {
				if (e.getBlock().getType() == Material.getMaterial(blockTypes[i])) {
					e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.getMaterial(itemTypes[i]), amount));
				}
			}
		}
	}

}
