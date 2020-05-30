package com.yibing.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @Author yibing
 * @CreateTime 2020年5月30日 上午10:00:43
 * @Description 类描述: 图片资源管理类
 */
public class ResourceMgr {
	// 坦克icon
	static BufferedImage tankL, tankU, tankR, tankD;
	// 子弹icon
	static BufferedImage bulletL, bulletU, bulletR, bulletD;
	// 定义一个ClassLoader，避免每一次都获取一次
	static ClassLoader classLoader = null;

	public static BufferedImage[] explodes = new BufferedImage[16];
	// 定义静态代码块，在加载本类的时候回自动加载到内存里
	static {
		try {
			classLoader = ResourceMgr.class.getClassLoader();
			// 加载坦克icon
			tankL = ImageIO.read(classLoader.getResourceAsStream("images/tankL.gif"));
			tankR = ImageIO.read(classLoader.getResourceAsStream("images/tankR.gif"));
			tankU = ImageIO.read(classLoader.getResourceAsStream("images/tankU.gif"));
			tankD = ImageIO.read(classLoader.getResourceAsStream("images/tankD.gif"));
			// 加载子弹icon
			bulletL = ImageIO.read(classLoader.getResourceAsStream("images/bulletL.gif"));
			bulletU = ImageIO.read(classLoader.getResourceAsStream("images/bulletU.gif"));
			bulletR = ImageIO.read(classLoader.getResourceAsStream("images/bulletR.gif"));
			bulletD = ImageIO.read(classLoader.getResourceAsStream("images/bulletD.gif"));
			// 加载爆炸图片
			for (int i = 0; i < explodes.length; i++) {
				int num = i + 1;
				explodes[i] = ImageIO.read(classLoader.getResourceAsStream("images/e" + num + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
