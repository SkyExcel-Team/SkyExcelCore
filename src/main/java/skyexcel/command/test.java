package skyexcel.command;


import skyexcel.annotation.Adjust;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Adjust(command = "Hi")
public class test {

    @Adjust(label = true)
    public void console(CommandSender sender, String[] args){
        if(args.length > 0){
            sender.sendMessage("Tests " + args[0]);
        } else{
            sender.sendMessage("인자값을 입력해 주세요.");
        }

    }
    @Adjust(args = "first", tab = true) //Hi test
    public void test(Player sender, String[] args) {
        sender.sendMessage("test");
    }

    @Adjust(args = "multifirst", tab = true) //Hi test
    public void atest(Player sender, String[] args) {
        System.out.println("test" + args[1]);
    }


    @Adjust(args = "other", tab = true) //Hi test
    public void inttest(Player sender, String[] args) {

    }

    @Adjust(args = "hi", parameter = 1, tab = true) //Hi test
    public void asdf(Player sender, String[] args) {
        sender.sendMessage("Test");
    }
}
