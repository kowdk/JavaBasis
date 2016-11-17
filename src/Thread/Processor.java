package Thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * ��ִ��produce��wait��produce������
 * consumeִ�У��ȴ��������룬���������ֱ������synchronized��ȫ��ִ����Ż����notify�������ѹ������ produce����ִ�С�
 * */

public class Processor {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				//whileѭ����⣬����������ʱ����Ҫ����
				while (list.size() == LIMIT) {
					lock.wait();
				}

				list.add(value);
				//���һ�������ٱ�֤���գ�����
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {
		Random random = new Random();

		while (true) {
			synchronized (lock) {
				//whileѭ����⣬�����пյ�ʱ����Ҫ����
				while (list.size() == 0) {
					lock.wait();
				}

				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				//ȡ��һ�������ٿ��Ա�֤���������Ի���
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
