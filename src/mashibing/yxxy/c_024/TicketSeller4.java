package mashibing.yxxy.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * ��������,Ч�ʸ�
 */
public class TicketSeller4 {

	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	static{
		for(int i=0;i<10000;i++){
			tickets.add("Ʊ��ţ�"+i);
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
						System.out.println("������--"+s+"�߳�"+Thread.currentThread().getName());
					}
				}
			}).start();
		}
	}
}
