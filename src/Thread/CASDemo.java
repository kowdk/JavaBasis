package Thread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyRunner implements Runnable {

	private Counter counter;
	public MyRunner(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			counter.increment();
		}
	}

}

public class CASDemo {
	public static void main(String[] args) throws IOException {
		final int nThreads = 5;
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		//Counter counter = CounterFactory.getCounter(new SynCounterFactory());
		Counter counter = CounterFactory.getCounter(new SynCounterFactory());

		long start = System.currentTimeMillis();
		System.out.println("Starting...");
		for (int i = 0; i < nThreads; i++) {
			threadPool.submit(new MyRunner(counter));
		}

		Map<Integer, String> map = new ConcurrentHashMap<Integer, String>();
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		
		threadPool.shutdown();

		try {
			threadPool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("Counter == " + counter.getValue());
		System.out.println("Completed. Time taken : " + (end - start));
		
		/*Selector selector = Selector.open();
		
		Socket socket = new Socket("172.16.18.9", 8008);
		SocketChannel channel =  socket.getChannel();
		
		channel.configureBlocking(false);
		
		SelectionKey key = channel.register(selector, SelectionKey.OP_READ);*/

		
	}
}
