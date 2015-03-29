package me.NotoriousPark.ScoreboardPlus.listeners;

import me.NotoriousPark.ScoreboardPlus.Objectives;
import me.NotoriousPark.ScoreboardPlus.ScoreboardPlus;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinServerListener extends ScoreboardPlus implements Listener {

    public PlayerJoinServerListener(ScoreboardPlus plugin) {}

    //Adds points to the dummy score for each objective.
    @EventHandler
    public void onJoinServer(PlayerJoinEvent event) {
        for (String s : getObjectivesList()) {
            if (Bukkit.getScoreboardManager().getMainScoreboard().getObjective(s).toString() == Objectives.playerJoinServer.toString()) {
                int score = Integer.parseInt(Bukkit.getScoreboardManager().getMainScoreboard().getObjective(s).getScore(s).toString());
                Bukkit.getScoreboardManager().getMainScoreboard().getObjective(s).getScore(s).setScore(score + 1);
            }
        }
    }
}