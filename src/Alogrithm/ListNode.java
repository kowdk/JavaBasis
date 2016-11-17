package Alogrithm;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	/**
	 * ������ת����
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
	 * �ݹ鷴ת����
	 * @param head
	 * @return
	 */
	public ListNode reverseListRecursive(ListNode head) {
	    return reverseListInt(head, null); // ��null��Ϊprev����ȥ
	}
	private ListNode reverseListInt(ListNode head, ListNode newHead) {
	    if (head == null) // ���head��null���ͷ���prev
	        return newHead;
	    ListNode next = head.next; // �ҵ���һ���ڵ�
	    head.next = newHead; // ��headָ��prev
	    return reverseListInt(next, head); // �ݹ����
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
