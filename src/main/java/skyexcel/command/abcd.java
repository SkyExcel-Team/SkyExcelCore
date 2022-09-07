package skyexcel.command;


import skyexcel.annotation.Adjust;
import org.bukkit.entity.Player;

@Adjust(command = "abcd")
public class abcd {

    @Adjust(args = "test", tab = true) //Hi test
    public void test(Player sender) {
        System.out.println("test");
    }

    @Adjust(args = "int", parameter = 1) //Hi test
    public void inttest(Player sender, int value) {
        sender.sendMessage("Test" + value);
    }

    @Adjust(args = "hi", parameter = 2) //Hi test
    public void asdf(Player sender, int value) {
        sender.sendMessage("Test" + value);
    }
}
