package mashibing.yxxy.c_018;
/*
 * ��Ҫ���ַ���������Ϊ��������
 * ����������s1��s2��������ͬһ������
 */
public class T {

	String s1 = "Hello";
	String s2 = "Hello";
	void m1(){
		synchronized(s1){
			
		}
	}
	void m2(){
		synchronized(s2){//s2��s1��ͬһ������
			
		}
	}
}
