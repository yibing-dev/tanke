package com.yibing.tank;
/**
* @Author yibing
* @CreateTime 2020年5月31日 下午9:29:34
* @Description 类描述: :资源文件加载类
*/

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	static Properties props = new Properties();

	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object get(String key) {
		if (props == null) {
			return null;
		}
		return props.get(key);
	}
	
	
	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankCount"));
		
	}
}
