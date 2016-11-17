package Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

class CountTask extends RecursiveTask<Integer>{

	private static final int THRESHOLD = 2;
	private int start = -0;
	private int end = -0;
	
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		
		boolean canCompute = (end - start) <= THRESHOLD;
		if(canCompute) {
			for(int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int mid = (start + end) / 2;
			CountTask leftTask = new CountTask(start, mid);
			CountTask rightTask = new CountTask(mid+1, end);
			
			leftTask.fork();
			rightTask.fork();
			
			int lo = leftTask.join();
			int hi = rightTask.join();
			sum = lo + hi;
		}
		
		return sum;
	}
	
}
public class ForkJoinDemo {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		CountTask task = new CountTask(1, 4);
		Future<Integer> res = pool.submit(task);
		try {
			System.out.println(res.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
