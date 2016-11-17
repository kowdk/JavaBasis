package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class MyTimer extends TimerTask{
	private String jobName = "";
	
	public MyTimer(String jobName){
		this.jobName = jobName;
	}
	
	@Override
	public void run() {
		System.out.println("execute " + jobName + "..." + new SimpleDateFormat("HH:mm:ss").format(new Date()));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class MyScheduledExecutor implements Runnable{

	private String jobName = "";
	
	public MyScheduledExecutor(String jobName){
		this.jobName = jobName;
	}
	
	@Override
	public void run() {
		System.out.println("execute " + jobName + "..." + new SimpleDateFormat("HH:mm:ss").format(new Date()));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class TimerTest{

	public static void main(String[] args) {
		//Timer t = new Timer();
		long delay = 1000;
		long period = 1000;
		//t.schedule(new MyTimer("task1"), delay, period);
		
		//ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		long sdelay = 0;
		long speriod = 1;
		//service.scheduleAtFixedRate(new MyScheduledExecutor("task2"), sdelay, speriod, TimeUnit.SECONDS);
		//service.scheduleWithFixedDelay(new MyScheduledExecutor("task2"), sdelay, speriod, TimeUnit.SECONDS);

	}
}
