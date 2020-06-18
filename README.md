# 坦克大战游戏(设计模式版正在更新中......)
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
* 设计模式 
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
* 把资源文件加载类改为单例模式（静态内部类式）
* 把配置文件加载类改为单例模式（静态内部类式）

##### 开发要点记录：
* 单例模式总结： https://github.com/yibing-dev/DesignPatterns/blob/master/%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F.md

``` java
//使用静态内部类只加载一次的特性实现单例
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

## 2020/6/3

##### 功能更新：
* 定义坦克的发火策略，并且把策略实例定义为单例

##### 开发要点记录：

* 策略模式总结：https://github.com/yibing-dev/DesignPatterns/blob/master/%E7%AD%96%E7%95%A5%E6%A8%A1%E5%BC%8F.md 

## 2020/6/4

##### 功能更新：
* 引入策略工厂类

## 2020/6/6

##### 功能更新：
* 定义抽象工厂，产生抽象的坦克，抽象的子弹，抽象的爆炸

##### 开发要点记录：

* 任何可以产生对象的方法或者类，都可以称为工厂
* 单例也是一种工厂
* 为什么有了new之后，还需要有工厂？(为了灵活控制生产过程，权限，修饰，日志...)
    

### 工厂模式的分类

* 简单工厂
* 静态工厂
* 抽象工厂

* 工厂模式总结：https://github.com/yibing-dev/DesignPatterns/blob/master/%E5%B7%A5%E5%8E%82%E6%A8%A1%E5%BC%8F.md

## 2020/6/8

##### 功能更新：

* 引入门面模式，抽象出GameModel，将model和view分离，同时将GameModel作为facade负责与Frame打交道，同时负责内部事务
* TankFrame解决了添加新游戏物体的问题
* TankFrame只做游戏展示，具体内部计算由GameModel负责


##### 开发要点记录：
* 门面模式总结：https://github.com/yibing-dev/DesignPatterns/blob/master/%E9%97%A8%E9%9D%A2%E6%A8%A1%E5%BC%8F.md

## 2020/6/14

##### 功能更新：

*  引入责任链模式
*  将坦克与坦克碰撞，子弹与坦克碰撞，子弹与墙碰撞，坦克与墙碰撞的逻辑分别抽离为各自的碰撞逻辑，并穿成一条链

##### 开发要点记录：
* 责任链模式总结：https://github.com/yibing-dev/DesignPatterns/blob/master/%E8%B4%A3%E4%BB%BB%E9%93%BE%E6%A8%A1%E5%BC%8F.md

## 2020/6/15

##### 功能更新：

*  引入装饰器模式
*  将部分代码重构

##### 开发要点记录：
* 装饰器模式总结：https://github.com/yibing-dev/DesignPatterns/blob/master/%E8%A3%85%E9%A5%B0%E6%A8%A1%E5%BC%8F.md

## 2020/6/17

##### 功能更新：

*  引入观察者模式
* 观察Ctrl按键

##### 开发要点记录：
* 观察者模式总结：https://github.com/yibing-dev/DesignPatterns/blob/master/%E8%A7%82%E5%AF%9F%E8%80%85%E6%A8%A1%E5%BC%8F.md


