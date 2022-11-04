package skyexcel.command.tab;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;


import java.util.*;


/**
 * P : Previous (이전 커맨드)
 * N : Next ( Tab에 보일 커맨드 )
 * Previous 값을 통해 Next 값을 불러오는 구조이다.
 */
public class Tab implements TabCompleter {


    // TODO 제너릭을 사용해, 다중 어레이의 값을 구현 Ex)
    //  tabs.args(Arrays.asList(args0));
    //  위에 방식일 경우, tabs.args("도움말","테스트") => 이런식으로 args0 값에 있는 것을 pre 값에 집어 넣어야 한다.
    //

    private TabNode[] tabNodes = {};


    public Tab(Plugin plugin, String label) {
        Objects.requireNonNull(plugin.getServer().getPluginCommand("")).setTabCompleter(this);

    }


    public Tab(String label) {
        Objects.requireNonNull(Bukkit.getServer().getPluginCommand("")).setTabCompleter(this);

    }


    // args값을 리스트로 만든다. 이 때에 String 값으로 해당 리스트를 인식 할 수 있어야 한다.


    public void args(String previous, String... args) {

        TabNode node = new TabNode(previous, args);
        try {
            if (previous != null)

                tabNodes = add(tabNodes, node);
        } catch (
                ArrayIndexOutOfBoundsException e) {

        }
    }




    public static class Args {

        private String a; // 첫번째 커맨드 값

        private String[] args; // 첫번째 값을 제외한 값


        public Args(String a, String[] args) {
            this.a = a;

            this.args = args;

            if (args.length == 2) {
                System.out.println(a + " " + args[0]);
            } else {
                System.out.println(a + " " + args[0] + " " + args[1]);
            }
            System.out.println(a + " " + args[0]);

        }

        public String getA() {
            return a;
        }

        public String[] getArgs(String a) {
            if (this.a.equalsIgnoreCase(a))
                return args;

            return null;
        }
    }

    public static class TabNode {

        private Args args;

        private String pre;


        public TabNode(String pre, String... next) {
            this.pre = pre;

            try {

                this.args = new Args(pre, next);


            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }

        public Args getArgs(String pre) {
            if (this.pre.equalsIgnoreCase(pre))
                return this.args;

            return null;
        }

        public String getPre() {
            return pre;
        }
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,  String label,  String[] args) {
        List<String> result = new ArrayList<String>();

        try {
            if (args.length == 1) {
                for (TabNode node : this.tabNodes) {
                    result.add(node.getPre());
                }
            } else {
                for (TabNode node : this.tabNodes) {

                    Args newArgs = node.getArgs(args[0]);

                }

            }


        } catch (CommandException | IndexOutOfBoundsException e) {

        }
        return result;
    }

    private static TabNode[] add(TabNode[] originArray, TabNode Val) {
        // 순서 1. (원본 배열의 크기 + 1)를 크기를 가지는 배열을 생성
        TabNode[] newArray = new TabNode[originArray.length + 1];

        // 순서 2. arraycopy() 메서드를 사용하여 복사
        System.arraycopy(originArray, 0, newArray, 0, originArray.length);

        // 순서 3. 새로운 배열의 마지막 위치에 추가하려는 값을 할당
        newArray[originArray.length] = Val;

        // 순서 4. 새로운 배열을 반환
        return newArray;
    }
}
