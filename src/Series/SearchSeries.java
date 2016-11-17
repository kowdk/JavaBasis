package Series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

import Alogrithm.Interval;

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}

public class SearchSeries {
	
	private PriorityQueue<Integer> minHeap = null;
	private PriorityQueue<Integer> maxHeap = null;
	
	/**
	 *  PriorityQueue��Ĭ��ʵ������С�ѣ�������Ҫ����Comparator
	 *  ���������ݷ�Ϊ���Σ����������ά�֣��ұ�����С��ά�֣����Ѻ���С�ѵĲ�ֵ������1
	 */
	public SearchSeries() {
		minHeap = new PriorityQueue<Integer>(); //pqĬ������С��
		maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);// ʵ��һ��Comparator�ӿڱ�Ϊ����
			}
		});
	}

	/**
	 * �����ݽṹ�д洢һ������
	 * @param num
	 */
	public void addNum(int num) {
		if (minHeap.size() == 0 && maxHeap.size() == 0) { // �����Ѷ�Ϊ�գ��嵽�ұ�
			minHeap.offer(num);
		} else if (minHeap.size() > maxHeap.size()) { // ����ұ߶�
			if (num < minHeap.peek()) { // num���ұ���С�Ļ�С���������
				maxHeap.offer(num);
			} else { // num��Ҫ�������ұߣ���˽��ұ���С�Ĳ�����ߣ�Ȼ������ұ�
				maxHeap.offer(minHeap.poll());
				minHeap.offer(num);
			}
		} else if (minHeap.size() < maxHeap.size()) { 
			if (num > minHeap.peek()) {
				minHeap.offer(num);
			} else {
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(num);
			}
		} else { // �����ѵĳ�����ͬ������ֵ����
			if (num > maxHeap.peek()) {
				minHeap.offer(num);
			} else {
				maxHeap.offer(num);
			}
		}
	}

	/**
	 * ȡ�����ݽṹ����λ��
	 * @return
	 */
	public double findMedian() {
		if (maxHeap.size() == 0 && minHeap.size() == 0) {
			return 0.0;
		} else if (maxHeap.size() != minHeap.size()) {
			return maxHeap.size() > minHeap.size() ? (double)(maxHeap.peek()) : (double)(minHeap.peek());
		} else {
			return ((double)(maxHeap.peek() + minHeap.peek())) / 2.0;
		}
	}
	
	/**
	 * �д�����������һ�е�һ������һ�����һ�����������Ķ�ά������Ѱ��Ԫ��
	 * [1,   3,  5,  7],
	 * [10, 11, 16, 20],
	 * [23, 30, 34, 50]
	 * target = 3, return true
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrixI(int[][] matrix, int target) {
        boolean exist = false;
		if(matrix == null || matrix.length == 0) 
			return exist;
		
		int start = 0, end = matrix.length - 1, row = 0;
		// ���ҵ�����һ��
		while(start <= end) {
			int mid = start + (end - start) / 2;
			if(target < matrix[mid][0]) {
				end = mid - 1;
			} else if(target > matrix[mid][0]) {
				start = mid + 1;
			} else {
				row = mid;
				exist = true;
				return exist;
			}
		}
		row = end < 0 ? 0 : end;
		start = 0;
		end = matrix[0].length - 1;
		//�Ը��������ֲ���
		while(start <= end) {
			int mid = start + (end - start) / 2;
			if(target < matrix[row][mid]) {
				end = mid - 1;
			} else if(target > matrix[row][mid]) {
				start = mid + 1;
			} else {
				exist = true;
				break;
			}
		}
		return exist;
    }
	
	
	/**
	 * ���������������������У��ҳ���kС������
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length; // matrix��n*n��
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>(); // ����һ����С��
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j])); // �õ�һ��Ԫ�س�ʼ����С��
        for(int i = 0; i < k-1; i++) { // ѭ��k�Σ��ҳ���kС����
            Tuple t = pq.poll(); // poll������t��֪�������к���
            if(t.x == n-1) continue; // �����һ�У�������������
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y])); //����poll������Ԫ�ص�ͬһ����һ�е�Ԫ�� 
        }
        return pq.poll().val;
    }
    
	/**
	 * ���������������������У��ö��ַ���ȷ��һ��midԪ�أ�Ȼ�����������ҳ�С�ڵ���mid��Ԫ�ظ�����k���
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = getLessEqual(matrix, mid);
            if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
    
	/**
	 * ��matrix���ҳ�С�ڵ���val��Ԫ�ظ���
	 * @param matrix
	 * @param val
	 * @return
	 */
    private int getLessEqual(int[][] matrix, int val) {
        int res = 0;
        int n = matrix.length, i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) i--;
            else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }
	
	/**
	 * �ڶ�ά�����в���Ԫ�أ������е�Ԫ�ش����Һʹ��ϵ��¶���˳������
	 * [1,   4,  7, 11, 15],
	 * [2,   5,  8, 12, 19],
	 * [3,   6,  9, 16, 22],
	 * [10, 13, 14, 17, 24],
	 * [18, 21, 23, 26, 30]
	 * ����Ԫ�ش�������������ģ����������������
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}

		int m = matrix.length, n = matrix[0].length;
		int row = 0, col = n - 1; //�Ӿ�������Ͻǿ�ʼ��
		while (row < m && col >= 0) {
			if (matrix[row][col] > target) { // matrix[row][col]�Ǹ�����С�ģ�targetС�����Ϳ�������
				col--;
			} else if (matrix[row][col] < target) { // matrix[row][col]�Ǹ������ģ�target�������Ϳ�������
				row++;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��������˳ʱ���ӡ�����Ԫ��
	 * [ 1, 2, 3 ],
	 * [ 4, 5, 6 ],
	 * [ 7, 8, 9 ]
	 * [1,2,3,6,9,8,7,4,5]
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<Integer>();
		if (matrix.length == 0 || matrix[0].length == 0)
			return res;
		int rowHi = matrix.length - 1;
		int colHi = matrix[0].length - 1;
		int rowLo = 0;
		int colLo = 0;

		// ά���ĸ�ָ�룬�еĸߵͺ��еĸߵͣ�ÿһ��Ȧѭ���Ĵδ�ӡ��->��->��->��
		while (rowLo <= rowHi && colLo <= colHi) {
			for (int j = colLo; j <= colHi; j++) {
				res.add(matrix[rowLo][j]);
			}
			rowLo++;
			for (int i = rowLo; i <= rowHi; i++) {
				res.add(matrix[i][colHi]);
			}
			colHi--;
			if (rowLo <= rowHi) { // rowLo�Ѿ�++���п���Խ����
				for (int j = colHi; j >= colLo; j--) {
					res.add(matrix[rowHi][j]);
				}
				rowHi--;
			}
			if (colLo <= colHi) { // colHi�Ѿ�--���п���Խ����
				for (int i = rowHi; i >= rowLo; i--) {
					res.add(matrix[i][colLo]);
				}
				colLo++;
			}
		}
		return res;
	}
	
	
	
	/**
	 * �ҳ��������ظ������֣�Ҫ��O(n)ʱ���O(1)�ռ�
	 * ����������������O(nlogn)ʱ�䣬���ʹ��Hash����O(n)ʱ��O(n)�ռ�
	 * @param nums
	 * @return
	 */
	public int findDuplicateUsingSwap(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        // 2 3 1 0 2 5 3
        // 1 3 2 0 2 5 3
        // 3 1 2 0 2 5 3
        // 0 1 2 3 [2] 5 3
        for(int i = 0; i < nums.length; i++) {
            while(nums[i] != i) { // ��ֵ���±겻���
                if(nums[i] == nums[nums[i]]) { // ������ֵ���±��Ӧ����ֵ��ȣ���ǰֵ�����ظ�����
                    return nums[i];
                }
                // �����������Ӧ�±�����ֱ�֤�±��ֵһ��
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }
	
	/**
	 * ��ֵ��Ӧ�±꽫�������鴮����������һ����������ת��Ϊ�ж������Ƿ��л�
	 * @param nums
	 * @return
	 */
	public int findDuplicateUsingTwoPointers(int[] nums) {
		if(nums == null || nums.length == 0) {
            return -1;
        }
		int n = nums.length;
		int slow = n;
	    int fast = n;
	    do{
	        slow = nums[slow-1];
	        fast = nums[nums[fast-1]-1];
	    } while(slow != fast);
	    slow = n;
	    while(slow != fast) {
	        slow = nums[slow-1];
	        fast = nums[fast-1];
	    }
	    return slow;
	}
	
	/**
	 * �ϲ�����
	 * Given [1,3],[2,6],[8,10],[15,18],
	 * return [1,6],[8,10],[15,18].
	 * @param intervals
	 * @return
	 */
	public List<Interval> mergeInterval(List<Interval> intervals) {
		if(intervals.size() <= 1) {
			return intervals;
		}
		
		List<Interval> res = new ArrayList<Interval>();
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});
		
		//System.out.println(intervals);
		// ��ʼ��start��end
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		
		for(int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if(curr.start <= end) { // ���ص�������end
				end = Math.max(end, curr.end);
			} else { 
				res.add(new Interval(start, end)); // û���ص��������º��interval������
				start = curr.start; // ����start��end
				end = curr.end;
			}
		}
		res.add(new Interval(start, end));
		return res;
	}

	/**
	 * ��newInterval���뵽intervals�У������ص���Ҫ���кϲ�
	 * [1,2],[3,5],[6,7],[8,10],[12,16] and [4,9] => [3,5],[6,7],[8,10]
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	public List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        if(intervals == null || newInterval == null) {
        	return intervals;
        }
        
        List<Interval> res = new ArrayList<Interval>();
        int len = intervals.size();
        int i = 0;
        // ������newInterval֮ǰ��Ԫ��
        while(i < len && intervals.get(i).end < newInterval.start) {
        	res.add(intervals.get(i++));
        }
        // ������newInterval�ཻ��Ԫ�飬���ϸ���newInterval
        while(i < len && intervals.get(i).start <= newInterval.end) {
        	newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), 
        			Math.max(intervals.get(i).end, newInterval.end));
        	i++;
        }
        // �����µ�newInterval����
        res.add(newInterval);
        // ������治�����ཻ���ֵ�interval
        while(i < len) {
        	res.add(intervals.get(i++));
        }
        return res;
    }
	
	/**
	 * ��������˳�����
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	private boolean searchInOrder(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target)
				return true;
		}
		return false;
	}

	/**
	 * ������������ظ�Ԫ�ص��������ҳ�ָ��Ԫ�ص���ʼ��ĩβ
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRange(int[] nums, int target) {
		int[] res = new int[2];
		if (nums == null || nums.length < 0)
			return res;
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] == target) { // ���ҵ�Ԫ�ص�ʱ������������
				int i = mid, j = mid;
				while (i >= lo && nums[i] == target)
					i--;
				while (j <= hi && nums[j] == target)
					j++;
				res[0] = i + 1;
				res[1] = j - 1;
				return res;
			} else if (nums[mid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return new int[] { -1, -1 }; // δ�ҵ�
	}

	/**
	 * ���ַ���x��n�η����ݹ�
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		if(n == 0) return 1.0; // �κ�����0�η�����1��0��0�η�û�����壬Ҳ���1
		if(x == 0 && n < 0) return 0.0; // 0�ĸ����η��ᵼ�³�����0,��Ҫ���⴦��
		if(n == Integer.MIN_VALUE) { // ���ָ��Ϊ��С��ֵ��Ӧ�ö����0������1���κδη�����1
			if(Math.abs(x) == 1) {
				return 1.0;
			} else {
				return 0.0;
			}
		}
		// ��������������������ָ���Ǹ������ͷ�תָ������������ĵ���
		if(n < 0) { 
			n = -n;
			x = 1 / x;
		}
		return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2); // �ݹ��󣬷�����ż��
	}
	
	/**
	 * ����x�Ŀ��������ֲ���
	 * @param x
	 * @return
	 */
    public int mySqrt(int x) {
        if(x == 0 || x == 1) return x;
		int lo = 1, hi = x;
		while(lo < hi) {
			int mid = lo + (hi - lo)/2;
			if(mid * mid < x) { // ��mid*mid��x���Ƚ�
				lo = mid + 1;
			} else if(mid * mid == x) {
				return mid;
			} else {
				hi = mid;
			}
		}
		return lo - 1;
    }
    
    /**
     * �ж�һ�����ǲ�����ȫƽ���������ַ�
     * 367. Valid Perfect Square
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1)
			return true;
		int lo = 1, hi = num;
		while (lo < hi) {
			long mid = (hi - lo) / 2 + lo;
			if (mid * mid < num) {
				lo = (int) mid + 1;
			} else if ((mid * mid) > num) {
				hi = (int) mid;
			} else {
				return true;
			}
		}
		return false;
    }
    
    /**
     * �ҳ�һ����������ֵ����Сֵ
     * 
     * @param nums
     * @return
     */
    public int[] findMaxAndMin(int[] nums){
		if(nums == null || nums.length == 0) {
			return new int[]{-1,-1};
		}
		
		int max = 0, min = 0;
		if(nums.length == 1) {
			return new int[]{nums[0], nums[0]};
		}
		min = nums[0] > nums[1] ? nums[1] : nums[0];
		max = nums[0] < nums[1] ? nums[1] : nums[0];
		
		//����������Ϊһ�飬����Ƚϣ�Ȼ���ĺ�max�ȣ�С�ĺ�min�ȣ������Ͱ�2n����Ϊ1.5n
		for(int i = 2; i < nums.length; i+=2){
			if(i+1 < nums.length) {
				min = Math.min(min, Math.min(nums[i], nums[i+1]));
				max = Math.max(max, Math.max(nums[i], nums[i+1]));
			} else {
				min = Math.min(min, nums[i]);
				max = Math.max(max, nums[i]);
			}
		}
		
		return new int[]{max, min};
	}

    /**
     * �ж�һ�������Ƿ����ظ�����
     * @param nums
     * @return
     */
	public boolean containsDuplicate(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			Integer value = map.get(nums[i]);
			if (value != null) {
				return true;
			} else {
				map.put(nums[i], 1);
			}
		}
		return false;
	}
    
	/**
	 * �ж�һ�������Ƿ����nums[i] = nums[j]����i��j�ľ��벻����k
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        //nums[i]��key��i��value
        /*Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;*/
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < nums.length; i++) {
			if(i > k) set.remove(nums[i-k-1]); // ������k��ʱ�򣬵ý���ǰiֵ�Ľ�k����֮ǰ����Ҫ��ɾ��
			if(!set.add(nums[i])) return true; // set��add�����Ӳ���ȥ��˵��set�а����ظ�ֵ
		}
		return false;
    }
	
	/**
	 * �ж�һ�������Ƿ����nums[i]��nums[j]��ֵ������Ϊt,������������Ϊk�� 
	 * @param nums
	 * @param k
	 * @param t
	 * @return
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0 || k <= 0) {
			return false;
		}
		final TreeSet<Integer> set = new TreeSet<>(); // TreeSet�������
		for (int i = 0; i < nums.length; i++) {
			final Integer floor = set.floor(nums[i] + t); // floor��ʾС�ڵ��ڲ��������ֵ
			final Integer ceil = set.ceiling(nums[i] - t); // ceiling��ʾ���ڵ��ڲ�������Сֵ
			// �ҵ���������Ϊt�Ĵ���nums[i]��������С��nums[i]����
			if ((floor != null && floor >= nums[i])
					|| (ceil != null && ceil <= nums[i])) {
				return true;
			}

			set.add(nums[i]);
			if (i >= k) { // ��֤�˴���Ϊk
				set.remove(nums[i - k]);
			}
		}
		return false;
	}
	
	/**
	 * �ж�������û�г���Ϊ���ĵ���������
	 * 334. Increasing Triplet Subsequence
	 * [1, 2, 3, 4, 5] -> true
	 * @param nums
	 * @return
	 */
	public boolean increasingTriplet(int[] nums) { //subsequence not need to be successive
        int lo = Integer.MAX_VALUE, hi = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
        	if(nums[i] <= lo) { // �ҵ���һ������
        		lo = nums[i];
        	} else if(nums[i] <= hi) { // �ҵ��ڶ������֣��ڶ������ֿ϶���lo��
        		hi = nums[i]; 
        	} else { // �ҵ����������֣����������ֿ϶���lo��hi��
        		return true; 
        	}
        }
        return false;
    }
	
	/**
	 * �ҳ����������������λ��
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int nums1[], int nums2[]) {
		int n1 = nums1.length;
	    int n2 = nums2.length;
	    if (n1 < n2) return findMedianSortedArrays(nums2, nums1); // �쳣������֤nums2������
	    if (n2 == 0) return ((double)nums1[(n1-1)/2] + (double)nums1[n1/2])/2;// �쳣�������nums2����Ϊ0
	    
	    int lo = 0, hi = n2 * 2;
	    // ��ʽ��c1+c2==n1+n2��c1��c2�Ƿָ�㣬Ҫ�ҵ�����ù�ʽ��L1��L2��С��R1��R2���������ʱ������#
	    // #, 1, #, 3, #, 5
	    // #, 2, #, 4, #, 6, #, 8
	    while (lo <= hi) {
	        int c2 = (lo + hi) / 2;   // �õ����c2
	        int c1 = n1 + n2 - c2;  // ���ݹ�ʽ�õ����c1
	        
	        // L = (c-1) / 2;
	        // R = (c) / 2;
	        double L1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1-1)/2]; // ����C1ָ��3����ôL1��R1����3,C1ָ��#����ôL1=3��R1=5.
	        double R1 = (c1 == n1 * 2) ? Integer.MAX_VALUE : nums1[(c1)/2]; // ?:��Ϊ��Ӧ�Ա߽������Խ���Ժ�����Ǹ�����ұ���������
	        double L2 = (c2 == 0) ? Integer.MIN_VALUE : nums2[(c2-1)/2];
	        double R2 = (c2 == n2 * 2) ? Integer.MAX_VALUE : nums2[(c2)/2];
	        
	        if (L1 > R2) lo = c2 + 1;       // nums1��Ӧ��ֵ̫��C1��Ҫ�����ƶ�
	        else if (L2 > R1) hi = c2 - 1;	// nums2��Ӧ��ֵ̫��C2��Ҫ�����ƶ�
	        else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2; // ��ȷ�����ȡL�нϴ�ֵ��R�н�Сֵ
	    }
	    return -1;
	}
	
	/**
	 * ����һ��m*n�Ķ�ά���飬���ĳһԪ����0���ͽ�����Ӧ���к��ж���Ϊ0
	 * 73. Set Matrix Zeroes
	 * O(m+n) space
	 * @param matrix
	 */
	public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
		int n = matrix[0].length;
		int[] zeroi = new int[m]; // ��zeroi����ʶ����û��0
		int[] zeroj = new int[n]; // ��zeroj����ʶ����û��0
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(matrix[i][j] == 0) { // ���������0���ȱ��
					zeroi[i] = 1;
					zeroj[j] = 1;
				}
			}
		}
		for(int i = 0; i < m; i++) {
			if(zeroi[i] == 1) { // �����б������0
				for(int j = 0; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		for(int j = 0; j < n; j++) {
			if(zeroj[j] == 1) { // �����б������0
				for(int i = 0; i < m; i++) {
					matrix[i][j] = 0;
				}
			}
		}
    }
	
	/**
	 * o(1) space
	 * @param matrix
	 */
	public void setZeroes2(int[][] matrix) {
		boolean firstRow = false; // ��firstRow��firstCol����ʶ��һ�к͵�һ�е�״̬
		boolean firstCol = false;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) { // �õ�һ�к͵�һ������¼�����Ƿ�Ӧ�ñ���Ϊ0�����
					if(i == 0) firstRow = true;
					if(j == 0) firstCol = true;
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
 		}
		
		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j < matrix[0].length; j++) {
				if(matrix[0][j] == 0 || matrix[i][0] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		
		if(firstRow) { // ��һ���Ƿ�Ӧ��Ϊ0
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[0][j] = 0;
			}
		}
		
		if(firstCol) { // ��һ���Ƿ�Ӧ��Ϊ0
			for(int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}
	
	/**
	 * Ѱ��������������С������
	 * 153. Find Minimum in Rotated Sorted Array
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
			if (mid == lo) {
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
	 * 154. Find Minimum in Rotated Sorted Array II
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
			if (mid == lo) {
				res = Math.min(nums[lo], nums[hi]);
				break;
			}
			if (nums[mid] > nums[hi]) { // min should in the latter
				lo = mid;
			} else if (nums[mid] < nums[hi]) { // min should in the former
				hi = mid;
			} else { // can not check min position
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
	 * �����������в���ָ��Ԫ�أ����鲻�����ظ�Ԫ��
	 * 33. Search in Rotated Sorted Array
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
			if (nums[mid] >= nums[lo]) { // lo to mid is increasing
				if (target >= nums[lo] && target < nums[mid]) { // target is in
																// the lo-mid
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			}

			if (nums[mid] <= nums[hi]) { // mid to hi is increasing
				if (target > nums[mid] && target <= nums[hi]) { // target is in
																// the mid-hi
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}

		return -1;
	}

	/**
	 * ���������������в���ָ�����֣������а����ظ�����
	 * 81. Search in Rotated Sorted Array II
	 * @param nums
	 * @param target
	 * @return
	 */
	public boolean searchInRotateSortedArray(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int lo = 0, hi = nums.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] == target) { // has found in mid
				return true;
			}
			if (nums[lo] == nums[hi]) { // ���ظ�Ԫ�ػ�����޷��ҵ������������˵�˳�����
				return searchInOrder(nums, target);
			}
			// �ֿ���ͬ�����������ڸ������������ж��ֲ���
			// now rotated array can be split to two sorted arrays
			if (nums[mid] >= nums[lo]) { // lo to mid is increasing
				if (target >= nums[lo] && target < nums[mid]) { // target in lo-mid
					hi = mid - 1;
				} else { // target in mid-hi
					lo = mid + 1;
				}
			}
			if (nums[mid] <= nums[hi]) { // mid to hi is increasing
				if (target > nums[mid] && target <= nums[hi]) { // target in mid-hi
					lo = mid + 1;
				} else { // target in lo-mid
					hi = mid - 1;
				}
			}
		}
		return false;
	}
	
	
	
    /**
     * 35. Search Insert Position
     * �����������в���ָ��Ԫ�صĲ���λ��
     * [1,3,5,6], 5 �� 2
     * [1,3,5,6], 2 �� 1
     * [1,3,5,6], 7 �� 4
     * [1,3,5,6], 0 �� 0
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertPosition(int[] nums, int target) {
        if (nums.length == 0)
			return 0;

		int lo = 0, hi = nums.length - 1;
		while (lo < hi) { // ���ֲ���ֱ��lo==hi
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		if(target == nums[lo]) // �ж�lo��Ԫ�غ�target��ȵ����
			return lo;
		return target < nums[lo] ? lo : lo + 1; // �ж�lo��Ԫ�غ�target���ȵ����
	
    }
    
    public static void main(String args[]) {
		SearchSeries t = new SearchSeries();
		
		int[][] matrix = { {1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
		int k = 8;
		 
		int[] nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
		int target = 0;
		int[] nums1 = new int[] { 1, 2, 3, 4};
		int[] nums2 = new int[] { 1, 4, 3, 2, 5, 3, 2};
		//t.findMedianSortedArrays(nums1, nums2);
		// System.out.println(t.searchInRotateSortedArray(nums, target));
		// System.out.println(t.myPow(0, -1));
		
		/*List<Interval> intervals = new ArrayList<Interval>(){{
			add(new Interval(1, 5));
		}};
		Interval newInterval = new Interval(2, 7);
		List<Interval> res = t.insertInterval(intervals, newInterval);
		System.out.println(res);*/
		//System.out.println(t.kthSmallest2(matrix, 8));
		//System.out.println(t.findDuplicateUsingTwoPointers(nums2));
		System.out.println(t.containsNearbyDuplicate(nums2, 3));
	}
}
