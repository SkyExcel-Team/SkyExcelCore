
import annotation.Registerclass;
import command.abcd;
import command.test;
import customer.Address;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    public static Plugin newPlugin;


    @Override
    public void onEnable() {

        super.onEnable();
        plugin = this;
        new Registerclass(new test(), this);
        new Registerclass(new abcd(), this);

        Address address = new Address();
        if (!address.Equal(0, "survivalgame.n-e.kr")) {
            plugin.getServer().getPluginManager().disablePlugin(this);
        }
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
