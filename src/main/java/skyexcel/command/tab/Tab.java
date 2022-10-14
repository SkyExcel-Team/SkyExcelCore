package skyexcel.command.tab;

import org.bukkit.command.Command;
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
        private String[] next;

        private String pre;


        public TabNode(String pre, String... next) {
            this.pre = pre;
            String[] newNext = new String[next.length];

            for (String line : next) {
                newNext[next.length + 1] = line;
            }

            this.next = newNext;
        }

        public String getNext(int index) {
            return next[index];
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
//                for (TabNode tabs : node) {
//                    if (tabs.getP() instanceof String) {
//                        if (args[0].equalsIgnoreCase((String) tabs.getP())) {
//                            String[] test = (String[]) tabs.getN(tabs.getP());
//                            int index = args.length - 2;
//                            if (tabs.isOp())
//                                result.add(test[index]);
//                        }
//
//                    }
//                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return result;
    }

}
