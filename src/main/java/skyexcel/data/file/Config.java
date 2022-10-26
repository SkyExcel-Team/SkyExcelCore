package skyexcel.data.file;

import com.google.common.io.Files;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import skyexcel.data.Item.PDCData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public class Config implements AConfig {
    public FileConfiguration config;
    public File file;
    private String name;
    private ConfigurationSection section;

    private Plugin plugin;

    public Config(String name) {
        this.name = name;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setSection(ConfigurationSection section) {
        this.section = section;
    }

    public void reloadConfig() {
        if (this.config == null)
            this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);

        InputStream inputStream = plugin.getResource(name + ".yml");
        if (inputStream != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
            this.config.setDefaults(config);
        }
    }


    public FileConfiguration loadDefualtConfig() {
        config = new YamlConfiguration();
        File file = new File(plugin.getDataFolder() + File.separator + name + ".yml");
        try {
            config.loadFromString(Files.toString(file, Charset.forName("UTF-8")));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }

    public boolean isFileExist() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        return this.file.exists();
    }

    public void deleteFile() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.file.delete();
    }

    public void renameFile(String name) {
        this.file = new File(plugin.getDataFolder(), this.name + ".yml");
        File file = new File(plugin.getDataFolder(), name + ".yml");

        this.file.renameTo(file);
    }

    /***
     * 파일을 다른 경로로 보내고, 원래 있는 파일은 지운다.
     * @param path 파일이 이동할 경로
     */
    public boolean moveTo(String path) {

        File newFile = new File(plugin.getDataFolder(), name + ".yml");

        try {
            System.out.println(config.getDefaults() + " 테스트");

            getConfig().save(newFile);
            saveConfig();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return config;
    }

    public boolean saveConfig() {
        if (this.config == null || this.file == null) return false;
        try {
            getConfig().save(this.file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadDefaultPluginConfig() {
        File fconfig = new File(plugin.getDataFolder(), this.name + ".yml");
        if (!fconfig.exists()) {
            plugin.saveResource(this.name + ".yml", false);
        }
    }

    public void saveDefualtConfig() {
        if (this.file == null)
            this.file = new File(plugin.getDataFolder(), name + ".yml");
        if (!this.file.exists()) {
            file = new File(plugin.getDataFolder(), name + ".yml");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setString(String path, String msg) {

        getConfig().set(path, msg);
        saveConfig();

    }


    @Override
    public void setInteger(String path, int value) {

    }

    public void setBoolean(String path, boolean Boolean) {

        getConfig().set(path, Boolean);

        this.saveConfig();

    }

    public void setLong(String path, Long value) {

        getConfig().set(path, value);
        saveConfig();

    }

    public void setFloat(String path, float value) {

        getConfig().set(path, value);

        saveConfig();
    }

    public float getFloat(String path) {
        return (float) getConfig().get(path);
    }

    public void setDouble(String path, double value) {

        getConfig().set(path, value);
        this.saveConfig();

    }

    @Override
    public void setLocation(String path, Location value) {
        getConfig().set(path + "." + value.getWorld().getName(), value.getWorld().getName());
        getConfig().set(path + ".x", value.getX());
        getConfig().set(path + ".y", value.getY());
        getConfig().set(path + ".z", value.getZ());
        getConfig().set(path + ".pitch", value.getPitch());
        getConfig().set(path + ".yaw", value.getYaw());
        saveConfig();
    }

    @Override
    public void setSound(String path, Sound value) {

    }

    @Override
    public void setLong(String path, long value) {

    }

    @Override
    public void setList(String path, List<Object> value) {

    }

    public void setObject(String path, Object value) {

        getConfig().set(path, value);
        this.saveConfig();

    }

    @Override
    public int getInteger(String path) {
        return 0;
    }


    @Override
    public boolean getBoolean(String path) {
        return false;
    }


    @Override
    public void setItemStack(String path, ItemStack value) {
        if (value.hasItemMeta()) {
            getConfig().set(path + ".meta.name", value.getItemMeta().getDisplayName());
            getConfig().set(path + ".meta.lore", value.getItemMeta().getLore());
            if (value.getItemMeta().hasCustomModelData()) {
                getConfig().set(path + ".meta.CustomModelData", value.getItemMeta().getCustomModelData());
            }
        }
        getConfig().set(path + ".type", value.getType().name());
        getConfig().set(path + ".amount", value.getAmount());

        saveConfig();
    }

    public List<ItemStack> addItemStack(String path, ItemStack stack) {
        List<ItemStack> items;
        if (getConfig().get(path) == null) {
            newArrayList(path);
            items = (List<ItemStack>) getConfig().getList(path);
            items.add(stack);

        } else {
            items = (List<ItemStack>) getConfig().getList(path);
            items.add(stack);
        }
        saveConfig();
        return items;
    }


    public ItemStack getItemStack(String path, boolean single) {
        if (getConfig().get(path) != null) {
            String name = getString(path + ".meta.name");
            String type = getString(path + ".type");
            ItemStack itemstack = new ItemStack(Material.valueOf(type));
            int amount = getInteger(path + ".amount");
            if (getConfig().get(path + ".meta") != null) {
                ItemMeta meta = itemstack.getItemMeta();
                if (name != "") {
                    meta.setDisplayName(name);
                }
                if (getConfig().get(path + ".meta.lore") != null) {
                    List<String> lore = (List<String>) getConfig().getList(path + ".meta.lore");

                    if (lore != null) {
                        if (name != "") {
                            meta.setLore(lore);
                        } else {
                            meta.setDisplayName(name);

                            meta.setLore(lore);
                        }
                    } else {
                        meta.setDisplayName(name);
                    }
                }
                if (getConfig().get(path + ".meta.CustomModelData") != null) {
                    int CustomModel = getInteger(path + ".meta.CustomModelData");
                    meta.setCustomModelData(CustomModel);
                }
                itemstack.setItemMeta(meta);
            }
            if (single) {
                itemstack.setAmount(1);
            } else {
                itemstack.setAmount(amount);
            }
            return itemstack;
        }
        return null;
    }

    public void saveInventory(String path, Inventory inv) {
        for (HumanEntity viewers : inv.getViewers()) {
            InventoryView OpenInv = viewers.getOpenInventory();
            if (OpenInv.getTopInventory().equals(inv) && !inv.getType().equals(InventoryType.CRAFTING)) {
                for (int i = 0; i < inv.getSize(); i++) {
                    ItemStack item = inv.getItem(i);

                    setString(path + ".inv.title", OpenInv.getTitle());
                    setInteger(path + ".inv.size", inv.getSize());
                    if (item != null) {
                        if (getConfig().get(path + ".inv.items") == null) {
                            setItem(path, item, i, 0);
                        } else {

                            setItem(path, item, i, i);
                            getConfig().set(getConfig().get(path + ".inv.items") + ".test", "asdf");

                        }
                    } else {
                        getConfig().set(path + ".inv.items." + i, null);
                    }
                }
            }
        }
    }


    private void setItem(String path, ItemStack item, int i, int index) {
        ConfigurationSection slot = getConfig().createSection(path + ".inv.items." + index);

        slot.set("slot", i);
        slot.set("Item.Material", item.getType().name());
        slot.set("Item.Amount", item.getAmount());
        slot.set("Item.Durability", item.getDurability());


        ItemMeta meta = item.getItemMeta();
        if (item.hasItemMeta()) {
            slot.set("Item.Meta.display-name", meta.getDisplayName());
            slot.set("Item.Meta.lore", meta.getLore());
            PDCData nbtData = new PDCData(plugin);
            for (String key : nbtData.getAllKeys(item)) {
                slot.set("Item.Meta.Data." + key, nbtData.getNBT(item, key));
            }
            if (item.getItemMeta().hasCustomModelData()) {
                slot.set("CustomModelData", meta.getCustomModelData());
            }

        }
        slot.set("Item.data", item.getDurability());

    }

    public Inventory getInventory(String path) {
        if (getConfig().get(path + ".inv.size") != null && getConfig().get(path + ".inv.title") != null) {
            Inventory inv = Bukkit.createInventory(null, getInteger(path + ".inv.size"), getString(path + ".inv.title"));
            for (int i = 0; i < inv.getSize(); i++) {

                String name = path + ".inv.items." + i;

                if (getConfig().get(name) != null) {
                    int slot = getInteger(name + ".slot");
                    int amount = getInteger(name + ".Item.Amount");
                    String type = getString(name + ".Item.Material");
                    ItemStack item = new ItemStack(Material.valueOf(type));
                    getItemStack("", false);
                    if (getConfig().get(name + ".Item.Meta") != null) {
                        String display = getString(name + ".Item.Meta.display-name");
                        int CustomModel = getInteger(name + ".CustomModelData");
                        ItemMeta meta = item.getItemMeta();
                        meta.setCustomModelData(CustomModel);
                        meta.setDisplayName(display);

                        item.setItemMeta(meta);
                    }

                    item.setAmount(amount);
                    inv.setItem(slot, item);
                }
            }
            return inv;
        }
        return null;
    }


    public Location getLocation(String path) {
        if (getConfig().get(path) != null) {
            return new Location(Bukkit.getWorld(getString(path + ".world")),
                    getDouble(path + ".x"), getDouble(path + ".y"), getDouble(path + ".z"), (float) getDouble(path + ".yaw"), (float) getDouble(path + ".pitch"));

        }
        return null;
    }

    public String getString(String path) {
        return getConfig().getString(path);
    }


    public double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    @Override
    public long getLong(String path) {
        return 0;
    }

    @Override
    public Object getObject(String path) {
        return null;
    }


    public boolean delete() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        return this.file.delete();
    }


    public void removeKey(String key) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        Set<String> toRemove = new HashSet<>(); // could also be TreeSet, or LinkedHashSet, or anything else, but of those HashSet performs the best
        toRemove.add(key);

        for (String remove : toRemove) {
            getConfig().set(remove, null);
        }
        saveConfig();
    }


    public void newArrayList(String path) {
        if (getConfig().getList(path) == null) {
            this.getConfig().set(path, new ArrayList<>());
            this.saveConfig();
        } else {
            System.out.print("해당 리스트는 이미 생성 되었습니다.");
        }
    }

    /***
     *@implSpec {@code This method Get A}
     * <pre>{@code
     *
     *   ConfigManager config;
     *   public void CreatePlayer(Player player){
     *   config = new ConfigManager(player.getUniqueId().toString());
     *   config.newArrayList("player", player);
     *   }
     *   public void addPlayer(Player player){
     *   config.addObject("player", player);
     *   }
     *
     * }</pre>
     * @return If the object is contained in List from path
     */
    public boolean AddArrayObject(String path, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);

        if (!list.contains(obj) && list != null) {
            list.add(obj);

            this.saveConfig();
            return true;
        }

        return false;
    }

    public Object getArrayObject(String path, int index) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);

        if (list != null) {
            return list.get(index);
        }
        return null;
    }

    public boolean removeArrayObject(String path, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);
        if (list.contains(obj)) {
            list.remove(obj);
            this.saveConfig();
            return true;
        }
        return false;
    }


    public boolean setArrayObject(String path, int index, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);
        if (!list.contains(obj)) {
            list.set(index, obj);
            return true;
        }

        return false;
    }

    /**
     * The name value of this class to be directory of file. so The name should be like "test/" this.
     *
     * @param results
     */
    public void setFileListTabComplete(ArrayList<String> results) {
        this.file = new File(plugin.getDataFolder(), name);
        File[] test = this.file.listFiles();
        for (File file : test) {
            if (file != null) {

                String name = file.getName();
                name = name.replaceAll(".yml", "");
                results.add(name);
            }
        }
    }

    public List<String> fileListName() {
        this.file = new File(plugin.getDataFolder(), name);
        ArrayList<String> newArray = new ArrayList<>();
        File[] test = this.file.listFiles();
        if (test != null) {
            for (File file : test) {
                if (file != null) {
                    String name = file.getName();
                    name = name.replaceAll(".yml", "");
                    newArray.add(name);
                }
            }
        }
        return newArray;
    }

    public File[] getFileList() {
        this.file = new File(plugin.getDataFolder(), name);
        return this.file.listFiles();
    }

    public ConfigurationSection newSection(String path) {
        return getConfig().createSection(path);
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
