package com.yibing.Utils;

import java.util.Random;

import com.yibing.tank.Dir;

/**
 * @Author yibing
 * @CreateTime 2020年5月30日 下午7:11:25
 * @Description 类描述:
 */
public class Util {
	public static Dir getRandomDir() {
		Random random = new Random();
		int val = random.nextInt(4);
		if (val == 1) {
			return Dir.LEFT;
		} else if (val == 2) {
			return Dir.RIGHT;
		} else if (val == 3) {
			return Dir.UP;
		} else {
			return Dir.DOWN;
		}
	}
}
