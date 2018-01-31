package mashibing.yxxy.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;import java.util.concurrent.TimeUnit;


/**
 * �����������ߵ�����ʵ��
 * @author Administrator
 *
 */
public class T05_LinkedBlockingQueue {

	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
	static Random r = new Random();
	
	public static void main(String[] args) {
		//һ���������߳�
		new Thread(()->{
			for(int i=0;i<100;i++){
				try {
					strs.put("a"+i);//������ˣ��ͻ�ȴ�
					TimeUnit.MICROSECONDS.sleep(r.nextInt(1000));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"p1").start();
		//5���������߳�
		for(int i=0;i<5;i++){
			new Thread(()->{
				try{
					for(;;){
						System.out.println(Thread.currentThread().getName()+"take - "+strs.take());
					}
				}catch(Exception e){
				}
			},"c"+i).start();
		}
		
		
	}
	
}
