package com.github.danielhobby.eggception;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static HashMap<Player, Boolean> settings = new HashMap<Player, Boolean>();
	public static HashMap<Player, String> amounts = new HashMap<Player, String>();
	
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new EggThrowListener(), this);
	}
	
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		if (args.length == 0){
			if(cmd.getLabel().equalsIgnoreCase("hatch") && player.isOp()){
				if (settings.containsKey(player)){
					settings.remove(player);
					amounts.remove(player);
					player.sendMessage("Instant chicken mode: " + ChatColor.RED + "OFF");
				}else{
					settings.put(player, true);
					player.sendMessage("Instant chicken mode: " + ChatColor.GREEN + "ON");
				}
				return true;
			}else if (cmd.getLabel().equalsIgnoreCase("hatch") && !player.isOp()){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
			}
		}else if (args.length == 1){
			if(cmd.getLabel().equalsIgnoreCase("hatch") && isNumber(args[0]) && player.isOp()){
				if (settings.containsKey(player) && amounts.containsKey(player)){
					settings.remove(player);
					amounts.remove(player);
					player.sendMessage("Instant chicken mode: " + ChatColor.RED + "OFF");
				}else{
					settings.put(player, true);
					amounts.put(player, args[0]);
					player.sendMessage("Instant chicken mode: " + ChatColor.GREEN + "ON");
				}
				return true;
			}else if(cmd.getLabel().equalsIgnoreCase("hatch") && !isNumber(args[0]) && player.isOp()){
				player.sendMessage("Argument 1 must be a number.");
			}else if (cmd.getLabel().equalsIgnoreCase("hatch") && !player.isOp()){
				player.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
			}
		}
		
		
		return false;
	}
	
	public boolean isNumber(String i){
		
		try {
			Integer.parseInt(i);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
}
