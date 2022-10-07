package skyexcel.command.tab;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * P : Previous (이전 커맨드)
 * N : Next ( Tab에 보일 커맨드 )
 * Previous 값을 통해 Next 값을 불러오는 구조이다.
 */
public class Tab<P, N> implements TabCompleter {
    private List<TabNode> node = new ArrayList<>();

    public Tab(Plugin plugin, String label) {
        Objects.requireNonNull(plugin.getServer().getPluginCommand(label)).setTabCompleter(this);
    }


    /**
     * @param previous args[0] 명령어
     * @param next     다음에 올 명령어 리스트
     */
    public void args(P previous, N... next) {

        TabNode newnode = new TabNode(previous, next);

        node.add(newnode);
    }

    private final class TabNode<P, N> {

        private P p;

        private N n;


        public TabNode(P p, N n) {
            this.p = p;

            this.n = n;
        }

        public N getN(P p) {
            if (p.equals(p))
                return n;
            return null;
        }

        public P getP() {
            return p;
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<String>();

        try {
            if (args.length == 1) {
                for (TabNode tabs : node) {
                    if (tabs.getP() instanceof String) {
                        String previous = (String) tabs.getP();
                        result.add(previous);
                    }
                }
            } else {
                for (TabNode tabs : node) {
                    if (tabs.getP() instanceof String) {
                        if (args[0].equalsIgnoreCase((String) tabs.getP())) {
                            String[] test = (String[]) tabs.getN(tabs.getP());
                            int index = args.length - 2;
                            result.add(test[index]);
                        }

                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return result;
    }
}
