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

# Config 사용법.
```java
public class test{

    public void test(){
        Config config = new Config("test/test"); // .yml 제외! 
        config.setPlugin(pluginInstance); //플러그인 경로 등록 
        
    }
}
```

#GUI 사용법.
```java
public class test{

    public void test(Inventory inv){
        Config config = new Config("test/test"); // .yml 제외! 
        config.setPlugin(pluginInstance); //플러그인 경로 등록 
        GUI gui = new GUI(config);
        gui.saveInventory(path ,inv);
        gui.getInventory(path); //Null 체크로 예외처리 해야함. 변수로 사용할 
    }
}
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
