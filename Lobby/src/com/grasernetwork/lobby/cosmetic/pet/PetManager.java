package com.grasernetwork.lobby.cosmetic.pet;

import java.util.HashMap;

import net.minecraft.server.v1_9_R1.Entity;
import net.minecraft.server.v1_9_R1.EntityInsentient;
import net.minecraft.server.v1_9_R1.PathEntity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.grasernetwork.lobby.cosmetic.pet.data.PetData;
import com.grasernetwork.lobby.cosmetic.pet.gui.PetMenu;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.util.inventory.MenuAPI;

public class PetManager implements Listener
{
	private HashMap<Player, PetData> _pets = new HashMap<Player, PetData>();
	private BukkitTask _task;
	private boolean _running;
	
	private JavaPlugin _plugin;
	private ProfileManager _profileManager;
	
	public PetManager(JavaPlugin plugin, ProfileManager profileManager)
	{
		_plugin = plugin;
		_profileManager = profileManager;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		if(_pets.isEmpty())
		{
			_running = false;
			return;
		}
	}
	
	public void startScheduler(JavaPlugin plugin)
	{
		if(_task != null)
			return;
		
		if(_running)
			return;
		
		System.out.print("Initial Startup of Loop");
		
		_running = true;
		_task = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				if(_pets.isEmpty())
				{
					System.out.print("Pet list Empty");
					this.cancel();
					_task = null;
					_running = false;
				}
				
				for(Player player : _pets.keySet())
				{
					if(player == null || !player.isOnline())
					{
						_pets.remove(player);
						continue;
					}
					
					Entity entity = _pets.get(player).entity;
					if(entity == null || entity.dead)
					{
						_pets.remove(player);
						continue;
					}
					
					PathEntity path;
					Location l = player.getLocation();
					
					double dist = l.distance(entity.getBukkitEntity().getLocation());
					if(dist < 2)
						continue;
					
					path = ((EntityInsentient) entity).getNavigation().a(l.getX(), l.getY(), l.getZ());
					
					if(path != null)
					{
						((EntityInsentient) entity).getNavigation().a(path, 1.0D);
						((EntityInsentient) entity).getNavigation().a(2.0D);
					}
					
					dist = l.distance(entity.getBukkitEntity().getLocation());
					if(dist > 10 && !entity.dead && player.isOnGround())
						entity.getBukkitEntity().teleport(player);
				}
			}
		}.runTaskTimer(plugin, 0L, 10L);
	}

	public void givePet(Player player, PetData petData)
	{
		if(_pets.containsKey(player))
			_pets.get(player).entity.getBukkitEntity().remove();
		
		Entity ent = petData.getPetType().givePet(player, petData.getPetType(), petData);
		petData.entity = ent;
		Creature c = (Creature) (ent.getBukkitEntity());
		c.setTarget(player);
		_pets.put(player, petData);
		
		startScheduler(_plugin);
	}
	
	public boolean isPet(org.bukkit.entity.Entity entity)
	{
		for(PetData data : _pets.values())
		{
			if(entity.getEntityId() != data.entity.getBukkitEntity().getEntityId())
				continue;
			
			return true;
		}
		
		return false;
	}
	
	/** Events */
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		if (_pets.isEmpty())
			return;
		
		if(isPet(event.getEntity()))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityTame(EntityTameEvent event)
	{
		if(_pets.isEmpty())
			return;
		
		if(isPet(event.getEntity()))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityInteract(PlayerInteractAtEntityEvent event)
	{
		if(_pets.isEmpty())
			return;
		
		if(!isPet(event.getRightClicked()))
			return;
			
		if(!event.getPlayer().isSneaking())
			return;
		
		if(!_pets.containsKey(event.getPlayer()))
			return;
		
		if(_pets.get(event.getPlayer()).entity.getId() != event.getRightClicked().getEntityId())
			return;
		
		MenuAPI.openMenu(event.getPlayer(), new PetMenu(_pets.get(event.getPlayer())));
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		if(_pets.isEmpty())
			return;
		
		if(!_pets.containsKey(event.getPlayer()))
			return;
		
		if(_pets.get(event.getPlayer()).entity.getBukkitEntity() != null)
			_pets.get(event.getPlayer()).entity.getBukkitEntity().remove();
		
		_pets.remove(event.getPlayer());
	}
	
	@EventHandler
	public void onBurn(EntityCombustEvent event)
	{
		if (_pets.isEmpty())
			return;
	
		if(isPet(event.getEntity()))
			event.setCancelled(true);
	}
}
 