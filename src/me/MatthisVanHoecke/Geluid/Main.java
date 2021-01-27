package me.MatthisVanHoecke.Geluid;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		
		
		
		player.sendMessage("You broke " + e.getBlock().getType());
	}
	
	@EventHandler
	public void onDamaged(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player)e.getEntity();
			
			player.sendMessage("ouch");
		}
	}
}
