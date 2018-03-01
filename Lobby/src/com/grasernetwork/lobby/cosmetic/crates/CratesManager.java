 package com.grasernetwork.lobby.cosmetic.crates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.server.v1_9_R1.BlockPosition;
import net.minecraft.server.v1_9_R1.EnumParticle;
import net.minecraft.server.v1_9_R1.PacketPlayOutBlockAction;
import net.minecraft.server.v1_9_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_9_R1.PlayerConnection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.lobby.cosmetic.crates.gui.CrateMenu;
import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic.ICosmeticReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.eco.IEcoReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.rank.IRankReward;
import com.grasernetwork.lobby.cosmetic.crates.type.Crate;
import com.grasernetwork.lobby.cosmetic.crates.type.HighCrate;
import com.grasernetwork.lobby.cosmetic.crates.type.LowCrate;
import com.grasernetwork.lobby.cosmetic.crates.type.MediumCrate;
import com.grasernetwork.util.AnnotationUtil;
import com.grasernetwork.util.C;
import com.grasernetwork.util.inventory.MenuAPI;

public class CratesManager implements Listener
{
	private List<Crate> _crates = new ArrayList<Crate>();
	private HashMap<CrateData, Player> _cratesInUse = new HashMap<CrateData, Player>();
	
	private JavaPlugin _plugin;
	
	public CratesManager(JavaPlugin plugin)
	{
		_plugin = plugin;
		
		_crates.add(new LowCrate());
		_crates.add(new MediumCrate());
		_crates.add(new HighCrate());
		
		Bukkit.getPluginManager().registerEvents(this, _plugin);
	}
	
