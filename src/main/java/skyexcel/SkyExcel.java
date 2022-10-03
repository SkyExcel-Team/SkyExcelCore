package skyexcel;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import skyexcel.bstat.Metrics;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.command.tab.Commands;
import skyexcel.command.tab.test;
import skyexcel.scoreboard.ScoreBoardAPI;


public class SkyExcel extends JavaPlugin implements Listener, CommandExecutor {

    public static SkyExcel plugin;

    int pluginId = 16492;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;

        new Metrics(this, pluginId);
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ScoreBoardAPI scoreboard = new ScoreBoardAPI("test", "dummy");
        scoreboard.newScoreBoard(DisplaySlot.SIDEBAR);
        scoreboard.newLine(player.getDisplayName(), 1);
        player.setScoreboard(scoreboard.getBoard());
    }

    public static SkyExcel getPlugin() {
        return plugin;
    }

    public static void RegisterEvents(Plugin plugin, Listener listener) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(listener, plugin);
    }
}
