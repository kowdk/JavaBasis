package Thread;

/*
 * ����������߳���ѭ���ȴ���Դ������޷�ִ�е�����
 * �������ĸ���Ҫ����������ʹ�á�������ռ����Դֻ�ܹ����������뱣�֣��̱߳���ռ����Դ��ȥ���룩��ѭ���ȴ�����Դ���ڻ�·��cyclic dependency for resource between Threads
 * 
 * 
 * */

/*
 * ��������Ĺ��ߣ�use [jstack] to generate thread dump��
 * ���裺
 * 1. ps -eaf | grep java
 * 2. jstack PID >> deadlock.tdump
 * ���Եõ���
 * Thread Status �̵߳�״̬��Ϣ������Runnable��Waiting��Blocked
 * Thread callstack �̵߳ĵ��ö�ջ
 * */

/*
 * ��������ķ�����
 * 1. ����Ƕ�׵���
 * 2. ֻ����ǰ��Ҫ����Դ
 * 3. �������޵ȴ���ʹ��join���޵ȴ���һ���߳̽������ܻᵼ����������˵�����ȴ���һ���߳̽���ʱ���������join�ȴ����ʱ��
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
