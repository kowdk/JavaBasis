package Alogrithm;

import java.util.Arrays;

public class Search {

	/**
	 * 二分查找经典程式
	 * @param nums
	 * @param des
	 * @return
	 */
	public int binarySearch(int[] nums, int target) {
		int lo = 0;
		int hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (target == nums[mid]) {
				return mid;
			} else if (target < nums[mid]) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return -1;
	}
	
	public int hIndex(int[] citations) {
		if (citations.length == 0)
			return 0;
		Arrays.sort(citations);
		int n = citations.length;
		int i = 0;
		while (i < n && citations[i] < (n - i))
			i++;
		return n - i;
	}

	public int hIndexBinarySearch(int[] citations) {
		Arrays.sort(citations);
		int n = citations.length;
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (citations[mid] < n - mid) {
				lo = mid + 1;
			} else if (citations[mid] > n - mid) {
				hi = mid - 1;
			} else {
				return citations[mid];
			}
		}
		return n - (hi + 1);
	}
	
	public static void main(String[] args) {
		Search t = new Search();
		
		int[] citations = new int[]{0,1,4,5,6};
		//System.out.println(t.hIndex(citations));
		//System.out.println(t.hIndexBinarySearch(citations));
		System.out.println(t.binarySearch(citations, -1));
	}
}
