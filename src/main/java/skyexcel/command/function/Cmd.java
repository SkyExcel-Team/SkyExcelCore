package skyexcel.command.function;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 커맨드에 액션 함수를 지정하여 커맨드 코딩을 줄임.
 */
public class Cmd extends MainCmd {


    private List<CmdNode> nodes = new ArrayList<>();

    private Label label;


    public Cmd(Plugin plugin, String label) {
        super(plugin, label);
    }

    public void action(String args, int index, Consumer<Action> action) {

        CmdNode node = new CmdNode();
        node.setAction(action);
        node.setArg(args);
        node.setIndex(index);
        this.nodes.add(node);
    }


    public void label(Consumer<Action> action) {
        Label label = new Label();

        label.setLabel(getLabel());
        label.setAction(action);

        this.label = label;
    }


    public class CmdNode {
        private String arg;

        private Consumer<Action> action;

        private int index;

        public void setAction(Consumer<Action> action) {
            this.action = action;
        }

        public void setArg(String arg) {
            this.arg = arg;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public String getArg() {
            return arg;
        }

        public Consumer<Action> getAction() {
            return action;
        }

    }

    public class Action {
        private String[] args;
        private CommandSender sender;

        public Action getThis() {
            return this;
        }

        public void setArgs(String[] args) {
            this.args = args;
        }

        public void setSender(CommandSender sender) {
            this.sender = sender;
        }

        public String[] getArgs() {
            return args;
        }

        public CommandSender getSender() {
            return sender;
        }
    }

    public class Label {
        private String[] args;
        private String label;
        private CommandSender sender;

        private Consumer<Action> action;

        public Label getThis() {
            return this;
        }

        public void setAction(Consumer<Action> action) {
            this.action = action;
        }


        public void setLabel(String label) {
            this.label = label;
        }

        public Consumer<Action> getAction() {
            return action;
        }

        public void setArgs(String[] args) {
            this.args = args;
        }

        public void setSender(CommandSender sender) {
            this.sender = sender;
        }

        public String[] getArgs() {
            return args;
        }

        public CommandSender getSender() {
            return sender;
        }

        public String getLabel() {
            return label;
        }
    }

    @Override
    public void runCmd(CommandSender sender, String label, String[] args) {
        try {
            if (label.equalsIgnoreCase(this.label.getLabel())) {
                if (args.length == 0) {
                    Action action = new Action();
                    action.setSender(sender);
                    action.setArgs(args);
                    this.label.getAction().accept(action);
                }
                for (CmdNode node : nodes) {
                    if (args.length > 0) {
                        if (node.getArg().equalsIgnoreCase(args[node.getIndex()])) {
                            Action action = new Action();
                            action.setArgs(args);
                            action.setSender(sender);
                            node.getAction().accept(action);
                        }
                    } else {
                        if (node.getArg().equalsIgnoreCase(args[0])) {
                            Action action = new Action();
                            action.setArgs(args);
                            action.setSender(sender);
                            node.getAction().accept(action);
                        }
                    }
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }
}
