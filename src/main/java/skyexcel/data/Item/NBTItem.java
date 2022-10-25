package skyexcel.data.Item;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NBTItem {

    private final ItemStack item;

    public NBTItem(ItemStack objItem) {
        final net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objItem);

        this.item = CraftItemStack.asBukkitCopy(item);
    }


    public static NBTTagCompound getTagCompound(net.minecraft.world.item.ItemStack item) {
        return item.s() ? item.t() : new NBTTagCompound();
    }

    /**
     * @param key   require String
     * @param value will be cast as String
     * @return ItemStack
     */
    @NotNull
    public ItemStack setObjectTag(String key, Object value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        final NBTTagCompound ntc = getTagCompound(item);

        ntc.a(key, NBTTagString.a(value.toString()));
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key require String
     * @return ItemStack
     */
    @NotNull
    public ItemStack removeTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        item.c(ntc);
        item.c(key);
        return CraftItemStack.asBukkitCopy(item);
    }

    @NotNull
    public ItemStack removeAllTags() {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        for (String key : ntc.d()) {
            item.c(key);
        }
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key String
     * @return String
     */
    @NotNull
    public String getStringTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.l(key).replace('"', ' ').trim();
    }


    /**
     * @param key String
     * @return byte
     */
    @NotNull
    public byte getByteTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.f(key);
    }

    /**
     * @param key String
     * @return short
     */
    @NotNull
    public short getShortTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.f(key);
    }

    /**
     * @param key String
     * @return int
     */
    @NotNull
    public int getIntegerTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.h(key);
    }

    /**
     * @param key String
     * @return float
     */
    @NotNull
    public float getFloatTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.j(key);
    }

    /**
     * @param key String
     * @return double
     */
    @NotNull
    public double getDoubleTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.k(key);
    }

    /**
     * @param key String
     * @return boolean
     */
    public boolean getBooleanTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.q(key);
    }

    /**
     * @param key String
     * @return long
     */
    @NotNull
    public long getLongTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.i(key);
    }

    /**
     * @param key String
     * @return byte[]
     */
    @NotNull
    public byte[] getByteArrayTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.m(key);
    }

    /**
     * @param key String
     * @return int[]
     */
    @NotNull
    public int[] getIntArrayTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.n(key);
    }

    /**
     * @param key String
     * @return NBTTagCompound
     */
    @NotNull
    public NBTTagCompound getCompoundTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.p(key);
    }

    /**
     * @param key String
     * @return Material
     */
    @Nullable
    public Material getMaterialTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return Material.getMaterial(ntc.l(key));
    }

    /**
     * @param key String
     * @return InventoryType
     */
    @Nullable
    public InventoryType getInventoryTypeTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return InventoryType.valueOf(ntc.l(key));
    }

    /**
     * @param key String
     * @return NBTTagList
     */
    @Nullable
    public EntityType getEntityTypeTag(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return EntityType.valueOf(ntc.l(key));
    }

    /**
     * @param key String
     * @return boolean
     */
    public boolean hasTagKey(String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.e(key);
    }

    @Nullable
    public List<String> getAllStringTag() {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        if (ntc.d().size() == 0) {
            return null;
        }
        List<String> tag = new ArrayList<>();
        for (String key : ntc.d()) {
            tag.add(key);
        }
        return tag;
    }


    @Nullable
    public Map<String, String> getAllTagKey() {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        if (ntc.d().size() == 0) {
            return null;
        }
        Map<String, String> tags = new HashMap<>();
        for (String key : ntc.d()) {
            tags.put(key, ntc.c(key).toString());
        }
        return tags;
    }

    /**
     * @param key   String
     * @param value String
     * @return ItemStack
     */
    @NotNull
    public ItemStack setStringTag(String key, String value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value byte
     * @return ItemStack
     */
    @NotNull
    public ItemStack setByteTag(String key, byte value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value short
     * @return ItemStack
     */
    @NotNull
    public ItemStack setShortTag(String key, short value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value int
     * @return ItemStack
     */
    @NotNull
    public ItemStack setIntTag(String key, int value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value long
     * @return ItemStack
     */
    @NotNull
    public ItemStack setLongTag(String key, long value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value float
     * @return ItemStack
     */
    @NotNull
    public ItemStack setFloatTag(String key, float value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value double
     * @return ItemStack
     */
    @NotNull
    public ItemStack setDoubleTag(String key, double value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value byte[]
     * @return ItemStack
     */
    @NotNull
    public ItemStack setByteArrayTag(String key, byte[] value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value int[]
     * @return ItemStack
     */
    @NotNull
    public ItemStack setIntArrayTag(String key, int[] value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param key   String
     * @param value Material
     * @return ItemStack
     */
    @NotNull
    public ItemStack setMaterialTag(String key, Material value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(this.item);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value.name());
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    public ItemStack getItem() {
        return item;
    }
}
