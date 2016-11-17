package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * semaphore 信号量
 * 1. To guard a critical section against entry by more than N threads at a time.
 * 信号量起到的是锁的作用
 * 2. To send signals between two threads.
 * 在两个线程之间传递信号，用于信号同步
 * */
public class SemaphoreDemo {
	private static final int SEM_MAX = 10;

	/**
	 * 信号量semaphore的许可总数是10个；共3个线程，分别需要获取的信号量许可数是5,4,7。
	 * 前面两个线程获取到信号量的许可后，semaphore中剩余的可用的许可数是1；
	 * 因此，最后一个线程必须等前两个线程释放了它们所持有的信号量许可之后，才能获取到7个信号量许可。
	 * */
	public static void main(String[] args) {
		
		Semaphore sem = new Semaphore(SEM_MAX);

		// 创建线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(3);

		System.out.println("Starting...");
		// 在线程池中执行任务
		threadPool.execute(new MyThreadInSemaphore(sem, 5));
		threadPool.execute(new MyThreadInSemaphore(sem, 4));
		threadPool.execute(new MyThreadInSemaphore(sem, 7));

		// 关闭池
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
	private volatile Semaphore sem; // 信号量
	private int count = 0; // 申请信号量的大小

	MyThreadInSemaphore(Semaphore sem, int count) {
		this.sem = sem;
		this.count = count;
	}

	public void run() {
		try {
			// 从信号量中获取count个许可
			sem.acquire(count);

			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName()
					+ " acquire count = " + count);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放给定数目的许可，将其返回到信号量。
			sem.release(count);
			System.out.println(Thread.currentThread().getName() + " release "
					+ count + "");
		}
	}
}
