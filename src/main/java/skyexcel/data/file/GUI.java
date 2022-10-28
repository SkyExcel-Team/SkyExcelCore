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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("all")
public class GUI implements DefaultConfig {

    private ConfigurationSection section;
    private final Config yaml;

    public GUI(Config yaml) {
        this.yaml = yaml;
        Objects.requireNonNull(yaml, "YAML 클래스를 찾을 수 없습니다! #GUI 구조체 ");
    }

    public GUI path(String path) {
        section = yaml.getConfig().getConfigurationSection(path);
        return this;
    }

    public void saveInventory(String path, Inventory inv) {
        for (HumanEntity viewers : inv.getViewers()) {
            InventoryView OpenInv = viewers.getOpenInventory();
            if (OpenInv.getTopInventory().equals(inv) && !inv.getType().equals(InventoryType.CRAFTING)) {
                yaml.setString(path + ".inv.title", OpenInv.getTitle());
                yaml.setInteger(path + ".inv.size", inv.getSize());
                yaml.getConfig().createSection(path + ".inv.items");
                for (int i = 0; i < inv.getSize(); i++) {
                    ItemStack item = inv.getItem(i);
                    if (item != null)
                        System.out.println(item.getType().name());
                    if (item != null) {
                        setItemStack(path, item, i, i);
                    }
                }
            }
        }
        yaml.saveConfig();
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

    public void setItemStack(String path, ItemStack item, int i, int index) {
        section = yaml.getConfig().createSection(path + ".inv.items." + index);
        section.set("slot", i);
        setItemStack(path, item);
    }


    @Override
    public void setInteger(String path, int value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setInteger, section=?");
        section.set(path, value);
        yaml.saveConfig();
    }

    @Override
    public void setBoolean(String path, boolean value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setBoolean, section=?");
        section.set(path + ".data", value);
        yaml.saveConfig();
    }

    @Override
    public void setString(String path, String value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setString, section=?");
        section.set(path + ".data", value);
        yaml.saveConfig();
    }

    @Override
    public void setDouble(String path, double value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setDouble, section=?");
        section.set(path + ".data", value);
        yaml.saveConfig();
    }

    public void setItemStack(String path, ItemStack value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setItemStack, section=?");

        section.set("Material", value.getType().name());
        section.set("Amount", value.getAmount());
        section.set("Durability", value.getDurability());

        if (value.hasItemMeta()) {

            ItemMeta meta = value.getItemMeta();
            assert meta != null;
            if (meta.hasDisplayName())
                section.set("Meta.Display", meta.getDisplayName());

            if (meta.hasCustomModelData())
                section.set("Meta.CustomModelData", meta.getCustomModelData());

            Map<Enchantment, Integer> enchants = meta.getEnchants();

            for (Enchantment enchantment : value.getEnchantments().keySet()) {
                int level = enchants.get(enchantment);
                section.set("Enchant." + enchantment.getName(), level);
            }
        }
        yaml.saveConfig();

        section = section.getConfigurationSection("Enchant");

        if (section != null) {
            for (String key : section.getKeys(false)) {
                Enchantment enchantment = Enchantment.getByName("");


                System.out.println(key + " : " + section.getInt(key));
            }
        }
    }

    @Override
    public void setLong(String path, long value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setLong, section=?");
        section.set(path + ".data", value);
        yaml.saveConfig();
    }

    @Override
    public void setList(String path, List<Object> value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setList, section=?");
        section.set(path + ".data", value);
        yaml.saveConfig();
    }

    @Override
    public void setObject(String path, Object value) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #setObject, section=?");
        section.set(path + ".data", value);
        yaml.saveConfig();
    }

    @Override
    public int getInteger(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getInteger, section=?");

        return section.getInt(path + ".data");
    }

    @Override
    public boolean getBoolean(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getBoolean, section=?");
        return section.getBoolean(path + ".data");
    }

    @Override
    public String getString(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getString, section=?");
        return section.getString(path + ".data");
    }

    @Override
    public double getDouble(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getDouble, section=?");
        return section.getDouble(path + ".data");
    }

    @Override
    public long getLong(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getLong, section=?");
        return section.getLong(path + ".data");
    }

    @Override
    public Object getObject(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getObject, section=?");
        return section.get(path + ".data");
    }


    public short getShort(String path) {
        Objects.requireNonNull(section, "section 변수가 없습니다! 확인해 주세요! 에러가 발생된 메소드 : #getObject, section=?");
        return (short) section.get(path + ".data");
    }

    public Inventory getInventory(String path) {

        if (yaml.getConfig().get(path + ".inv.size") != null && yaml.getConfig().get(path + ".inv.title") != null) {


            Inventory inv = Bukkit.createInventory(null, yaml.getInteger(path + ".inv.size"), yaml.getString(path + ".inv.title"));

            section = yaml.getConfig().getConfigurationSection(path + ".inv.items");

            for (int i = 0; i < inv.getSize(); i++) {

                String materialName = section.getString(i + ".Material");
                if (materialName != null) {

                    Material material = Material.valueOf(materialName);

                    int slot = section.getInt(i + ".slot");
                    int amount = section.getInt(i + ".Amount");

                    ItemStack item = new ItemStack(material, amount);
                    int durability = section.getInt(i + ".Durability");
                    inv.setItem(slot, item);
                }

                if (materialName != null) {

//

                }
            }
            return inv;
        }
        return null;
    }

    public ConfigurationSection getSection() {
        return section;
    }
}
