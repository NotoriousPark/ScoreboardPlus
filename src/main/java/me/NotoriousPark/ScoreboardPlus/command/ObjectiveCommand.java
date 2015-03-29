package me.NotoriousPark.ScoreboardPlus.command;

import me.NotoriousPark.ScoreboardPlus.Objectives;
import me.NotoriousPark.ScoreboardPlus.ScoreboardPlus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.ScoreboardManager;

public class ObjectiveCommand extends ScoreboardPlus implements CommandExecutor {

    //Accesses the /scoreboard command to register the new objectives
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("scoreboard") && args[0].equalsIgnoreCase("objective")) {

            ScoreboardManager board = Bukkit.getScoreboardManager();

            if (args[1].equalsIgnoreCase("add")) {
                if (board.getMainScoreboard().getObjective(args[2]) != null) {
                    return false;
                } else {
                    if (!args[3].isEmpty()) {
                        for (Objectives obj : Objectives.values()) {
                            if (args[3].equalsIgnoreCase(obj.toString())) {

                                //Sets the objectives display name to a dummy.
                                board.getNewScoreboard().registerNewObjective(args[2], "dummy");
                                getObjectiveMap().put(args[2], obj);
                                getObjectivesList().add(args[2]);

                                //Checks if it is a world objective
                                if (args[3].equalsIgnoreCase(Objectives.playerWorldJoin.toString())) {
                                    if (args[4].isEmpty()) {
                                        sender.sendMessage(ChatColor.RED + "Invalid Syntax.");
                                        return false;
                                    } else {
                                        //Registers worlds for world objectives.
                                        getObjectiveWorldMap().put(args[2], Bukkit.getWorld(args[4]));
                                        return true;
                                    }
                                }

                                return true;
                            }
                        }
                    }
                }
            } else if (args[1].equalsIgnoreCase("remove")) {
                if (board.getMainScoreboard().getObjective(args[2]) == null) {
                    return false;
                } else {
                    board.getNewScoreboard().getObjectives().remove(args[2]);
                    getObjectiveMap().remove(args[2]);
                    getObjectiveWorldMap().remove(args[2]);
                    getObjectivesList().remove(args[2]);
                    return true;
                }
            }
        }
        return false;
    }
}