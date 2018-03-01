package com.grasernetwork.game.components.combat;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import net.minecraft.server.v1_9_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class HitIndicatorComponent extends Component
{
	private int i = 100000000;
	private EntityType[] entityTypes;
	private GamePlugin plugin;

	public HitIndicatorComponent(GamePlugin plugin, EntityType... entityTypes)
	{
		super(plugin, true);

		this.plugin = plugin;
		this.entityTypes = entityTypes;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event)
	{
		if(event.isCancelled())
			return;

		if(!(event.getEntity() instanceof Player))
			return;

		if(!(event.getDamager() instanceof Player))
			return;

		Player player = ((Player) event.getDamager());

		Location l = event.getEntity().getLocation();
		EntityArmorStand armorStand = new EntityArmorStand(
				((CraftWorld) player.getWorld()).getHandle(),
				(int)Math.floor(l.getX() * 32.0),
				(int)Math.floor((l.getY() + 2.1) * 32.0),
				(int)Math.floor(l.getZ() * 32.0));

		armorStand.setSmall(true);
		armorStand.setMarker(false);
//		armorStand.a(new AxisAlignedBB(null));
//		armorStand.a(UUID.randomUUID());

		PacketPlayOutSpawnEntityLiving spawn = new PacketPlayOutSpawnEntityLiving(armorStand);
		final List<Player> recievedPacket = new ArrayList<Player>();

		for(org.bukkit.entity.Entity e : l.getWorld().getEntities())
		{
			if(!(e instanceof Player))
				continue;

			Player target = (Player) e;
			if(target.equals((Player) event.getEntity()))
				continue;

			if(target.getLocation().distance(player.getLocation()) > 30)
				continue;

			((CraftPlayer) target).getHandle().playerConnection.sendPacket(spawn);
			recievedPacket.add(target);
		}

		final PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(armorStand.getId());
		new BukkitRunnable()
		{
			public void run()
			{
				for(Player p : recievedPacket)
				{
					if(p == null)
						continue;

					((CraftPlayer) p).getHandle().playerConnection.sendPacket(destroy);
				}
			}
		}.runTaskLater(plugin, 40);
	}

	public int getRandomUuid()
	{
		if(i == Integer.MAX_VALUE)
			i = 100000000;

		return i;
	}
}
