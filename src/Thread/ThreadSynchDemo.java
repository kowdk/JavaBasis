package Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSynchDemo {
	/*
	 * 有多个线程需要对相同的对象进行数据的共享访问时，如果多个线程都调用了改变对象状态的方法，数据可能会发生侵蚀。 
	 * 这种情况称为竞争条件，race condition
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
		//+-的语句不是原子操作，很有可能被其他线程抹去数据
		accounts[from] -= amount;
		//输出语句加剧了侵蚀的发生
		System.out.printf(" %d from %d to %d", from, to, amount);
		accounts[to] += amount;
		System.out.printf(" Total Balance: %d%n", getTotalBalance());
	}
	
	/*
	 * 使用线程锁把临界区保护起来，每一个rank对象都拥有一个Lock对象，如果两个线程试图访问同一个Bank对象，锁就会串行访问。
	 * 每一个锁对象都维护一个持有计数追踪lock的嵌套调用
	 * 
	 * 条件对象：一个线程在获得临界区后，必须等待某个条件满足后才能执行。
	 * 条件对象调用await时，当前线程被阻塞，并且放弃了锁，希望其他线程可以把账户余额增加至够给别人，然后该线程进入了等待该条件集中，
	 * 然后有其他线程调用signalAll时，它解除了阻塞状态，然后等待进入临界区。
	 * 对await的调用出现在循环中。
	 * */
	private Lock bankLock; //线程锁
	//读写锁
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private Lock readLock = rwl.readLock();
	private Lock writeLock = rwl.writeLock();
	private Condition sufficientFunds; //条件对象
	
	/*
	 * 当所有线程被wait处于阻塞状态,没有线程来为这些线程notifyAll的时候就会出现死锁。
	 * */
	
	/*
	 * java为每一个对象提供一个隐式的锁，由synchronized关键字声明，对象的锁就会保护整个方法
	 * 隐式对象锁只能有一个关联条件，wait方法把线程加到等待集中，notifyAll方法解除等待线程的阻塞状态
	 * 能用隐式对象锁就不要使用Lock和condition
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