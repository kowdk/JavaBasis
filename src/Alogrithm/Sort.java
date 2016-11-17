package Alogrithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Random;

public class Sort {

	/**
	 * 选择排序, 分为有序区和无序区，每次都从无序区i-nums.length-1选一个当前最小的值插入到有序区的结尾
	 * @param nums
	 */
	public void selectionSort(int[] nums) {
		int i, j, minIndex;
		for (i = 0; i < nums.length - 1; i++) { // from 0 to n-2
			minIndex = i;
			for (j = i + 1; j < nums.length; j++) { // from i to n-1
				if (nums[j] < nums[minIndex]) // select the position of min ele
					minIndex = j;
			}
			if (minIndex != i) { // if curr is not min, swap
				swap(nums, i, minIndex);
			}
		}
	}

	/**
	 * 插入排序，将当前元素插入到前面已经排好序的元素中
	 * @param nums
	 */
	public void insertionSort(int[] nums) {
		int i, j, curr;
		for (i = 1; i < nums.length; i++) { // from 1 to n-1
			curr = nums[i]; 
			j = i; 
			while (j > 0 && nums[j - 1] > curr) { // from 0 to i, find position for nums[i]
				nums[j] = nums[j - 1]; // 选择是0-i与i比较
				j--;
			}
			nums[j] = curr;
		}
	}
	
	/**
	 * 冒泡排序，大的元素向后沉，小的元素向前冒
	 * @param nums
	 */
	public void bubbleSort(int nums[]) {
		for (int i = 0; i < nums.length - 1; i++) { // n-1 times
			boolean flag = false;
			for (int j = 1; j < nums.length - i; j++) { // compare j-1 and j, bigger ele go to latter, smaller ele go to former
				if (nums[j - 1] > nums[j]) { // 冒泡是相邻元素比较
					swap(nums, j - 1, j);
					flag = true;
				}
			}
			if (!flag) // no swap means nums is already sorted
				break;
			System.out.println(Arrays.toString(nums));
		}
	}

	/**
	 * 合并数组
	 * @param nums
	 * @param lo
	 * @param mid
	 * @param hi
	 * @param tmp
	 */
	private void mergeArray(int nums[], int lo, int mid, int hi, int tmp[]) {
		int i = lo, j = mid + 1, k = 0;
		while (i <= mid && j <= hi) {
			if (nums[i] <= nums[j])
				tmp[k++] = nums[i++];
			else
				tmp[k++] = nums[j++];
		}

		while (i <= mid)
			tmp[k++] = nums[i++];
		while (j <= hi)
			tmp[k++] = nums[j++];

		// System.out.println("lo : " + lo + "; k : " + k + "; tmp : " +
		// Arrays.toString(tmp));
		for (i = 0; i < k; i++)
			nums[lo + i] = tmp[i];// cause lo is not 0 every time
		// System.out.println("nums: " + Arrays.toString(nums));
	}

	/**
	 * 归并 core 
	 * @param nums
	 * @param lo
	 * @param hi
	 * @param tmp
	 */
	private void mergeSort(int nums[], int lo, int hi, int tmp[]) {
		if (lo < hi) {
			// divide
			int mid = lo + (hi - lo) / 2;
			mergeSort(nums, lo, mid, tmp);
			mergeSort(nums, mid + 1, hi, tmp);

			// conquer
			mergeArray(nums, lo, mid, hi, tmp);
		}
	}

