package Series;

import Alogrithm.ListNode;

public class RemoveSeries {

	/**
	 * 27. Remove Element
	 * 将nums中的与val相等的元素全部删除
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {
        int cnt = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] != val) {
				nums[cnt] = nums[i];
				cnt++;
			}
		}
		return cnt; 
    }
	
	/**
	 * 删除有序数组中的重复元素，比如1-2-2-3-3-4变成1-2-3-4
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums){
		if(nums == null || nums.length == 0) {
			return 0;
		}
		
		int cnt = 1;
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] > nums[i-1]) { // i比前面的大，cnt++
				nums[cnt++] = nums[i];
			}
		}
		
		return cnt;
	}
	
	/**
	 * 每一个数字最多允许出现2次，删除多于两次的数字
	 * @param nums
	 * @return
	 */
	public int removeDuplicatesII(int[] nums) {
		if(nums == null || nums.length <= 2){
			return nums.length;
		}
		
		int cnt = 1, repeat = 0;
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] == nums[i-1] && repeat < 1) {
				nums[cnt++] = nums[i];
				repeat++; // 在相等的情况下可以累加一次
			} else if(nums[i] > nums[i-1]){
				nums[cnt++] = nums[i];
				repeat = 0; // 进入下一个数字重复数置0
			}
		}
		return cnt;
	}
	
	/**
	 * 在链表中删除指定元素，链表头可能被删除，因此需要假头
	 * 1-2-3-4,3 变成 1-2-4 
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElements(ListNode head, int val) {
		if(head == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy, q = head;
		while(q != null) {
			if(q.val == val) {
				p.next = q.next;
				q = q.next;
			} else {
				p = p.next;
				q = q.next;
			}
		}
		return dummy.next;
	}
	
	/**
	 * 删除链表中的重复元素,使重复元素剩下一个
	 * 1-2-3-3-4-4-5 => 1-2-3-4-5
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null) {
			return head;
		}
		
		ListNode p = head, q = head.next;
		while(q != null) {
			if(p.val == q.val) {
				p.next = q.next;
				q = q.next;
			} else {
				p = p.next;
				q = q.next;
			}
		}
		
		return head;
	}
	
	/**
	 * 删除链表中的重复元素
	 * 1-2-3-3-4-4-5 => 1-2-5
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicatesII(ListNode head) {
		if(head == null)
			return head;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy, q = head;
		while(q != null && q.next != null) {
			if(q.val != q.next.val) { // 只要涉及到取值就需要进行保护
				p = p.next;
				q = q.next;
			} else {
				int tmp = q.val;
				while(q != null && tmp == q.val) {
					q = q.next;
				}
				p.next = q;
			}
		}
		
		return dummy.next;
	}
	
	public static void main(String[] args) {
		RemoveSeries t = new RemoveSeries();
		int[] nums = new int[]{1,1,2,3,3,4};
		/*System.out.println(t.removeDuplicates(nums));
		System.out.println(Arrays.toString(nums));*/
		ListNode head = ListNode.createList(new int[]{1,2,3,3,3});
		ListNode newHead = t.deleteDuplicatesII(head);
		ListNode.printList(newHead);
	}

}
