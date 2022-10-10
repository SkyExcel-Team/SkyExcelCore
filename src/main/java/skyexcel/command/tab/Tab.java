package skyexcel.command.tab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import skyexcel.data.DataTable;

import java.util.*;


/**
 * P : Previous (이전 커맨드)
 * N : Next ( Tab에 보일 커맨드 )
 * Previous 값을 통해 Next 값을 불러오는 구조이다.
 */
public class Tab<P> implements TabCompleter {

    private NextList next = new NextList();

    private List<String> pre = new ArrayList<>();
    private List<Object> args = new ArrayList<>();

    private DataTable table;

    public Tab(Plugin plugin, String label) {
        table = new DataTable();

        Objects.requireNonNull(plugin.getServer().getPluginCommand(label)).setTabCompleter(this);
    }


    /**
     * @param previous args[0] 명령어
     * @param next     다음에 올 명령어 리스트
     */
    public void args(String previous, Object... args) {

        String newPre = (String) previous;


        pre.add(previous);
        this.args.add(args);

        for (Object obj : this.args) {
            System.out.println((String) obj);
        }

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

    }


    private final class TabNode<P, N> {


        private P p;

        private boolean isOp = true;


        public TabNode(P e, Object... next) {
            this.p = p;

        }

        public void setOp(boolean op) {
            isOp = op;
        }


        public boolean isOp() {
            return isOp;
        }

        public P getP() {
            return p;
        }
    }

    /**
     * 다음 값의 다음을 지정 하기 위해선, 해당 클래스를 노드 안에 넣어
     * 현재 값이 커맨드 값과 같다면, 현재 커맨드 값으로 다음 값을 가져올 수 있도록 한다.
     * 예:) 현재 커맨드 : /금고 입금 일때 다음 값으로 Amount를 출력한다.
     * 해당 방식을 사용하면 /금고 잠금 과 같이 다음 파라미터 값이 없는 현재 값일 경우
     * 다르게 추가 할 수 있다.
     */
    public class NextList {

        private Object[] NEXT = {};

        private int size;


        public void add(Object obj) {

            assert obj != null;
        }

        public Object get(P e) {

            return null;
        }

        public int getSize() {
            return size;
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<String>();

        try {
            if (args.length == 1) {
//                for (TabNode tabs : node) {
//                    if (tabs.isOp()) {
//                        if (sender.isOp()) {
//                            if (tabs.getP() instanceof String) {
//
//                                String previous = (String) tabs.getP();
//                                result.add(previous);
//
////                                String[] arg = (String[]) tabs.getN(previous);
//
//                            }
//                        }
//                    } else {
//                        if (tabs.getP() instanceof String) {
//
//                            String previous = (String) tabs.getP();
//                            result.add(previous);
//                        }
//                    }
//                }
            } else {

//                for (TabNode tabs : node) {
//                    if (tabs.isOp()) {
//                        if (sender.isOp()) {
//                            String previous = (String) tabs.getP();
//
//                            String[] arg = (String[]) tabs.getN(previous).next;
//
//
//                            // 다차원 변수로 매게 변수들을 불러와, 비교하여저장한다.
//                            System.out.println(arg.length);
//
//
//                            result.add(arg[args.length - 1]);
//
//                        }
//                    } else {
//                        if (((String) tabs.getP()).equalsIgnoreCase(args[0])) {
////                            String[] arg = (String[]) tabs.getN(tabs.getP());
////                            for (String test : arg) {
////                                result.add(test);
////                            }
//                        }
//                    }
//                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return result;
    }
}
