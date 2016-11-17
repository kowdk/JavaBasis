package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor2 implements Runnable {

	private int id;

	public Processor2(int id) {
		this.id = id;
	}

	public void run() {
		System.out.println("Starting... " + id);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Completed... " + id);
	}
}

public class ThreadPools {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		long start = System.currentTimeMillis();
		
		for(int i=0; i<5; i++) {
			executor.submit(new Processor2(i));
		}
		
		executor.shutdown();
		
		System.out.println("All tasks submitted.");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		System.out.println("All tasks completed.");
		System.out.println("time token: " + (end - start));
	}
}
