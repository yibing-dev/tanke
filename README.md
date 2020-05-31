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





## 2020/5/29
 
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
## 2020/5/30

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
* 调整坦克随机改变方向的概率

##### 开发要点记录：

* 调整坦克随机改变方向的概率

``` java
Dir.values()[random.nextInt(4)];
```




