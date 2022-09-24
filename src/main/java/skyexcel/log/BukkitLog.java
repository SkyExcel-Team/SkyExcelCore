package skyexcel.log;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Arrays;

public class BukkitLog {
    public static void Log(Object... args) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "=======================================================================");
        Arrays.stream(args).forEach(test -> {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(test));
        });
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "=======================================================================");
    }
}
