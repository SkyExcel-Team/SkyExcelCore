package skyexcel.data.file.util;


import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public abstract class Flowable {

    private OfflinePlayer player;

    private Config config;

    private JavaPlugin plugin;


    public Flowable(OfflinePlayer player, String path, JavaPlugin plugin) {
        this.player = player;
        this.config = new Config(path + "/" + player.getUniqueId());
        this.config.setPlugin(plugin);
    }

    public boolean deposit(long amount) {

        if (getAmount() != -1) {
            long result = getAmount() + amount;
            setAmount(result);
        } else {
            setAmount(amount);
        }

        return false;
    }

    public void withdraw(long amount) {
        long result = getAmount() - amount;
        if (result > 0) {
            setAmount(getAmount() - amount);
        }
    }

    public void setAmount(long amount) {

        config.getConfig().set("money", amount);
        config.saveConfig();
    }

    public long getAmount() {
        return (config.getConfig().get("money") != null ? config.getConfig().getLong("money") : -1);
    }
}
