package mashibing.yxxy.c_011;

import java.util.concurrent.TimeUnit;

/*
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 */
public class T {

	int count = 0;
	synchronized void m(){
		System.out.println(Thread.currentThread().getName()+" start");
		while(true){
			count++;
			System.out.println(Thread.currentThread().getName()+" count= "+count);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(count == 5){
				int i=1/0;//此处抛出异常，锁将被释放，可以在这里进行catch，然后让循环继续
			}
		}
	}
	public static void main(String[] args) {
		T t = new T();
		new Thread(()->t.m(),"t").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(()->t.m(),"t2").start();
	}
	
}
