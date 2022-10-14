package skyexcel.command.tab;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


/**
 * P : Previous (이전 커맨드)
 * N : Next ( Tab에 보일 커맨드 )
 * Previous 값을 통해 Next 값을 불러오는 구조이다.
 */
public class Tab implements TabCompleter {

    private List<TabNode> tabNodes = new ArrayList<>();


    public Tab(Plugin plugin, String label) {
        Objects.requireNonNull(plugin.getServer().getPluginCommand(label)).setTabCompleter(this);
    }


    public void args(String previous, String... args) {
        TabNode node = new TabNode(previous, args);
        tabNodes.add(node);

    }


    public static class TabNode {
        private List<String> next = new ArrayList<>();

        private String pre;


        public TabNode(String pre, String... next) {
            this.pre = pre;

            for (String line : next) {
                Bukkit.getConsoleSender().sendMessage(pre + " : " + line + " 등록 완료!");
                this.next.add(line);
            }

        }

        public String getNext(int index) {
            return next.get(index);
        }


        public List<String> getNext() {
            return next;
        }

        public String getPre() {
            return pre;
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<String>();

        try {
            if (args.length == 1) {


                for (TabNode tabs : tabNodes) {
                    if (tabs.getPre() != null) {
                        String previous = tabs.getPre();
                        result.add(previous);
                    }
                }

            } else {
                // TODO 문제점 : 인자값을 하나하나 비교해서 등록 해 줘야 함. 이 문제를 어떻게 해결하냐인데...
                for (TabNode tabs : tabNodes) {
                    if (tabs.pre.equalsIgnoreCase(args[0])) {
                        System.out.println(tabs.getNext().size());
                        if (args.length == 2) {
                            result.add( tabs.getNext().get(0));
                        } else if (tabs.getNext().get(0).equalsIgnoreCase(args[1])) {
                            result.add(tabs.getNext().get(1));
                        }
                    }
                }
            }
        } catch (CommandException | IndexOutOfBoundsException e) {

        }
        return result;
    }

}
