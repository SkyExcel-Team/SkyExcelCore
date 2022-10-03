package skyexcel.data.file;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Section implements AConfig {
    private ConfigurationSection section;

    private Config config;

    public Section(Config config) {
        this.config = config;
    }

    public ConfigurationSection addArray(String path, String newpath) {

        this.section = config.getConfig().getConfigurationSection(path + "." + config.getConfig().getConfigurationSection(path).getKeys(false).size());

        if (config.getConfig().getConfigurationSection(path) != null) {
            if (section != null) {
                return section;
            }
        } else {
            return config.getConfig().createSection(section.getCurrentPath() + "." + newpath);
        }
        return null;
    }

    @Override
    public void setInteger(String path, int value) {
        section.set(path, value);
    }

    @Override
    public void setBoolean(String path, boolean value) {
        section.set(path, value);
    }

    @Override
    public void setString(String path, String value) {
        section.set(path, value);
    }

    @Override
    public void setDouble(String path, double value) {
        section.set(path, value);
    }

    @Override
    public void setLocation(String path, Location value) {
        section.set(path + ".world", value.getWorld().getName());
        section.set(path + ".x", value.getX());
        section.set(path + ".y", value.getY());
        section.set(path + ".z", value.getZ());
        section.set(path + ".pitch", value.getPitch());
        section.set(path + ".yaw", value.getYaw());

    }

    @Override
    public void setItemStack(String path, ItemStack value) {
        if (value.hasItemMeta()) {
            section.set(path + ".meta.name", value.getItemMeta().getDisplayName());
            section.set(path + ".meta.lore", value.getItemMeta().getLore());
            if (value.getItemMeta().hasCustomModelData()) {
                section.set(path + ".meta.CustomModelData", value.getItemMeta().getCustomModelData());
            }
        }

        section.set(path + ".type", value.getType().name());
        section.set(path + ".amount", value.getAmount());
    }

    @Override
    public void setSound(String path, Sound value) {
        section.set(path, value.name());
    }

    @Override
    public void setLong(String path, long value) {
        section.set(path, value);
    }

    @Override
    public void setList(String path, List<Object> value) {
        section.set(path, value);
    }

    @Override
    public void setObject(String path, Object value) {
        section.set(path, value);
    }

    @Override
    public int getInteger(String path) {
        return section.getInt(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return section.getBoolean(path);
    }

    @Override
    public String getString(String path) {
        return section.getString(path);
    }

    @Override
    public double getDouble(String path) {
        return section.getDouble(path);
    }

    @Override
    public long getLong(String path) {
        return section.getLong(path);
    }

    @Override
    public Object getObject(String path) {
        return section.get(path);
    }
}
