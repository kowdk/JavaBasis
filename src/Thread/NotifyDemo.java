package Thread;

public class NotifyDemo {
	public static void main(String[] args) {
		Message msg = new Message("process it");

		Waiter waiter1 = new Waiter(msg);
		Thread t1 = new Thread(waiter1, "waiter1");
		t1.start();

		Waiter waiter2 = new Waiter(msg);
		new Thread(waiter2, "waiter2").start();

		Notifier notifier = new Notifier(msg);
		Thread t2 = new Thread(notifier, "notifier");
		t2.start();
		System.out.println("All the threads are started");

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------All Done-----------------");
	}
}

class Waiter implements Runnable {

	private Message msg;

	public Waiter(Message m) {
		this.msg = m;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		synchronized (msg) {
			try {
				System.out.println(name + " waiting to get notified at time:"
						+ System.currentTimeMillis());
				msg.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + " waiter thread got notified at time:"
					+ System.currentTimeMillis());
			// process the message now
			System.out.println(name + " processed: " + msg.getMsg());
		}
	}

}

class Notifier implements Runnable {

	private Message msg;

	public Notifier(Message msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started");
		try {
			Thread.sleep(1000);
			synchronized (msg) {
				msg.setMsg(name + " [Notifier msg]");
				// msg.notify();
				msg.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Message {
	private String msg;

	public Message(String str) {
		this.msg = str;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String str) {
		this.msg = str;
	}
}