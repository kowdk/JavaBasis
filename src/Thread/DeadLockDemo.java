package Thread;

/*
 * 死锁：多个线程因循环等待资源而造成无法执行的现象。
 * 死锁的四个必要条件：互斥使用、不可抢占（资源只能够）、请求与保持（线程必须占有资源再去申请）、循环等待（资源存在环路）cyclic dependency for resource between Threads
 * 
 * 
 * */

/*
 * 检测死锁的工具：use [jstack] to generate thread dump：
 * 步骤：
 * 1. ps -eaf | grep java
 * 2. jstack PID >> deadlock.tdump
 * 可以得到：
 * Thread Status 线程的状态信息，比如Runnable、Waiting和Blocked
 * Thread callstack 线程的调用堆栈
 * */

/*
 * 死锁避免的方法：
 * 1. 避免嵌套的锁
 * 2. 只锁当前需要的资源
 * 3. 避免无限等待，使用join无限等待另一个线程结束可能会导致死锁，因此当必须等待另一个线程结束时，最好限制join等待的最长时间
 * */
public class DeadLockDemo {
	public static void main(String[] args) throws InterruptedException {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();

		Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
		Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
		Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");

		t1.start();
		Thread.sleep(5000);
		t2.start();
		Thread.sleep(5000);
		t3.start();
	}
}

class SyncThread implements Runnable {
	private Object obj1;
	private Object obj2;

	public SyncThread(Object o1, Object o2) {
		this.obj1 = o1;
		this.obj2 = o2;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " acquiring lock on " + obj1);
		synchronized (obj1) {
			System.out.println(name + " acquired lock on " + obj1);
			work();
			System.out.println(name + " acquiring lock on " + obj2);
			synchronized (obj2) {
				System.out.println(name + " acquired lock on " + obj2);
				work();
			}
			System.out.println(name + " released lock on " + obj2);
		}
		System.out.println(name + " released lock on " + obj1);
		System.out.println(name + " finished execution.");
	}

	private void work() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
