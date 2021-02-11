package com.emiv.awesomeenchantcommands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class aecCommand implements CommandExecutor {

	Main plugin;
	public aecCommand(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String firstLine = "&7------[&3&lAwesomeEnchantCommands&7]------";
		String secondLine = "&6/aec enable [enchant] &8- &eEnables a disabled enchant";
		String thirdLine = "&6/aec disable [enchant] &8- &eDisables a enabled enchant";
		String fourthLine = "&6/aec list [enchant] &8- &eShows a list of players that use this enchant";
		String fifthLine = "&6/aec reload &8- &eReloads the config";
		String sixthLine = "&7---------------------------------------";
		String all = firstLine + "\n" + secondLine + "\n" + thirdLine + "\n" + fourthLine + "\n" + fifthLine + "\n" + sixthLine;
		List<String> enchants = Arrays.asList("fortune", "protection", "thorns", "featherfalling", "knockback", "looting", "infinity", "sharpness");
		HashMap<String, HashMap<String, Integer>> enchantLists = new HashMap<String, HashMap<String, Integer>>();
		enchantLists.put("fortune", plugin.fortunePlayers);
		enchantLists.put("protection", plugin.protectionPlayers);
		enchantLists.put("thorns", plugin.thornsPlayers);
		enchantLists.put("featherfalling", plugin.featherFallingPlayers);
		enchantLists.put("knockback", plugin.knockbackPlayers);
		enchantLists.put("looting", plugin.lootingPlayers);
		enchantLists.put("infinity", plugin.infinityPlayers);
		enchantLists.put("sharpness", plugin.sharpnessPlayers);
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', all));
			} else {
				if (args[0].toLowerCase().equals("enable")) {
					if (p.hasPermission("AwesomeEnchantCommands.enable")) {
						if(args.length == 2) {
							if (enchants.contains(args[1].toLowerCase())) {
								if (plugin.getConfig().getString(args[1].toLowerCase()).equals("false")) {
									plugin.getConfig().set(args[1].toLowerCase(), "true");
									plugin.saveConfig();
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &6%enchant% &ehas been &aenabled".replace("%enchant%", args[1])));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cEnchant is already enabled."));
								}
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no such enchant."));
							}
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + secondLine + "\n\n" + sixthLine));
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
					}
				} else if (args[0].toLowerCase().equals("disable")) {
					if (p.hasPermission("AwesomeEnchantCommands.disable")) {
						if(args.length == 2) {
							if (enchants.contains(args[1].toLowerCase())) {
								if (plugin.getConfig().getString(args[1].toLowerCase()).equals("true")) {
									plugin.getConfig().set(args[1].toLowerCase(), "false");
									plugin.saveConfig();
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &6%enchant% &ehas been &cdisabled".replace("%enchant%", args[1])));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cEnchant is already disabled."));
								}
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no such enchant."));
							}
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + thirdLine + "\n\n" + sixthLine));
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
					}
				} else if (args[0].toLowerCase().equals("list")) {
					if (p.hasPermission("AwesomeEnchantCommands.list")) {
						if(args.length == 2) {
							if (enchantLists.keySet().contains(args[1].toLowerCase())) {
								if (enchantLists.get(args[1].toLowerCase()).size() > 0) {
									String msg = firstLine + "\n";
									for (int i = 0; i < enchantLists.get(args[1].toLowerCase()).size(); i++) {
										String name = (String) enchantLists.get(args[1].toLowerCase()).keySet().toArray()[i];
										String temp = "&3" + String.valueOf(i+1) + ". &6" + name + "&8(&d" + enchantLists.get(args[1].toLowerCase()).get(name) + "&8)";
										msg += temp + "\n";
									}
									msg += sixthLine;
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no player that use this enchant."));
								}
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no such enchant."));
							}
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + fourthLine + "\n\n" + sixthLine));
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
					}
				} else if (args[0].toLowerCase().equals("reload")) {
					if (p.hasPermission("AwesomeEnchantCommands.reload")) {
						if(args.length == 1) {
							reloadCommand();
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &aConfig has been reloaded successfully."));
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + fifthLine + "\n\n" + sixthLine));
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " " + plugin.getConfig().getString("NoPermission")));
					}
				} 
			}
		} else {
			if (args.length == 0) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', all));
			} else {
				if (args[0].toLowerCase().equals("enable")) {
					if(args.length == 2) {
						if (enchants.contains(args[1].toLowerCase())) {
							if (plugin.getConfig().getString(args[1].toLowerCase()).equals("false")) {
								plugin.getConfig().set(args[1].toLowerCase(), "true");
								plugin.saveConfig();
								Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &6%enchant% &ehas been &aenabled".replace("%enchant%", args[1])));
							} else {
								Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cEnchant is already enabled."));
							}
						} else {
							Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no such enchant."));
						}
					} else {
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + secondLine + "\n\n" + sixthLine));
					}
				} else if (args[0].toLowerCase().equals("disable")) {
					if(args.length == 2) {
						if (enchants.contains(args[1].toLowerCase())) {
							if (plugin.getConfig().getString(args[1].toLowerCase()).equals("true")) {
								plugin.getConfig().set(args[1].toLowerCase(), "false");
								plugin.saveConfig();
								Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &6%enchant% &ehas been &cdisabled".replace("%enchant%", args[1])));
							} else {
								Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cEnchant is already disabled."));
							}
						} else {
							Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no such enchant."));
						}
					} else {
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + thirdLine + "\n\n" + sixthLine));
					}
				} else if (args[0].toLowerCase().equals("list")) {
					if(args.length == 2) {
						if (enchantLists.keySet().contains(args[1].toLowerCase())) {
							if (enchantLists.get(args[1].toLowerCase()).size() > 0) {
								String msg = firstLine + "\n";
								for (int i = 0; i < enchantLists.get(args[1].toLowerCase()).size(); i++) {
									String name = (String) enchantLists.get(args[1].toLowerCase()).keySet().toArray()[i];
									String temp = "&3" + String.valueOf(i+1) + ". &6" + name + "&8(&d" + enchantLists.get(args[1].toLowerCase()).get(name) + "&8)";
									msg += temp + "\n";
								}
								msg += sixthLine;
								Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no player that use this enchant."));
							}
						} else {
							Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &cThere is no such enchant."));
						}
					} else {
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + fourthLine + "\n\n" + sixthLine));
					}
				} else if (args[0].toLowerCase().equals("reload")) {
					if(args.length == 1) {
						reloadCommand();
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ServerPrefix") + " &aConfig has been reloaded successfully."));
					} else {
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', firstLine + "\n\n" + fifthLine + "\n\n" + sixthLine));
					}
				} 
			}
		}
		return false;
	}

	public void reloadCommand() {
		Plugin pl = Bukkit.getServer().getPluginManager().getPlugin("AwesomeEnchantCommands");
		plugin.reloadConfig();
		pl.getPluginLoader().disablePlugin(pl);
		pl.getPluginLoader().enablePlugin(pl);
	}
	
}
