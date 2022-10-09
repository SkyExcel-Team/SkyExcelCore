package skyexcel.command.function;

import org.bukkit.command.CommandSender;

public interface Function {
    public void runCmd(CommandSender sender, String label, String[] args);
}
