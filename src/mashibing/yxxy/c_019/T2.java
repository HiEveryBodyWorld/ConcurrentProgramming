package mashibing.yxxy.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * wait()会释放锁，notify不会释放锁
 */
public class T2 {
	List<Object> lists = new ArrayList<Object>();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		T t = new T();
		final Object lock = new Object();

		new Thread(() -> {
			synchronized (lock) {
				if (t.size() != 5) {
					try {
						lock.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("t2 结束");
			}
		}, "t2").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Thread(() -> {
			synchronized (lock) {
				System.out.println("t1启动");
				for (int i = 0; i < 10; i++) {
					t.add(new Object());
					System.out.println("add " + i);
					if (t.size() == 5) {
						lock.notify();// 这里不会释放锁所以t2也不会执行
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}, "t1"	).start();

	}
}
