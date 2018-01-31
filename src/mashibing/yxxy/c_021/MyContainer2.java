package mashibing.yxxy.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 */
public class MyContainer2<T> {
	/*
	 * 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
	 * 2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
	 * 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
	 */
	final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;
	private int count = 0;
	
	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
	
	public void put(T t){
		try {
			lock.lock();
			while (lists.size() == MAX) {

				producer.await();

			}
			lists.add(t);
			++count;
			consumer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public T get(){
		T t = null;
		try {
			lock.lock();
			while (lists.size() == 0) {

				consumer.await();
			}
			t = lists.removeFirst();
			count--;
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return t;
	}
	
	public static void main(String[] args) {
		MyContainer2<String> c = new MyContainer2<String>();
		//启动消费者线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for(int j=0;j<5;j++){
					System.out.println(c.get());
				}
			},"c"+i).start();;
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//启动消费者线程
		for(int i=0;i<2;i++){
			new Thread(()->{
				for(int j=0;j<25;j++){
					c.put(Thread.currentThread().getName()+" "+j);
				}
			},"p"+i).start();
		}
		
	}
	
	
}
