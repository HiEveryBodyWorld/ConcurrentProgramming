package mashibing.yxxy.c_001;
/*
 * synchronized 关键字
 * 对某个对象枷锁
 * @author darren
 */
public class T {

	private int count =10;
	
	private Object o = new Object();
	public void m(){
		synchronized(o){
			count--;
			System.out.println(Thread.currentThread().getName()+" count = "+count);
		}
	}
	
}
