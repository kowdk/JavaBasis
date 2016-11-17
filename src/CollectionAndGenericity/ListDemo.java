package CollectionAndGenericity;

import java.util.LinkedList;

public class ListDemo {
	public static void actAsStack(LinkedList<String> list) {
		list.push("1");
		list.push("2");
		System.out.println(list);
		System.out.println(list.pop());
		System.out.println(list);
	}

	public static void actAsQueue(LinkedList<String> list) {
		list.offer("1");
		list.offer("2");
		System.out.println(list.peek());
		list.poll();
		System.out.println(list);
	}

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		actAsQueue(list);
	}
}
