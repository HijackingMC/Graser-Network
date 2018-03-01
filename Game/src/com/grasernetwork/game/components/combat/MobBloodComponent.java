package com.grasernetwork.game.components.combat;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class MobBloodComponent extends Component
{
	public MobBloodComponent(GamePlugin plugin)
	{
		super(plugin, true);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event)
	{
		if(event.isCancelled())
			return;

		if(!(event.getEntity() instanceof LivingEntity))
			return;

		if(event.getEntity() instanceof Player)
			return;

		if(!(event.getDamager() instanceof Player))
			return;

		Entity ent = event.getEntity();
		Player player = (Player) event.getDamager();

		Effect effect = Effect.STEP_SOUND;
		int id = 0;

		switch(ent.getType())
		{
			case BAT:          id = 173; break;
			case BLAZE:        id = 41; break;
			case CAVE_SPIDER:  id = 49; break;
			case CHICKEN:      id = 35; break;
			case COW:          id = 88; break;
			case CREEPER:      id = 18; break;
			case ENDERMAN:     id = 49; break;
			case ENDERMITE:    break;
			case ENDER_DRAGON: id = 49; break;
			case GHAST:        id = 35; break;
			case GIANT:        break;
			case GUARDIAN:     break;
			case HORSE:        effect = Effect.ITEM_BREAK; id = 334; break;
			case IRON_GOLEM:   effect = Effect.ITEM_BREAK; id = 265; break;
			case MAGMA_CUBE:   id = 112; break;
			case MUSHROOM_COW: effect = Effect.ITEM_BREAK; id = 40; break;
			case OCELOT:       break;
			case PIG:          effect = Effect.ITEM_BREAK; id = 319; break;
			case PIG_ZOMBIE:   effect = Effect.ITEM_BREAK; id = 367; break;
			case RABBIT:       break;
			case SHEEP:        effect = Effect.ITEM_BREAK; id = 35; break;
			case SILVERFISH:   break;
			case SKELETON:     effect = Effect.ITEM_BREAK; id = 352; break;
			case SLIME:        id = 341; break;
			case SNOWMAN:      effect = Effect.ITEM_BREAK; id = 332; break;
			case SPIDER:       id = 173; break;
			case SQUID:        break;
			case VILLAGER:     effect = Effect.ITEM_BREAK; id = 388; break;
			case WITCH:        break;
			case WITHER:       id = 49; break;
			case WOLF:         id = 35; break;
			case ZOMBIE:       effect = Effect.ITEM_BREAK; id = 367; break;
			default:           id = 152; break;

		}

		if(id == 0)
			return;

		player.playEffect(ent.getLocation().add(0,1,0), effect, id);
//		ent.getWorld().playEffect(ent.getLocation(), effect, id);
//		ent.getWorld().playEffect(ent.getLocation(), Effect.ITEM_BREAK, 331);
	}
}
