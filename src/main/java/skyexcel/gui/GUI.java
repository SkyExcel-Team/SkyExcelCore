package skyexcel.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class GUI {
    private static final List<Node> node = new ArrayList<>();

    private static Inventory inv;


    public GUI(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new Event(), plugin);
    }

    public void createGUI(String title, int size) {
        inv = Bukkit.createInventory(null, size, title);
    }

    public void line(ItemStack item, int slot, Consumer<Action> action) {
        Node node = new Node(slot);

        node.setAction(action);

        this.node.add(node);

        this.inv.setItem(slot, item);
    }


    /***
     * open 메소드를 사용하기 전에 createGUI 메소드를 무조건
     * 호출 해야합니다.
     * @param player 오픈할 플레이어
     */
    public void open(Player player) {
        assert inv != null;
        player.openInventory(inv);
    }

    public static Inventory getInv() {
        return inv;
    }

    private static class Node {

        private int slot;

        private Consumer<Action> action;


        private Node(int slot) {
            this.slot = slot;
        }

        public void setAction(Consumer<Action> action) {

            this.action = action;
        }

        public int getSlot() {
            return slot;
        }

        public Consumer<Action> getAction() {
            return action;
        }


    }

    public static class Action {
        private Player player;
        private ItemStack item;
        private int slot;
        private String title;
        private Inventory inv;

        private InventoryClickEvent event;


        public Action(InventoryClickEvent event, Player player, ItemStack item, int slot, String title, Inventory inv) {
            this.player = player;
            this.item = item;
            this.slot = slot;
            this.title = title;
            this.inv = inv;
            this.event = event;
        }

        public Action(InventoryClickEvent event, Player player, int slot, String title, Inventory inv) {
            this.event = event;
            this.player = player;
            this.slot = slot;
            this.title = title;
            this.inv = inv;
        }

        public Action(Player player, String title, Inventory inv) {
            this.player = player;
            this.title = title;
            this.inv = inv;
        }

        public int getSlot() {
            return slot;
        }

        public String getTitle() {
            return title;
        }

        public Player getPlayer() {
            return player;
        }

        public ItemStack getItem() {
            return item;
        }

        public Inventory getInv() {
            return inv;
        }

        public InventoryClickEvent getEvent() {
            return event;
        }
    }

    private static class Event implements Listener {
        @EventHandler
        public void onClick(InventoryClickEvent event) {
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                int slot = event.getSlot();
                String title = event.getView().getTitle();

                Inventory inv = event.getClickedInventory();
                ItemStack item = event.getCurrentItem();

                Action action = new Action(event, player, item, slot, title, inv);

                if (title.equalsIgnoreCase(action.getTitle())) {
                    node.get(0).getAction().accept(action);
                }

            }
        }

    }
}
