package com.emiv.awesomeenchantcommands;

import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class infinityPlayers implements Listener {

	Main plugin;
	public infinityPlayers(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerShoot(EntityShootBowEvent e) {
		if (e.getProjectile() instanceof Arrow) {
			Arrow ar = (Arrow) e.getProjectile();
			if (ar.getShooter() instanceof Player) {
				Player p = (Player) ar.getShooter();
				if (plugin.infinityPlayers.containsKey(p.getName())) {
					p.getInventory().addItem(new ItemStack(Material.ARROW));
					p.getInventory().removeItem(new ItemStack(Material.ARROW));
					ar.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
				}
			}
		}
	}
	
}
