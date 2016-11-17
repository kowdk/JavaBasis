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
        	res += n & 1; // mask��1
        	n = n >>> 1;
        	res = res << 1;
        }
        res += n & 1; // ���һλres����������
        
        return res;
    }
	
	/**
	 * ��תһ��������
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
	 * ��תһ������ע������ָ�룬��ת������prevָ��ͷ
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
	 * ��ָ��������λ��֮�䷴ת����
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
		
		// prevһ��ʼָ��q���������ת����Ľ�βԪ�ؾ�ָ��q��.prevָ��null����ô��ת�б�Ľ�βԪ�ؾ�ָ��null����prev����ָ��ת����ͷ
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
	 * ��ת�ַ����е�ÿ������ 
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		// exception handling
		if(s == null || s.length() == 0)
			return s;
		
		// �ȷ�ת�����ַ���
		// hello world
		char[] cs = s.toCharArray();
		reverseChars(cs, 0, cs.length-1);
		// dlrow olleh
		
		int lo = 0, hi = 0;
		// Ȼ�������ݿո�תÿ���ַ���
		for(int i = 0; i < cs.length; i++) {
			if(cs[i] != ' '){
				cs[hi++] = cs[i];
			} else if(i >= 1 && cs[i-1] != ' ') {
				reverseChars(cs, lo, hi-1); // hi is next to " "
				cs[hi++] = ' ';
				lo = hi; // lo���ٵ���ÿ�����ʵĿ�ͷ����hi���ٵ���ÿ�����ʵĽ�β�Ŀո�
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
	 * ��תԪ����ĸ
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
