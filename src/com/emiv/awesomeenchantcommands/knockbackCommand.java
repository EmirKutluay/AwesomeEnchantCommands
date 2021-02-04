package com.emiv.awesomeenchantcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class knockbackCommand implements CommandExecutor {
	Main plugin;
	public knockbackCommand(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (plugin.getConfig().getString("knockback").equals("true")) {
				int knockback = 0;
				if (plugin.knockbackPlayers.containsKey(p.getName())) {
					knockback = plugin.knockbackPlayers.get(p.getName());
				}
				String correctUse = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eYour current knockback: &d" + String.valueOf(knockback) + "\n" + "&eType &6/knockback [player] [level] &eto change your or any players level";
				String correctOther = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eCurrent knockback of &c%player%&e: &d%knockback%\n" + "&eType &6/knockback [player] [level] &eto change any players level";
				if (args.length == 0) {
					if (p.hasPermission("AwesomeEnchantCommands.knockback")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctUse));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
					}
				} else if (args.length == 1) {
					if (isNumeric(args[0])) {
						if (p.hasPermission("AwesomeEnchantCommands.knockback." + args[0]) || p.hasPermission("AwesomeEnchantCommands.knockback.*")) {
							if (Integer.valueOf(args[0]) != 0) {
								if (Integer.valueOf(args[0]) <= 100) {
									plugin.knockbackPlayers.put(p.getName(), Integer.valueOf(args[0]));
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Knockback").replace("%player%", p.getName())));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", "100").replace("%enchant%", "Knockback")));
								}
							} else {
								plugin.knockbackPlayers.remove(p.getName());
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Knockback").replace("%player%", p.getName())));
							}
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
						}
					} else if (p.getServer().getPlayer(args[0]) != null) {
						if (args[0].equals(p.getName())) {
							if (p.hasPermission("AwesomeEnchantCommands.knockback")) {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctUse));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
							}
						} else {
							if (p.hasPermission("AwesomeEnchantCommands.knockback.other")) {
								int i = 0;
								if (plugin.knockbackPlayers.containsKey(args[0])) {
									i = plugin.knockbackPlayers.get(args[0]);
								}
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctOther.replace("%player%", args[0]).replace("%knockback%", String.valueOf(i))));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
							}
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPlayer").replace("%player%", args[0])));
					}
				} else if (args.length == 2) {
					if (isNumeric(args[1])) {
						if (p.getServer().getPlayer(args[0]) != null) {
							if (p.hasPermission("AwesomeEnchantCommands.knockback.other." + args[0]) || p.hasPermission("AwesomeEnchantCommands.knockback.other.*")) {
								if (Integer.valueOf(args[1]) != 0) {
									if (Integer.valueOf(args[1]) <= 100) {
										plugin.knockbackPlayers.put(args[0], Integer.valueOf(args[1]));
										p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Knockback").replace("%player%", args[0])));
									} else {
										p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", "100").replace("%enchant%", "Knockback")));
									}
								} else {
									plugin.knockbackPlayers.remove(args[0]);
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Knockback").replace("%player%", args[0])));
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
			} else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NotEnabled").replace("%enchant%", "Knockback")));
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
