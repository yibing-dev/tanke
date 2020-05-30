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
	static BufferedImage tankL, tankU, tankR, tankD;
	// 定义静态代码块，在加载本类的时候回自动加载到内存里
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
