package Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** BlockingQueue是阻塞队列
 * 			Throws Exception	Special Value	Blocks	Times Out
 *	Insert	add(o)				offer(o)		put(o)	offer(o, timeout, timeunit)
 *	Remove	remove(o)			poll()			take()	poll(timeout, timeunit)
 *	Examine	element()			peek()	 	
 *  Special Value 可以返回true或者false 
 * */
public class BlockingQueueDemo {

	public static void main(String[] args) {
		
		System.out.println("Starting...");
		BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(1024);

		Producer1 producer = new Producer1(queue);
		Consumer1 consumer = new Consumer1(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished.");
	}

}

class Producer2 implements Runnable {

	protected BlockingQueue<Object> queue;

	public Producer2(BlockingQueue<Object> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			queue.put("1");
			Thread.sleep(1000);
			queue.put("2");
			Thread.sleep(1000);
			queue.put("3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class Consumer2 implements Runnable {

	protected BlockingQueue<Object> queue;

	public Consumer2(BlockingQueue<Object> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
