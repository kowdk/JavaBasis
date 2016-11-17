package Series;

import java.util.ArrayList;
import java.util.List;

import Alogrithm.ListNode;

public class PalindromeSeries {

	/**
	 * 判断一个整形是否是回文数
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) { // 121
		if(x < 0)
        	return false;
        int res = 0, tmp = x;
        while(x > 0) {
        	res = res * 10 + x % 10;
        	x /= 10;
        }
        return res == tmp ? true : false;
	}

	/**
	 * 125. Valid Palindrome
	 * 判断一个字符串是不是回文字符串
	 * @param s
	 * @return
	 */
	public boolean isPalindrome(String s) {
		if (s == null)
			return false;
		if (s.length() == 0)
			return true;
		int lo = 0, hi = s.length() - 1;
		System.out.println(s.toLowerCase());
		char[] cs = s.toLowerCase().toCharArray();
		while (lo < hi) {
			while (!Character.isLetterOrDigit(cs[lo])) {
				lo++;
			}
			while (!Character.isLetterOrDigit(cs[hi])) {
				hi--;
			}
			if (cs[lo++] != cs[hi--])
				return false;
		}
		return true;
	}

	/**
	 * 234. Palindrome Linked List
	 * 判断一个链表是不是回文链表
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head) {
		// find the middle of list
		if (head == null)
			return false;
		ListNode slow = head, fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// now slow points to the middle
		ListNode tail = reverseList(slow);
		ListNode.printList(head);
		ListNode.printList(tail);
		
		// now compare head and tail
		ListNode p = head, q = tail;
		while (p != null && q != null) {
			if (p.val != q.val)
				return false;
			p = p.next;
			q = q.next;
		}

		return true;
	}

	// reverse list by head
	private ListNode reverseList(ListNode head) {
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
	 * 131. Palindrome Partitioning
	 * 将一个字符串切割，使得其所有子串均为回文串
	 * @param s
	 * @return
	 */
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<List<String>>();
		if(s == null || s.length() == 0)
			return res;
		partitionHelper(res, s, new ArrayList<String>(), 0);
		return res;
	}
	
	private void partitionHelper(List<List<String>> res, String s,
			ArrayList<String> list, int pos) {
		if (pos == s.length()) { // partition string, the string must in the ending
			res.add(new ArrayList<String>(list));
			return;
		}

		for (int i = pos; i < s.length(); i++) { // 需要每次都往前移，因此从pos开始
			String tmp = s.substring(pos, i + 1);
			System.out.println(tmp);
			if (isValidPalindrome(s, pos, i)) { // 需要判断一下子串是否为回文串
				list.add(s.substring(pos, i + 1));
				partitionHelper(res, s, list, i + 1); // 不能重复，因此从i+1向前移动
				list.remove(list.size() - 1);
			}
		}
	}
	
	private boolean isValidPalindrome(String s, int lo, int hi) {
		while(lo < hi) {
			if(s.charAt(lo++) != s.charAt(hi--))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		PalindromeSeries t = new PalindromeSeries();
		// String str = "A man, a plan, a canal: Panama";
		// System.out.println(t.isPalindrome(str));
		//ListNode head = ListNode.createList(new int[] {1,2,3,4,5});
		//System.out.println(t.isPalindrome(head));
		// System.out.println(t.isPalindrome(21132));
		String s = "aac";
		System.out.println(t.partition(s));
	}

}
