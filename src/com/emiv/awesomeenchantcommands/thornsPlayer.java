package com.emiv.awesomeenchantcommands;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class thornsPlayer implements Listener {

	Main plugin;
	public thornsPlayer(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (plugin.thornsPlayers.containsKey(p.getName())) {
				if (e.getDamager() instanceof LivingEntity) {
					LivingEntity attacker = (LivingEntity) e.getDamager();
					double dmg = e.getDamage();
					double value = plugin.thornsPlayers.get(p.getName()) / 5;
					attacker.damage(dmg * value);
				}
			}
		}
	}
}