	@EventHandler
	public void onCrateInteract(PlayerInteractEvent event)
	{
		final Player player = event.getPlayer();
		Action action = event.getAction();
		
		if(action != Action.LEFT_CLICK_BLOCK && action != Action.RIGHT_CLICK_BLOCK)
			return;

		Block block = event.getClickedBlock();
		
		if(block.getType() != Material.CHEST && block.getType() != Material.ENDER_CHEST && block.getType() != Material.ENDER_PORTAL_FRAME)
			return;
		
		if(block.getType() == Material.ENDER_PORTAL_FRAME)
		{
			if(!containsPlayer(player))
			{
				MenuAPI.openMenu(player, new CrateMenu(this, block));
				return;
			}
		}
		
		for(CrateData crateData : _cratesInUse.keySet())
		{
			for(Location loc : crateData.getCrateLocations())
			{
				if(!block.getLocation().equals(loc))
					continue;
				
				if(crateData.getPlayerUUID().equals(player.getUniqueId()))
					break;
				
				player.sendMessage("This crate is still in progress, please wait your turn.");
				if(!event.isCancelled())
					event.setCancelled(true);
				return;
			}
			
			if(crateData.getStartLocation().equals(block.getLocation()))
			{
				if(!crateData.getPlayerUUID().equals(player.getUniqueId()))
				{
					player.sendMessage("Something has gone wrong..");
					if(!event.isCancelled())
						event.setCancelled(true);
					return;
				}
				
				player.sendMessage("This crate is still in progress, please wait your turn.");
				if(!event.isCancelled())
					event.setCancelled(true);
				return;
			}
		}
		
		if(block.getType() == Material.ENDER_PORTAL_FRAME)
		{
			
			//TODO: Opening PORTAL FRAME Crate.
		}
		
		if(block.getType() == Material.CHEST || block.getType() == Material.ENDER_CHEST)
		{
			if(!containsPlayer(player))
				return;

			if(!event.isCancelled())
				event.setCancelled(true);
			
			final CrateData crateData = getPlayerCrate(player);
			if(crateData.getState() != CrateState.IN_PROGRESS)
				return;
			
			if(crateData.hasOpenedCrate(block.getLocation()))
			{
				player.sendMessage("Open a chest that you havn't already opened");
				return;
			}
			
			crateData.addCrateOpened(block.getLocation());
			openChestPacket(block.getLocation(), true);
			
			//TODO: RUN REWARD VISUALS HERE
			Reward reward = crateData.rewards.get(new Random().nextInt(crateData.rewards.size()));
			
			player.sendMessage("RewardType: " + reward.getRewardType().toString());
			player.sendMessage("ID: " + reward.getRewardId());
			player.sendMessage("Name: " + reward.getRewardName());
			player.sendMessage("Rarity: " + reward.getRarity());
			player.sendMessage(" ");
			
			crateData.rewards.remove(reward);
			
			ArmorStand hologram = player.getLocation().getWorld().spawn(block.getLocation().add(0.5, 1.25, 0.5), ArmorStand.class);
			hologram.setVisible(false);
			hologram.setGravity(false);
			hologram.setSmall(true);
			if(reward instanceof ICosmeticReward)
			{
				if(reward.getRewardType() == RewardType.GADGET)
					hologram.setCustomName(C.AquaB + "Gadget id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.HAT)
					hologram.setCustomName(C.AquaB + "Hat id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.MORPH)
					hologram.setCustomName(C.AquaB + "Morph id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.PARTICLE)
					hologram.setCustomName(C.AquaB + "Particle id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.PET)
					hologram.setCustomName(C.AquaB + "Pet id:" + ((ICosmeticReward) reward).getCosmeticId());
			}
			else if(reward instanceof IEcoReward)
				hologram.setCustomName(C.AquaB + "Crystals: " + ((IEcoReward) reward).getAmount());
			else if(reward instanceof IRankReward)
				hologram.setCustomName(C.AquaB + "Rank: " + ((IRankReward) reward).getRank().toString());
			hologram.setCustomNameVisible(true);
			crateData.holograms.add(hologram);
			
			ArmorStand hologram2 = player.getLocation().getWorld().spawn(block.getLocation().add(0.5, 1.0, 0.5), ArmorStand.class);
			hologram2.setVisible(false);
			hologram2.setGravity(false);
			hologram2.setSmall(true);
			hologram2.setCustomName("test");
			hologram2.setCustomNameVisible(true);
			crateData.holograms.add(hologram2);
			
			if(crateData.getCratesOpened() < 3)
				return;
			
			crateData.setState(CrateState.ENDING);
			
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					for(Location l : crateData.getCrateLocations())
					{
						l.getBlock().setType(Material.AIR);
					}
					
					player.teleport(crateData.getSavedLocation());
					crateData.getStartLocation().getBlock().setType(Material.ENDER_PORTAL_FRAME);
					for(ArmorStand armorStand : crateData.holograms)
					{
						if(armorStand == null)
							continue;
						
						armorStand.remove();
					}
					
					_cratesInUse.remove(crateData);
				}
			}.runTaskLater(_plugin, 60);
		}
	}
	
	public void start(final Player player, Block block)
	{
		if(isCrateInProgress(block.getLocation()))
		{
			player.sendMessage("this crate is in progress, soz m8.. r8 8/8 ");
			return;
		}
		
		player.sendMessage("Redeeming Crate!");
		Block face = block.getLocation().add(0,-2,0).getBlock();
		
		if(face == null || face.getType() == Material.AIR)
			return;
		
		if(face.getType() != Material.IRON_BLOCK && face.getType() != Material.EMERALD_BLOCK && face.getType() != Material.DIAMOND_BLOCK && face.getType() != Material.GOLD_BLOCK)
			return;
		
		BlockFace blockFace = null;
		
		if(face.getType() == Material.IRON_BLOCK)
			blockFace = BlockFace.SOUTH;
		else if(face.getType() == Material.EMERALD_BLOCK)
			blockFace = BlockFace.WEST;
		else if(face.getType() == Material.DIAMOND_BLOCK)
			blockFace = BlockFace.NORTH;
		else if(face.getType() == Material.GOLD_BLOCK)
			blockFace = BlockFace.EAST;
		
		Location[] crateLocations = new Location[]
				{
				getLocation(blockFace, 1, block.getLocation()),
				getLocation(blockFace, 2, block.getLocation()),
				getLocation(blockFace, 3, block.getLocation())
				};
		
		Location loc = block.getLocation();
		final CrateData crateData = new CrateData(
				player, 
				CrateRating.UNRATED, 
				CrateState.STARTING, 
				loc, 
				crateLocations, 
				blockFace, 
				player.getLocation());
		
		_cratesInUse.put(crateData, player);
		crateData.getStartLocation().getBlock().setType(Material.AIR);
		//TODO: Teleport player
		player.teleport(crateData.getStartLocation().add(0.5, 0.5, 0.5));
		crateData.rewards.addAll(Arrays.asList(getRandomReward(crateData.getCrateRating())));
		
		final List<FallingBlock> fallingBlocks = new ArrayList<FallingBlock>();
		for(int i=0; i<crateData.getCrateLocations().length; i++)
		{
			Location l = crateData.getCrateLocations()[i];
			Crate crate = getCratesFromRating(crateData.getCrateRating())[i];
			Material material = null;
			
			for(Material mat : AnnotationUtil.getMaterials(crate.getClass()))
			{
				if(mat != Material.WOOD && mat != Material.OBSIDIAN && mat != Material.ENDER_STONE)
					continue;
				
				material = mat;
			}
			
			if(material == null)
				material = Material.WOOD;
			
			@SuppressWarnings("deprecation")
			FallingBlock fb = l.getWorld().spawnFallingBlock(new Location(l.getWorld(), l.getX(), (l.getY() + 30), l.getZ()), material, (byte) 0);
			fallingBlocks.add(fb);
		}
		
		new BukkitRunnable()
		{
			
			@Override
			public void run()
			{
				for(FallingBlock fb : fallingBlocks)
				{
					if(fb == null || fb.isDead())
					{
						if(crateData.getState() != CrateState.IN_PROGRESS)
							crateData.setState(CrateState.IN_PROGRESS);
						
						this.cancel();
						break;
					}
					
					final Location loc = fb.getLocation();
					PacketPlayOutWorldParticles[] particles = new PacketPlayOutWorldParticles[]
							{
							new PacketPlayOutWorldParticles(EnumParticle.LAVA, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0.6, (float)0.4, (float)0.6, 0, 10, 0),

							new PacketPlayOutWorldParticles(EnumParticle.CLOUD, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0, (float)0, (float)0, 0, 3, 0),
							new PacketPlayOutWorldParticles(EnumParticle.CLOUD, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0.2, (float)0.2, (float)0.2, 0, 3, 0),
							new PacketPlayOutWorldParticles(EnumParticle.CLOUD, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0.4, (float)0.4, (float)0.4, 0, 3, 0),
							new PacketPlayOutWorldParticles(EnumParticle.CLOUD, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0.6, (float)0.4, (float)0.6, 0, 3, 0),
							
							new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_NORMAL, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0, (float)0, (float)0, 0, 1, 0),
							new PacketPlayOutWorldParticles(EnumParticle.SMOKE_LARGE, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0.2, (float)0.2, (float)0.2, 0, 2, 0),
							new PacketPlayOutWorldParticles(EnumParticle.SMOKE_LARGE, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0.4, (float)0.4, (float)0.4, 0, 2, 0),
							new PacketPlayOutWorldParticles(EnumParticle.SMOKE_LARGE, true, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), (float)0.6, (float)0.4, (float)0.6, 0, 2, 0)
							};
					
					for(Player online : loc.getWorld().getPlayers())
					{
						PlayerConnection connection = ((CraftPlayer) online).getHandle().playerConnection;
						for(PacketPlayOutWorldParticles packet : particles)
							connection.sendPacket(packet);
					}
				}
			}
		}.runTaskTimer(_plugin, 1, 3);
	}
	
