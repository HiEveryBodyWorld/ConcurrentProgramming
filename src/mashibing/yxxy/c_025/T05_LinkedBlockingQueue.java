package mashibing.yxxy.c_025;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;import java.util.concurrent.TimeUnit;


/**
 * 生产者消费者的容器实现
 * @author Administrator
 *
 */
public class T05_LinkedBlockingQueue {

	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
	static Random r = new Random();
	
	public static void main(String[] args) {
		//一个生产者线程
		new Thread(()->{
			for(int i=0;i<100;i++){
				try {
					strs.put("a"+i);//如果满了，就会等待
					TimeUnit.MICROSECONDS.sleep(r.nextInt(1000));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"p1").start();
		//5个消费者线程
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
