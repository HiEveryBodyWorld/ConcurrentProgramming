package mashibing.yxxy.c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 有n张火车票，每张火车票都有一个编号
 * 同时有10个窗口对外售票
 */
public class TicketSeller1 {

	static List<String> tickets = new ArrayList<>();
	static{
		for(int i=0;i<10000;i++){
			tickets.add("票编号："+i);
		}
	}
	
	public static void main(String[] args) {
//		ReentrantLock lock = new ReentrantLock();
		for(int i=0;i<10;i++){
			new Thread(()->{
				synchronized(tickets){
					while(tickets.size()>0){
						System.out.println("销售了--"+tickets.remove(0)+"  线程"+Thread.currentThread().getName());
					}
				}
//				lock.lock();
				
//				lock.unlock();
			}).start();
		}
	}
}
