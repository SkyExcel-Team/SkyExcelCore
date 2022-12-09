package skyexcel.data.file;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

@SuppressWarnings("all")
public class GUI implements DefaultConfig {

    private ConfigurationSection section;


    /**
     * 다른 데이터를 저장할 공간.
     */
    private ConfigurationSection data;

    private ItemStack item;
    private final Config yaml;

    public GUI(Config yaml) {
        this.yaml = yaml;
        Objects.requireNonNull(yaml, "YAML 클래스를 찾을 수 없습니다! #GUI 구조체 ");
    }

    public GUI path(String path) {
        if (yaml.getConfig().get(path) != null) {
            section = yaml.getConfig().getConfigurationSection(path);
        } else {
            section = yaml.getConfig().createSection(path);
        }

        return this;
    }

    public void saveInventory(String path, Inventory inv) {
        for (HumanEntity viewers : inv.getViewers()) {
            InventoryView OpenInv = viewers.getOpenInventory();
            if (OpenInv.getTopInventory().equals(inv) && !inv.getType().equals(InventoryType.CRAFTING)) {

                yaml.setString(path + ".inv.title", OpenInv.getTitle());
                yaml.setInteger(path + ".inv.size", inv.getSize());

                List<ItemStack> itemStacks = new ArrayList<>();

                for (int i = 0; i < inv.getSize(); i++) {
                    ItemStack item = inv.getItem(i);
                    if (item != null) {
                        if (yaml.getConfig().get(path + ".inv.items." + i + ".data") != null) {
                            ConfigurationSection data = yaml.getConfig().getConfigurationSection(path + ".inv.items." + i + ".data");
                            if (data != null) {
                                for (String key : data.getKeys(false)) {
                                    ItemMeta meta = item.getItemMeta();
                                    PersistentDataContainer pdc = meta.getPersistentDataContainer();
                                    NamespacedKey namespacedKey = new NamespacedKey(yaml.getPlugin(), key);

                                    if (pdc.has(namespacedKey, PersistentDataType.LONG)) {
                                        pdc.set(namespacedKey, PersistentDataType.LONG, data.getLong(key));
                                        item.setItemMeta(meta);
                                        itemStacks.add(item);
                                    }
                                }
                            }
                        }
                    }
                }

                ConfigurationSection newSection = yaml.getConfig().createSection(path + ".inv.items");

                List<ItemStack> items = new ArrayList<>();

                for (int i = 0; i < inv.getSize(); i++) {
                    ItemStack item = inv.getItem(i);

                    if (item != null) {
                        items.add(item);
                        setItemStack(path, item, i, i);
                    }
                }
            }
        }
        yaml.saveConfig();
    }

    public void setItemStack(String path, ItemStack item, int i, int index) {
        section = yaml.getConfig().createSection(path + ".inv.items." + index);
        section.set("slot", i);
        setItemStack(path, item);
    }


    public void setItemStack(String path, ItemStack value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setItemStack, section=?");

        section.set("Material", value.getType().name());
        section.set("Amount", value.getAmount());
        section.set("Durability", value.getDurability());


        if (value != null) {
            ItemMeta meta = value.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            if (data != null) {
                for (NamespacedKey key : data.getKeys()) {
                    long test = data.get(key, PersistentDataType.LONG);
                    section.set("data." + key.getKey(), test);
                }
            }
        }


        if (!value.getType().equals(Material.ENCHANTED_BOOK)) {
            if (value.hasItemMeta()) {
                ItemMeta meta = value.getItemMeta();
                assert meta != null;
                if (meta.hasDisplayName())
                    section.set("Meta.Display", meta.getDisplayName());

                if (meta.hasCustomModelData())
                    section.set("Meta.CustomModelData", meta.getCustomModelData());

                if (meta.hasLore()) {
                    List<String> lore = meta.getLore();
                    section.set("Meta.Lore", lore);

                }
                Map<Enchantment, Integer> enchants = meta.getEnchants();

                for (Enchantment enchantment : value.getEnchantments().keySet()) {
                    int level = enchants.get(enchantment);
                    section.set("Enchant." + enchantment.getName(), level);
                }
            }
        } else {


            EnchantmentStorageMeta enchantMeta = (EnchantmentStorageMeta) value.getItemMeta();

            Map<Enchantment, Integer> enchants = enchantMeta.getStoredEnchants();

            if (enchants != null) {
                for (Enchantment enchantment : enchants.keySet()) {
                    int level = enchants.get(enchantment);
                    section.set("Enchant." + enchantment.getName(), level);
                }
            }
        }
        yaml.saveConfig();
    }

    /**
     * 데이터를 저장할 아이템을 설정 해 주는 메소드 입니다.
     *
     * @param item
     */
    public void setItem(ItemStack item) {
        this.item = item;
    }

    public List<ItemStack> addItemStack(String path, ItemStack stack) {
        List<ItemStack> items;
        if (yaml.getConfig().get(path) == null) {
            yaml.newArrayList(path);
            items = (List<ItemStack>) yaml.getConfig().getList(path);
            items.add(stack);

        } else {
            items = (List<ItemStack>) yaml.getConfig().getList(path);
            items.add(stack);
        }
        yaml.saveConfig();
        return items;
    }


