package com.yibing.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @createTime 2020年5月29日 下午12:47:24
 * @author yibing
 */
public class ImageTest {
	public static void main(String[] args) {
		// BufferImage 从名字就可以看出来是读到缓存里的
		try {
			// 把图片加载到内存里
			// BufferedImage bufferImage = ImageIO.read(new
			// File("C:\\Users\\admin\\Desktop\\tank.jpg"));

			// 通过该classLoader把图片加载到内存里
			BufferedImage image = ImageIO
					.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			System.out.println(image.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
