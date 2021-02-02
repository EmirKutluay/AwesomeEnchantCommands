package com.emiv.awesomeenchantcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class fortuneCommand implements CommandExecutor {

	Main plugin;
	public fortuneCommand(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			int fortune = 0;
			if (plugin.fortunePlayers.containsKey(p.getName())) {
				fortune = plugin.fortunePlayers.get(p.getName());
			}
			String correctUse = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eYour current fortune: &d" + String.valueOf(fortune) + "\n" + "&eType &6/fortune [player] [level] &eto change your or any players level";
			String correctOther = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eCurrent fortune of &c%player%&e: &d" + String.valueOf(fortune) + "\n" + "&eType &6/fortune [player] [level] &eto change any players level";
			if (args.length == 0) {
				if (p.hasPermission("awesomefortune.fortune")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctUse));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
				}
			} else if (args.length == 1) {
				if (isNumeric(args[0])) {
					if (p.hasPermission("awesomefortune.fortune." + args[0]) || p.hasPermission("awesomefortune.fortune.*")) {
						if (Integer.valueOf(args[0]) != 0) {
							if (Integer.valueOf(args[0]) <= 100) {
								plugin.fortunePlayers.put(p.getName(), Integer.valueOf(args[0]));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Fortune").replace("%player%", p.getName())));	
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", "100").replace("%enchant%", "Fortune")));
							}
						} else {
							plugin.fortunePlayers.remove(p.getName());
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Fortune").replace("%player%", p.getName())));
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
					}
				} else if (p.getServer().getPlayer(args[0]) != null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctOther.replace("%player%", args[0])));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPlayer").replace("%player%", args[0])));
				}
			} else if (args.length == 2) {
				if (isNumeric(args[1])) {
					if (p.getServer().getPlayer(args[0]) != null) {
						if (p.hasPermission("awesomefortune.fortune.other." + args[0]) || p.hasPermission("awesomefortune.fortune.other.*")) {
							if (Integer.valueOf(args[1]) != 0) {
								if (Integer.valueOf(args[1]) <= 100) {
									plugin.fortunePlayers.put(args[0], Integer.valueOf(args[1]));
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Fortune").replace("%player%", args[0])));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", "100").replace("%enchant%", "Fortune")));
								}
							} else {
								plugin.fortunePlayers.remove(args[0]);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Fortune").replace("%player%", args[0])));
							}
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPlayer").replace("%player%", args[0])));
					}
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctUse));
				}
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
			}
		}
		return false;
	}

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			double d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}	
	
}
