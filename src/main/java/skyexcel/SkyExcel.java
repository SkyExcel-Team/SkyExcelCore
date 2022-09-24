package skyexcel;

import skyexcel.bstat.Metrics;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;
import skyexcel.data.file.lang.Lang;
import skyexcel.log.BukkitLog;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    public static Plugin newPlugin;

    public static Config config;


    @Override
    public void onEnable() {

        super.onEnable();
        config = new Config("config");
        config.setPlugin(this);
        config.loadDefaultPluginConfig();

        Lang english = new Lang("English");
        english.setPlugin(this);
        english.loadDefaultPluginConfig();

        Lang Korean = new Lang("Korean");
        Korean.setPlugin(this);
        Korean.loadDefaultPluginConfig();


        if (config.getString("lang").equalsIgnoreCase("English")) {
            System.out.println(english.getString("test"));
        } else if (config.getString("lang").equalsIgnoreCase("Korean")) {
            System.out.println(Korean.getString("test"));
        }

        plugin = this;
        int pluginId = 16492;
        new Metrics(this, pluginId);
    }

    public static void setNewPlugin(Plugin newPlugin) {
        SkyExcel.newPlugin = newPlugin;
    }

    public static Plugin getNewPlugin() {
        return newPlugin;
    }

    public static SkyExcel getPlugin() {
        return plugin;
    }


    public static void RegisterEvents(Plugin plugin, Listener listener) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(listener, plugin);
    }
}
