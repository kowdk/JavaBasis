package Thread;

/*
 * 
 * */

public class ThreadDemo {
	/*
	 * runnable接口更适合实现更多的功能，而继承Thread只是想新开一个线程
	 * Runnable没有返回值，如果需要返回值需要callable接口
	 * */
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new HeavyWorkRunnable(), "t1");
        Thread t2 = new Thread(new HeavyWorkRunnable(), "t2");
        System.out.println("Starting Runnable threads");
        t1.start();
        t2.start();
        System.out.println("Runnable Threads has been started");
        Thread t3 = new MyThread("t3");
        Thread t4 = new MyThread("t4");
        System.out.println("Starting MyThreads");
        try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        t3.start();
        t4.start();
        System.out.println("MyThreads has been started");
	}
}

class MyThread extends Thread {

	public MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		System.out.println("MyThread - START "
				+ Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
			// Get database connection, delete unused data from DB
			doDBProcessing();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out
				.println("MyThread - END " + Thread.currentThread().getName());
	}

	private void doDBProcessing() throws InterruptedException {
		Thread.sleep(1000);
	}

}

class HeavyWorkRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Doing heavy processing - START "
				+ Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
			// Get database connection, delete unused data from DB
			doDBProcessing();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Doing heavy processing - END "
				+ Thread.currentThread().getName());
	}

	private void doDBProcessing() throws InterruptedException {
		Thread.sleep(1000);
	}

}
