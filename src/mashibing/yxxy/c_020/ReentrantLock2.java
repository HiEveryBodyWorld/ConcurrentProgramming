package mashibing.yxxy.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ʹ��reentrantlock���Խ��г�������
 */
public class ReentrantLock2 {

	Lock lock = new ReentrantLock();
	void m1(){
		
		try {
			lock.lock();
			for(int i=0;i<10;i++){
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	void m2(){
		/*
		boolean locked = lock.tryLock();//�õ������ò�������ִ��
		System.out.println("m2..."+locked);
		if(locked){
			lock.unlock();
		}*/
		boolean locked=false;
		try {
			locked = lock.tryLock(5,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(locked)lock.unlock();
		}
	}
}
