package mashibing.yxxy.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/*
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 * 使用wait和notify/notifyAll来实现
 */
public class MyContainer1<T> {
	
	/*
	 * 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
	 * 2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
	 * 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
	 */
	final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;
	private int count = 0;
	
	
	public synchronized void put(T t){
		while(lists.size() == MAX){//为什么用while不用if
			/*如果两个线程同时在wait，如果某一时刻container中可以生产对象了，这两个线程会被同时唤醒，但是如果一个线程添加了一个object之后，
			container容量已经满了，另外一个线程在添加object会出错，如果用while的话，线程在wait被唤醒之后还会继续执行while循环，而if则不会*/
			try {
				this.wait();
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lists.add(t);
		++count;
		this.notifyAll();//通知消费者线程进行消费
	}
	
	public synchronized T get(){
		T t = null;
		while(lists.size() == 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t= lists.removeFirst();
		count--;
		this.notifyAll();//通知生产者进行生产
		return t;
	}
	
	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<String>();
		//启动消费者线程
		for(int i =0;i<10;i++){
			new Thread(()->{
				for(int j =0;j<5;j++){
					System.out.println(c.get());
				}
			},"c"+i).start();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//启动生产者线程
		for(int i=0;i<2;i++){
			new Thread(()->{
				for(int j=0;j<25;j++){
					c.put(Thread.currentThread().getName()+" "+j);
				}
			},"p"+i).start();
		}
		
	}
	
	
	
	
	
	/*public static void main(String[] args) {
		boolean r = true;
		int i =0;
		while(r){
			System.out.println(i++);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(i==10){
				r = false;
			}
		}
		System.out.println("while range");
	}*/
}
