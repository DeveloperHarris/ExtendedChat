package com.gmail.codervortex.ExtendedChat;

import java.io.IOException;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	private static Main plugin;
	
	
	public void onEnable()
	{
		plugin = this;
		registerCommands();
		registerEvents();
		try
		{
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		}
		catch (IOException e)
		{
			System.out.println("Failed to sumbit Metric stats.");
		}
	}
	
	public void registerCommands()
	{
		getCommand("lc").setExecutor(new com.gmail.codervortex.ExtendedChat.LongChat(this));
		getCommand("longchat").setExecutor(new com.gmail.codervortex.ExtendedChat.LongChat(this));
	}
	
	public void registerEvents()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
	}
	
	public static Main getPlugin()
	{
		return plugin;
	}
	
}
