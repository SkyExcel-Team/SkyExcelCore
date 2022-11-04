package skyexcel.data.Item;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;

public class InventoryUtil implements InventoryHolder {

    private Inventory inv;
    private Player player;

    private String title;
    private int size;


    public InventoryUtil(Inventory inv, Player player) {
        this.inv = inv;
        this.player = player;
    }

    public InventoryUtil(Inventory inv) {
        this.inv = inv;
    }

    public void CreateInventory() {

        Inventory inv = Bukkit.createInventory(null, size, title);

        InventoryView view = (InventoryView) inv;

    }

    public Inventory getInv() {
        return inv;
    }

    public int getSize() {
        return size;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
