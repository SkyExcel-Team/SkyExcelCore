package skyexcel.data.file.lang;

import skyexcel.data.file.Config;

public class Lang extends Config {
    private String name;
    private Config config;

    public Lang(String name) {
        super("lang/" + name);
        this.name = name;
        config = new Config("lang/" + name);
    }
}
