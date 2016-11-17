package Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor1 implements Runnable {
	private CountDownLatch latch;

	public Processor1(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		System.out.println("Started...");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 当前线程调用此方法，则计数减一
			latch.countDown();
		}
	}
}

/**
 * CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * */
public class CountDownDemo {

	public static void main(String[] args) {

		final int LATCH_SIZE = 3;

		// CountDownLatch构造方法参数指定了计数的次数LATCH_SIZE
		CountDownLatch latch = new CountDownLatch(LATCH_SIZE);

		// newFixedThreadPool表示建立最多LATCH_SIZE个线程的线程池
		ExecutorService executor = Executors.newFixedThreadPool(LATCH_SIZE);

		for (int i = 0; i < LATCH_SIZE; i++) {
			executor.submit(new Processor1(latch));
		}
		executor.shutdown();

		try {
			// 调用此方法会一直阻塞当前线程，直到计时器的值为0
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Completed...");
	}

}
