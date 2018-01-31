package mashibing.yxxy.c_013;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 */
public class T {

	volatile int count=0;
	void m(){
		for(int i=0;i<10000;i++){
			count++;
		}
	}
	
	public static void main(String[] args) {
		T t = new T();
		List<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<10;i++){
			threads.add(new Thread(t::m,"thread-"+i));
		}
		threads.forEach((o)->o.start());
		threads.forEach((o)->{
			try {
				o.join();//使所有thread执行完成之后在执行主线程main
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		System.out.println(t.count);
	}
}