	/**
	 * 归并排序
	 * @param nums
	 */
	public void mergeSort(int nums[]) {
		int[] tmp = new int[nums.length];
		mergeSort(nums, 0, nums.length - 1, tmp);
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	/**
	 * 打乱数组顺序
	 * @param nums
	 */
	private void shuffle(int nums[]) {
		final Random random = new Random();
		for (int i = 1; i < nums.length; i++) {
			final int r = random.nextInt(i + 1); // 产生0-i之间的某个数
			swap(nums, i, r);
		}
	}

	/**
	 * 延伸问题，找到无序数组中第K大的数字
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest(int[] nums, int k) {
		int lo = 0, hi = nums.length - 1;
		int target = nums.length - k; // target-th smallest 9 - 3 = 6
		while(lo <= hi) {
			int index = partition(nums, lo, hi); // partition return kth smallest
			if(index < target) {
				lo = index + 1;
			} else if(index > target) {
				hi = index - 1;
			} else {
				return nums[index];
			}
		}
		return nums[lo];
	}
	
	/**
	 * 寻找数组中第k大的元素，使用最小堆，O(nlogk)
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargestUsingHeap(int[] nums, int k) {
	    final PriorityQueue<Integer> pq = new PriorityQueue<>();
	    for(int val : nums) {
	        pq.offer(val);// offer维持了最小堆的性质

	        if(pq.size() > k) {
	            pq.poll(); // poll的是堆顶元素
	        }
	    }
	    return pq.peek();
	}
	
	/**
	 * nums在lo和hi之间执行一次分割
	 * @param nums
	 * @param lo
	 * @param hi
	 * @return
	 */
	private int partition(int[] nums, int lo, int hi) {
		int i = lo, j = hi, pivot = lo; // nums[pivot]用于比较，是哨兵
		while (i <= j) {
			while (i <= j && nums[i] <= nums[pivot]) { // =号是要略过自己
				i++;
			}
			while (i <= j && nums[j] > nums[pivot]) {
				j--;
			}
			if (i > j) {
				break;
			}
			swap(nums, i, j);
		}
		swap(nums, pivot, j); // j是最后一个小于哨兵的，pivot则是哨兵下标
		return j; // 只考虑j左边和右边的
	}

	/**
	 * 快排core
	 * @param nums
	 * @param lo
	 * @param hi
	 */
	private void quickSort(int[] nums, int lo, int hi) {
		int index = partition(nums, lo, hi); // 第index小的数据
		System.out.println(index + "--" + nums[index]);
		System.out.println(index + " " + Arrays.toString(nums));
		if (index > lo)
			quickSort(nums, lo, index - 1); // sort from lo to index-1;
		if (index < hi)
			quickSort(nums, index + 1, hi); // sort index+1 to hi.
	}

	/**
	 * 快速排序
	 * @param nums
	 */
	public void quickSort(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
	}

	/**
	 * 小顶堆
	 * @param nums
	 * @param heapSize
	 * @param curr
	 */
	private void minHeap(int[] nums, int heapSize, int curr) {
		int left = curr * 2 + 1;
		int right = left + 1;

		int minIndex = curr;
		if (left < heapSize && nums[left] < nums[minIndex]) {
			minIndex = left;
		}

		if (right < heapSize && nums[right] < nums[minIndex]) {
			minIndex = right;
		}

		if (curr != minIndex) {
			swap(nums, curr, minIndex);

			minHeap(nums, heapSize, minIndex);
		}
	}
	
	/**
	 * 建堆，时间复杂度O(n)
	 * @param nums
	 */
	private void buildMaxHeap(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return;
		}

		// 从没有儿子的节点开始，维持大顶堆性质
		int half = nums.length / 2;
		for (int i = half; i >= 0; i--) {
			maxHeap(nums, nums.length, i);
		}
	}
	
	/**
	 * 维持大顶堆的性质
	 * @param nums
	 * @param heapSize 堆的大小
	 * @param curr 需要调整的元素
	 */
	private void maxHeap(int[] nums, int heapSize, int curr) {
		int left = curr * 2 + 1;
		int right = left + 1;

		// max(自己，左儿子，右儿子)
		int maxIndex = curr;
		if (left < heapSize && nums[left] > nums[maxIndex]) {
	 		maxIndex = left;
		}

		if (right < heapSize && nums[right] > nums[maxIndex]) {
			maxIndex = right;
		}

		// 如果找到大儿子，则替换，并继续向下探
		if (curr != maxIndex) {
			swap(nums, curr, maxIndex); 
			maxHeap(nums, heapSize, maxIndex);
		}
	}

	/**
	 * 堆排序
	 * @param nums
	 */
	public void heapSort(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return;
		}

		// 从nums[half]到nums[0]构建大顶堆
		// 建堆时间复杂度：N/2 * O(logn)
		buildMaxHeap(nums);

