package com.emiv.awesomeenchantcommands;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class lootingPlayers implements Listener {

	Main plugin;
	public lootingPlayers(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity().getKiller();
			if (plugin.lootingPlayers.containsKey(p.getName())) {
				List<ItemStack> drop = e.getDrops();
				for (ItemStack i : drop) {
					Material type = i.getType();
					Random rand = new Random();
					int amount = rand.nextInt(plugin.lootingPlayers.get(p.getName()));
					e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), new ItemStack(type, amount));
				}
			}
		}
	}
}
