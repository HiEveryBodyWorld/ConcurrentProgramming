package mashibing.yxxy.c_022;

import java.util.concurrent.TimeUnit;

/*
 * ThreadLocal 是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
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
