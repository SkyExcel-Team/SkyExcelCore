package skyexcel.annotation;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Registerclass implements CommandExecutor, TabCompleter {

    private Plugin plugin;
    private Object clazz;
    private String label;

    private List<Parameter> tabs = new ArrayList<>();


    public Registerclass(Object clazz, Plugin plugin) {
        this.plugin = plugin;
        this.clazz = clazz;
        registerClass(clazz.getClass());
        plugin.getServer().getPluginCommand(label).setExecutor(this);
        plugin.getServer().getPluginCommand(label).setTabCompleter(this);
    }


    public void registerClass(Class clazz) {
        Adjust adjustclass = (Adjust) clazz.getAnnotation(Adjust.class);
        if (adjustclass.console()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "console is " + adjustclass.console());
            this.label = adjustclass.command();
            this.tabs.add(new Parameter(adjustclass.parameter(), adjustclass.tab(), adjustclass.args(), adjustclass.console()));
        } else {
            if (adjustclass != null) {
                this.label = adjustclass.command();
                for (Method method : clazz.getMethods()) {
                    Annotation adjustcclazz = method.getAnnotation(Adjust.class);
                    if (adjustcclazz instanceof Adjust) {
                        Adjust adjust = (Adjust) adjustcclazz;

                        if (adjust != null) {
                            this.tabs.add(new Parameter(adjust.parameter(), adjust.tab(), adjust.args(), adjustclass.console()));
                        } else {
                            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " Can't find the adjust annotation anywhere");
                        }
                    }
                }
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " Can't find the adjust annotation anywhere in class : " + clazz.getName());
            }
        }
    }

    public void invoke(Class clazz, CommandSender sender, String[] args) {
        try {
            Adjust adjustclass = (Adjust) clazz.getAnnotation(Adjust.class);
            if (adjustclass.console()) {
                for (Method method : clazz.getMethods()) {
                    Adjust adjustcclazz = method.getAnnotation(Adjust.class);
                    if (adjustcclazz != null) {
                        if (adjustcclazz.label()) {
                            Class<?> newclazz = Class.forName(clazz.getName());
                            Object object = newclazz.newInstance();
                            method.invoke(object, sender, args);
                            System.out.println("Test");
                        }
                    }

                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public void invoke(Class clazz, Player sender, String[] args) {
        try {
            Adjust adjustclass = (Adjust) clazz.getAnnotation(Adjust.class);
            if (!adjustclass.console()) {
                for (Method method : clazz.getMethods()) {
                    Adjust adjustcclazz = method.getAnnotation(Adjust.class);
                    if (adjustcclazz != null) {

                       //TODO 현재 문제 : 파라미터 인자값보다 많을 시 이상의 args 값을 못 호출함.
                        if (adjustcclazz.parameter() == args.length - 1 && args[adjustcclazz.parameter()].equalsIgnoreCase(adjustcclazz.args())) {
                            if (!adjustcclazz.label()) {
                                Class<?> newclazz = Class.forName(clazz.getName());
                                Object object = newclazz.newInstance();
                                method.invoke(object, sender, args);
                            }
                        } else if (adjustcclazz.parameter() < args.length) {

                            System.out.println(args[adjustcclazz.parameter()].equalsIgnoreCase(adjustcclazz.args()));
                            if (args[adjustcclazz.parameter()].equalsIgnoreCase(adjustcclazz.args())) {
                                if (!adjustcclazz.label()) {
                                    Class<?> newclazz = Class.forName(clazz.getName());
                                    Object object = newclazz.newInstance();
                                    method.invoke(object, sender, args);
                                }

                            }
                        }
                    }

                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String
            label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            invoke(clazz.getClass(), player, args);

        } else {
            for (Parameter parameter : this.tabs) {
                if (parameter.console) {
                    invoke(clazz.getClass(), sender, args);

                }
            }
        }
        return false;
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String
            label, @NotNull String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (label.equalsIgnoreCase(label)) {
            if (tabs != null) {
                for (Parameter tabclazz : this.tabs) {
                    if (tabclazz.parameter == args.length - 1) {
                        if (tabclazz.tab) {
                            tabs.add(tabclazz.args);
                        }
                    }
                }
            }
            return tabs;
        }
        return null;
    }

    public class Parameter {
        int parameter;
        boolean tab;
        String args;

        boolean console;

        public Parameter(int parameter, boolean tab, String args, boolean console) {
            this.parameter = parameter;
            this.tab = tab;
            this.args = args;
            this.console = console;
        }
    }
}
