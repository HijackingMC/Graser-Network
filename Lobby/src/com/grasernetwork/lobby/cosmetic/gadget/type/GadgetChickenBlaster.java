package com.grasernetwork.lobby.cosmetic.gadget.type;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.lobby.cosmetic.gadget.Gadget;
import com.grasernetwork.lobby.cosmetic.gadget.misc.Cooldown;
import com.grasernetwork.lobby.cosmetic.gadget.misc.TriggerType;
import com.grasernetwork.lobby.cosmetic.misc.Buyable;
import com.grasernetwork.lobby.cosmetic.misc.RarityType;

public class GadgetChickenBlaster extends Gadget implements Buyable, Cooldown
{
	private Random _r = new Random();

	public GadgetChickenBlaster()
	{
		super("Chicken Blaster", Material.COOKED_CHICKEN, TriggerType.LEFT_RIGHT_CLICK, RarityType.COMMON, 5000);
	}

	@Override
	public double getCooldown()
	{
		return 4;
	}

	@Override
	public void trigger(final Player player)
	{
		final Chicken chicken = (Chicken) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.CHICKEN);
		
		chicken.setVelocity(player.getLocation().getDirection().multiply(Math.PI / 1.5));
		chicken.setNoDamageTicks(1000);
//		player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_HURT, 1.5F, 1.6F);
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				spawnRandomFirework(chicken.getLocation(), player);
				player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_HURT, 1.4f, 1.5f);
				chicken.remove();
			}
		}.runTaskLater(getManager().getPlugin(), 9);
	}
	
	private void spawnRandomFirework(Location location, Player player) 
	{
        final ArrayList<Firework> fireworks = new ArrayList<>();
        for (int i = 0; i < 2; i++)
        {
            final Firework f = player.getWorld().spawn(location, Firework.class);

            FireworkMeta fm = f.getFireworkMeta();
            fm.addEffect(getRandomFireworkEffect());
            f.setFireworkMeta(fm);
            fireworks.add(f);
        }
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                for (Firework f : fireworks)
                    f.detonate();
            }
        }.runTaskLater(getManager().getPlugin(), 2);
    }
	
    private FireworkEffect getRandomFireworkEffect()
    {
        FireworkEffect.Builder builder = FireworkEffect.builder();
        FireworkEffect effect = builder.flicker(false).trail(false).with(FireworkEffect.Type.BALL_LARGE).withColor(Color.fromRGB(_r.nextInt(255), _r.nextInt(255), _r.nextInt(255))).withFade(Color.fromRGB(_r.nextInt(255), _r.nextInt(255), _r.nextInt(255))).build();
        return effect;
    }
}