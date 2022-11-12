package skyexcel.data.file.util;


import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;

public abstract class Flowable {

    private OfflinePlayer player;

    private Config config;

    private JavaPlugin plugin;

    private String valuePath;


    public Flowable(OfflinePlayer player, String path, JavaPlugin plugin) {
        this.player = player;
        this.config = new Config(path + "/" + player.getUniqueId());
        this.config.setPlugin(plugin);
    }

    public void setValuePath(String valuePath) {
        this.valuePath = valuePath;
    }

    public boolean add(double amount) {
        if (getDouble() != -1) {
            double result = getDouble() + amount;
            setAmount(result);
            return true;
        } else {
            setAmount(amount);
            return true;
        }
    }

    public boolean sub(double amount) {
        if (getLong() != -1) {
            double result = getDouble() + amount;
            setAmount(result);
            return true;
        } else {
            setAmount(amount);
            return true;
        }
    }

    public boolean add(long amount) {
        if (getLong() != -1) {
            long result = getLong() + amount;
            setAmount(result);
            return true;
        } else {
            setAmount(amount);
            return true;
        }
    }

    public boolean sub(long amount) {
        if (getLong() != -1) {
            long result = getLong() + amount;
            setAmount(result);
            return true;
        } else {
            setAmount(amount);
            return true;
        }
    }


    public boolean deposit(long amount) {
        if (getLong() != -1) {
            long result = getLong() + amount;
            setAmount(result);
            return true;
        } else {
            setAmount(amount);
            return true;
        }
    }

    public boolean withdraw(long amount) {
        long result = getLong() - amount;
        if (result > 0) {
            setAmount(getLong() - amount);
            return true;
        }
        return false;
    }


    public void setAmount(double amount) {
        config.getConfig().set(valuePath, amount);
        config.saveConfig();
    }


    public void setAmount(long amount) {

        config.getConfig().set(valuePath, amount);
        config.saveConfig();
    }

    public double getDouble() {
        return (config.getConfig().get(valuePath) != null ? config.getConfig().getLong(valuePath) : -1);
    }

    public long getLong() {
        return (config.getConfig().get(valuePath) != null ? config.getConfig().getLong(valuePath) : -1);
    }
}
