package com.yibing.designer.singleton;

/**
 * @createTime 2020��6��1�� ����12:44:54
 * @author yibing
 */
public class Mgr02 {
	public static Mgr02 INSTANCE = null;

	private Mgr02() {
	}

	public static Mgr02 getInstance() {
		if (INSTANCE == null) {
			synchronized (Mgr02.class) {
				if (INSTANCE == null) {
					INSTANCE = new Mgr02();
				}
			}
		}
		return INSTANCE;
	}
}
