package Thread;

public class SynchronizeKeywordDemo {

	private int count = 0;
	
	/*java中每一个对象都持有一个锁，这个锁是线程互斥的*/
	private synchronized void countPlus() {
		count++;
	}

	private void dowork() {
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i<10000; i++) {
					countPlus();
				}
			}
		});
		
		t1.start();

		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0; i<10000; i++) {
					countPlus();
				}
			}
		});
		
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count is: " + count);
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		SynchronizeKeywordDemo demo = new SynchronizeKeywordDemo();
		demo.dowork();
		long end = System.currentTimeMillis();
		System.out.println("time span : " + (end - start));
	}
}
