package mashibing.yxxy.c_012;

import java.util.concurrent.TimeUnit;

/*
 * volatile �ؼ��֣�ʹһ�������ڶ���̼߳�ɼ�
 * A B�̶߳��õ�һ��������javaĬ����A�߳��б���һ��copy���������B�߳��޸��˸ñ�������A�߳�δ��֪��ʹ��volatile�ؼ��֣�
 * ���������̶߳�������������޸�ֵ
 * 
 * 
 * ������Ĵ����У�running �Ǵ����ڶ��ڴ��t������
 * ���߳�t1��ʼ���е�ʱ�򣬻��runningֵ���ڴ��ж���t1�̵߳Ĺ������������й�����ֱ��ʹ�����copy��
 * ������ÿ�ζ�ȥ��ȡ���ڴ棬���������߳��޸�running��ֵ֮��t1�̸߳�֪���������Բ���ֹͣ����
 * 
 * ʹ��volatile������ǿ�������̶߳�ȥ���ڴ��ж�ȡrunning��ֵ
 * 
 * volatile�����ܱ�֤ԭ���ԣ�ֻ�ܱ�֤�ɼ���
 * 
 */
public class T {

	volatile boolean running = true;
	void m(){
		System.out.println("m start");
		while(running){
		}
		System.out.println("m end!");
	}
	public static void main(String[] args) {
		T t = new T();
		new Thread(t::m,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.running = false;
	}
}
