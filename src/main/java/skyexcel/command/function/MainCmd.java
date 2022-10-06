package skyexcel.command.function;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class MainCmd implements CommandExecutor, Function {
    private String label;

    protected MainCmd(Plugin plugin, String label) {
        this.label = label;
        plugin.getServer().getPluginCommand(label).setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase(label))
            runCmd(sender, args);
        return false;
    }
}