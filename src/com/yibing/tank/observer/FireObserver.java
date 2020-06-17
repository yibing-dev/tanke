package com.yibing.tank.observer;

import com.yibing.tank.gamemodel.GameModel;
import com.yibing.tank.strategy.FireStrategyFactory;

/**
 * @Author yibing
 * @CreateTime 2020年6月17日 上午7:40:15
 * @Description 类描述:
 */
public class FireObserver extends Observer {

	public FireObserver(GameModel gameMdoel) {
		this.gameMdoel = gameMdoel;
	}

	@Override
	public void todo() {
		this.gameMdoel.getMainTank().fire(FireStrategyFactory.getStrategy("goodFireStrategy"));
	}

}
