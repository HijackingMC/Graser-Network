package com.grasernetwork.lobby.cosmetic.morph;

import com.grasernetwork.lobby.cosmetic.morph.command.MorphCommand;
import com.grasernetwork.lobby.cosmetic.morph.type.*;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.profile.ProfileManager;
import net.minecraft.server.v1_9_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class MorphManager implements Listener, IMorph
{
	private HashMap<String, Morph> _morphs = new HashMap<String, Morph>();
	
	private JavaPlugin _plugin;
	private ProfileManager _profileManager;
	
	public MorphManager(JavaPlugin plugin, ProfileManager profileManager)
	{
		new MorphCommand(this);
		_plugin = plugin;
		_profileManager = profileManager;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@Override
	public void morphPlayer(Player player, Player target, Morph morph)
	{
		EntityType entityType = morph.getEntityType();
		if(entityType == null)
			return;
		
		Entity entity = null;
		World world = ((CraftWorld) player.getWorld()).getHandle();
		
		switch(entityType)
		{
		case BAT:
			entity = new EntityBat(world);
			break;
			
		case BLAZE:
			entity = new EntityBlaze(world);
			break;
			
		case CAVE_SPIDER:
			entity = new EntityCaveSpider(world);
			break;
			
		case CHICKEN:
			entity = new EntityChicken(world);
			break;

		case COW:
			entity = new EntityCow(world);
			break;
			
		case CREEPER:
			entity = new EntityCreeper(world);
			break;
			
		case ENDERMAN:
			entity = new EntityEnderman(world);
			break;
			
		case ENDERMITE:
			entity = new EntityEndermite(world);
			break;
			
		case GHAST:
			entity = new EntityGhast(world);
			break;
			
		case GUARDIAN:
			entity = new EntityGuardian(world);
			break;
			
		case HORSE:
			entity = new EntityHorse(world);
			break;
			
		case IRON_GOLEM:
			entity = new EntityIronGolem(world);
			break;
			
		case MAGMA_CUBE:
			entity = new EntityMagmaCube(world);
			break;
			
		case MUSHROOM_COW:
			entity = new EntityMushroomCow(world);
			break;
			
		case OCELOT:
			entity = new EntityOcelot(world);
			break;
			
		case PIG:
			entity = new EntityPig(world);
			break;
			
		case PIG_ZOMBIE:
			entity = new EntityPigZombie(world);
			break;
			
		case PLAYER:
//			entity = new EntityPlayer(world);
			//TODO
			break;
			
		case RABBIT:
			entity = new EntityRabbit(world);
			break;
			
		case SHEEP:
			entity = new EntitySheep(world);
			break;
			
		case SILVERFISH:
			entity = new EntitySilverfish(world);
			break;
			
		case SKELETON:
			entity = new EntitySkeleton(world);
			break;
			
		case SLIME:
			entity = new EntitySlime(world);
			break;
			
		case SNOWMAN:
			entity = new EntitySnowman(world);
			break;
			
		case SPIDER:
			entity = new EntitySpider(world);
			break;
			
		case SQUID:
			entity = new EntitySquid(world);
			break;
			
		case VILLAGER:
			entity = new EntityVillager(world);
			break;
			
		case WITCH:
			entity = new EntityWitch(world);
			break;
			
		case WITHER:
			entity = new EntityWither(world);
			break;
			
		case WOLF:
			entity = new EntityWolf(world);
			break;
			
		case ZOMBIE:
			entity = new EntityZombie(world);
			break;
			
		default:
			break;
		}
		
		entity.setPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
		entity.setCustomName(player.getName());
		entity.setCustomNameVisible(true);
		entity.c(player.getEntityId());
		entity.locX = player.getLocation().getX();
		entity.locY = player.getLocation().getY();
		entity.locZ = player.getLocation().getZ();
		entity.yaw = player.getLocation().getYaw();
		entity.pitch = player.getLocation().getPitch();
		
		PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(player.getEntityId());
		PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving((EntityLiving) entity);
		
		if(target == null)
			return;
		
		((CraftPlayer) target).getHandle().playerConnection.sendPacket(destroyPacket);
		((CraftPlayer) target).getHandle().playerConnection.sendPacket(spawnPacket);
		
		_morphs.put(player.getName(), morph);
		
		if(_profileManager.getProfile(player) == null)
			return;
		
		Profile profile = _profileManager.getProfile(player);
//		if(profile.getMorph() == null)
//		{
//			profile.setMorph(morph); TODO
//			return;
//		}
		
//		if(profile.getMorph().getEntityType() == entityType)
//			return;
		
//		profile.setMorph(morph); TODO
	}
	
	@Override
	public void morphPlayerToAll(Player player, Morph morph)
	{
		EntityType entityType = morph.getEntityType();
		if(entityType == null)
			return;
		
		Entity entity = null;
		World world = ((CraftWorld) player.getWorld()).getHandle();
		
		switch(entityType)
		{
		case BAT:
			entity = new EntityBat(world);
			break;
			
		case BLAZE:
			entity = new EntityBlaze(world);
			break;
			
		case CAVE_SPIDER:
			entity = new EntityCaveSpider(world);
			break;
			
		case CHICKEN:
			entity = new EntityChicken(world);
			break;

		case COW:
			entity = new EntityCow(world);
			break;
			
		case CREEPER:
			entity = new EntityCreeper(world);
			break;
			
		case ENDERMAN:
			entity = new EntityEnderman(world);
			break;
			
		case ENDERMITE:
			entity = new EntityEndermite(world);
			break;
			
		case GHAST:
			entity = new EntityGhast(world);
			break;
			
		case GUARDIAN:
			entity = new EntityGuardian(world);
			break;
			
		case HORSE:
			entity = new EntityHorse(world);
			break;
			
		case IRON_GOLEM:
			entity = new EntityIronGolem(world);
			break;
			
		case MAGMA_CUBE:
			entity = new EntityMagmaCube(world);
			break;
			
		case MUSHROOM_COW:
			entity = new EntityMushroomCow(world);
			break;
			
		case OCELOT:
			entity = new EntityOcelot(world);
			break;
			
		case PIG:
			entity = new EntityPig(world);
			break;
			
		case PIG_ZOMBIE:
			entity = new EntityPigZombie(world);
			break;
			
		case PLAYER:
//			entity = new EntityPlayer(world);
			//TODO
			break;
			
		case RABBIT:
			entity = new EntityRabbit(world);
			break;
			
		case SHEEP:
			entity = new EntitySheep(world);
			break;
			
		case SILVERFISH:
			entity = new EntitySilverfish(world);
			break;
			
		case SKELETON:
			entity = new EntitySkeleton(world);
			break;
			
		case SLIME:
			entity = new EntitySlime(world);
			break;
			
		case SNOWMAN:
			entity = new EntitySnowman(world);
			break;
			
		case SPIDER:
			entity = new EntitySpider(world);
			break;
			
		case SQUID:
			entity = new EntitySquid(world);
			break;
			
		case VILLAGER:
			entity = new EntityVillager(world);
			break;
			
		case WITCH:
			entity = new EntityWitch(world);
			break;
			
		case WITHER:
			entity = new EntityWither(world);
			break;
			
		case WOLF:
			entity = new EntityWolf(world);
			break;
			
		case ZOMBIE:
			entity = new EntityZombie(world);
			break;
			
		default:
			break;
		}
		
		entity.setPosition(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
		entity.setCustomName(player.getName());
		entity.setCustomNameVisible(true);
		entity.c(player.getEntityId());
		entity.locX = player.getLocation().getX();
		entity.locY = player.getLocation().getY();
		entity.locZ = player.getLocation().getZ();
		entity.yaw = player.getLocation().getYaw();
		entity.pitch = player.getLocation().getPitch();
		
		PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(player.getEntityId());
		PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving((EntityLiving) entity);
		
		for(Player online : Bukkit.getOnlinePlayers())
		{
			if(online == null)
				continue;
			
			if(online.getName().equals(player.getName()))
				continue;
			
			((CraftPlayer) online).getHandle().playerConnection.sendPacket(destroyPacket);
			((CraftPlayer) online).getHandle().playerConnection.sendPacket(spawnPacket);
		}
		
		_morphs.put(player.getName(), morph);
		
		if(_profileManager.getProfile(player) == null)
			return;
		
		Profile profile = _profileManager.getProfile(player);
//		if(profile.getMorph() == null)
//		{
//			profile.setMorph(morph); TODO
//			return;
//		}
//
//		if(profile.getMorph().getEntityType() == entityType)
//			return;
		
//		profile.setMorph(morph); TODO
	}

	@Override
	public void unMorphPlayer(Player player)
	{
		PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(player.getEntityId());
		PacketPlayOutSpawnEntityLiving spawnPacket = new PacketPlayOutSpawnEntityLiving(((CraftPlayer) player).getHandle());
		
		for(Player online : Bukkit.getOnlinePlayers())
		{
			if(online == null)
				continue;
			
			if(online.getName().equals(player.getName()))
				continue;
			
			((CraftPlayer) online).getHandle().playerConnection.sendPacket(destroyPacket);
			((CraftPlayer) online).getHandle().playerConnection.sendPacket(spawnPacket);
		}
		
		_morphs.remove(player.getName());
	}
	
	public Morph getMorphFromType(EntityType entityType)
	{
		Morph morph = null;
		
		switch(entityType)
		{
		case BAT:
			morph = new MorphBat();
			break;
			
		case BLAZE:
			morph = new MorphBlaze();
			break;
			
		case CAVE_SPIDER:
			morph = new MorphCaveSpider();
			break;
			
		case CHICKEN:
			morph = new MorphChicken();
			break;

		case COW:
			morph = new MorphCow();
			break;
			
		case CREEPER:
			morph = new MorphCreeper();
			break;
			
		case ENDERMAN:
			morph = new MorphEnderman();
			break;
			
		case ENDERMITE:
			morph = new MorphEndermite();
			break;
			
		case GHAST:
			morph = new MorphGhast();
			break;
			
		case GUARDIAN:
			morph = new MorphGuardian();
			break;
			
		case HORSE:
			morph = new MorphHorse();
			break;
			
		case IRON_GOLEM:
			morph = new MorphIronGolem();
			break;
			
		case MAGMA_CUBE:
			morph = new MorphMagmaCube();
			break;
			
		case MUSHROOM_COW:
			morph = new MorphMooshroom();
			break;
			
		case OCELOT:
			morph = new MorphOcelot();
			break;
			
		case PIG:
			morph = new MorphPig();
			break;
			
		case PIG_ZOMBIE:
			morph = new MorphZombiePig();
			break;
			
		case PLAYER:
//			entity = new EntityPlayer(world);
			//TODO
			break;
			
		case RABBIT:
			morph = new MorphRabbit();
			break;
			
		case SHEEP:
			morph = new MorphSheep();
			break;
			
		case SILVERFISH:
			morph = new MorphSilverfish();
			break;
			
		case SKELETON:
			morph = new MorphSkeleton();
			break;
			
		case SLIME:
			morph = new MorphSlime();
			break;
			
		case SNOWMAN:
			morph = new MorphSnowman();
			break;
			
		case SPIDER:
			morph = new MorphSpider();
			break;
			
		case SQUID:
			morph = new MorphSquid();
			break;
			
		case VILLAGER:
			morph = new MorphVillager();
			break;
			
		case WITCH:
			morph = new MorphWitch();
			break;
			
		case WITHER:
			morph = new MorphWither();
			break;
			
		case WOLF:
			morph = new MorphWolf();
			break;
			
		case ZOMBIE:
			morph = new MorphZombie();
			break;
			
		default:
			break;
		}
		
		return morph;
	}
	
	@EventHandler
	public void handleJoinPackets(PlayerJoinEvent event)
	{
		for(String str : _morphs.keySet())
		{
			if(Bukkit.getPlayerExact(str) == null)
			{
				_morphs.remove(str);
				continue;
			}
			
			morphPlayer(Bukkit.getPlayerExact(str), event.getPlayer(), _morphs.get(str));
		}
	}
	
	@EventHandler
	public void handleLeavePackets(PlayerQuitEvent event)
	{
		if(!_morphs.containsKey(event.getPlayer().getName()))
			return;
		
		unMorphPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void handleUpdatePackets(ChunkLoadEvent event)
	{
		for(org.bukkit.entity.Entity entity : event.getChunk().getEntities())
		{
			if(!(entity instanceof Player))
				continue;
			
			Player player = (Player) entity;
			if(_morphs.containsKey(player.getName()) && _morphs.get(player.getName()) != null)
			{
				for(Player online : player.getWorld().getPlayers())
				{
					if(online.getName().equalsIgnoreCase(player.getName()))
						continue;
					
					morphPlayer(player, online, _morphs.get(player.getName()));
				}
			}
			else
			{
				for(Player online : player.getWorld().getPlayers())
				{
					if(online.getName().equalsIgnoreCase(player.getName()))
						continue;
					
					if(_morphs.containsKey(online.getName()) && _morphs.get(online.getName()) != null)
					{
						morphPlayer(online, player, _morphs.get(player));
					}
					
					if(_morphs.containsKey(player.getName()) && _morphs.get(player.getName()) != null)
					{
						morphPlayer(player, online, _morphs.get(player));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void handleWorldChnagePackets(PlayerChangedWorldEvent event)
	{
		final Player player = event.getPlayer();
		
		new BukkitRunnable()
		{
			
			@Override
			public void run()
			{
				for(Player online : player.getWorld().getPlayers())
				{
					if(_morphs.containsKey(player.getName()) && _morphs.get(player.getName()) != null)
					{
						morphPlayer(player, online, _morphs.get(player.getName()));
					}
					
					if(_morphs.containsKey(online.getName()) && _morphs.get(online.getName()) != null)
					{
						morphPlayer(online, player, _morphs.get(player.getName()));
					}
				}
			}
		}.runTaskLater(_plugin, 20);
	}
}
