package com.yibing.tank.mgr;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.yibing.Utils.ImageUtil;

/**
 * @Author yibing
 * @CreateTime 2020年5月30日 上午10:00:43
 * @Description 类描述: 图片资源管理类
 */
public class ResourceMgr {
	private ResourceMgr() {}

	private static class ResourceMgrInner {
		private static ResourceMgr INSTANCE = new ResourceMgr();
	}

	public static ResourceMgr getInstance() {
		return ResourceMgrInner.INSTANCE;
	}

	// 坦克icon
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
	public static BufferedImage badTankL, badTankU, badTankR, badTankD;
	// 子弹icon
	public static BufferedImage bulletL, bulletU, bulletR;
	public static BufferedImage bulletD;
	// 定义一个ClassLoader，避免每一次都获取一次
	static ClassLoader classLoader = null;

	public static BufferedImage[] explodes = new BufferedImage[16];
	// 定义静态代码块，在加载本类的时候回自动加载到内存里
	static {
		try {
			classLoader = ResourceMgr.class.getClassLoader();
			// 加载坦克icon
			goodTankU = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);

			badTankU = ImageIO.read(classLoader.getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);

			// 加载子弹icon
			bulletU = ImageIO.read(classLoader.getResourceAsStream("images/bulletU.gif"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
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
