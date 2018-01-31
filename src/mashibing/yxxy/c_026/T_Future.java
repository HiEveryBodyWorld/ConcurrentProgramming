package mashibing.yxxy.c_026;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T_Future {

	public static void main(String[] args) throws Exception, ExecutionException {
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		});
		new Thread(task).start();
		
		System.out.println(task.get());//����
		
		//******************************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		Future<Integer> f = service.submit(()->{
			TimeUnit.MICROSECONDS.sleep(500);
			return 1;
		});
		
		System.out.println(f.isDone());
		System.out.println(f.get());//��ȡֵ֮��isDone��Ϊtrue
		System.out.println(f.isDone());
	}
	
}
