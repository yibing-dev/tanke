package com.yibing.designer.singleton;

/**
 * @createTime 2020��6��1�� ����12:50:05
 * @author yibing
 */
public class Mgr03 {
	
	private Mgr03() {}

	/**
	 * 
	 * ��̬�ڲ��಻������������ص�ʱ����أ�ֻ�����õ���ʱ��Ż����
	 * JVM������ֻ����һ�Σ����Ҳ��֤���̰߳�ȫ
	 *
	 */
	private static class Mgr02Hodler {
		private static final Mgr03 INSTANCE = new Mgr03();
	}

	public static Mgr03 getInstance() {
		return Mgr02Hodler.INSTANCE;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Thread a = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Mgr03.getInstance().hashCode());
					
				}
			});
			a.start();
		}
	}
}
