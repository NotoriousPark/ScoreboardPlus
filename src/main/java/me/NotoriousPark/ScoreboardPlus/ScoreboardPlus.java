package me.NotoriousPark.ScoreboardPlus;

import me.NotoriousPark.ScoreboardPlus.listeners.PlayerJoinServerListener;
import me.NotoriousPark.ScoreboardPlus.listeners.PlayerLeaveServerListener;
import me.NotoriousPark.ScoreboardPlus.listeners.PlayerWorldJoinListener;
import me.NotoriousPark.ScoreboardPlus.listeners.PlayerWorldLeaveListener;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreboardPlus extends JavaPlugin {
    private Command cmd;
    private String[] args;
    private ArrayList<String> objectives = new ArrayList<String>();
    private HashMap<String, Objectives> objectiveMap = new HashMap<String, Objectives>();
    private HashMap<String, World> objectiveWorldMap = new HashMap<String, World>();

    @Override
    public void onEnable() {
        register();

        for (Objectives obj : Objectives.values()) {
            objectives.add(obj.toString());
        }
    }

    public void register() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinServerListener(this), this);
        pm.registerEvents(new PlayerLeaveServerListener(this), this);
        pm.registerEvents(new PlayerWorldJoinListener(this), this);
        pm.registerEvents(new PlayerWorldLeaveListener(this), this);
    }

    //Saves the command name and arguments when a command is executed.
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.cmd = cmd;
        this.args = args;
        return true;
    }

    //Adds tab suggestions for the new objectives.
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase("scoreboard") && args[0].equalsIgnoreCase("objectives") && (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove")) && !args[2].isEmpty() && args[3].isEmpty()) {
            return objectives;
        }
        return null;
    }

    public HashMap<String, Objectives> getObjectiveMap() {
        return objectiveMap;
    }

    public HashMap<String, World> getObjectiveWorldMap() {
        return objectiveWorldMap;
    }

    public ArrayList<String> getObjectivesList() {
        return objectives;
    }
}