package Series;

import Alogrithm.ListNode;

public class ReverseSeries {

	/**
	 * 
	 * @param n
	 * @return
	 */
	public int reverseBits(int n) {
        int res = 0;
        
        for(int i = 0; i < 31; i++) {
        	res += n & 1; // mask是1
        	n = n >>> 1;
        	res = res << 1;
        }
        res += n & 1; // 最后一位res不能左移了
        
        return res;
    }
	
	/**
	 * 反转一个整形数
	 * @param x
	 * @return
	 */
	public int reverseInteger(int x) {
		int flag = (x > 0) ? 1 : -1;
		x = Math.abs(x);
		long res = 0L;
		while (x > 0) {
			res *= 10;
			res += x % 10;
			x /= 10;
		}

		return res > Integer.MAX_VALUE ? 0 : (int) (res * flag);
	}

	/**
	 * 反转一个链表，注意三个指针，反转结束后prev指向头
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
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
	 * 在指定的两个位置之间反转链表
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if(head == null || head.next == null)
			return head;
		
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy, q = head;
		
		// p points before head, q points after tail.
		for(int i = 1; i < n+1; i++) {
			if(i < m){
				p = p.next;
			}
			q = q.next;
		}
		
		// prev一开始指向q，这样最后反转链表的结尾元素就指向q了.prev指向null，那么反转列表的结尾元素就指向null，而prev最终指向反转链表头
		ListNode prev = q, newHead = p.next;
		while(newHead != q) {
			ListNode curr = newHead.next;
			newHead.next = prev;
			prev = newHead;
			newHead = curr;
		}
		// now prev is the newHead.
		p.next = prev;
		return dummy.next;
	}
	
	/**
	 * 反转字符串中的每个单词 
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		// exception handling
		if(s == null || s.length() == 0)
			return s;
		
		// 先反转整个字符串
		// hello world
		char[] cs = s.toCharArray();
		reverseChars(cs, 0, cs.length-1);
		// dlrow olleh
		
		int lo = 0, hi = 0;
		// 然后再依据空格反转每个字符串
		for(int i = 0; i < cs.length; i++) {
			if(cs[i] != ' '){
				cs[hi++] = cs[i];
			} else if(i >= 1 && cs[i-1] != ' ') {
				reverseChars(cs, lo, hi-1); // hi is next to " "
				cs[hi++] = ' ';
				lo = hi; // lo跟踪的是每个单词的开头，而hi跟踪的是每个单词的结尾的空格
			}
		}
		reverseChars(cs, lo, hi-1);
		return new String(cs, 0, cs[hi-1]==' ' ? hi-1 : hi);
	}
	
	private char[] reverseChars(char[] cs, int lo, int hi) {
		while(lo < hi) {
			swap(cs, lo++, hi--);
		}
		return cs;
	}
	
	private void swap(char[] cs, int i, int j){
		char tmp = cs[i];
		cs[i] = cs[j];
		cs[j] = tmp;
	}
	
	/**
	 * 反转元音字母
	 * @param s
	 * @return
	 */
	public String reverseVowels(String s) {
		if (s == null)
			return null;
		int lo = 0, hi = s.length() - 1;
		char[] cs = s.toCharArray();
		while (lo < hi) {
			while (lo < hi && !isVowels(cs[lo])) {
				lo++;
			}
			while (lo < hi && !isVowels(cs[hi])) {
				hi--;
			}
			/*char c = cs[lo];
			cs[lo++] = cs[hi];
			cs[hi--] = c;*/
			swap(cs, lo++, hi--);
		}

		return new String(cs);
	}

	private boolean isVowels(char c) {
		char[] vs = { 'a', 'e', 'i', 'o', 'u' };
		for (char i : vs) {
			if (c == i || c == i - 32) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		ReverseSeries tester = new ReverseSeries();
		//tester.reverseInteger(-3214);
		/*ListNode head = ListNode.createList(new int[]{1,2,3,4,5});
		ListNode.printList(head);
		ListNode newHead = tester.reverseBetween(head, 2, 4);
		ListNode.printList(newHead);*/
		
		System.out.println(tester.reverseBits(43261596));
	}

}
