package mashibing.yxxy.c_008;

import java.util.concurrent.TimeUnit;

/*
 * 对业务写方法枷锁
 * 对读方法不加锁
 * 容易产生脏读问题（dirtyRead）
 */
public class T {

	String name;
	double balance;
	public synchronized void set(String name,double balance){
		this.name = name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}
	public double getBalance(){
		return this.balance;
	}
	
	public static void main(String[] args) {
		T t = new T();
		new Thread(()->t.set("zs",10.0),"t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getBalance());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getBalance());
	}
}
