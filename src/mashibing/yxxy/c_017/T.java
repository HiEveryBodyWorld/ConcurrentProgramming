package mashibing.yxxy.c_017;

import java.util.concurrent.TimeUnit;

/*
 * ����ĳ����o,���o�����Է����ı䣬��Ӱ������ʹ�ã�
 * �������oָ��������һ�������������Ķ������ı�
 * Ӧ�ñ��⽫������������ñ������Ķ���
 */
public class T {

	Object o = new Object();
	void m(){
		synchronized(o){
			while(true){
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		T t = new T();
		new Thread(t::m,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread t2 = new Thread(t::m,"t2");
		//t.o = new Object();
		t2.start();
		
	}
}
