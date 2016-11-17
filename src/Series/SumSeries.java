package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SumSeries {

	// map restore num and its count
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public void add(int num) {
		map.put(num, map.containsKey(num) ? map.get(num) + 1 : 1);
	}

	public boolean find(int target) {
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int one = entry.getKey();
			int another = target - one;
			if ((one == another && entry.getValue() > 1) || (one != another)
					&& map.containsKey(another))
				return true;
		}
		return false;
	}

	/**
	 * 1. Two Sum
	 * 找出nums中和值为为target的元素下标，数组未排序，使用hashmap
	 * nums = [2, 7, 11, 15], target = 9
	 * return [0,1], 2 + 7 = 9
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSumArrayUnsorted(int[] nums, int target) {
		int[] res = new int[2];
		if (nums == null || nums.length < 2) {
			return res;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int one = nums[i];
			int two = target - one;
			if (map.containsKey(two)) {
				res[0] = i;
				res[1] = map.get(two);
				break;
			} else {
				map.put(one, i);
			}
		}
		return res;
	}

	/**
	 * 找出数组中两个数字之和是target的下标，数组已排序，使用二分查找
	 * 167. Two Sum II - Input array is sorted
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> twoSumArraySorted(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 2) {
			return list;
		}
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			if (nums[lo] + nums[hi] == target) {
				list.add(Arrays.asList(nums[lo], nums[hi]));
				if(lo < hi && nums[lo] == nums[lo+1]) lo++;
				if(lo < hi && nums[hi] == nums[hi-1]) hi--;
				lo++;
				hi--;
			} else if (nums[lo] + nums[hi] > target) {
				hi--;
			} else {
				lo++;
			}
		}
		return list;
	}

	/**
	 * 给定一个数组，从中找出所有三个数之和是0的
	 * 15. 3Sum
	 * S =  [-1, 0, 1, 2, -1, -4] 
	 *   => [ [-1, 0, 1], [-1, -1, 2] ]
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		// use set to split duplicates
		Set<List<Integer>> set = new HashSet<List<Integer>>(); 
		if (nums == null || nums.length < 3) {
			return new ArrayList<List<Integer>>();
		}
		// sort the array
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int twoSum = 0 - nums[i];
			int lo = i + 1, hi = nums.length - 1;
			while (lo < hi) { 
				if (nums[lo] + nums[hi] == twoSum) {
					// set防止重复-1, 0, 1, 2, -1, -4会导致-1,0,1和-1,0,1
					set.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
					while (lo < hi && nums[lo] == nums[lo + 1]) 
						lo++;
					while (lo < hi && nums[hi] == nums[hi - 1])
						hi--;
					lo++;
					hi--;
				} else if (nums[lo] + nums[hi] > twoSum) {
					hi--;
				} else {
					lo++;
				}
			}
		}
		return new ArrayList<List<Integer>>(set);
	}

	/**
	 * 从数组中找出使得nums[i] + nums[j] + nums[k] < target的这类三个数的个数, 其中0<=i<j<k<n.
	 * 259. 3Sum Smaller
	 * nums = [-2, 0, 1, 3], and target = 2.
	 * return 2 cause [-2, 0, 1], [-2, 0, 3] 两组小于2
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumSmaller(int[] nums, int target) {
		int cnt = 0;
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			int lo = i + 1, hi = nums.length - 1;
			while (lo < hi) {
				if (nums[i] + nums[lo] + nums[hi] >= target) {
					hi--;
				} else {
					cnt += (hi - lo); // nums[hi] + nums[lo] 已经比target小了,比nums[hi]小的一定小于target
					lo++;
				}
			}
		}
		return cnt;
	}

	/**
	 * 16. 3Sum Closest
	 * 找到数组中三个数字之和与target最接近的值
	 * S = {-1 2 1 -4}, and target = 1.
	 * -1 + 2 + 1 = 2
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3)
			return 0;

		Arrays.sort(nums);
		int res = nums[0] + nums[1] + nums[2];
		for (int i = 0; i < nums.length - 2; i++) {
			int lo = i + 1, hi = nums.length - 1;
			while (lo < hi) {
				int curr = nums[i] + nums[lo] + nums[hi];
				if (curr == target) {
					res = target;
					break;
				} else if (curr >= target) {
					hi--;
				} else {
					lo++;
				}
				res = Math.abs(target - res) > Math.abs(target - curr) ? curr
						: res;
			}
		}
		return res;
	}

	/**
	 * 数组中四个数字之和为target，找出所有的四个数
	 * 18. 4Sum
	 * S = [1, 0, -1, 0, -2, 2], and target = 0.
	 * [-1,  0, 0, 1], [-2, -1, 1, 2], [-2,  0, 0, 2]
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Set<List<Integer>> set = new HashSet<List<Integer>>();

		// sort the array
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 3; i++) {
			for (int j = i + 1; j < nums.length - 2; j++) {
				int twoSum = target - nums[i] - nums[j];
				int lo = j + 1, hi = nums.length - 1;
				while (lo < hi) {
					if (nums[lo] + nums[hi] == twoSum) {
						set.add(Arrays.asList(nums[i], nums[j], nums[lo],
								nums[hi]));
						while (lo < hi && nums[lo] == nums[lo + 1])
							lo++;
						while (lo < hi && nums[hi] == nums[hi - 1])
							hi--;
						lo++;
						hi--;
					} else if (nums[lo] + nums[hi] > twoSum) {
						hi--;
					} else {
						lo++;
					}
				}
			}
		}

		res.addAll(set);
		return res;
	}
	
	public static void main(String[] args) {
		SumSeries tester = new SumSeries();
		int[] nums = new int[] {-4, -1, -1, 0, 1, 2};
		int target = 2;
		// System.out.println(Arrays.toString(tester.twoSum(nums, target)));
		// System.out.println(tester.threeSum(nums));
		// System.out.println(tester.fourSum(nums, target));
		System.out.println(tester.twoSumArraySorted(nums, target));
		// tester.add(2); tester.add(7); tester.add(11); tester.add(15);
		// System.out.println(tester.find(target));
		// System.out.println(tester.threeSumSmaller(nums, target));
		// System.out.println(tester.threeSumClosest(nums, target));
		// System.out.println(tester.summaryRanges(nums));
		
	}

}
