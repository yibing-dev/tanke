package com.yibing.designer.singleton;

/**
 * @createTime 2020��6��1�� ����12:59:27
 * @author yibing
 */
public class Mgr01 {

	private Mgr01() {}

	static Mgr01 INSTANCE = new Mgr01();

	static Mgr01 getInstance() {
		return INSTANCE;
	}
}
