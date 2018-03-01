package com.grasernetwork.game.components.player.inventory;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;

/**
 * Created by Teddeh on 10/03/2016.
 */
public class CraftItemComponent extends Component
{
	private boolean allow;
	private Material[] materials;

	public CraftItemComponent(GamePlugin plugin, boolean selfRegister, boolean allow)
	{
		super(plugin, selfRegister);

		this.allow = allow;
		this.materials = null;
	}

	public CraftItemComponent addExceptions(Material... materials)
	{
		this.materials = materials;
		return this;
	}

	@EventHandler
	public void onItemCraft(CraftItemEvent event)
	{
		if(event.getRecipe() == null)
			return;

		if(event.getRecipe().getResult() == null)
			return;

		if(materials != null)
		{
			for(Material material : materials)
			{
				if (event.getRecipe().getResult().getType() != material)
					continue;

				event.setCancelled(false);
				return;
			}

			event.setCancelled(allow);
		}

		event.setCancelled(allow);
	}
}
