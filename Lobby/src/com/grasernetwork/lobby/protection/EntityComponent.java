package com.grasernetwork.lobby.protection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.CreeperPowerEvent.PowerCause;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityComponent extends ProtectionComponent
{
	public EntityComponent(JavaPlugin plugin)
	{
		super(plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event)
	{
		switch(event.getSpawnReason())
		{
		case BED: event.setCancelled(true); break;
		case BREEDING: event.setCancelled(true); break;
		case BUILD_IRONGOLEM: event.setCancelled(true); break;
		case BUILD_SNOWMAN: event.setCancelled(true); break;
		case BUILD_WITHER: event.setCancelled(true); break;
		case DEFAULT: event.setCancelled(true); break;
		case DISPENSE_EGG: event.setCancelled(true); break;
		case EGG: event.setCancelled(true); break;
		case INFECTION: event.setCancelled(true); break;
		case JOCKEY: event.setCancelled(true); break;
		case LIGHTNING: event.setCancelled(true); break;
		case MOUNT: event.setCancelled(true); break;
		case NATURAL: event.setCancelled(true); break;
		case NETHER_PORTAL: event.setCancelled(true); break;
		case OCELOT_BABY: event.setCancelled(true); break;
		case REINFORCEMENTS: event.setCancelled(true); break;
		case SILVERFISH_BLOCK: event.setCancelled(true); break;
		case SLIME_SPLIT: event.setCancelled(true); break;
		case SPAWNER: event.setCancelled(true); break;
		case SPAWNER_EGG: event.setCancelled(true); break;
		case VILLAGE_DEFENSE: event.setCancelled(true); break;
		case VILLAGE_INVASION: event.setCancelled(true); break;
		default:
			break;
		
		}
	}
	
	@EventHandler
	public void onCreeperPower(CreeperPowerEvent event)
	{
		if(event.getCause() == PowerCause.LIGHTNING)
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityBreakDoor(EntityBreakDoorEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityCreatePortal(EntityCreatePortalEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityEnterPortal(EntityPortalEvent event)
	{
		if(!(event.getEntity() instanceof Player))
			event.setCancelled(true);
	}
	
	
}
