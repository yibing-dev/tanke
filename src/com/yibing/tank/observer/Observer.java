package com.yibing.tank.observer;

import java.awt.event.KeyEvent;

import com.yibing.tank.gamemodel.GameModel;

/**
* @Author yibing
* @CreateTime 2020年6月17日 上午7:39:04
* @Description 类描述:
*/
public abstract class Observer {
	public GameModel gameMdoel;
	
	public abstract void todo();
}
