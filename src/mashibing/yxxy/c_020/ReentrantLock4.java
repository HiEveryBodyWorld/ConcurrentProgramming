package mashibing.yxxy.c_020;

import java.util.concurrent.locks.ReentrantLock;


/*
 * ReetrantLockָ����ƽ����Ĭ��synchronized�ǲ���ƽ��
 */
public class ReentrantLock4 extends Thread{

	private static ReentrantLock lock = new ReentrantLock(true);//����true��ʾΪ��ƽ������Ա�������
	
	public void run(){
		for(int i = 0;i<10;i++){
			lock.lock();
			try{
				System.out.println(Thread.currentThread().getName()+"�����");
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
