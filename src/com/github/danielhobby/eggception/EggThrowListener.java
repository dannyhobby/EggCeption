package com.github.danielhobby.eggception;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

public class EggThrowListener implements Listener {
	
	
	@EventHandler
	public void onEggThrow(PlayerEggThrowEvent event){
		
		Player player = (Player) event.getPlayer();
		
		if(Main.settings.containsKey(player)){
			if (Main.settings.get(player)){
				event.setHatching(true);
				if(Main.amounts.containsKey(player)){
					event.setNumHatches(Byte.parseByte(Main.amounts.get(player)));
				}else{
					event.setNumHatches((byte) 1);
				}
				
			}
		}
		
	}
	
}
