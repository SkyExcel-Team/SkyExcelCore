package skyexcel.command.tab;

public class TabNode {

    private String pre;

    private String[] next;


    public TabNode(String pre, String... next) {
        this.pre = pre;
        this.next = next;
    }
}
