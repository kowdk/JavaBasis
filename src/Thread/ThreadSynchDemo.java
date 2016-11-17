package Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSynchDemo {
	/*
	 * �ж���߳���Ҫ����ͬ�Ķ���������ݵĹ������ʱ���������̶߳������˸ı����״̬�ķ��������ݿ��ܻᷢ����ʴ�� 
	 * ���������Ϊ����������race condition
	 */
	private static final int accounts = 10;
	private static final int initBalance = 1000;

	public static void main(String[] args) {
		Bank b = new Bank(accounts, initBalance);
		for (int i = 0; i < accounts; i++) {
			TransferRunnable r = new TransferRunnable(b, i, initBalance);
			Thread t = new Thread(r);
			t.start();
		}
	}
}

class Bank {
	private final int[] accounts;
	private final int balance;

	public Bank(int n, int initBalance) {
		accounts = new int[n];
		balance = initBalance;
		for (int i = 0; i < accounts.length; i++)
			accounts[i] = initBalance;
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}

	public int getTotalBalance(){
		int sum = 0;
		for (int i = 0; i < accounts.length; i++)
			sum += accounts[i];
		return sum;
	}
	
	public synchronized int getSyncTotalBalance() {
		//bankLock.lock();
		//try{
			int sum = 0;
			for (int i = 0; i < accounts.length; i++)
				sum += accounts[i];
			return sum;
		//}
		/*finally
		{
			bankLock.unlock();
		}*/
	}

	public int getSize() {
		return accounts.length;
	}

	public int getMaxAmount() {
		return balance;
	}

	public void transfer(int from, int to, int amount) {
		if (accounts[from] < amount)
			return;
		System.out.print(Thread.currentThread() + "  ");
		//+-����䲻��ԭ�Ӳ��������п��ܱ������߳�Ĩȥ����
		accounts[from] -= amount;
		//������Ӿ�����ʴ�ķ���
		System.out.printf(" %d from %d to %d", from, to, amount);
		accounts[to] += amount;
		System.out.printf(" Total Balance: %d%n", getTotalBalance());
	}
	
	/*
	 * ʹ���߳������ٽ�������������ÿһ��rank����ӵ��һ��Lock������������߳���ͼ����ͬһ��Bank�������ͻᴮ�з��ʡ�
	 * ÿһ��������ά��һ�����м���׷��lock��Ƕ�׵���
	 * 
	 * ��������һ���߳��ڻ���ٽ����󣬱���ȴ�ĳ��������������ִ�С�
	 * �����������awaitʱ����ǰ�̱߳����������ҷ���������ϣ�������߳̿��԰��˻�����������������ˣ�Ȼ����߳̽����˵ȴ����������У�
	 * Ȼ���������̵߳���signalAllʱ�������������״̬��Ȼ��ȴ������ٽ�����
	 * ��await�ĵ��ó�����ѭ���С�
	 * */
	private Lock bankLock; //�߳���
	//��д��
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private Lock readLock = rwl.readLock();
	private Lock writeLock = rwl.writeLock();
	private Condition sufficientFunds; //��������
	
	/*
	 * �������̱߳�wait��������״̬,û���߳���Ϊ��Щ�߳�notifyAll��ʱ��ͻ����������
	 * */
	
	/*
	 * javaΪÿһ�������ṩһ����ʽ��������synchronized�ؼ�����������������ͻᱣ����������
	 * ��ʽ������ֻ����һ������������wait�������̼߳ӵ��ȴ����У�notifyAll��������ȴ��̵߳�����״̬
	 * ������ʽ�������Ͳ�Ҫʹ��Lock��condition
	 * */
	public synchronized void syncTransfer(int from, int to, int amount) throws InterruptedException
	{
		//bankLock.lock();
		//try{
			while(accounts[from] < amount)
				//sufficientFunds.await();
				wait();
			
			System.out.print(Thread.currentThread() + "  ");
			accounts[from] -= amount;
			System.out.printf(" %d from %d to %d", from, to, amount);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %d%n", getSyncTotalBalance());
		
			notifyAll();
		/*sufficientFunds.signalAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			bankLock.unlock();
		}*/	
		//}
	}
		
}

class TransferRunnable implements Runnable {
	private Bank bank;
	private int fromAccount;

	public TransferRunnable(Bank b, int from, int max) {
		bank = b;
		fromAccount = from;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			int toAccount = (int) (bank.getSize() * Math.random());
			int amount = (int) ((bank.getMaxAmount()+1000) * Math.random());
			try {
				bank.syncTransfer(fromAccount, toAccount, amount);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep((int) (10 * Math.random()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}