    @Override
    public void setInteger(String path, int value) {
//        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setInteger, section=?");
//        section.set("data." + path, value);
//
//        yaml.saveConfig();

        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            NamespacedKey namespacedKey = new NamespacedKey(yaml.getPlugin(), path);
            pdc.set(namespacedKey, PersistentDataType.INTEGER, value);
            item.setItemMeta(meta);
        }

    }

    @Override
    public void setBoolean(String path, boolean value) {
//        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setBoolean, section=?");
//        section.set("data." + path, value);
//
//        yaml.saveConfig();
        persistentdataSet(path, PersistentDataType.STRING, value ? "true" : "false");
    }

    @Override
    public void setString(String path, String value) {
//        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setString, section=?");
//        section.set("data." + path, value);
//        yaml.saveConfig();
        persistentdataSet(path, PersistentDataType.STRING, value);
    }

    @Override
    public void setDouble(String path, double value) {
//        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setDouble, section=?");
//        section.set("data." + path, value);
//        yaml.saveConfig();
        persistentdataSet(path, PersistentDataType.DOUBLE, value);
    }


    @Override
    public void setLong(String path, long value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setLong, section=?");
        section.set(path, value);

        yaml.saveConfig();
        persistentdataSet(path, PersistentDataType.LONG, value);
    }

    @Override
    public void setList(String path, List<Object> value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setList, section=?");
        section.set("data." + path, value);
        yaml.saveConfig();

    }

    @Override
    public void setObject(String path, Object value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setObject, section=?");
        section.set("data." + path, value);
        yaml.saveConfig();
    }

    @Override
    public int getInteger(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getInteger, section=?");

        return section.getInt("data." + path);
    }

    @Override
    public boolean getBoolean(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getBoolean, section=?");
        return section.getBoolean("data." + path);
    }

    @Override
    public String getString(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getString, section=?");
        return section.getString("data." + path);
    }

    @Override
    public double getDouble(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getDouble, section=?");
        return section.getDouble("data." + path);
    }

    @Override
    public long getLong(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getLong, section=?");
        return section.getLong("data." + path);
    }

    @Override
    public Object getObject(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getObject, section=?");
        return section.get("data." + path);
    }


    public short getShort(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getObject, section=?");
        return (short) section.get("data." + path);
    }

    // TODO 인첸트 아이템이 데이터 저장이 안됨. ps. 되긴 함. 불러오질 못함
    public Inventory getInventory(String path) {

        if (yaml.getConfig().get(path + ".inv.size") != null && yaml.getConfig().get(path + ".inv.title") != null) {

            Inventory inv = Bukkit.createInventory(null, yaml.getInteger(path + ".inv.size"), yaml.getString(path + ".inv.title"));

            section = yaml.getConfig().getConfigurationSection(path + ".inv.items");
            //TODO MetaData 를 저장해야함.
            ConfigurationSection metaData = section.getConfigurationSection("Meta");


            for (int i = 0; i < inv.getSize(); i++) {
                String materialName = section.getString(i + ".Material");

                String displayName = section.getString(i + ".Meta.Display");

                List<String> lore = section.getStringList(i + ".Meta.Lore");

                if (materialName != null) {

                    Material material = Material.valueOf(materialName);
                    if (material.equals(Material.ENCHANTED_BOOK)) {
                        int slot = section.getInt(i + ".slot");
                        int amount = section.getInt(i + ".Amount");

                        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, amount);
                        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();

                        if (displayName != null) {
                            meta.setDisplayName(displayName);
                        } else if (lore != null) {
                            meta.setLore(lore);
                        }

                        int durability = section.getInt(i + ".Durability");

                        section = section.getConfigurationSection(i + ".Enchant");

                        if (section != null) {
                            for (String key : section.getKeys(false)) {
                                Enchantment enchantment = Enchantment.getByName(key);
                                meta.addStoredEnchant(enchantment, section.getInt(key), false);
                            }
                        }

                        ConfigurationSection data = section.getConfigurationSection(i + ".data");
                        if (data != null) {
                            PersistentDataContainer pdc = meta.getPersistentDataContainer();
                            for (String key : data.getKeys(false)) {
                                long value = data.getLong(key);
                                NamespacedKey name = new NamespacedKey(yaml.getPlugin(), key);
                                pdc.set(name, PersistentDataType.LONG, value);
                            }
                        }

                        item.setItemMeta(meta);
                        inv.setItem(slot, item);
                    } else {
                        int slot = section.getInt(i + ".slot");
                        int amount = section.getInt(i + ".Amount");

                        ItemStack item = new ItemStack(material, amount);
                        ItemMeta meta = item.getItemMeta();

                        if (displayName != null) {
                            meta.setDisplayName(displayName);
                        }
                        meta.setLore(lore);
                        int durability = section.getInt(i + ".Durability");

                        ConfigurationSection data = section.getConfigurationSection(i + ".data");
                        if (data != null) {
                            PersistentDataContainer pdc = meta.getPersistentDataContainer();
                            for (String key : data.getKeys(false)) {
                                long value = data.getLong(key);
                                NamespacedKey name = new NamespacedKey(yaml.getPlugin(), key);
                                pdc.set(name, PersistentDataType.LONG, value);
                            }
                        }
                        item.setItemMeta(meta);

                        section = section.getConfigurationSection(i + ".Enchant");

                        if (section != null) {
                            for (String key : section.getKeys(false)) {
                                Enchantment enchantment = Enchantment.getByName(key);
                                item.addUnsafeEnchantment(enchantment, section.getInt(key));
                            }
                        }


                        inv.setItem(slot, item);
                    }
                    section = yaml.getConfig().getConfigurationSection(path + ".inv.items");
                }
            }
            return inv;
        }
        return null;
    }

//    public ItemStack getItemStack(String path) {
//
//    }


    private void persistentdataSet(String path, PersistentDataType type, Object obj) {

        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            NamespacedKey namespacedKey = new NamespacedKey(yaml.getPlugin(), path);
            pdc.set(namespacedKey, type, obj);
            item.setItemMeta(meta);
        }
    }


    public ConfigurationSection getSection() {
        return section;
    }
}
