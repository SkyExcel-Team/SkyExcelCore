# SkyExcelCore
![](https://bstats.org/signatures/bukkit/SkyExcelCore.svg)

# Import Gradle

```gradle
repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.github.SkyExcel-Team:SkyExcelCore:v1.1.21'

}
```
# Data Table Exmaples 
```java
private DataTable table;

//Column, Row
table = new DataTable(0, 0);
```



# Command Examples for Java

```java
       Tab<Object, String> tab = new Tab<>(MiniGame.plugin, label);

        tab.args("생성", "이름");
        tab.args("랭킹");
        tab.args("은행", "입금", "[<Integer>]");
        tab.args("은행", "출금", "[<Integer>]");

        Cmd cmd = new Cmd(MiniGame.plugin, label);

        cmd.action("은행", 0, action -> {
            Player player = (Player) action.getSender();
            player.sendMessage("섬 은행 시스템을 열었습니다!");
        });

```
 

---
