package mashibing.yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * ʵ��һ�������ṩ����������add size
 * д�����̣߳��߳�1��10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������,
 * 
 * t2�߳���ѭ���˷�cpu,volatile��������ȷ
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
			System.out.println("t2 ����");
		},"t2").start();
	}
}