	public Reward[] getRandomReward(CrateRating crateRating)
	{
		List<Reward> test = new ArrayList<Reward>();
		
		for(Reward reward : crateRating.getAvailableRewards())
		{
			for(double x = 0; x < reward.getRarity(); x+=0.1)
			{
				test.add(reward);
			}
		}
		
		Reward reward = test.get(new Random().nextInt(test.size()));
		Reward reward2 = test.get(new Random().nextInt(test.size()));
		Reward reward3 = test.get(new Random().nextInt(test.size()));
		
		test.clear();
		
		if(reward == null || reward2 == null || reward3 == null)
			return getRandomReward(crateRating);
		
		return new Reward[] {reward, reward2, reward3};
	}
	
	public boolean containsPlayer(Player player)
	{
		for(CrateData crateData : _cratesInUse.keySet())
		{
			if(crateData.getPlayerUUID().equals(player.getUniqueId()))
				return true;
		}
		
		return false;
	}
	
	public boolean isCrateInProgress(Location startLocation)
	{
		for(CrateData crate : _cratesInUse.keySet())
		{
			if(crate.getStartLocation().getBlock().getLocation().equals(startLocation.getBlock().getLocation()))
				return true;
		}
		
		return false;
	}
	
	public CrateData getPlayerCrate(Player player)
	{
		for(CrateData crateData : _cratesInUse.keySet())
		{
			if(_cratesInUse.get(crateData).getUniqueId().equals(player.getUniqueId()))
				return crateData;
		}
		
		return null;
	}
	
	public Crate[] getCratesFromRating(CrateRating rating)
	{
		if(rating == null)
			return new Crate[] {new LowCrate(), new LowCrate(), new LowCrate()};
		
		if(rating == CrateRating.UNRATED)
			return new Crate[] {new LowCrate(), new LowCrate(), new LowCrate()};

		if(rating == CrateRating.COMMON)
			return new Crate[] {new LowCrate(), new MediumCrate(), new LowCrate()};

		if(rating == CrateRating.RARE)
			return new Crate[] {new MediumCrate(), new LowCrate(), new MediumCrate()};

		if(rating == CrateRating.EPIC)
			return new Crate[] {new MediumCrate(), new MediumCrate(), new MediumCrate()};

		if(rating == CrateRating.LEGENDARY)
			return new Crate[] {new MediumCrate(), new HighCrate(), new MediumCrate()};
		
		return new Crate[] {new LowCrate(), new LowCrate(), new LowCrate()};
	}
	
