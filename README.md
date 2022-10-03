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
        
        Tab<Object, String> tab = new Tab<>();

        tab.args("생성", "이름");
        tab.args("랭킹");
        tab.args("은행", "입금", "[<Integer>]");
        tab.args("은행", "출금", "[<Integer>]");

    }
}
```

---
