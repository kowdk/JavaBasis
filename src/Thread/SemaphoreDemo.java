package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * semaphore �ź���
 * 1. To guard a critical section against entry by more than N threads at a time.
 * �ź����𵽵�����������
 * 2. To send signals between two threads.
 * �������߳�֮�䴫���źţ������ź�ͬ��
 * */
public class SemaphoreDemo {
	private static final int SEM_MAX = 10;

	/**
	 * �ź���semaphore�����������10������3���̣߳��ֱ���Ҫ��ȡ���ź����������5,4,7��
	 * ǰ�������̻߳�ȡ���ź�������ɺ�semaphore��ʣ��Ŀ��õ��������1��
	 * ��ˣ����һ���̱߳����ǰ�����߳��ͷ������������е��ź������֮�󣬲��ܻ�ȡ��7���ź�����ɡ�
	 * */
	public static void main(String[] args) {
		
		Semaphore sem = new Semaphore(SEM_MAX);

		// �����̳߳�
		ExecutorService threadPool = Executors.newFixedThreadPool(3);

		System.out.println("Starting...");
		// ���̳߳���ִ������
		threadPool.execute(new MyThreadInSemaphore(sem, 5));
		threadPool.execute(new MyThreadInSemaphore(sem, 4));
		threadPool.execute(new MyThreadInSemaphore(sem, 7));

		// �رճ�
		threadPool.shutdown();

		try {
			threadPool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished.");
	}
}

class MyThreadInSemaphore extends Thread {
	private volatile Semaphore sem; // �ź���
	private int count = 0; // �����ź����Ĵ�С

	MyThreadInSemaphore(Semaphore sem, int count) {
		this.sem = sem;
		this.count = count;
	}

	public void run() {
		try {
			// ���ź����л�ȡcount�����
			sem.acquire(count);

			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName()
					+ " acquire count = " + count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// �ͷŸ�����Ŀ����ɣ����䷵�ص��ź�����
			sem.release(count);
			System.out.println(Thread.currentThread().getName() + " release "
					+ count + "");
		}
	}
}