	public byte getOppositeFace(BlockFace face)
	{
		if(face == BlockFace.NORTH)
			return 3;
		
		else if(face == BlockFace.EAST)
			return 4;
		
		else if(face == BlockFace.SOUTH)
			return 2;
		
		else if(face == BlockFace.WEST)
			return 5;
		
		return 3;
	}
	
	public Location getLocation(BlockFace face, int i, Location loc)
	{
		if(face == BlockFace.NORTH)
		{
			if(i == 1)
				return new Location(loc.getWorld(), (loc.getX() - 2.0), loc.getY(), (loc.getZ() - 3.0));
			else if(i == 2)
				return new Location(loc.getWorld(), loc.getX(), loc.getY(), (loc.getZ() - 4.0));
			else if(i == 3)
				return new Location(loc.getWorld(), (loc.getX() + 2.0), loc.getY(), (loc.getZ() - 3.0));
		}
		
		else if(face == BlockFace.EAST)
		{
			if(i == 1)
				return new Location(loc.getWorld(), (loc.getX() + 3.0), loc.getY(), (loc.getZ() - 2.0));
			else if(i == 2)
				return new Location(loc.getWorld(), (loc.getX() + 4.0), loc.getY(), loc.getZ());
			else if(i == 3)
				return new Location(loc.getWorld(), (loc.getX() + 3.0), loc.getY(), (loc.getZ() + 2.0));
		}
		
		else if(face == BlockFace.SOUTH)
		{
			if(i == 1)
				return new Location(loc.getWorld(), (loc.getX() + 2.0), loc.getY(), (loc.getZ() + 3.0));
			else if(i == 2)
				return new Location(loc.getWorld(), loc.getX(), loc.getY(), (loc.getZ() + 4.0));
			else if(i == 3)
				return new Location(loc.getWorld(), (loc.getX() - 2.0), loc.getY(), (loc.getZ() + 3.0));
		}
		
		else if(face == BlockFace.WEST)
		{
			if(i == 1)
				return new Location(loc.getWorld(), (loc.getX() - 3.0), loc.getY(), (loc.getZ() + 2.0));
			else if(i == 2)
				return new Location(loc.getWorld(), (loc.getX() - 4.0), loc.getY(), loc.getZ());
			else if(i == 3)
				return new Location(loc.getWorld(), (loc.getX() - 3.0), loc.getY(), (loc.getZ() - 2.0));
		}
		
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void blockForm(EntityChangeBlockEvent event)
	{
		if(event.getEntityType() == EntityType.FALLING_BLOCK)
		{
			FallingBlock fb = (FallingBlock) event.getEntity();
			if(fb.getBlockId() != Material.ENDER_STONE.getId() && fb.getBlockId() != Material.OBSIDIAN.getId() && fb.getBlockId() != Material.WOOD.getId())
				return;
			
			event.setCancelled(true);
			event.getEntity().remove();
			Block block = event.getBlock();
			block.setType(Material.AIR);
			
			BlockFace face = null;
			for(CrateData data : _cratesInUse.keySet())
			{
				for(Location loc : data.getCrateLocations())
				{
					if(!loc.getBlock().getLocation().equals(block.getLocation()))
						continue;
					
					face = data.getFacing();
				}
			}
			
			if(face == null)
				face = BlockFace.NORTH;
			
			if(fb.getBlockId() == Material.ENDER_STONE.getId())
			{
				block.setType(Material.ENDER_PORTAL_FRAME);
			}
			
			if(fb.getBlockId() == Material.OBSIDIAN.getId())
			{
				block.setType(Material.ENDER_CHEST);
				block.setData(getOppositeFace(face));
			}
			
			if(fb.getBlockId() == Material.WOOD.getId())
			{
				block.setType(Material.CHEST);
				block.setData(getOppositeFace(face));
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void openChestPacket(Location loc, Boolean isOpen)
	{
		Byte data = (isOpen) ? (byte) 1 : 0;
		BlockPosition position = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(position, net.minecraft.server.v1_9_R1.Block.getById(loc.getBlock().getTypeId()), (byte) 1, data);
		
		for (Player online : Bukkit.getOnlinePlayers())
			((CraftPlayer) online).getHandle().playerConnection.sendPacket(packet);
	}
}
