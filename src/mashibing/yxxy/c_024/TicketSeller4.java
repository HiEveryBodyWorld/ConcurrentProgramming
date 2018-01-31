package mashibing.yxxy.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * 并发容器,效率高
 */
public class TicketSeller4 {

	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	static{
		for(int i=0;i<10000;i++){
			tickets.add("票编号："+i);
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				while(true){
					String s = tickets.poll();
					if(s == null){
						break;
					}else{
						System.out.println("销售了--"+s+"线程"+Thread.currentThread().getName());
					}
				}
			}).start();
		}
	}
}
