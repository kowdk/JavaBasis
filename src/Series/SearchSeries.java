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
	 *  PriorityQueue的默认实现是最小堆，最大堆需要重载Comparator
	 *  将整个数据分为两段，左边用最大堆维持，右边用最小堆维持，最大堆和最小堆的差值不超过1
	 */
	public SearchSeries() {
		minHeap = new PriorityQueue<Integer>(); //pq默认是最小堆
		maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);// 实现一个Comparator接口变为最大堆
			}
		});
	}

	/**
	 * 向数据结构中存储一个数据
	 * @param num
	 */
	public void addNum(int num) {
		if (minHeap.size() == 0 && maxHeap.size() == 0) { // 两个堆都为空，插到右边
			minHeap.offer(num);
		} else if (minHeap.size() > maxHeap.size()) { // 如果右边多
			if (num < minHeap.peek()) { // num比右边最小的还小，插入左边
				maxHeap.offer(num);
			} else { // num需要被插入右边，因此将右边最小的插入左边，然后插入右边
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
		} else { // 两个堆的长度相同，按照值插入
			if (num > maxHeap.peek()) {
				minHeap.offer(num);
			} else {
				maxHeap.offer(num);
			}
		}
	}

	/**
	 * 取出数据结构的中位数
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
	 * 行从左到右有序，下一列第一个比上一列最后一个大，在这样的二维数组中寻找元素
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
		// 先找到在哪一行
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
		//对该行做二分查找
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
	 * 在行有序和列有序的数组中，找出第k小的数字
	 * @param matrix
	 * @param k
	 * @return
	 */
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length; // matrix是n*n的
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>(); // 创建一个最小堆
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j])); // 用第一行元素初始化最小堆
        for(int i = 0; i < k-1; i++) { // 循环k次，找出第k小的数
            Tuple t = pq.poll(); // poll出来的t，知道它的行和列
            if(t.x == n-1) continue; // 到最后一行，不能再向下了
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y])); //插入poll出来的元素的同一列下一行的元素 
        }
        return pq.poll().val;
    }
    
	/**
	 * 在行有序和列有序的数组中，用二分法，确定一个mid元素，然后在数组中找出小于等于mid的元素个数与k相比
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
	 * 在matrix中找出小于等于val的元素个数
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
	 * 在二维数组中查找元素，数组中的元素从左到右和从上到下都是顺序排列
	 * [1,   4,  7, 11, 15],
	 * [2,   5,  8, 12, 19],
	 * [3,   6,  9, 16, 22],
	 * [10, 13, 14, 17, 24],
	 * [18, 21, 23, 26, 30]
	 * 数组元素从左至右是有序的，从上至下是有序的
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}

		int m = matrix.length, n = matrix[0].length;
		int row = 0, col = n - 1; //从矩阵的右上角开始找
		while (row < m && col >= 0) {
			if (matrix[row][col] > target) { // matrix[row][col]是该列最小的，target小于它就砍掉该列
				col--;
			} else if (matrix[row][col] < target) { // matrix[row][col]是该行最大的，target大于他就砍掉改行
				row++;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 由外向内顺时针打印矩阵的元素
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

		// 维持四个指针，行的高低和列的高低，每一个圈循环四次打印上->右->下->左
		while (rowLo <= rowHi && colLo <= colHi) {
			for (int j = colLo; j <= colHi; j++) {
				res.add(matrix[rowLo][j]);
			}
			rowLo++;
			for (int i = rowLo; i <= rowHi; i++) {
				res.add(matrix[i][colHi]);
			}
			colHi--;
			if (rowLo <= rowHi) { // rowLo已经++，有可能越界了
				for (int j = colHi; j >= colLo; j--) {
					res.add(matrix[rowHi][j]);
				}
				rowHi--;
			}
			if (colLo <= colHi) { // colHi已经--，有可能越界了
				for (int i = rowHi; i >= rowLo; i--) {
					res.add(matrix[i][colLo]);
				}
				colLo++;
			}
		}
		return res;
	}
	
	
	
	/**
	 * 找出数组中重复的数字，要求O(n)时间和O(1)空间
	 * 如果对数组排序就是O(nlogn)时间，如果使用Hash就是O(n)时间O(n)空间
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
            while(nums[i] != i) { // 数值与下标不相等
                if(nums[i] == nums[nums[i]]) { // 发现数值与下标对应的数值相等，当前值就是重复数字
                    return nums[i];
                }
                // 交换数字与对应下标的数字保证下标和值一致
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }
	
	/**
	 * 用值对应下标将整个数组串起来，就是一个链表，问题转化为判断链表是否有环
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
	 * 合并区间
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
		// 初始化start和end
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		
		for(int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if(curr.start <= end) { // 有重叠，更新end
				end = Math.max(end, curr.end);
			} else { 
				res.add(new Interval(start, end)); // 没有重叠，将更新后的interval加入结果
				start = curr.start; // 更新start和end
				end = curr.end;
			}
		}
		res.add(new Interval(start, end));
		return res;
	}

	/**
	 * 将newInterval插入到intervals中，如有重叠需要进行合并
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
        // 处理在newInterval之前的元组
        while(i < len && intervals.get(i).end < newInterval.start) {
        	res.add(intervals.get(i++));
        }
        // 处理与newInterval相交的元组，不断更新newInterval
        while(i < len && intervals.get(i).start <= newInterval.end) {
        	newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), 
        			Math.max(intervals.get(i).end, newInterval.end));
        	i++;
        }
        // 将更新的newInterval加入
        res.add(newInterval);
        // 处理后面不存在相交部分的interval
        while(i < len) {
        	res.add(intervals.get(i++));
        }
        return res;
    }
	
	/**
	 * 辅助程序，顺序查找
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
	 * 在已排序的有重复元素的数组中找出指定元素的起始和末尾
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
			if (nums[mid] == target) { // 当找到元素的时候向两侧延伸
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
		return new int[] { -1, -1 }; // 未找到
	}

	/**
	 * 二分法求x的n次方，递归
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		if(n == 0) return 1.0; // 任何数的0次方都是1，0的0次方没有意义，也输出1
		if(x == 0 && n < 0) return 0.0; // 0的负数次方会导致除数是0,需要特殊处理
		if(n == Integer.MIN_VALUE) { // 如果指数为最小负值，应该都输出0，除非1的任何次方都是1
			if(Math.abs(x) == 1) {
				return 1.0;
			} else {
				return 0.0;
			}
		}
		// 接下来是正常情况，如果指数是负数，就反转指数，并求底数的倒数
		if(n < 0) { 
			n = -n;
			x = 1 / x;
		}
		return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2); // 递归求，分奇数偶数
	}
	
	/**
	 * 计算x的开方，二分查找
	 * @param x
	 * @return
	 */
    public int mySqrt(int x) {
        if(x == 0 || x == 1) return x;
		int lo = 1, hi = x;
		while(lo < hi) {
			int mid = lo + (hi - lo)/2;
			if(mid * mid < x) { // 用mid*mid和x作比较
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
     * 判断一个数是不是完全平方数，二分法
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
     * 找出一个数组的最大值和最小值
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
		
		//数组两两分为一组，互相比较，然后大的和max比，小的和min比，这样就把2n缩减为1.5n
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
     * 判断一个数组是否有重复数字
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
	 * 判断一个数组是否包含nums[i] = nums[j]，且i和j的距离不超过k
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        //nums[i]是key，i是value
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
			if(i > k) set.remove(nums[i-k-1]); // 当超过k的时候，得将当前i值的近k个数之前的数要被删掉
			if(!set.add(nums[i])) return true; // set的add方法加不进去，说明set中包含重复值
		}
		return false;
    }
	
	/**
	 * 判断一个数组是否包含nums[i]和nums[j]的值相差最多为t,而距离相差最多为k。 
	 * @param nums
	 * @param k
	 * @param t
	 * @return
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums == null || nums.length == 0 || k <= 0) {
			return false;
		}
		final TreeSet<Integer> set = new TreeSet<>(); // TreeSet是有序的
		for (int i = 0; i < nums.length; i++) {
			final Integer floor = set.floor(nums[i] + t); // floor表示小于等于参数的最大值
			final Integer ceil = set.ceiling(nums[i] - t); // ceiling表示大于等于参数的最小值
			// 找到了最大距离为t的大于nums[i]的数或者小于nums[i]的数
			if ((floor != null && floor >= nums[i])
					|| (ceil != null && ceil <= nums[i])) {
				return true;
			}

			set.add(nums[i]);
			if (i >= k) { // 保证了窗口为k
				set.remove(nums[i - k]);
			}
		}
		return false;
	}
	
	/**
	 * 判断数组有没有长度为三的递增子序列
	 * 334. Increasing Triplet Subsequence
	 * [1, 2, 3, 4, 5] -> true
	 * @param nums
	 * @return
	 */
	public boolean increasingTriplet(int[] nums) { //subsequence not need to be successive
        int lo = Integer.MAX_VALUE, hi = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
        	if(nums[i] <= lo) { // 找到第一个数字
        		lo = nums[i];
        	} else if(nums[i] <= hi) { // 找到第二个数字，第二个数字肯定比lo大
        		hi = nums[i]; 
        	} else { // 找到第三个数字，第三个数字肯定比lo、hi大
        		return true; 
        	}
        }
        return false;
    }
	
	/**
	 * 找出两个有序数组的中位数
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int nums1[], int nums2[]) {
		int n1 = nums1.length;
	    int n2 = nums2.length;
	    if (n1 < n2) return findMedianSortedArrays(nums2, nums1); // 异常处理，保证nums2数字少
	    if (n2 == 0) return ((double)nums1[(n1-1)/2] + (double)nums1[n1/2])/2;// 异常处理，如果nums2长度为0
	    
	    int lo = 0, hi = n2 * 2;
	    // 公式：c1+c2==n1+n2，c1和c2是分割点，要找到满足该公式且L1和L2均小于R1和R2，计算割点的时候连着#
	    // #, 1, #, 3, #, 5
	    // #, 2, #, 4, #, 6, #, 8
	    while (lo <= hi) {
	        int c2 = (lo + hi) / 2;   // 得到割点c2
	        int c1 = n1 + n2 - c2;  // 根据公式得到割点c1
	        
	        // L = (c-1) / 2;
	        // R = (c) / 2;
	        double L1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1-1)/2]; // 比如C1指向3，那么L1和R1都是3,C1指向#，那么L1=3，R1=5.
	        double R1 = (c1 == n1 * 2) ? Integer.MAX_VALUE : nums1[(c1)/2]; // ?:是为了应对边界情况，越界以后左边是负无穷，右边是正无穷
	        double L2 = (c2 == 0) ? Integer.MIN_VALUE : nums2[(c2-1)/2];
	        double R2 = (c2 == n2 * 2) ? Integer.MAX_VALUE : nums2[(c2)/2];
	        
	        if (L1 > R2) lo = c2 + 1;       // nums1对应的值太大，C1需要向左移动
	        else if (L2 > R1) hi = c2 - 1;	// nums2对应的值太大，C2需要向左移动
	        else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2; // 正确情况，取L中较大值和R中较小值
	    }
	    return -1;
	}
	
	/**
	 * 给定一个m*n的二维数组，如果某一元素是0，就将它对应的行和列都置为0
	 * 73. Set Matrix Zeroes
	 * O(m+n) space
	 * @param matrix
	 */
	public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
		int n = matrix[0].length;
		int[] zeroi = new int[m]; // 用zeroi来标识行有没有0
		int[] zeroj = new int[n]; // 用zeroj来标识列有没有0
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(matrix[i][j] == 0) { // 如果出现了0，先标记
					zeroi[i] = 1;
					zeroj[j] = 1;
				}
			}
		}
		for(int i = 0; i < m; i++) {
			if(zeroi[i] == 1) { // 根据行标记来置0
				for(int j = 0; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
		}
		for(int j = 0; j < n; j++) {
			if(zeroj[j] == 1) { // 根据列标记来置0
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
		boolean firstRow = false; // 用firstRow和firstCol来标识第一行和第一列的状态
		boolean firstCol = false;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) { // 用第一行和第一列来记录行列是否应该被记为0的情况
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
		
		if(firstRow) { // 第一行是否应该为0
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[0][j] = 0;
			}
		}
		
		if(firstCol) { // 第一列是否应该为0
			for(int i = 0; i < matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
	}
	
	/**
	 * 寻找右旋数组中最小的数字
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
	 * 在有重复元素的右旋数组中寻找最小的元素
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
	 * 在右旋数组中查找指定元素，数组不包含重复元素
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
	 * 在右旋排序数组中查找指定数字，数组中包含重复数字
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
			if (nums[lo] == nums[hi]) { // 有重复元素会造成无法找到有序区，回退到顺序查找
				return searchInOrder(nums, target);
			}
			// 分开不同的有序区，在各自有序区进行二分查找
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
     * 在有序数组中查找指定元素的插入位置
     * [1,3,5,6], 5 → 2
     * [1,3,5,6], 2 → 1
     * [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertPosition(int[] nums, int target) {
        if (nums.length == 0)
			return 0;

		int lo = 0, hi = nums.length - 1;
		while (lo < hi) { // 二分查找直到lo==hi
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		if(target == nums[lo]) // 判断lo处元素和target相等的情况
			return lo;
		return target < nums[lo] ? lo : lo + 1; // 判断lo处元素和target不等的情况
	
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
