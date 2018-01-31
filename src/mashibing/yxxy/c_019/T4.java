package mashibing.yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T4 {


	List<Object> lists = new ArrayList<Object>();
	public void add(Object o){
		lists.add(o);
	}
	public int size(){
		return lists.size();
	}
	
	public static void main(String[] args) {
		T t = new T();
		CountDownLatch latch = new CountDownLatch(1);
		
		new Thread(()->{
				if(t.size()!=5){
					try {
						latch.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			System.out.println("t2 ½áÊø");
		},"t2").start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		new Thread(()->{
			for(int i=0;i<10;i++){
				t.add(new Object());
				System.out.println("add "+i);
				if(t.size()==5){
					latch.countDown();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"t1").start();
		
		
	}
}
