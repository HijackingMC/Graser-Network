package com.grasernetwork.core.command;

import org.bukkit.entity.Player;

public interface ICommand
{
	void execute(Player sender, String[] args);
}
