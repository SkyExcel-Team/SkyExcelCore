package skyexcel.data.location;

import org.bukkit.Location;
import skyexcel.data.file.Config;

public class Area {

    private String name;
    private Location pos1;
    private Location pos2;

    private Config config;

    public Area() {

    }

    public Area(String name) {
        this.name = name;
        this.config = new Config(name);
    }

    public Area(String name, Location pos1, Location pos2) {
        this.name = name;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.config = new Config(name);
    }

    public void saveData() {
        config.setLocation("arena.pos1", pos1);
        config.setLocation("arena.pos2", pos2);
        config.saveConfig();
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }
}
