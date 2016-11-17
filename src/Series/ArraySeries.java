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
	 * ����������Ľ�����Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectionI(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) {
        	return new int[0];
        }
        //ʹ��set��Ϊ�˷�ֹ�ظ�Ԫ��
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>(); // �����������ظ�Ԫ�أ���set
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
	 * ������������Ľ�����Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectionII(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0) {
			return new int[0];
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<Integer> intersectionList = new ArrayList<Integer>(); // ����Ҫ�����ظ�Ԫ����list

		// ��hash�洢nums1��ֵ�������ظ�ֵ����
		for (int i = 0; i < nums1.length; i++) {
			if (!map.containsKey(nums1[i])) {
				map.put(nums1[i], 1);
			} else {
				map.put(nums1[i], map.get(nums1[i]) + 1);
			}
		}
		// ������ظ��ľ����һ����������
		for (int i = 0; i < nums2.length; i++) {
			if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
				intersectionList.add(nums2[i]);
				map.put(nums2[i], map.get(nums2[i]) - 1);
			}
		}
		//�������
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
	 * ��0�ƶ���һ�������ĩβ
	 * @param nums
	 */
    public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		int cnt = 0; // cnt�Ƿ������ֵ�ָ��
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
    	for(int cnt = 0, i = 0; i < nums.length; i++) { // cnt��ʶ��0�����±꣬i��nums�±꣬��ǰnums[i]����0��swap
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
     * �����е�Ԫ����0,1,2,����,��0,1,2���Ծ���һ��
     * 75. Sort Colors
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2)
			return;
		int lo = 0, hi = nums.length - 1; // lo�Ǽ�¼0��ָ�룬hi�Ǽ�¼2��ָ��
		int i = 0;
		while (i <= hi) { // i��ʾǰ�Ƶ�Ԫ��
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
     * �ҳ������г���n/2������
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int cnt = 1;
        int major = nums[0]; // ָ��major��һ��������
        for(int i = 1; i < nums.length; i++) {
            if(major == nums[i]) {
                cnt++; // ��major������1
            } else {
                cnt--; // ����major������1
                if(cnt == 0) { // ����Ϊ0����major
                    major = nums[i];
                    cnt = 1;
                }
            }
        }
        return major;
    }
    
    /**
     * 217. Contains Duplicate
     * �ж�һ�������Ƿ�����ظ�Ԫ��
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
     * ������������Ԫ����ˣ����ֱ�������
     * 238. Product of Array Except Self
     * [1,2,3,4] => [24,12,8,6].
     * O(n) time, O(1) space.
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
		
        // ��һ��������ң������Լ����ұߵ�����
		int prefix = 1;
		for(int i = 0; i < nums.length; i++) {
			output[i] = prefix;
			prefix *= nums[i];
		}
		
		// �ڶ���������󣬲����Լ�����ߵ�����
		int suffix = 1;
		for(int i = nums.length-1; i >= 0; i--) {
			output[i] *= suffix;
			suffix *= nums[i];
		}
		
		return output;
    }
    
    /**
     * 152. Maximum Product Subarray
     * ��˻���������������
     * [2,3,-2,4] => 6
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0)
			return 0;
		int max = nums[0], min = nums[0], res = nums[0]; // �����и��������ʹ��max��min�������ֵ����¼
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] >= 0) { // ��nums[i]���������ֱ���
				 max = Math.max(nums[i], nums[i] * max); // max�����Ǹ������������Թ�Ϊ��nums[i]
				 min = Math.min(nums[i], nums[i] * min); // min�Ǹ����͸�С�ĸ�����min��������ʹ�õ�ǰ��nums[i]
			} else {
				int tmp = max;
				max = Math.max(nums[i], nums[i] * min); // nums[i]�Ǹ�������ǰ��Сֵ*nums[i]��nums[i]��ѡ����
				min = Math.min(nums[i], nums[i] * tmp); // nums[i]�Ǹ�������СֵӦ����������������Ե�ǰ����max
			}
			res = Math.max(res, max); // ÿ������һ�ξ͸��³˻����ֵ
		}
		return res;
    }
    
    /**
     * ˳ʱ����תͼƬ
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
