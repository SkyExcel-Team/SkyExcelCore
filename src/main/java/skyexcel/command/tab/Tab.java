package skyexcel.command.tab;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
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

    /***
     * Op 여부를 지정하여 저장 가능.
     * 마지막으로 설정한 값의 Op를 설정해 주는 메소드 이다.
     * 그러므로, 먼저 args 메소드를 사용 후 이 메소드를 사용 하는게 맞는 순서 이다.
     *
     *
     * @param isOp 해당 변수의 초기값은 true 이다. false로 변수를 바꾸어 주면 오피가 아닌 플레이어에게 return 값이 안 보일 것이다.
     */
    public void setOp(boolean isOp) {
        int index = node.size() - 1;
        TabNode setNode = node.get(index);
        setNode.setOp(isOp);
        node.set(index, setNode);
    }


    public void setPer(String... per) {
        int index = node.size() - 1;
        TabNode setNode = node.get(index);
        setNode.setPer(per);
        node.set(index, setNode);
    }

    private final class TabNode<P, N> {

        private N n;
        private P p;

        private boolean isOp = true;

        private String[] per;

        public TabNode(P p, N n) {
            this.p = p;

            this.n = n;
        }

        public void setOp(boolean op) {
            isOp = op;
        }

        public void setPer(String[] per) {
            this.per = per;
        }

        public N getN(P p) {
            if (p.equals(p))
                return n;
            return null;
        }

        public String[] getPer() {
            return per;
        }

        public boolean isOp() {
            return isOp;
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
                    if (tabs.isOp()) {
                        if (sender.isOp()) {
                            if (tabs.getP() instanceof String) {

                                String previous = (String) tabs.getP();
                                result.add(previous);
                            }
                        }
                    } else {
                        if (tabs.getP() instanceof String) {

                            String previous = (String) tabs.getP();
                            result.add(previous);
                        }
                    }
                }
            } else {
                for (TabNode tabs : node) {
                    if (tabs.isOp()) {
                        if (sender.isOp()) {

                            String previous = (String) tabs.getP();
                            if (previous.equalsIgnoreCase(args[0])) {
                                String[] arg = (String[]) tabs.getN(previous);

                                if (arg.length >= args.length - 1) {

                                    result.add(arg[args.length - 2]);
                                }
                            }
                        }
                    } else {
                        if (((String) tabs.getP()).equalsIgnoreCase(args[0])) {
                            String[] arg = (String[]) tabs.getN(tabs.getP());
                            for (String test : arg) {
                                result.add(test);
                            }
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return result;
    }

    private boolean hasPer(TabNode tabs, CommandSender sender) {
        if (tabs.getPer() != null) {
            for (String per : tabs.getPer()) {
                if (sender.hasPermission(per)) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
