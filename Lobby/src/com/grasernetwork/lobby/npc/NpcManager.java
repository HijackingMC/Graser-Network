package com.grasernetwork.lobby.npc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.EnumItemSlot;
import net.minecraft.server.v1_9_R1.MinecraftServer;
import net.minecraft.server.v1_9_R1.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_9_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_9_R1.PlayerConnection;
import net.minecraft.server.v1_9_R1.PlayerInteractManager;
import net.minecraft.server.v1_9_R1.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R1.CraftServer;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class NpcManager implements Listener
{
	private HashSet<EntityPlayer> _npcs = new HashSet<EntityPlayer>();
	private HashMap<EntityPlayer, ItemStack> _equipment = new HashMap<EntityPlayer, ItemStack>();
	
	public NpcManager(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        
        String texture = "textures";
        GameProfile profile = null;
        
        for(NpcList e : NpcList.values())
        {
        	profile = new GameProfile(UUID.randomUUID(), e.getServerName());
            profile.getProperties().clear();
            profile.getProperties().put(texture, new Property(texture, e.getSkinData(), e.getSkinSignature()));
            EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, profile, new PlayerInteractManager(nmsWorld));
            npc.setLocation(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), e.getLocation().getYaw(), e.getLocation().getPitch());
            
            _npcs.add(npc);
            
            if(e.getEquipment() != null)
            {
            	_equipment.put(npc, e.getEquipment());
            }
        }
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event)
	{
		PlayerConnection connection = ((CraftPlayer) event.getPlayer()).getHandle().playerConnection;
		for(EntityPlayer npc : _npcs)
		{
	        connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, npc));
	        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
	        
	        if(_equipment.containsKey(npc))
	        {
	        	connection.sendPacket(new PacketPlayOutEntityEquipment(npc.getId(), EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(_equipment.get(npc))));
	        }
		}
	}
}
