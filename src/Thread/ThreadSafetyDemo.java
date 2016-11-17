package Thread;

/*
 * java���̰߳�ȫ����ȷ�������ڶ��̵߳Ļ����¿�����ȷ���У������ж��ַ�ʽ��ȷ�����̰߳�ȫ��
 * 1. Synchronization
 * 2. concurrent.atomic
 * 3. concurrent.locks Lock��+Condition ��������
 * 4. thread safe collection classes
 * 5. ��volatile�ؼ���ȷ�������̶߳����ڴ��ж�ȡ���ݶ������̻߳���.
 * */

public class ThreadSafetyDemo {
	
	/*
	 * JVM��֤��ֻ��һ���߳�������synchronized�ؼ��ֵķ����������ڵ����߳�������
	 * ����ʵ��synchronized���synchronized����
	 * ���������Synchronized�Ļ�������ס�˶��󣬶�������static�Ļ��ͻ���ס����class
	 * synchronized����ֻ��ͬһ��JVM��Ч�����jvm֮��ʧȥЧ��
	 * synchronized���ܻᵼ������
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