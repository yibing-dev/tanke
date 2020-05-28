# 坦克大战游戏
介绍：写这个小游戏的目的是为了练习一下常用的设计模式，这里会以时间线的方式记录一些在开发过程中遇到的问题，功能正在一步步完善中... ...
* Java小游戏
* Java设计模式

## 2020/5/29 | 需要注意在遍历(forech)集合的时候，是不允许有删除操作的，会报并发操作异常
* 比如使用forech遍历一个ArrayList的时候，如果其他操作里有remove操作，就会报异常()

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
