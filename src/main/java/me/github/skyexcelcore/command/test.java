package me.github.skyexcelcore.command;

import me.github.skyexcelcore.annotation.Adjust;
import org.bukkit.entity.Player;

@Adjust(command = "Hi")
public class test {
    @Adjust(args = {"test"}) //Hi test
    public void test(Player sender){
        sender.sendMessage("Test");
    }
    @Adjust(args = {"test int"}) //Hi test
    public void inttest(Player sender, int value){
        sender.sendMessage("Test" + value);
    }
}
