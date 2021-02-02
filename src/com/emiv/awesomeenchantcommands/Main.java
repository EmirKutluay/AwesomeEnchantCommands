package com.emiv.awesomeenchantcommands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	
	public HashMap<String, Integer> fortunePlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> protectionPlayers = new HashMap<String, Integer>();
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		
		Bukkit.getPluginManager().registerEvents(new fortunePlayer(this), this);
		Bukkit.getPluginManager().registerEvents(new protectionPlayer(this), this);
		
		getCommand("fortune").setExecutor(new fortuneCommand(this));
		getCommand("protection").setExecutor(new protectionCommand(this));
	}
	
}
