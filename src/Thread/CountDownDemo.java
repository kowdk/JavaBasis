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
			// ��ǰ�̵߳��ô˷������������һ
			latch.countDown();
		}
	}
}

/**
 * CountDownLatch��һ��ͬ�������࣬�����һ�����������߳���ִ�еĲ���֮ǰ��������һ�������߳�һֱ�ȴ���
 * */
public class CountDownDemo {

	public static void main(String[] args) {

		final int LATCH_SIZE = 3;

		// CountDownLatch���췽������ָ���˼����Ĵ���LATCH_SIZE
		CountDownLatch latch = new CountDownLatch(LATCH_SIZE);

		// newFixedThreadPool��ʾ�������LATCH_SIZE���̵߳��̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(LATCH_SIZE);

		for (int i = 0; i < LATCH_SIZE; i++) {
			executor.submit(new Processor1(latch));
		}
		executor.shutdown();

		try {
			// ���ô˷�����һֱ������ǰ�̣߳�ֱ����ʱ����ֵΪ0
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Completed...");
	}

}
