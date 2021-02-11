package com.emiv.awesomeenchantcommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class sharpnessPlayer implements Listener {
	
	Main plugin;
	public sharpnessPlayer(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (plugin.sharpnessPlayers.containsKey(p.getName())) {
				double dmg = e.getDamage();
				e.setDamage(dmg + (plugin.sharpnessPlayers.get(p.getName()) * 1.25f));
			}
		}
	}
}
