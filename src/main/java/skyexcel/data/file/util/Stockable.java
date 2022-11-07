package skyexcel.data.file.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

public abstract class Stockable {

    private String name;

    private Config config;

    private GUI gui;

    private int slot;

    private ItemStack itemStack;

    private Inventory inv;

    private SetType type = SetType.BUY;


    public Stockable(String path, String name, JavaPlugin plugin) {
        this.name = name;
        this.config = new Config(path + "/" + name);
        this.config.setPlugin(plugin);
        this.gui = new GUI(config);
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, name);
        this.inv = inv;
        player.openInventory(inv);
    }

    public void load(Player player) {
        Inventory inv = gui.getInventory(name);
        this.inv = inv;
        player.openInventory(inv);
    }

    public void create(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, name);
        this.inv = inv;
        player.openInventory(inv);
        gui.saveInventory(name, inv);
    }


    public void save(Inventory inv) {
        gui.saveInventory(name, inv);
    }


    public void setType(SetType type) {
        this.type = type;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        System.out.println(itemStack);
    }

    public void editGUI() {
        Inventory inv = Bukkit.createInventory(null, 27, "마일리지");
    }


    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setBuy(int amount) {
        gui.setItem(itemStack);
        gui.setInteger("buy", amount);
    }

    public void setSell(int amount) {
        gui.setItem(itemStack);
        gui.setInteger("sell", amount);
    }

    public void delete(Player player) {
        config.renameFile("trash/" + name);
        config.saveConfig();
        config.delete();
    }

    public SetType getType() {
        return type;
    }

    public Inventory getInv() {
        return inv;
    }

    public enum SetType {
        BUY, SELL, CLOSE
    }
}
