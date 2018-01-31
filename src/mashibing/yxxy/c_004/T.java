package mashibing.yxxy.c_004;

public class T {

	private static int count=10;
	public synchronized static void m(){//这里等同于synchronized(T.class)
		count--;
		System.out.println("");
	}
	
	public void mm(){
		synchronized(T.class){
			count--;
		}
	}
}
