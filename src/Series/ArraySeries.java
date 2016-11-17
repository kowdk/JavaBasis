package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArraySeries {

	/**
	 * 求两个数组的交集，Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectionI(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) {
        	return new int[0];
        }
        //使用set是为了防止重复元素
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>(); // 交集不包含重复元素，用set
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {
            result[i++] = num;
        }
        return result;
    }
	
	/**
	 * 350. Intersection of Two Arrays II
	 * 返回两个数组的交集，Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectionII(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0) {
			return new int[0];
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<Integer> intersectionList = new ArrayList<Integer>(); // 交集要包含重复元素用list

		// 用hash存储nums1的值，并对重复值计数
		for (int i = 0; i < nums1.length; i++) {
			if (!map.containsKey(nums1[i])) {
				map.put(nums1[i], 1);
			} else {
				map.put(nums1[i], map.get(nums1[i]) + 1);
			}
		}
		// 如果有重复的就添加一个到交集中
		for (int i = 0; i < nums2.length; i++) {
			if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
				intersectionList.add(nums2[i]);
				map.put(nums2[i], map.get(nums2[i]) - 1);
			}
		}
		//输出交集
		int[] nums = new int[intersectionList.size()];
		int k = 0;
		Iterator<Integer> it = intersectionList.iterator();
		while (it.hasNext()) {
			nums[k++] = it.next();
		}
		return nums;
	}
	
	/**
	 * 283. Move Zeroes
	 * [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
	 * 把0移动到一个数组的末尾
	 * @param nums
	 */
    public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		int cnt = 0; // cnt是非零数字的指针
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[cnt] = nums[i];
				cnt++;
			}
		}
		for (int i = cnt; i < nums.length; i++) {
			nums[i] = 0;
		}
    }
    
    /**
     * 283. Move Zeroes
     * [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
     * @param nums
     */
    public void moveZeroesSwap(int[] nums) {
    	if(nums == null || nums.length == 0) {
    		return;
    	}
    	for(int cnt = 0, i = 0; i < nums.length; i++) { // cnt标识非0数字下标，i是nums下标，当前nums[i]不是0，swap
    		if(nums[i] != 0) {
    			swap(nums, i, cnt++);
    		}
    	}
    }
	
    private void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
    
    /**
     * 数组中的元素是0,1,2,乱序,将0,1,2各自聚在一起
     * 75. Sort Colors
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2)
			return;
		int lo = 0, hi = nums.length - 1; // lo是记录0的指针，hi是记录2的指针
		int i = 0;
		while (i <= hi) { // i表示前移的元素
			if (nums[i] == 0) {
				swap(nums, i++, lo++);
			} else if (nums[i] == 2) {
				swap(nums, i, hi--);
			} else {
				i++;
			}
		}
    }
    
    /**
     * 169. Majority Element
     * 找出数组中超过n/2的数字
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int cnt = 1;
        int major = nums[0]; // 指定major和一个计数器
        for(int i = 1; i < nums.length; i++) {
            if(major == nums[i]) {
                cnt++; // 是major，贡献1
            } else {
                cnt--; // 不是major，抵消1
                if(cnt == 0) { // 计数为0，换major
                    major = nums[i];
                    cnt = 1;
                }
            }
        }
        return major;
    }
    
    /**
     * 217. Contains Duplicate
     * 判断一个数组是否包含重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicateHash(int[] nums) {
    	if(nums == null || nums.length == 0)
            return false;
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);
            if(value != null){
                return true;
            }
            else{
                map.put(nums[i], 1);
            }
        }
        return false;
    }
    
    
    public boolean containsDuplicateSort(int[] nums) {
    	if(nums == null || nums.length == 0)
            return false;
    	Arrays.sort(nums);
    	for(int i = 0; i < nums.length - 1; i++) {
    		if(nums[i] == nums[i+1])
    			return true;
    	}
    	return false;
    }
    
    /**
     * 对数组中其他元素相乘，数字本身不参与
     * 238. Product of Array Except Self
     * [1,2,3,4] => [24,12,8,6].
     * O(n) time, O(1) space.
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
		
        // 第一遍从左往右，不乘自己和右边的数字
		int prefix = 1;
		for(int i = 0; i < nums.length; i++) {
			output[i] = prefix;
			prefix *= nums[i];
		}
		
		// 第二遍从右往左，不乘自己和左边的数字
		int suffix = 1;
		for(int i = nums.length-1; i >= 0; i--) {
			output[i] *= suffix;
			suffix *= nums[i];
		}
		
		return output;
    }
    
    /**
     * 152. Maximum Product Subarray
     * 求乘积最大的连续子数组
     * [2,3,-2,4] => 6
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0)
			return 0;
		int max = nums[0], min = nums[0], res = nums[0]; // 由于有负数，因此使用max和min两个标记值来记录
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] >= 0) { // 对nums[i]的正负数分别处理
				 max = Math.max(nums[i], nums[i] * max); // max可能是负数，这样可以归为正nums[i]
				 min = Math.min(nums[i], nums[i] * min); // min是负数就更小的负数，min是正数就使用当前的nums[i]
			} else {
				int tmp = max;
				max = Math.max(nums[i], nums[i] * min); // nums[i]是负数，当前最小值*nums[i]和nums[i]中选择大的
				min = Math.min(nums[i], nums[i] * tmp); // nums[i]是负数，最小值应该是用这个负数乘以当前最大的max
			}
			res = Math.max(res, max); // 每计算完一次就更新乘积最大值
		}
		return res;
    }
    
    /**
     * 顺时针旋转图片
     * 48. Rotate Image
     * 1  2  3      1  4  7      7  4  1
     * 4  5  6  =>  2  5  8  =>  8  5  2
     * 7  8  9		3  6  9      9  6  3
     * @param matrix
     */
    public void rotateImage(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix[0].length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length / 2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix.length - 1 - j];
				matrix[i][matrix.length - 1 - j] = temp;
			}
		}
    }
    
	public static void main(String[] args) {

	}

}
