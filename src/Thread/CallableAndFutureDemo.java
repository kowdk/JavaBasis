package Thread;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<?> future = executor.submit(new Callable<Void>(){

			@Override
			public Void call() throws Exception {
					
				Random random = new Random();
				int duration = random.nextInt(4000);
								
				if(duration > 2000){
					throw new IOException("Sleeping for too long...");
				}
				
				System.out.println("Starting...");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Finished.");
				
				return null;
			}
		});
		
		executor.shutdown();
		
		try {
			System.out.println("Result is : " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}
	}
}
