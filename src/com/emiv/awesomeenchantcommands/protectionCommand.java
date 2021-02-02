package com.emiv.awesomeenchantcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class protectionCommand implements CommandExecutor {

	Main plugin;
	public protectionCommand(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			int protection = 0;
			if (plugin.protectionPlayers.containsKey(p.getName())) {
				protection = plugin.protectionPlayers.get(p.getName());
			}
			String correctUse = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eYour current protection: &d" + String.valueOf(protection) + "\n" + "&eType &6/protection [player] [level] &eto change your or any players level";
			String correctOther = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eCurrent fortune of &c%player%&e: &d" + String.valueOf(protection) + "\n" + "&eType &6/fortune [player] [level] &eto change any players level";
			if (args.length == 0) {
				if (p.hasPermission("awesomeprotection.protection")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctUse));
				} else {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
				}
			} else if (args.length == 1) {
				if (isNumeric(args[0])) {
					if (p.hasPermission("awesomeprotection.protection." + args[0]) || p.hasPermission("awesomeprotection.protection.*")) {
						if (Integer.valueOf(args[0]) != 0) {
							if (Integer.valueOf(args[0]) <= 20) {
								plugin.protectionPlayers.put(p.getName(), Integer.valueOf(args[0]));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Protection").replace("%player%", p.getName())));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", "20").replace("%enchant%", "Protection")));
							}
						} else {
							plugin.protectionPlayers.remove(p.getName());
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Protection").replace("%player%", p.getName())));
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
						if (p.hasPermission("awesomeprotection.protection.other." + args[0]) || p.hasPermission("awesomeprotection.protection.other.*")) {
							if (Integer.valueOf(args[1]) != 0) {
								if (Integer.valueOf(args[1]) <= 20) {
									plugin.protectionPlayers.put(args[0], Integer.valueOf(args[1]));
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Protection").replace("%player%", args[0])));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", "20").replace("%enchant%", "Protection")));
								}
							} else {
								plugin.protectionPlayers.remove(args[0]);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Protection").replace("%player%", args[0])));
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
