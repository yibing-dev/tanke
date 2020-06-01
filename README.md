# 坦克大战游戏(更新中......)
介绍：写这个小游戏的目的是为了练习一下常用的设计模式，这里会以时间线的方式记录开发日志，功能正在一步步完善中... ...
* Java小游戏
* Java设计模式

## 项目时间
* 开始时间:2020/5/26
* 结束时间:开发ing 

## Java版本
* Java8

## 使用到的设计模式或技术
* NIO
* Netty
*    设计模式 

* 正在更新......

## 开发日志



### 2020/5/29
 
##### 功能更新：
* 坦克可以打多发子弹，并规避了可能出现的内存溢出的问题，即及时销毁打出的子弹
* 添加游戏icon

##### 开发要点记录：
* 需要注意在遍历(forech)集合的时候，是不允许有删除操作的，会报并发操作异常，比如使用forech遍历一个ArrayList的时候，如果其他操作里有remove操作，就会报异常()

``` java
Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
    at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
```
* 如何解决？
使用最朴素的for循环解决，例如：

``` java
for (int i = 0; i < bullets.size(); i++) {
    bullets.get(i).paint(g);
}
```
### 2020/5/30

##### 功能更新：
* 根据按键方向显示不同方向的坦克icon和子弹icon
* 子弹在正确的位置发射
* 添加敌方坦克
* 子弹和坦克碰撞检测(使用坦克位置和字段位置，以及子弹和坦克的宽高构建一个矩形，如若两个矩形相交，则说明子弹和坦克发生了碰撞)
* 子弹和坦克碰撞在一起，销毁坦克和子弹
* 避免坦克被自己打出的子弹销毁
* 敌方坦克随机移动，随机发射子弹
* 坦克移动边界检测

##### 开发要点记录：
*  通过classLoader加载项目图片

``` java
ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif")
```
*  定义静态代码块，在加载本类的时候会自动加载到内存里

``` java
public class ResourceMgr {
    static BufferedImage tankL, tankU, tankR, tankD;
    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 2020/5/31

##### 功能更新：
* 更换了更漂亮的坦克图片
* 增加了一个图片旋转工具类
* 添加子弹发射的声音
* 爆炸之后爆炸痕迹自动消失
* 优化坦克随机改变方向的概率
* 优化爆炸位置在坦克的中心位置
* 我方坦克和敌方坦克显示为不同的图片
* 优化Rectangle对象占用内存，避免了潜在的内存泄露风险
* 加入配置文件，让程序更灵活

##### 开发要点记录：

* 调整坦克随机改变方向的概率

``` java
Dir.values()[random.nextInt(4)];
```

## 阶段小结

* 目前写的代码是属于直肠子型的，相对来说比较简单，缺什么功能直接堆代码，没有任何设计模式可言，接下来要针对这几天的代码进行重构！！！不进行CodeReview的码农不是程序员 [大笑脸]

## 2020/6/2

##### 功能更新：
* 把资源文件加载类改为单例模式
* 把配置文件加载类改为单例模式

##### 开发要点记录：

* 4种常见的单例模式(Singleton) --> 应用场景：只需要一个实例存在，比如各种的manager类和各种的factory类

* 饿汉式

``` java
/**
 * 类加载到内存后，就会实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用
 * 唯一缺点：不管用到与否，类装载的时候都会被实例化
 */
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();
    // 禁止new
    private Mgr01() {};
    // 初始化方法
    public static Mgr01 getInstance() {
        return INSTANCE;
    }
}
```

* 懒汉式

``` java
public class Mgr02 {
    private Mgr02() {}
    
    static Mgr02 INSTANCE = null;

    static Mgr02 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr02.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Mgr02();
                }
            }
        }
        return INSTANCE;
    }
}
```

* 使用静态内部类只加载一次的特性实现单例

``` java
public class Mgr03 {
    private Mgr03() {
    }

    private static class Mgr03Inner {
        static Mgr03 INSTANCE = new Mgr03();
    }

    static Mgr03 getInstance() {
        return Mgr03Inner.INSTANCE;
    }

}
```

* 使用枚举实现单例

``` java
public enum Mgr04 {
    INSTANCE;
    
    private User user;
    
    private Mgr04() {
        user = new User();
    }
    
    private User getUser() {
        return user;
    }
    
    static User getInstance() {
        return Mgr04.INSTANCE.getInstance();
    }
}
```


