# SkyExcelCore
![](https://bstats.org/signatures/bukkit/SkyExcelCore.svg)

# Import 

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.github.SkyExcel-Team:SkyExcelCore:v1.1.14'

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
