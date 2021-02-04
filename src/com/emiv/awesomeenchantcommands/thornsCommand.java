package com.emiv.awesomeenchantcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class thornsCommand implements CommandExecutor {

	Main plugin;
	public thornsCommand(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (plugin.getConfig().getString("thorns").equals("true")) {
				int thorns = 0;
				if (plugin.thornsPlayers.containsKey(p.getName())) {
					thorns = plugin.thornsPlayers.get(p.getName());
				}
				String correctUse = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eYour current Thorns: &d" + String.valueOf(thorns) + "\n" + "&eType &6/thorns [player] [level] &eto change your or any players level";
				String correctOther = "&7-------" + plugin.getConfig().getString("ServerPrefix") + "&7-------" + "\n" + "&eCurrent Thorns of &c%player%&e: &d%thorns%\n" + "&eType &6/thorns [player] [level] &eto change any players level";
				if (args.length == 0) {
					if (p.hasPermission("AwesomeEnchantCommands.thorns")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctUse));
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
					}
				} else if (args.length == 1) {
					if (isNumeric(args[0])) {
						if (p.hasPermission("AwesomeEnchantCommands.thorns." + args[0]) || p.hasPermission("AwesomeEnchantCommands.thorns.*")) {
							if (Integer.valueOf(args[0]) != 0) {
								if (Integer.valueOf(args[0]) <= 10) {
									plugin.thornsPlayers.put(p.getName(), Integer.valueOf(args[0]));
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Thorns").replace("%player%", p.getName())));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", "10").replace("%enchant%", "Thorns")));
								}
							} else {
								plugin.thornsPlayers.remove(p.getName());
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[0]).replace("%enchant%", "Thorns").replace("%player%", p.getName())));
							}
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
						}
					} else if (p.getServer().getPlayer(args[0]) != null) {
						if (args[0].equals(p.getName())) {
							if (p.hasPermission("AwesomeEnchantCommands.thorns")) {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctUse));
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
							}
						} else {
							if (p.hasPermission("AwesomeEnchantCommands.thorns.other")) {
								int i = 0;
								if (plugin.thornsPlayers.containsKey(args[0])) {
									i = plugin.thornsPlayers.get(args[0]);
								}
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', correctOther.replace("%player%", args[0]).replace("%thorns%", String.valueOf(i))));
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
							if (p.hasPermission("AwesomeEnchantCommands.thorns.other." + args[0]) || p.hasPermission("AwesomeEnchantCommands.thorns.other.*")) {
								if (Integer.valueOf(args[1]) != 0) {
									if (Integer.valueOf(args[1]) <= 10) {
										plugin.thornsPlayers.put(args[0], Integer.valueOf(args[1]));
										p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Thorns").replace("%player%", args[0])));
									} else {
										p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("MaxValue").replace("%level%", " ").replace("%enchant%", "Thorns")));
									}
								} else {
									plugin.thornsPlayers.remove(args[0]);
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("ValueChanged").replace("%amount%", args[1]).replace("%enchant%", "Thorns").replace("%player%", args[0])));
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
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NotEnabled").replace("%enchant%", "Thorns")));
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
