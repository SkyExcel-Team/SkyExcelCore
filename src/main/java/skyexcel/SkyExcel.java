package skyexcel;

import skyexcel.bstat.Metrics;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;
import skyexcel.data.file.lang.Japanese;
import skyexcel.data.file.lang.Lang;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    public static Plugin newPlugin;

    public static Config config;

    public static Config english;

    public static Config korean;
    public static Config japanese;
    public static Config chinese;
    int pluginId = 16492;

    @Override
    public void onEnable() {
        super.onEnable();

        config = new Config("config");
        config.setPlugin(this);
        config.loadDefaultPluginConfig();

        System.out.println(Lang.getLang(config, this).getString("test"));

        japanese = new Japanese(this);
        japanese.loadDefaultPluginConfig();
        plugin = this;

        Lang.getLang(config, this);
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
