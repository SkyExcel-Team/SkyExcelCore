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
보다 편하게 상점 데이터를 처리 할 수 있습니다! 
 
```java
public class CashShop extends Stockable {


    public CashShop(String path, String name) {
        super(path, name);
    }
}
```

```java
String path = "shop/cash";
CashShop shop = new CashShop(path, name);
```
유동 변수를 편하게 만들 수 있습니다. 
이제 유동 변수의 이름을 정할 수 있습니다!

```java
public class Cash extends Flowable {

    public Cash(OfflinePlayer player, String path) {
        super(player, path);
        setValuePath("cash");
    }
}
```
---