		// 排序时间复杂度：N/2 * O(logn)
		for (int i = nums.length - 1; i >= 1; i--) {
			// 从堆的最底部开始，将当前元素与堆顶即最大元素交换
			swap(nums, 0, i);
			// 对大顶堆元素维持大顶堆性质，这样保证每次与当前元素交换的都是堆中最大元素
			maxHeap(nums, i, 0); // 每次都maxHeap第0个元素，并且将堆的size-1
		}
	}
	
	public List<Integer> topKFrequentUsingBucket(int[] nums, int k){
		// 以统计值为桶
		List<Integer>[] bucket = new List[nums.length+1];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		// 对数组进行hash统计
		for(int key : nums) {
			if(map.containsKey(key)) {
				map.put(key, map.get(key)+1);
			} else {
				map.put(key, 1);
			}
		}
		
		// 以统计值为桶，将原始数字链接在每个桶上
		for(int key : map.keySet()) {
			int freq = map.get(key);
			if(bucket[freq] == null) {
				bucket[freq] = new ArrayList<Integer>();
			}
			bucket[freq].add(key);
		}
		
		// 从后向前遍历桶并输出结果
		List<Integer> res = new ArrayList<Integer>();
		for(int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
			if(bucket[i] != null) {
				res.addAll(bucket[i]);
			}
		}
		
		return res;
	}
	
	/**
	 * 堆排序的延伸，求数组的top-k，时间复杂度是O(nlogk)
	 * @param args
	 */
	public List<Integer> topKFrequentUsingHeap(int[] nums, int k) {
		List<Integer> list = new ArrayList<Integer>();
		
		// hash calculation to calculate 
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int num : nums) {
			if(map.containsKey(num)){
				int count = map.get(num);
				map.put(num, count+1);
			} else {
				map.put(num, 1);
			}
		}
		// heap sort, use comparator to hold a min-heap
		Comparator<Entry<Integer, Integer>> comp = new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o1.getValue() - o2.getValue();
			}
		};
		
		// PriorityQueue is an implementation of min-heap, create it with size of k and Comparator c
		PriorityQueue<Entry<Integer, Integer>> pq = new PriorityQueue<Entry<Integer, Integer>>(k, comp);
		// pq 默认小顶堆，弹出堆顶元素是最小元素
		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			pq.offer(entry);
			if(pq.size() > k) pq.poll(); // pq容量一旦大于k，就将无用的低频词出栈
		}
		
		// dump top k numbers 
		while(!pq.isEmpty()){
			list.add(0, pq.poll().getKey());
		}
		
		return list;
	}
	
	/**
	 * 归并排序的延伸，求数组中的逆序对
	 * @param nums
	 * @return
	 */
	public int inversePairs(int[] nums){
		int[] tmp = new int[nums.length];
		return mergeSortExtention(nums, 0, nums.length - 1, tmp);
	}
	
	
	private int mergeSortExtention(int nums[], int lo, int hi, int tmp[]) {
		int cnt = 0;
		if (lo < hi) {
			// divide
			int mid = lo + (hi - lo) / 2;
			cnt += mergeSortExtention(nums, lo, mid, tmp);
			cnt += mergeSortExtention(nums, mid + 1, hi, tmp);

			// conquer
			cnt += mergeArrayExtention(nums, lo, mid, hi, tmp);
		}
		return cnt;
	}
	
	private int mergeArrayExtention(int nums[], int lo, int mid, int hi, int tmp[]) {
		int i = lo, j = mid + 1, k = 0, cnt = 0;
		while (i <= mid && j <= hi) {
			if (nums[i] <= nums[j]){
				tmp[k++] = nums[i++];
			}
			else {
				tmp[k++] = nums[j++];
				cnt += (mid - i + 1); // nums[i] > nums[j], means all else between i-(mid-1) can form inverse pairs, only caled in merge station
			}
		}

		while (i <= mid){
			tmp[k++] = nums[i++];
		}
		while (j <= hi) {
			tmp[k++] = nums[j++];
		}

		for (i = 0; i < k; i++) {
			nums[lo + i] = tmp[i];
		}
		return cnt;
	}
	
	/**
	 * 桶排序， 将数据根据一定规则比如除以10分配到各个桶，对每个桶进行排序，顺次打出各个桶的已排序元素
	 * @param nums
	 */
	public void bucketSort(int nums[]) {
		final int bucketsNum = 10;
		//类似于HashMap一样的桶，每个桶跟一个列表
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>(bucketsNum);
		
		//向桶中存放原始数据
		for (int i = 0; i < nums.length; i++) {
			int bucketIndex = nums[i] / bucketsNum;
			if (!map.containsKey(bucketIndex))
				map.put(bucketIndex, new ArrayList<Integer>());
			map.get(bucketIndex).add(nums[i]);
		}

		//给每一个桶排序
		for (int i = 0; i < bucketsNum; i++) {
			if (map.get(i) != null)
				Collections.sort(map.get(i));
		}

		//将每一个桶都打印出来
		int j = 0;
		for (int i = 0; i < bucketsNum; i++) {
			if (map.get(i) != null) {
				for(int k = 0; k < map.get(i).size(); k++) {
					nums[j++] = map.get(i).get(k);
				}
			}
		}
	}
	
	/**
	 * 计数排序
	 * @param nums
	 */
	public void CountingSort(int[] nums) {
		int[] output = new int[nums.length];
		int[] count = new int[256];
		for (int i = 0; i < nums.length; ++i) {
			++count[nums[i]];
		}
		int j = 0;
		for (int i = 0; i <= 255; i++) {
			while (count[i] > 0) {
				output[j++] = i;
				count[i]--;
			}
		}
		System.arraycopy(output, 0, nums, 0, nums.length);
	}
	
	public static void main(String[] args) {
		Sort s = new Sort();
		//int[] nums = new int[] { 1, 12, 5, 26, 7, 14, 3, 7, 2 };
		int[] nums = new int[]{1,1,1,2,2,3,3,3,3,4};
		//int[] nums = new int[]{4,5,1,6,2,7,3,8};
		//System.out.println(Arrays.toString(nums));
		// s.shuffle(nums);
		//s.quickSort(nums);
		// s.mergeSort(nums);
		// s.bubbleSort(nums);
		// s.insertionSort(nums);
		// s.selectionSort(nums);
		// s.heapSort(nums);
		// System.out.println(Arrays.toString(nums));
		// System.out.println(s.findKthLargest(nums, 3));
		// System.out.println(s.inversePairs(nums));
		//List<Integer> res = s.topKFrequentUsingHeap(nums, 3);
		//System.out.println(res);
		
		System.out.println(s.topKFrequentUsingHeap(nums,2));
	}

}
