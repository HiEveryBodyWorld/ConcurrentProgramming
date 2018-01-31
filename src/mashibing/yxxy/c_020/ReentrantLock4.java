package mashibing.yxxy.c_020;

import java.util.concurrent.locks.ReentrantLock;


/*
 * ReetrantLock指定公平锁，默认synchronized是不公平锁
 */
public class ReentrantLock4 extends Thread{

	private static ReentrantLock lock = new ReentrantLock(true);//参数true表示为公平锁，请对比输出结果
	
	public void run(){
		for(int i = 0;i<10;i++){
			lock.lock();
			try{
				System.out.println(Thread.currentThread().getName()+"获得锁");
			}finally{
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		ReentrantLock4 rl= new ReentrantLock4();
		Thread th1 = new Thread(rl);
		Thread th2 = new Thread(rl);
		th1.start();
		th2.start();
	}
}
