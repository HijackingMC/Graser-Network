package com.grasernetwork.game.components.player.inventory;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Teddeh on 10/03/2016.
 */
public class ItemDropComponent extends Component
{
	private boolean allow;

	public ItemDropComponent(GamePlugin plugin, boolean selfRegister, boolean allow)
	{
		super(plugin, selfRegister);

		this.allow = allow;
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event)
	{
		event.setCancelled(allow);
	}
}
