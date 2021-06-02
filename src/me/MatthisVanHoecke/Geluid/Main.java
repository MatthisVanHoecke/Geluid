package me.MatthisVanHoecke.Geluid;

import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	private String dir_path = System.getProperty("user.dir") + "\\plugins\\Geluid\\";
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(dir_path);
		}
	}
	
	Villager puttemans;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(label.equals("puttemans")) {
				puttemans = (Villager) Bukkit.getWorld(player.getWorld().getName()).spawnEntity(player.getLocation(), EntityType.VILLAGER);
				puttemans.setCustomName("mr. Puttemans");
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		
		playSound(player);
	}
	
	public void playSound(Player player) {
		try {
			Runtime.getRuntime().exec("python " + dir_path + "lightpin.py");
			Runtime.getRuntime().exec("python " + dir_path + "playSound1\\playSound1.py");
		}
		catch(Exception ex) {
			player.sendMessage("couldn't play");
		}
		
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		
		
		
		player.sendMessage("You placed " + e.getBlock().getType().name());
	}
	
	public Player player1;
	
	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player)e.getEntity();
			
			player.sendMessage("ouch");
		}
		
		if(e.getEntity() instanceof Chicken && e.getDamager() instanceof Player) {
			
			player1 = (Player) e.getDamager();
			try {
				Runtime.getRuntime().exec("python " + dir_path + "hitChicken.py");
			} catch (IOException e1) {
				
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			    @Override
			    public void run() {
			        Bukkit.getWorld(player1.getWorld().getName()).createExplosion(player1.getLocation(), 5, true);
			    }
			}, (long) (20*4)); //20 Tick (1 Second) delay before run() is called
		}
		
		if(e.getEntity() == puttemans && e.getDamager() instanceof Player) {
			player1 = (Player) e.getDamager();
			puttemans.remove();
			player1.sendTitle("Je bent gebuisd!", "", 20, 40, 20);
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			    @Override
			    public void run() {
					for(int i = 0; i < 100; i++) {
						Random rand = new Random();
						int perc = rand.nextInt(4);
						int chance = rand.nextInt(2);
						summonDestruction(chance, perc, rand);
					}
			    }
			}, (long) (20*3.5)); //20 Tick (1 Second) delay before run() is called
		}
	}
	
	public void summonDestruction(int chance, int perc, Random rand) {
		if(chance > 0) {
			switch(perc) {
				case 0:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()+rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()+rand.nextInt(20)));
					Bukkit.getWorld(player1.getWorld().getName()).createExplosion(player1.getLocation().getX()+rand.nextInt(20), player1.getLocation().getY()-rand.nextInt(5), player1.getLocation().getZ()+rand.nextInt(20), 5, true);
					break;
				case 1:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()-rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()-rand.nextInt(20)));
					Bukkit.getWorld(player1.getWorld().getName()).createExplosion(player1.getLocation().getX()-rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()-rand.nextInt(20), 5, true);
					break;
				case 2:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()+rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()-rand.nextInt(20)));
					Bukkit.getWorld(player1.getWorld().getName()).createExplosion(player1.getLocation().getX()+rand.nextInt(20), player1.getLocation().getY()-rand.nextInt(5), player1.getLocation().getZ()-rand.nextInt(20), 5, true);
					break;
				case 3:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()-rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()+rand.nextInt(20)));
					Bukkit.getWorld(player1.getWorld().getName()).createExplosion(player1.getLocation().getX()-rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()+rand.nextInt(20), 5, true);
					break;
			}
		}
		else {
			switch(perc) {
				case 0:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()+rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()+rand.nextInt(20)));
					break;
				case 1:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()-rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()-rand.nextInt(20)));
					break;
				case 2:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()+rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()-rand.nextInt(20)));
					break;
				case 3:
					Bukkit.getWorld(player1.getWorld().getName()).strikeLightning(new Location(player1.getWorld(), player1.getLocation().getX()-rand.nextInt(20), player1.getLocation().getY()+rand.nextInt(5), player1.getLocation().getZ()+rand.nextInt(20)));
					break;
		}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		
		String blockname = e.getClickedBlock() != null ? e.getClickedBlock().getType().name().toLowerCase() : "air";
		
		switch(blockname) {
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
		}
	}
}
