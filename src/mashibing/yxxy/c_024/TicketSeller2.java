package mashibing.yxxy.c_024;

import java.util.Vector;import java.util.concurrent.TimeUnit;


public class TicketSeller2 {

	static Vector<String> tickets = new Vector<>();
	static{
		for(int i=0;i<10000;i++){
			tickets.add("Ʊ��ţ�"+i);
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(()->{
				while(tickets.size()>0){
					try {
						TimeUnit.MICROSECONDS.sleep(10);//size remove��Ȼ��ԭ�ӵĵ����м�Ĵ��벻�ܱ�֤ԭ����
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("������--"+tickets.remove(0)+"  �߳�"+Thread.currentThread().getName());
				}
			}).start();
		}
	}
}
