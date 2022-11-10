package skyexcel.data.file.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

public abstract class Stockable {

    private String name;

    private Config config;

    private GUI gui;


    private Inventory inv;

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

    public boolean delete() {
        return config.delete();
    }

    public Inventory getInv() {
        return inv;
    }

    public GUI getGui() {
        return gui;
    }
}
