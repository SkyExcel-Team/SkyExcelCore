package skyexcel.data.file.lang;

import org.bukkit.plugin.Plugin;
import skyexcel.SkyExcel;
import skyexcel.data.file.Config;

public abstract class Lang extends Config {
    private String name;
    private Config config;

    public Lang(String name) {
        super("lang/" + name);
        this.name = name;
        config = new Config("lang/" + name);
    }



    public static Config getLang(Config config, Plugin plugin) {
        if (config.getString("lang").equalsIgnoreCase("English")) {
            English english = new English();
            english.setPlugin(plugin);
            return english;
        } else if (config.getString("lang").equalsIgnoreCase("Korean")) {
            Korean korean = new Korean();
            korean.setPlugin(plugin);
            return korean;
        }
        return null;
    }
}
