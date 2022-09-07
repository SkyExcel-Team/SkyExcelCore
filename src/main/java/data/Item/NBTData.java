package data.Item;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("all")
public class NBTData {
    private Plugin plugin;

    public NBTData(Plugin plugin) {
        Objects.requireNonNull(plugin, "JeffLib hasn't been initialized.");
        this.plugin = plugin;
    }

    public @Nullable String getNBT(@NotNull ItemStack item, String key) {
        Objects.requireNonNull(item, "item must not be null");
        if (!item.hasItemMeta()) return null;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        if (pdc.has(namespacedKey, PersistentDataType.STRING)) {
            return pdc.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    public @Nullable String getNBT(@NotNull Entity entity, String key) {
        Objects.requireNonNull(entity, "entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        if (pdc.has(namespacedKey, PersistentDataType.STRING)) {
            return pdc.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    public void addNBT(@NotNull ItemStack item, String key, String value) {
        Objects.requireNonNull(item, "item must not be null");
        ItemMeta meta = item.hasItemMeta() ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
    }

    public void addNBT(@NotNull Entity entity, String key, String value) {
        Objects.requireNonNull(entity, "entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
    }

    public boolean hasNBT(@NotNull ItemStack item, String key) {
        Objects.requireNonNull(item, "item must not be null");
        if (!item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public boolean hasNBT(@NotNull Entity entity, String key) {
        Objects.requireNonNull(entity, "entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public void removeNBT(@NotNull ItemStack item, String key) {
        Objects.requireNonNull(item, "item must not be null");
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(new NamespacedKey(plugin, key));
        item.setItemMeta(meta);
    }

    public void removeNBT(@NotNull Entity entity, String key) {
        Objects.requireNonNull(entity, "entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        pdc.remove(new NamespacedKey(plugin, key));
    }

    public HashMap<String, String> getAllValues(@NotNull ItemStack item) {
        Objects.requireNonNull(item, "item must not be null");
        HashMap<String, String> map = new HashMap<>();
        if (!item.hasItemMeta()) return map;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        for (NamespacedKey key : pdc.getKeys()) {
            map.put(key.toString(), pdc.get(key, PersistentDataType.STRING));
        }
        return map;
    }

    public List<String> getAllKeys(@NotNull ItemStack item) {
        Objects.requireNonNull(item, "item must not be null");
        List<String> keys = new ArrayList<>();
        if (!item.hasItemMeta()) return null;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        for (NamespacedKey key : pdc.getKeys()) {
            keys.add(key.getKey());
        }
        return keys;
    }
}
