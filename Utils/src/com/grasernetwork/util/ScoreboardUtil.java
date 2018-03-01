package com.grasernetwork.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardUtil
{
	private static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static Scoreboard mainScoreboard = manager.getMainScoreboard();

    public static Scoreboard createNewScoreboard() {
        Scoreboard board = manager.getNewScoreboard();
        return board;
    }

    public static Objective setupObjective(Scoreboard board, DisplaySlot slot, String name)
    {
        Objective objective = board.registerNewObjective("Board", "dummy");
        objective.setDisplayName(name);
        objective.setDisplaySlot(slot);

        return objective;
    }

    public static void setScore(Objective o, String message, int row) {
        if (message == null) {
            int spaces = Integer.parseInt((row + "").replace("-", ""));
            message = ChatColor.WHITE + "";
            for (int x = 0; x < spaces; x++) {
                message = message + " ";
            }
        }

        Score score = o.getScore(message);
        score.setScore(row);
    }
}
