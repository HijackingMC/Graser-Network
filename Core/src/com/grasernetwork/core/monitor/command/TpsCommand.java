package com.grasernetwork.core.monitor.command;

import java.text.DecimalFormat;

import net.minecraft.server.v1_9_R1.MinecraftServer;

import org.bukkit.entity.Player;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.PlayerUtil;

/**
 * Created by luke1 on 26/01/2016.
 */
public class TpsCommand extends CommandBase
{
    public TpsCommand()
    {
        super("tps", new String[]{"lag", "usage", "monitor"}, Rank.DEVELOPER, "/lag", "Monitor the server.");
    }

    @Override
    public void execute(Player sender, String[] args)
    {
        StringBuilder tps = new StringBuilder("Ticks Per Second: ");
        for(double d : MinecraftServer.getServer().recentTps)
        {
            tps.append(new DecimalFormat("##.#").format(d*5) + ", ");
        }

        PlayerUtil.message(sender, "");
        PlayerUtil.message(sender, "Current Tick: " + MinecraftServer.currentTick);
        PlayerUtil.message(sender, tps.toString());
        PlayerUtil.message(sender, "");
    }
}
