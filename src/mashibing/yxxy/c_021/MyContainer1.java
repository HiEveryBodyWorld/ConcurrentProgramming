package mashibing.yxxy.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/*
 * дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 * 
 * ʹ��wait��notify/notifyAll��ʵ��
 */
public class MyContainer1<T> {
	
	/*
	 * 1.ArrayList��ʵ���˻��ڶ�̬��������ݽṹ��LinkedList������������ݽṹ��
	 * 2.�����������get��set��ArrayList��������LinkedList����ΪLinkedListҪ�ƶ�ָ�롣
	 * 3.����������ɾ������add��remove��LinedList�Ƚ�ռ���ƣ���ΪArrayListҪ�ƶ����ݡ�
	 */
	final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;
	private int count = 0;
	
	
	public synchronized void put(T t){
		while(lists.size() == MAX){//Ϊʲô��while����if
			/*��������߳�ͬʱ��wait�����ĳһʱ��container�п������������ˣ��������̻߳ᱻͬʱ���ѣ��������һ���߳������һ��object֮��
			container�����Ѿ����ˣ�����һ���߳������object����������while�Ļ����߳���wait������֮�󻹻����ִ��whileѭ������if�򲻻�*/
			try {
				this.wait();
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lists.add(t);
		++count;
		this.notifyAll();//֪ͨ�������߳̽�������
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
		this.notifyAll();//֪ͨ�����߽�������
		return t;
	}
	
	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<String>();
		//�����������߳�
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
		//�����������߳�
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
