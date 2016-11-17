package Thread;

/*
 * java多线程安全用于确保程序在多线程的环境下可以正确运行，可以有多种方式来确保多线程安全：
 * 1. Synchronization
 * 2. concurrent.atomic
 * 3. concurrent.locks Lock类+Condition 条件对象
 * 4. thread safe collection classes
 * 5. 用volatile关键字确保所有线程都从内存中读取数据而不是线程缓存.
 * */

public class ThreadSafetyDemo {
	
	/*
	 * JVM保证了只有一个线程在运行synchronized关键字的方法，它基于的是线程锁技术
	 * 可以实现synchronized块和synchronized函数
	 * 函数如果是Synchronized的话，他锁住了对象，而函数是static的话就会锁住整个class
	 * synchronized函数只对同一个JVM有效，多个jvm之间失去效果
	 * synchronized可能会导致死锁
	 * */
	public static void main(String[] args){
		while(true){
			ProcessRunnable pr = new ProcessRunnable();
			Thread t1 = new Thread(pr);
			t1.start();
			Thread t2 = new Thread(pr);
			t2.start();
			
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Processing count is: " + pr.getCount());
		}
	}
}

class ProcessRunnable implements Runnable
{
	private int count;
	
	private Object lock = new Object();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(lock){
			for(int i = 1; i < 5; i++)
			{
				processingSth(i);
				count++;
			}
		}
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void processingSth(int i)
	{
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}