package me.NotoriousPark.ScoreboardPlus.listeners;

import me.NotoriousPark.ScoreboardPlus.Objectives;
import me.NotoriousPark.ScoreboardPlus.ScoreboardPlus;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerWorldJoinListener extends ScoreboardPlus implements Listener {

    public PlayerWorldJoinListener(ScoreboardPlus plugin) {}

    @EventHandler
    public void onWorldJoin(PlayerChangedWorldEvent event) {
        for (String s : getObjectivesList()) {
            if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective(s).toString() == Objectives.playerWorldJoin.toString()) {
                if (event.getPlayer().getWorld() == getObjectiveWorldMap().get(s)) {
                    int score = Integer.parseInt(Bukkit.getScoreboardManager().getMainScoreboard().getObjective(s).getScore(s).toString());
                    Bukkit.getScoreboardManager().getMainScoreboard().getObjective(s).getScore(s).setScore(score + 1);
                }
            }
        }
    }
}