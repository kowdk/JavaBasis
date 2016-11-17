package Series;

import Alogrithm.ListNode;

public class RotateSeries {

	/** 
	 * ������ת����
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode rotateList(ListNode head, int k) {
		if (head == null)
			return head;

		// constrain k into 0-len.
		int len = listLen(head);
		k = k % len;
		if (k == 0)
			return head;

		// find new head using fast-slow pointers
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode p = dummy, q = head;

		// qָ������k��
		for (int i = 0; i < k; i++) {
			q = q.next;
		}
		// pqͬʱǰ�ƣ�pӦ�û�ָ�����ת�������һ���ڵ�
		while (q != null) {
			p = p.next;
			q = q.next;
		}

		// now p is before newHead..
		// System.out.println(p.val);
		ListNode newHead = p.next;
		// set p.next to null
		p.next = null;

		// find rotate tail
		q = newHead;
		while (q.next != null) {
			q = q.next;
		}

		// now q is tail of rotate list
		// System.out.println(q.val);
		q.next = head;
		return newHead;
	}

	/** 
	 * �����б���
	 * @param head
	 * @return
	 */
	public int listLen(ListNode head) {
		if (head == null)
			return 0;
		int len = 0;
		ListNode p = head;
		while (p != null) {
			len++;
			p = p.next;
		}
		return len;
	}

	/**
	 * ������ת����
	 * @param nums
	 * @param k
	 */
	public void rotateArray(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return;
		k = k % nums.length;
		// 1 2 3 4 5 k = 2
		reverse(nums, 0, nums.length - k - 1);
		// 2 1 3 4 5
		reverse(nums, nums.length - k, nums.length - 1);
		// 2 1 5 4 3
		reverse(nums, 0, nums.length - 1);
		// 3 4 5 1 2 
	}

	private void reverse(int[] nums, int lo, int hi) {
		while (lo < hi) {
			int tmp = nums[lo];
			nums[lo++] = nums[hi];
			nums[hi--] = tmp;
		}
	}

	/**
	 * Ѱ��������������С�����֣����鲿�����򣬶��ֲ���
	 * 4 5 6 7 1 2 3
	 * @param nums
	 * @return
	 */
	public int findMinInRotateArray(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int res = 0;
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (mid == lo) { // ��mid==lo��ʱ��˵��lo��hi�Ѿ������ˣ��ͷ��ؽ�Сֵ
				res = Math.min(nums[lo], nums[hi]);
				break;
			}
			if (nums[mid] > nums[hi]) { // min should in the latter
				lo = mid;
			} else { // min should in the former
				hi = mid;
			}
		}
		return res;
	}

	/**
	 * �����ظ�Ԫ�ص�����������Ѱ����С��Ԫ��
	 * @param nums
	 * @return
	 */
	public int findMinInRotateArrayHasDuplicates(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int res = 0;
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (mid == lo) { // lo��hi�Ѿ�����
				res = Math.min(nums[lo], nums[hi]);
				break;
			}
			if (nums[mid] > nums[hi]) { // min should in the latter
				lo = mid;
			} else if (nums[mid] < nums[hi]) { // min should in the former
				hi = mid;
			} else { // ��Ϊ��������ȵ�����޷��ж�minӦ�ô���ǰ���Ǻ�����ֻ��˳�����
				res = degenerateToSequential(nums);
				break;
			}
		}
		return res;
	}

	private int degenerateToSequential(int[] nums) {
		int min = nums[0];
		for (int i = 1; i < nums.length; i++) {
			min = Math.min(min, nums[i]);
		}
		return min;
	}

	/**
	 * �����������в���ָ��Ԫ�أ����ֲ���
	 * 4 5 6 7 1 2 3
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInRotateArray(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[mid] >= nums[lo]) { // lo to mid �ǵ�����
				if (target >= nums[lo] && target < nums[mid]) { // target��lo��mid֮��
					hi = mid - 1;
				} else { // target��mid��hi֮��
					lo = mid + 1;
				}
			}

			if (nums[mid] <= nums[hi]) { // mid to hi �ǵ�����
				if (target > nums[mid] && target <= nums[hi]) { // target��mid��hi֮��
					lo = mid + 1;
				} else { // target��lo��mid֮��
					hi = mid - 1;
				}
			}
		}

		return -1;
	}

	/**
	 * �ڰ����ظ�Ԫ�ص����������в���ָ��Ԫ��
	 * @param nums
	 * @param target
	 * @return
	 */
	public boolean searchInRotateArrayHasDuplicates(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return false;
		}

		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] == target) { // has found in mid
				return true;
			}
			if (nums[lo] == nums[hi]) { // not in order due to duplicates
				return searchInOrder(nums, target); // for [1,1,1,3,1]; mid==lo==hi, we do not know what to do next
			}
			// now rotated array can be split to two sorted arrays
			if (nums[mid] >= nums[lo]) { // target in former
				if(target >= nums[lo] && target < nums[mid]) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			}

			if (nums[mid] <= nums[hi]) { // target in latter
				if (target > nums[mid] && target <= nums[hi]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}

		return false;
	}

	private boolean searchInOrder(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		RotateSeries tester = new RotateSeries();

		
		ListNode head = ListNode.createList(new int[] { 1, 2, 3, 4, 5 }); 
		int k = 2; 
		ListNode.printList(head); 
		ListNode newHead = tester.rotateList(head, k); 
		ListNode.printList(newHead);
		 

		/*
		 * int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7 }; int k = 3;
		 * System.out.println(Arrays.toString(nums)); tester.rotateArray(nums,
		 * k); System.out.println(Arrays.toString(nums));
		 */

		/*int[] nums = new int[] { 0, 1, 2, 4, 5, 6, 7 };
		System.out.println(tester.searchInRotateArray(nums, 5));*/
		
		/*int[] nums = new int[]{4,5,1,2,3};
		int target = 5;
		System.out.println(tester.searchInRotateArrayHasDuplicates(nums, target));*/
	}

}
