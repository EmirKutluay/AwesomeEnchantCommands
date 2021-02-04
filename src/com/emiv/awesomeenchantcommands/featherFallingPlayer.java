package com.emiv.awesomeenchantcommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class featherFallingPlayer implements Listener {

	Main plugin;
	public featherFallingPlayer(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getCause() == DamageCause.FALL) {
				double dmg = e.getDamage();
				double value = plugin.featherFallingPlayers.get(p.getName()) * 0.1;
				if (value > 1) {
					value = 1;
				}
				e.setDamage(dmg * (1 - value));
			}
		}
	}
	
}
