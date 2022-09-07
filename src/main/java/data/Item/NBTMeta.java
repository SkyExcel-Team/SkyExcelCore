package data.Item;

import me.github.skyexcelcore.SkyExcel;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class NBTMeta{
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private PersistentDataContainer data;

    private Plugin plugin;

    public NBTMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        this.data = getItemData();
        this.plugin = SkyExcel.getNewPlugin();

    }


    public String setString(String key, String value) {
        NamespacedKey newkey = new NamespacedKey(plugin, key);

        itemMeta.getPersistentDataContainer().set(newkey, PersistentDataType.STRING, value);


        return value;
    }

    public int setInt(String key, int value) {
        if (!hasDataKey()) {
            data.set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value);
        }

        return value;
    }

    public long setLong(String key, long value) {
        if (!hasDataKey()) {
            data.set(new NamespacedKey(plugin, key), PersistentDataType.LONG, value);
        } else {
            data.set(new NamespacedKey(plugin, key), PersistentDataType.LONG, value);
            Merge(key);
        }

        return value;
    }

    public float setFloat(String key, float value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.FLOAT, value);
        return value;
    }

    public byte setByte(String key, byte value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.BYTE, value);
        return value;
    }

    public byte[] setByteArray(String key, byte[] value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.BYTE_ARRAY, value);
        return value;
    }

    public int[] setIntArray(String key, int[] value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY, value);
        return value;
    }

    public long[] setLongArray(String key, long[] value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY, value);
        return value;
    }

    public int getInt(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.INTEGER)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
        } else {

            throw new NullPointerException("§cNBTMeta 오류! Int 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다. ");

        }
    }

    public long getLong(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.LONG)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.LONG);
        } else {
            throw new NullPointerException("§cNBTMeta 오류! Int 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다. ");
        }
    }

    public long getByte(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.BYTE)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.BYTE);
        } else {
            throw new NullPointerException("§cNBTMeta 오류! Int 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다. ");
        }
    }

    public float getFloat(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.FLOAT)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.FLOAT);
        } else {
            throw new NullPointerException("§cNBTMeta 오류! Int 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다. ");
        }
    }

    public String getString(String key) {

        try {
            if (data.has(new NamespacedKey(plugin, key), PersistentDataType.STRING)) {
                return data.get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
            } else {

            }
        } catch (NullPointerException e) {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! String 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            return "";
        }
        return "";
    }

    public int[] getIntArray(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! IntArray 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§cNull을 출력합니다...");
            return null;
        }
    }

    public long[] getLongList(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Long 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§cNull을 출력합니다...");
            return null;
        }
    }

    public byte[] getByteList(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.BYTE)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.BYTE_ARRAY);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Byte 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§cNull을 출력합니다...");
            return null;
        }
    }

    public void apply() {
        itemStack.setItemMeta(itemMeta);
    }

    public ItemStack MergeData(ItemStack item) {

        return item;
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        for(NamespacedKey key : data.getKeys()){
            keys.add(key.getKey());
        }
        return keys;
    }

    public boolean hasDataKey() {
        if (data.getKeys() != null) {
            for (NamespacedKey keys : data.getKeys()) {
                return data.has(keys, PersistentDataType.STRING)
                        || data.has(keys, PersistentDataType.FLOAT)
                        || data.has(keys, PersistentDataType.BYTE)
                        || data.has(keys, PersistentDataType.LONG)
                        || data.has(keys, PersistentDataType.DOUBLE)
                        || data.has(keys, PersistentDataType.INTEGER)
                        || data.has(keys, PersistentDataType.INTEGER_ARRAY)
                        || data.has(keys, PersistentDataType.LONG_ARRAY)
                        || data.has(keys, PersistentDataType.BYTE_ARRAY)
                        || data.has(keys, PersistentDataType.TAG_CONTAINER_ARRAY);
            }

        }
        return false;
    }

    public void Merge(String originalkey) {

        if (getItemData() != null) {
            if (hasDataKey()) {
                for (NamespacedKey keys : data.getKeys()) {
                    String key = keys.getKey();
                    Bukkit.getConsoleSender().sendMessage("null 아님" + key);
                }
            }
        }
    }

    public Object getObject() {
        if (data.getKeys() != null) {
            for (NamespacedKey keys : data.getKeys()) {
                String key = keys.getKey();

                if (data.get(new NamespacedKey(plugin, key), PersistentDataType.STRING) != null) {
                    return getString(key);
                }

            }
        }
        return null;
    }


    public PersistentDataContainer getItemData() {
        if (itemMeta != null) {

            return itemMeta.getPersistentDataContainer();
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! ItemMeta 값을 설정해 주세요.");
        }
        Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! ItemMeta 값을 설정해 주세요.");
        return null;
    }
}
