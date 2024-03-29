package skyexcel.command.function;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;


import java.util.Objects;

public abstract class MainCmd implements CommandExecutor, Function {
    private final String label;

    protected MainCmd(Plugin plugin, String label) {
        this.label = label;
        Objects.requireNonNull(plugin.getServer().getPluginCommand(label)).setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase(label))
            runCmd(sender, label, args);
        return false;
    }

    public String getCmdLabel() {
        return label;
    }
}
