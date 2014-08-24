package com.gmail.codervortex.ExtendedChat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.codervortex.ExtendedChat.MessageGUI.MessageCompleteEvent;
import com.gmail.codervortex.ExtendedChat.MessageGUI.MessageHandler;
import com.gmail.codervortex.ExtendedChat.MessageGUI.MessageReceivedEvent;

public class LongChat implements CommandExecutor
{
	public Main plugin;
	public LongChat(Main instance){	
		plugin = instance;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if (sender instanceof Player)
	      {
	       final Player player = (Player) sender; 
	       if (cmd.getName().equalsIgnoreCase("lc") || cmd.getName().equalsIgnoreCase("longchat"))
	        {
	    	    if(player.hasPermission("ec.longchat") || player.hasPermission("ec.lc"))	
	   	    	{
	    	    	 MessageGUI.createCommandBlockGUI(
	    	    	 (Player)player, new MessageHandler()
	    	    	 {
	    	                @Override
	    	                public void onMessageReceived(MessageReceivedEvent e)
	    	                {
	    	                    ((Player)player).chat(e.getMessage());
	    	                    e.setComplete(true);
	    	                }
	    	                
	    	                @Override
	    	                public void onComplete(MessageCompleteEvent e)
	    	                {
	    	                    return;
	    	                }
	    	            }
	    	    	 ).start();
	    	    	 return true;
	    	    		    	    	
	   	    	}
	    	    else player.sendMessage(ChatColor.WHITE + "Unknown command. Type \"help\" for help.");
	    	    return true;
	        }	       
	      }
		return false;
	}
	
	
	
}
