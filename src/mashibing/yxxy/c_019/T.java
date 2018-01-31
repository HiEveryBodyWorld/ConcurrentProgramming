package mashibing.yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * 实现一个容器提供两个方法，add size
 * 写两个线程，线程1加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束,
 * 
 * t2线程死循环浪费cpu,volatile还不够精确
 */
public class T {

	volatile List<Object> lists = new ArrayList<Object>();
	public void add(Object o){
		lists.add(o);
	}
	public int size(){
		return lists.size();
	}
	
	public static void main(String[] args) {
		T t = new T();
		new Thread(()->{
			for(int i=0;i<10;i++){
				t.add(new Object());
				System.out.println("add "+i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"t1").start();
		
		new Thread(()->{
			while(true){
				if(t.size()==5){
					break;
				}
			}
			System.out.println("t2 结束");
		},"t2").start();
	}
}
