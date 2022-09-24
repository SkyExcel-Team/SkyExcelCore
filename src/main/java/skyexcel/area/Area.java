package skyexcel.area;

import org.bukkit.Location;

public class Area {

    private Location pos1;
    private Location pos2;
    private String name;


    public boolean createArea(String name) {
        if (pos1 == null && pos2 == null) {
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean setPos1(Location pos1) {
        if (pos1 != null) {
            this.pos1 = pos1;
            return true;
        }
        return false;
    }

    public boolean setPos2(Location pos2) {
        if (pos2 != null) {
            this.pos2 = pos2;
            return true;
        }
        return false;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public String getName() {
        return name;
    }
}
