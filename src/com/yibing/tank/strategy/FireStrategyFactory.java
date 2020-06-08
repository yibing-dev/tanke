package com.yibing.tank.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yibing
 * @CreateTime 2020年6月4日 上午7:47:02
 * @Description 类描述:开火策略工厂类
 */
public class FireStrategyFactory {
	private static final Map<String, FireStrategy> strategies = new HashMap<>();

	static {
		strategies.put("goodFireStrategy", FourDirFireStrategy.getInstance());
		strategies.put("badFireStrategy", DefaultFireStrategy.getInstance());
	}

	public static FireStrategy getStrategy(String type) {
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type should not be empty.");
		}
		return strategies.get(type);
	}
}
