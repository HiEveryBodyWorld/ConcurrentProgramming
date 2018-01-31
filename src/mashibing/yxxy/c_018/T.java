package mashibing.yxxy.c_018;
/*
 * 不要以字符串常量作为锁定对象
 * 下面例子中s1和s2锁定的是同一个对象
 */
public class T {

	String s1 = "Hello";
	String s2 = "Hello";
	void m1(){
		synchronized(s1){
			
		}
	}
	void m2(){
		synchronized(s2){//s2与s1是同一个对象
			
		}
	}
}
