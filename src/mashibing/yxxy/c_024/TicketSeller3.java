package mashibing.yxxy.c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {
	static List<String> tickets = new ArrayList<>();
	static{
		for(int i=0;i<10000;i++){
			tickets.add("票编号："+i);
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				while(true){
					synchronized(tickets){
						if(tickets.size()<=0){
							break;
						}
						try {
							TimeUnit.MICROSECONDS.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
						System.out.println("销售了--"+tickets.remove(0)+"  线程"+Thread.currentThread().getName());
					}
				}
					
			}).start();
		}
	}
}
