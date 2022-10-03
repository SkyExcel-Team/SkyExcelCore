package skyexcel.data.file;


import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface AConfig {
    public void setInteger(String path, int value);
    public void setBoolean(String path, boolean value);
    public void setString(String path, String value);
    public void setDouble(String path, double value);

    public void setLocation(String path, Location value);


    public void setItemStack(String path, ItemStack value);
    public void setSound(String path, Sound value);
    public void setLong(String path, long value);
    public void setList(String path, List<Object> value);
    public void setObject(String path, Object value);


    public int getInteger(String path);

    public boolean getBoolean(String path);
    public String getString(String path);
    public double getDouble(String path);
    public long getLong(String path);

    public Object getObject(String path);
}
