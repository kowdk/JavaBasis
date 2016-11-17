package Thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureDemo {
	public static void main(String[] args)
	{
		final String basedir = "G://Experiment";
		final String keyword = "echo";
		
		//FutureTask包装器是一种很方便地将Callable转换成Future和Runnable的机制，callable有返回值，Runnable沒有返回值
		MatchCounter counter = new MatchCounter(new File(basedir), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread t = new Thread(task);
		t.start();
		
		try {
			System.out.println(task.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class MatchCounter implements Callable<Integer>
{
	private int count;
	private File dir = null;
	private String keyword = "";
	
	public MatchCounter(File basedir, String keyword)
	{
		 this.dir = basedir;
		 this.keyword = keyword;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count = 0;
		
		File[] files = dir.listFiles();
		ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
		
		for(File file : files)
		{
			if(file.isDirectory())
			{
				MatchCounter counter = new MatchCounter(file, keyword);
				FutureTask<Integer> task = new FutureTask<Integer>(counter);
				results.add(task);
				Thread t = new Thread(task);
				t.start();
			}
			else
			{
				if(search(file)) count++;
			}
		}
		
		//get方法会阻塞直到所有的FutureTask计算完毕
		for(Future<Integer> res : results)
		{
			count += res.get();
		}
		
		return count;
	}
	
	public boolean search(File file)
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
		
		boolean found = false;
		String str = "";
		while(!found && (str = br.readLine()) != null)
		{
			if(str.contains(keyword))
				found = true;
		}
			br.close();
			return found;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
