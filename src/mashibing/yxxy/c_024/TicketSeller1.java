package mashibing.yxxy.c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ��n�Ż�Ʊ��ÿ�Ż�Ʊ����һ�����
 * ͬʱ��10�����ڶ�����Ʊ
 */
public class TicketSeller1 {

	static List<String> tickets = new ArrayList<>();
	static{
		for(int i=0;i<10000;i++){
			tickets.add("Ʊ��ţ�"+i);
		}
	}
	
	public static void main(String[] args) {
//		ReentrantLock lock = new ReentrantLock();
		for(int i=0;i<10;i++){
			new Thread(()->{
				synchronized(tickets){
					while(tickets.size()>0){
						System.out.println("������--"+tickets.remove(0)+"  �߳�"+Thread.currentThread().getName());
					}
				}
//				lock.lock();
				
//				lock.unlock();
			}).start();
		}
	}
}
