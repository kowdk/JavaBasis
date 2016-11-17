package Thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * 先执行produce，wait后，produce被挂起
 * consume执行，等待程序输入，程序输入后直到整个synchronized块全部执行完才会调用notify方法唤醒挂起程序 produce继续执行。
 * */

public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				//while循环检测，当队列满的时候需要挂起
				while (list.size() == LIMIT) {
					lock.wait();
				}

				list.add(value);
				//添加一个，至少保证不空，唤醒
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {
		Random random = new Random();

		while (true) {
			synchronized (lock) {
				//while循环检测，当队列空的时候需要挂起
				while (list.size() == 0) {
					lock.wait();
				}

				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				//取完一个，至少可以保证不满，所以唤醒
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}

class Processor3 {
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running...");
			wait();
			System.out.println("resumed...");
		}
	}

	public void consume() throws InterruptedException {

		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);

		synchronized (this) {
			System.out.println("waiting for return key..");
			scanner.nextLine();
			System.out.println("Return key pressed...");
			notify();
			Thread.sleep(5000);
		}
	}
}
