# SkyExcelCore
![](https://bstats.org/signatures/bukkit/SkyExcelCore.svg)

# Import 

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.brucefreedy:mccommand:1.0.1")
}
```

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.github.brucefreedy:mccommand:1.0.1'
}
```

# Command-Examples for Java

```java
public class Command {
    private String label = "games";

    public Command(String cmd) {
        this.label = cmd;

        Config config = new Config("test/");
        config.setPlugin(MiniGame.plugin);

        Commands commands = new Commands(label) {
            @Override
            public void run(CommandSender sender, String[] args) {
                sender.sendMessage("asfd");
            }
        };

        Tab tab = new Tab(commands, List.of(
            List.of(Arrays.toString(config.getFileList())),
            List.of("test2", "asdf2"),
            List.of("test3", "asdf3", "1234"))) {
        };

        tab.select(1).of(0).is(config.getFileList() != null);
    }
}
```

---