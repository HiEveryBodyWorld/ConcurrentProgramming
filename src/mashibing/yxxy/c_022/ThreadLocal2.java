package mashibing.yxxy.c_022;

import java.util.concurrent.TimeUnit;

/*
 * ThreadLocal ��ʹ�ÿռ任ʱ�䣬synchronized��ʹ��ʱ�任�ռ�
 * ������hibernate��session�ʹ�����ThreadLocal�У�����synchronized��ʹ��
 * 
 * 
 */
public class ThreadLocal2 {

	static ThreadLocal<Person> tl = new ThreadLocal<>();
	
	public static void main(String[] args){
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(tl.get());
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tl.set(new Person());
		}).start();
	}
	
	static class Person{
		String name="zhangsan";
	}
}
