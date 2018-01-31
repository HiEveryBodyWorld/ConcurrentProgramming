package mashibing.yxxy.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * 固定线程池容量
 */
public class ThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		for(int i=0;i<6;i++){
			service.execute(()->{
				try {
					TimeUnit.MICROSECONDS.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(service);
		service.shutdown();
	}

}
