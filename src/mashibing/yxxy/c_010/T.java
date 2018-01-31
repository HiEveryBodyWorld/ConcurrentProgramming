package mashibing.yxxy.c_010;

import java.util.concurrent.TimeUnit;

/*
 * һ��ͬ���������Ե�������һ��ͬ��������һ���߳��Ѿ�ӵ��ĳ������������ٴ������ʱ����Ȼ��õ��ö��������
 * Ҳ����˵synchronized��õ����ǿ������
 * ����̳����п��ܷ��������Σ�������ø����ͬ������
 */
public class T {

	synchronized void m(){
		System.out.println("m start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}
	public static void main(String[] args) {
		new TT().m();//��TT�ϵ���
	}
}

class TT extends T{
	@Override
	synchronized void m(){
		System.out.println("child m start");
		super.m();
		System.out.println("child m end");
	}
}
