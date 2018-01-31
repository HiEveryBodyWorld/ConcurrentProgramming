package mashibing.yxxy.c_023;

import java.util.Arrays;

/*
 * 线程安全的单例模式
 * 更好的是采用下面的方式，既不用枷锁，也能实现懒加载
 * 
 * 内部实现类
 * 
 * https://www.cnblogs.com/xudong-bupt/p/3433643.html
 */
public class Singleton {
	
	 /* 
	  * “延迟加载”，我们可以在第一次使用的时候才初始化第一个该类对象
	  * 
	  * 
	  * public static synchronized Singleton getInstance(){    //对获取实例的方法进行同步
	       if (instance == null)     
	         instance = new Singleton(); 
	       return instance;
	     }
	     
	     这个锁的粒度有点大 ，改进就是只锁住其中的new语句就OK。就是所谓的“双重锁”机制
	
	 private static Singleton instance;  
     private Singleton (){
     }   
     public static Singleton getInstance(){    //对获取实例的方法进行同步
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
		Arrays.asList(ths).forEach(o->o.start());//只返回一个single对象
	}
}
