package skyexcel.data.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class Region {
    private UUID worldUniqueId;

    private double maxX;
    private double maxY;
    private double maxZ;

    private double minX;
    private double minY;
    private double minZ;


    public Region(Location firstPoint, Location secondPoint) {
        if(firstPoint != null && secondPoint != null){
            worldUniqueId = firstPoint.getWorld().getUID();

            maxX = Math.max(firstPoint.getX(), secondPoint.getX());
            maxY = Math.max(firstPoint.getY(), secondPoint.getY());
            maxZ = Math.max(firstPoint.getZ(), secondPoint.getZ());

            minX = Math.min(firstPoint.getX(), secondPoint.getX());
            minY = Math.min(firstPoint.getY(), secondPoint.getY());
            minZ = Math.min(firstPoint.getZ(), secondPoint.getZ());
        }

    }
    public Location getMinLoc(){
        return new Location(Bukkit.getWorld(worldUniqueId),minX,minY,minZ);
    }

    public Location getMaxLoc(){
        return new Location(Bukkit.getWorld(worldUniqueId),maxX,maxY,maxZ);
    }

    public boolean locationIsInRegion(Location loc) {
        return loc.getWorld().getUID().equals(worldUniqueId)
                && loc.getX() > minX && loc.getX() < maxX
                && loc.getY() > minY && loc.getY() < maxY
                && loc.getZ() > minZ && loc.getZ() < maxZ;
    }
}
