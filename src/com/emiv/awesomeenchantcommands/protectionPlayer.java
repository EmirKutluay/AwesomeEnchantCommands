package com.emiv.awesomeenchantcommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class protectionPlayer implements Listener {

	Main plugin;
	public protectionPlayer(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockBreak(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (plugin.protectionPlayers.containsKey(p.getName())) {
				double dmg = e.getDamage();
				double value = plugin.protectionPlayers.get(p.getName()) * 0.05;
				if (value > 1) {
					value = 1;
				}
				e.setDamage(dmg * (1 - value));
			}
		}
	}
	
}
