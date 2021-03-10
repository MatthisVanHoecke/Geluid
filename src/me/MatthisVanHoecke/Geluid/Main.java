package me.MatthisVanHoecke.Geluid;

import java.io.IOException;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	private String dir_path = System.getProperty("user.dir") + "\\plugins\\Geluid\\";
	
	private Process p;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(dir_path);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		
		
		
		player.sendMessage("You broke " + e.getBlock().getType().name());
		if(p == null) {
			try {
				p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd " + "plugins\\Geluid\\playSound1" + " && " + "playSound1.exe" + "\"");
				p.waitFor();
				System.out.println(p.exitValue());
			}
			catch(Exception ex) {
				player.sendMessage("couldn't play");
			}
		}
		else {
			try {
				OutputStream out = p.getOutputStream();
				out.write("cd C:/ /r/n".getBytes()); //verander deze waarden
			    out.flush();
			    out.write("dir /r/n".getBytes());
			    out.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		
		
		
		player.sendMessage("You placed " + e.getBlock().getType().name());
	}
	
	@EventHandler
	public void onDamaged(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player)e.getEntity();
			
			player.sendMessage("ouch");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		
		if(e.getClickedBlock() != null) {
			switch(e.getClickedBlock().getType().name().toLowerCase()) {
				case "chest":
					player.sendMessage("steal their shit");
					
					break;
				case "ender_chest":
					player.sendMessage("Steal their secret shit");
					break;
				case "oak_door":
				case "spruce_door":
				case "birch_door":
				case "jungle_door":
				case "acacia_door":
				case "dark_oak_door":
					player.sendMessage("breaking and entering?");
					break;
				case "lever":
				case "stone_button":
				case "wooden_button":
					player.sendMessage("what does this do?");
					break;
				case "furnace":
				case "lit_furnace":
					player.sendMessage("that's hot");
					break;
				case "brewing_stand":
					player.sendMessage("magic stuff");
					break;
				case "flint_and_steel":
					player.sendMessage("arson time");
					break;
				default:
					break;
			}
		}
	}
	
}
