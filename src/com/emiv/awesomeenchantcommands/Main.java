package com.emiv.awesomeenchantcommands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	
	public HashMap<String, Integer> fortunePlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> protectionPlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> thornsPlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> featherFallingPlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> knockbackPlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> lootingPlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> infinityPlayers = new HashMap<String, Integer>();
	public HashMap<String, Integer> sharpnessPlayers = new HashMap<String, Integer>();
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		
		Bukkit.getPluginManager().registerEvents(new fortunePlayer(this), this);
		Bukkit.getPluginManager().registerEvents(new thornsPlayer(this), this);
		Bukkit.getPluginManager().registerEvents(new protectionPlayer(this), this);
		Bukkit.getPluginManager().registerEvents(new featherFallingPlayer(this), this);
		Bukkit.getPluginManager().registerEvents(new knockbackPlayer(this), this);
		Bukkit.getPluginManager().registerEvents(new lootingPlayers(this), this);
		Bukkit.getPluginManager().registerEvents(new infinityPlayer(this), this);
		Bukkit.getPluginManager().registerEvents(new sharpnessPlayer(this), this);

		getCommand("fortune").setExecutor(new fortuneCommand(this));
		getCommand("protection").setExecutor(new protectionCommand(this));
		getCommand("thorns").setExecutor(new thornsCommand(this));
		getCommand("featherfalling").setExecutor(new featherFallingCommand(this));
		getCommand("knockback").setExecutor(new knockbackCommand(this));
		getCommand("looting").setExecutor(new lootingCommand(this));
		getCommand("infinity").setExecutor(new infinityCommand(this));
		getCommand("sharpness").setExecutor(new sharpnessCommand(this));
		getCommand("awesomeenchantcommands").setExecutor(new aecCommand(this));
		
	}
	
}
