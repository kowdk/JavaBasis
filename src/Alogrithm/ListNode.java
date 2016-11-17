package Alogrithm;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	/**
	 * 迭代反转链表
	 * @param head
	 * @return
	 */
	public static ListNode reverseListIterative(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode curr = head.next;
			head.next = prev;
			prev = head;
			head = curr;
		}
		return prev;
	}

	/**
	 * 递归反转链表
	 * @param head
	 * @return
	 */
	public ListNode reverseListRecursive(ListNode head) {
	    return reverseListInt(head, null); // 把null作为prev传进去
	}
	private ListNode reverseListInt(ListNode head, ListNode newHead) {
	    if (head == null) // 如果head是null，就返回prev
	        return newHead;
	    ListNode next = head.next; // 找到下一个节点
	    head.next = newHead; // 将head指向prev
	    return reverseListInt(next, head); // 递归调用
	}
	
	public static ListNode createList(int[] vals) {
		ListNode head = new ListNode(0);
		ListNode p = head;
		for (int i = 0; i < vals.length; i++) {
			ListNode l = new ListNode(vals[i]);
			p.next = l;
			p = p.next;
		}
		return head.next;
	}

	public static void printList(ListNode l) {
		ListNode p = l;
		while (p != null) {
			System.out.print(p.val);
			if (p.next != null)
				System.out.print("->");
			p = p.next;
		}
		System.out.println();
	}
}
