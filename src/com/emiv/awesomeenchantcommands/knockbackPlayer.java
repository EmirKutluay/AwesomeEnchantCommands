package com.emiv.awesomeenchantcommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class knockbackPlayer implements Listener {

	Main plugin;
	public knockbackPlayer(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void omHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (plugin.knockbackPlayers.containsKey(p.getName())) {
				e.getEntity().setVelocity(p.getLocation().getDirection().multiply(plugin.knockbackPlayers.get(p.getName())));
			}
		}
	}
	
}
