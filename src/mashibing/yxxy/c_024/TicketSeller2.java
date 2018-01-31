package mashibing.yxxy.c_024;

import java.util.Vector;import java.util.concurrent.TimeUnit;


public class TicketSeller2 {

	static Vector<String> tickets = new Vector<>();
	static{
		for(int i=0;i<10000;i++){
			tickets.add("票编号："+i);
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				while(tickets.size()>0){
					try {
						TimeUnit.MICROSECONDS.sleep(10);//size remove虽然是原子的但是中间的代码不能保证原子性
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("销售了--"+tickets.remove(0)+"  线程"+Thread.currentThread().getName());
				}
			}).start();
		}
	}
}
