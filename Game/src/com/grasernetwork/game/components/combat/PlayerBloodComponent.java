package com.grasernetwork.game.components.combat;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class PlayerBloodComponent extends Component
{
	public PlayerBloodComponent(GamePlugin plugin)
	{
		super(plugin, true);
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event)
	{
		if(event.isCancelled())
			return;

		if(!(event.getEntity() instanceof Player))
			return;

		Player player = (Player) event.getEntity();

		if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			return;

		player.getWorld().playEffect(player.getLocation().add(0,1,0), Effect.STEP_SOUND, 152);
	}
}
