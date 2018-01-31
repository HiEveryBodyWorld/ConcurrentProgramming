package mashibing.yxxy.c_023;

import java.util.Arrays;

/*
 * �̰߳�ȫ�ĵ���ģʽ
 * ���õ��ǲ�������ķ�ʽ���Ȳ��ü�����Ҳ��ʵ��������
 * 
 * �ڲ�ʵ����
 * 
 * https://www.cnblogs.com/xudong-bupt/p/3433643.html
 */
public class Singleton {
	
	 /* 
	  * ���ӳټ��ء������ǿ����ڵ�һ��ʹ�õ�ʱ��ų�ʼ����һ���������
	  * 
	  * 
	  * public static synchronized Singleton getInstance(){    //�Ի�ȡʵ���ķ�������ͬ��
	       if (instance == null)     
	         instance = new Singleton(); 
	       return instance;
	     }
	     
	     ������������е�� ���Ľ�����ֻ��ס���е�new����OK��������ν�ġ�˫����������
	
	 private static Singleton instance;  
     private Singleton (){
     }   
     public static Singleton getInstance(){    //�Ի�ȡʵ���ķ�������ͬ��
       if (instance == null){
           synchronized(Singleton.class){
               if (instance == null)
                   instance = new Singleton(); 
           }
       }
       return instance;
     }
     */
	private Singleton(){
		System.out.println("single");
	}
	private static class Inner{
		private static Singleton s= new Singleton();
	}
	static Singleton getSingle(){
		return Inner.s;
	}
	public static void main(String[] args){
		Thread[] ths = new Thread[200];
		for(int i=0;i<ths.length;i++){
			ths[i] = new Thread(()->{
				Singleton.getSingle();
			});
		}
		Arrays.asList(ths).forEach(o->o.start());//ֻ����һ��single����
	}
}
