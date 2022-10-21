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

import java.util.HashMap;
import java.util.Map;

public class NBTItem {
    public NBTTagCompound getTagCompound(net.minecraft.world.item.ItemStack item) {
        return item.s() ? item.t() : new NBTTagCompound();
    }

    /**
     * @param objitem require ItemStack
     * @param key     require String
     * @param value   will be cast as String
     * @return ItemStack
     */
    @NotNull
    public ItemStack setObjectTag(ItemStack objitem, String key, Object value) {
        final net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        final NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, NBTTagString.a(value.toString()));
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem require ItemStack
     * @param key     require String
     * @return ItemStack
     */
    @NotNull
    public ItemStack removeTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        item.c(ntc);
        item.c(key);
        return CraftItemStack.asBukkitCopy(item);
    }

    @NotNull
    public ItemStack removeAllTags(ItemStack objitem) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        for (String key : ntc.d()) {
            item.c(key);
        }
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return String
     */
    @NotNull
    public String getStringTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.l(key).replace('"', ' ').trim();
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return byte
     */
    @NotNull
    public byte getByteTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.f(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return short
     */
    @NotNull
    public short getShortTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.f(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return int
     */
    @NotNull
    public int getIntegerTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.h(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return float
     */
    @NotNull
    public float getFloatTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.j(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return double
     */
    @NotNull
    public double getDoubleTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.k(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return boolean
     */
    public boolean getBooleanTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.q(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return long
     */
    @NotNull
    public long getLongTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.i(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return byte[]
     */
    @NotNull
    public byte[] getByteArrayTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.m(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return int[]
     */
    @NotNull
    public int[] getIntArrayTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.n(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return NBTTagCompound
     */
    @NotNull
    public NBTTagCompound getCompoundTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.p(key);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return Material
     */
    @Nullable
    public Material getMaterialTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return Material.getMaterial(ntc.l(key));
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return InventoryType
     */
    @Nullable
    public InventoryType getInventoryTypeTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return InventoryType.valueOf(ntc.l(key));
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return NBTTagList
     */
    @Nullable
    public EntityType getEntityTypeTag(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return EntityType.valueOf(ntc.l(key));
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @return boolean
     */
    public boolean hasTagKey(ItemStack objitem, String key) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        return ntc.e(key);
    }

    @Nullable
    public Map<String, String> getAllStringTag(ItemStack objitem) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
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
     * @param objitem ItemStack
     * @param key     String
     * @param value   String
     * @return ItemStack
     */
    @NotNull
    public ItemStack setStringTag(ItemStack objitem, String key, String value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   byte
     * @return ItemStack
     */
    @NotNull
    public ItemStack setByteTag(ItemStack objitem, String key, byte value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   short
     * @return ItemStack
     */
    @NotNull
    public ItemStack setShortTag(ItemStack objitem, String key, short value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   int
     * @return ItemStack
     */
    @NotNull
    public ItemStack setIntTag(ItemStack objitem, String key, int value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   long
     * @return ItemStack
     */
    @NotNull
    public ItemStack setLongTag(ItemStack objitem, String key, long value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   float
     * @return ItemStack
     */
    @NotNull
    public ItemStack setFloatTag(ItemStack objitem, String key, float value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   double
     * @return ItemStack
     */
    @NotNull
    public ItemStack setDoubleTag(ItemStack objitem, String key, double value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   byte[]
     * @return ItemStack
     */
    @NotNull
    public ItemStack setByteArrayTag(ItemStack objitem, String key, byte[] value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   int[]
     * @return ItemStack
     */
    @NotNull
    public ItemStack setIntArrayTag(ItemStack objitem, String key, int[] value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value);
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }

    /**
     * @param objitem ItemStack
     * @param key     String
     * @param value   Material
     * @return ItemStack
     */
    @NotNull
    public ItemStack setMaterialTag(ItemStack objitem, String key, Material value) {
        net.minecraft.world.item.ItemStack item = CraftItemStack.asNMSCopy(objitem);
        NBTTagCompound ntc = getTagCompound(item);
        ntc.a(key, value.name());
        item.c(ntc);
        return CraftItemStack.asBukkitCopy(item);
    }
}
