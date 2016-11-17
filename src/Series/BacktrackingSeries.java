package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BacktrackingSeries {

	/**
	 * Combination Sum I:定和子集问题，元素可重复取 candidate set [2, 3, 6, 7] and target 7
	 * ---> [7], [2, 2, 3]
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSumUnlimitedTimes(int[] candidates,
			int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (candidates == null || candidates.length == 0)
			return res;

		Arrays.sort(candidates); // cans should be sorted
		combinationSumHelper(candidates, target, res, new ArrayList<Integer>(),
				0);

		return res;
	}

	private void combinationSumHelper(int[] cans, int remain,
			List<List<Integer>> res, List<Integer> list, int from) {

		if (remain == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = from; i < cans.length && remain >= cans[i]; i++) {
			list.add(cans[i]);
			System.out.println(list + " " + (remain - cans[i]));
			combinationSumHelper(cans, remain - cans[i], res, list, i); // 可以重复取元素，所以还是从i开始
			list.remove(list.size() - 1);
		}

	}

	/**
	 * Combination Sum II:定和子集问题，元素只能取一次
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSumOnlyOnce(int[] candidates,
			int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (candidates == null || candidates.length == 0)
			return res;

		Arrays.sort(candidates); // should sort first
		combinationSumOnlyOnceHelper(candidates, target, res,
				new ArrayList<Integer>(), 0);

		return res;
	}

	private void combinationSumOnlyOnceHelper(int[] cans, int remains,
			List<List<Integer>> res, ArrayList<Integer> list, int from) {
		if (remains == 0) { // equals target, add to res
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for (int i = from; i < cans.length && remains >= cans[i]; i++) {
			list.add(cans[i]);
			System.out.println(list + " " + (remains - cans[i]));
			combinationSumOnlyOnceHelper(cans, remains - cans[i], res, list,
					i + 1); // use nums[i] and move to nums[i+1], i can be used
							// only once
			list.remove(list.size() - 1);
		}
	}

	/**
	 * Combination Sum III:从1到9选取所有的k个数字的组合，使得这k个数字之和等于n，数字不能重复
	 * 
	 * @param k
	 * @param n
	 * @return
	 */
	public List<List<Integer>> combinationSumHasKEles(int k, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		int[] cans = new int[9];
		for (int i = 0; i < 9; i++) {
			cans[i] = i + 1;
		}

		combinationSumHasKElesHelper(res, cans, k, n, new ArrayList<Integer>(),
				0);

		return res;
	}

	private void combinationSumHasKElesHelper(List<List<Integer>> res,
			int[] cans, int k, int remain, ArrayList<Integer> list, int from) {
		if (remain == 0 && list.size() == k) { // 定和子集的出口是和为target
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = from; i < cans.length && remain >= cans[i]; i++) {
			list.add(cans[i]);
			combinationSumHasKElesHelper(res, cans, k, remain - cans[i], list,
					i + 1);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 77. Combinations：列出所有1-n的有k个数字的子集
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (k > n) {
			return res;
		}
		int[] cans = new int[n];
		for (int i = 0; i < n; i++) {
			cans[i] = i + 1;
		}

		combineHelper(res, cans, k, new ArrayList<Integer>(), 0);

		return res;
	}

	private void combineHelper(List<List<Integer>> res, int[] cans, int k,
			ArrayList<Integer> list, int from) {

		if (k == list.size()) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = from; i < cans.length; i++) {
			list.add(cans[i]);
			combineHelper(res, cans, k, list, i + 1);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 46. Permutations：列出nums数组中数字的全排列,nums无重复 [1,2,3] ---> [1,2,3], [1,3,2],
	 * [2,1,3], [2,3,1], [3,1,2], [3,2,1]
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permuteWithUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		boolean[] used = new boolean[nums.length];
		permuteHelper(res, nums, new ArrayList<Integer>(), used);

		return res;
	}

	private void permuteHelper(List<List<Integer>> res, int[] nums,
			ArrayList<Integer> list, boolean[] used) {

		if (list.size() == nums.length) { // 全排列的出口是长度为nums.length
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) { // 全排列每次都从0开始，因为当到23的时候还必须使用1
			// if(list.contains(nums[i])) continue;
			if (used[i])
				continue;
			list.add(nums[i]);
			used[i] = true;
			// System.out.println(i + " : " + list);
			permuteHelper(res, nums, list, used);
			used[i] = false;
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 47. Permutations II：列出nums数组中数字的全排列,nums有重复 [1,1,2] ---> [1,1,2],
	 * [1,2,1], [2,1,1]
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permuteWithDuplicates(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return res;

		Arrays.sort(nums); // sort to split duplicates
		boolean[] used = new boolean[nums.length];
		permuteWithDuplicatesHelper(res, nums, new ArrayList<Integer>(), used);

		return res;
	}

	private void permuteWithDuplicatesHelper(List<List<Integer>> res,
			int[] nums, ArrayList<Integer> list, boolean[] used) {
		// dfs termination
		if (list.size() == nums.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) { // 排列需要使用之前被越过的数字，所以从0开始
			// if the number has been used, skip it
			if (used[i])
				continue;
			// when a number has the same value with its previous, we can use
			// this number only if his previous is used
			if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
				continue;
			list.add(nums[i]);
			// System.out.println(list);
			used[i] = true;
			permuteWithDuplicatesHelper(res, nums, list, used);
			used[i] = false;
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 从1-n的所有排列中，取第k个
	 * 
	 * @param n
	 *            1-9
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = i + 1;
		}
		System.out.println(Arrays.toString(nums));
		List<String> res = new ArrayList<String>();
		Set<Integer> used = new HashSet<Integer>();
		getPermutationHelper(res, nums, k, 0, "", used);
		System.out.println(res);
		return res.get(k - 1);
	}

	private void getPermutationHelper(List<String> res, int[] nums, int k,
			int cnt, String str, Set<Integer> set) {

		if (k == cnt)
			return;
		if (str.length() == nums.length) {
			cnt += 1;
			res.add(new String(str));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]))
				continue;
			str += nums[i];
			set.add(nums[i]);
			getPermutationHelper(res, nums, k, cnt, str, set);
			set.remove(nums[i]);
			str.substring(0, str.length() - 1);
		}
	}

	/**
	 * aac -> [a, a, c], [a, ac], [aa, c], [aac]
	 * 
	 * @param s
	 * @return
	 */
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<List<String>>();
		if (s == null || s.length() == 0)
			return res;
		partitionHelper(res, s, new ArrayList<String>(), 0);
		return res;
	}

	private void partitionHelper(List<List<String>> res, String s,
			ArrayList<String> list, int pos) {
		if (pos == s.length()) { // partition string, the string must in the
									// ending
			res.add(new ArrayList<String>(list));
			return;
		}

		for (int i = pos; i < s.length(); i++) { // 需要每次都往前移，因此从pos开始
			String tmp = s.substring(pos, i + 1);
			System.out.println(tmp);
			if (isValidPalindrome(s, pos, i)) { // 需要判断一下子串是否为回文串
				list.add(s.substring(pos, i + 1));
				partitionHelper(res, s, list, i + 1); // 不能重复，因此从i+1向前移动
				list.remove(list.size() - 1);
			}
		}
	}

	private boolean isValidPalindrome(String s, int lo, int hi) {
		while (lo < hi) {
			if (s.charAt(lo++) != s.charAt(hi--))
				return false;
		}
		return true;
	}

	/**
	 * 列出nums的所有子集 [1,2,3]---> [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], []
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return res;
		Arrays.sort(nums);
		subsetsHelper(res, nums, new ArrayList<Integer>(), 0);
		return res;
	}

	private void subsetsHelper(List<List<Integer>> res, int[] nums,
			ArrayList<Integer> list, int pos) {

		// if(pos == nums.length){
		res.add(new ArrayList<Integer>(list)); // 求全子集，没有任何限制
		// }

		for (int i = pos; i < nums.length; i++) { // 子集不允许取之前的值
			list.add(nums[i]);
			System.out.println(list);
			subsetsHelper(res, nums, list, i + 1); // 不能重复，因此需要向前移动
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 列出nums的子集，nums包括重复数字，重复数字都使用与元素对应的boolean数组来标识是否被使用过。
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDuplicates(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return res;
		boolean[] used = new boolean[nums.length];
		Arrays.sort(nums);
		subsetsWithDuplicatesHelper(res, nums, new ArrayList<Integer>(), used,
				0);

		return res;
	}

	private void subsetsWithDuplicatesHelper(List<List<Integer>> res,
			int[] nums, ArrayList<Integer> list, boolean[] used, int pos) {

		res.add(new ArrayList<Integer>(list));
		for (int i = pos; i < nums.length; i++) { // 元素只向前访问，1,12,123,13,132之后，1已经处理完毕，接下来第一个数字就成了2了
			if (used[i])
				continue;
			if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1])
				continue;
			list.add(nums[i]);
			used[i] = true;
			subsetsWithDuplicatesHelper(res, nums, list, used, i + 1);
			used[i] = false;
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 从board数组中任意点开始，可以向上下左右走，查找word是否在board数组中
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean wordSearch(char[][] board, String word) {
		if (board == null || word == null)
			return false;

		final int m = board.length;
		final int n = board[0].length;
		// 对于每一个点都深度优先遍历，查找word
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (wordSearchHelper(board, i, j, word, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * wordSearch的辅助程序
	 * 
	 * @param board
	 * @param start
	 *            表示word中判断字符的位置
	 * @param i
	 *            表示board中字符行
	 * @param j
	 *            表示board中字符列
	 * @param word
	 * @return
	 */
	private boolean wordSearchHelper(char[][] board, int i, int j, String word,
			int curr) {
		// curr在word中越界，结束递归
		if (curr == word.length())
			return true;

		// i和j在数组中越界，结束递归
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
			return false;

		// 如果board当前字符没有被访问过且word中当前字符相等
		if (board[i][j] == word.charAt(curr)) {
			char tmp = board[i][j];
			board[i][j] = '#';
			boolean res = wordSearchHelper(board, i - 1, j, word, curr + 1)
					|| wordSearchHelper(board, i + 1, j, word, curr + 1)
					|| wordSearchHelper(board, i, j - 1, word, curr + 1)
					|| wordSearchHelper(board, i, j + 1, word, curr + 1);
			board[i][j] = tmp;
			return res;
		}
		// board当前字符和word中当前字符不相等
		return false;
	}

	/**
	 * 在矩阵中寻找最长的路径，每个点可以左右上下移动，移动的标准是下一个点的值大于等于前一个点
	 * 
	 * @param matrix
	 * @return
	 */
	public int longestIncreasingPath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int[][] cache = new int[matrix.length][matrix[0].length];
		int max = 0;
		// 对矩阵中每一个数值都查找一遍
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int length = findSmallAround(i, j, matrix, cache,
						Integer.MIN_VALUE);
				max = Math.max(length, max);
			}
		}
		return max;
	}

	private int findSmallAround(int i, int j, int[][] matrix, int[][] cache,
			int pre) {
		// 如果超出了矩阵边界，递归结束
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return 0;
		}

		// 如果未超过上一个点，递归结束
		if (matrix[i][j] <= pre) {
			return 0;
		}

		// cache保存了该点的深度，如果计算过就直接相加
		if (cache[i][j] > 0) {
			return cache[i][j];
		} else { // 该点深度没有被计算过
			int cur = matrix[i][j]; // 记录当前数值
			int tempMax = 0; // tempMax用于计算该点的深度，是上下左右四个方向最大的深度
			tempMax = Math.max(findSmallAround(i - 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i + 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j - 1, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j + 1, matrix, cache, cur),
					tempMax);
			cache[i][j] = ++tempMax; // 更新cache数组
			return tempMax;
		}
	}

	/**
	 * 走迷宫问题，迷宫左上角为入口，右下角为出口，0表示可以走，1表示障碍
	 * 
	 * @param matrix
	 */
	public void mazePath(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		final int m = matrix.length;
		final int n = matrix[0].length;
		boolean[][] visited = new boolean[m][n];
		int[][] solution = new int[m][n];
		if (this.findMinPathHelper(matrix, 0, 0, visited, solution)) {
			this.printSolution(solution);
		} else {
			System.out.println("No solution!");
		}
	}

	/**
	 * 打印路径
	 * 
	 * @param s
	 */
	private void printSolution(int[][] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				System.out.print(" " + s[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 迷宫问题辅助函数
	 * 
	 * @param matrix
	 * @param i
	 * @param j
	 * @param visited
	 * @param solution
	 * @return
	 */
	private boolean findMinPathHelper(int[][] matrix, int i, int j,
			boolean[][] visited, int[][] solution) {
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) { // 如果走出界就返回错误
			return false;
		}

		if (i == matrix.length - 1 && j == matrix[0].length - 1) { // 走到出口处成功返回
			solution[i][j] = 1;
			return true;
		}

		if (!visited[i][j] && matrix[i][j] == 0) {
			visited[i][j] = true;
			solution[i][j] = 1;
			boolean res = findMinPathHelper(matrix, i + 1, j, visited, solution)
					|| findMinPathHelper(matrix, i - 1, j, visited, solution)
					|| findMinPathHelper(matrix, i, j - 1, visited, solution)
					|| findMinPathHelper(matrix, i, j + 1, visited, solution);
			if (!res)
				solution[i][j] = 0;
			visited[i][j] = false;
			return res;
		}
		return false;
	}

	/**
	 * 200. Number of Islands 计算矩阵中孤岛的个数，1表示孤岛，0表示水，矩阵外全是水，孤岛是指被水包围的1集合 11110 |
	 * 11000 11010 | 11000 11000 | 00100 00000 | 00011 1 | 3
	 * 
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		if (grid.length == 0 || grid[0].length == 0)
			return 0;
		int islands = 0;
		int m = grid.length, n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					islandsHelper(grid, i, j, m, n); // 一次前探和累加
					islands++;
				}
			}
		}
		return islands;
	}

	private void islandsHelper(char[][] grid, int i, int j, int m, int n) {
		if (i < 0 || i >= m || j < 0 || j >= n)
			return; // 如果i和j越界，则返回
		if (grid[i][j] == '0')
			return; // 如果探测到节点是0，返回
		grid[i][j] = '0'; // 将当前节点置为0，并继续前探
		islandsHelper(grid, i - 1, j, m, n);
		islandsHelper(grid, i + 1, j, m, n);
		islandsHelper(grid, i, j - 1, m, n);
		islandsHelper(grid, i, j + 1, m, n);
	}

	/**
	 * 正面迭代的方式解析IP
	 * 
	 * @param ip
	 * @return
	 */
	public List<String> restoreIPAddress(String ip) {
		if (ip == null || ip.length() == 0) {
			return null;
		}

		int len = ip.length();
		List<String> res = new ArrayList<String>();
		for (int i = 1; i < 4 && i < len - 2; i++) {
			for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
				for (int k = j + 1; k < j + 4 && k < len; k++) {
					String ip1 = ip.substring(0, i);
					String ip2 = ip.substring(i, j);
					String ip3 = ip.substring(j, k);
					String ip4 = ip.substring(k, len);
					if (isValidIP(ip1) && isValidIP(ip2) && isValidIP(ip3)
							&& isValidIP(ip4))
						res.add(new StringBuilder().append(ip1).append(".")
								.append(ip2).append(".").append(ip3)
								.append(".").append(ip4).toString());
				}
			}
		}
		return res;
	}

	/**
	 * 判断.间item是否正确
	 * 
	 * @param ip
	 * @return
	 */
	private boolean isValidIP(String ip) {
		if (ip.length() == 0 || ip.length() > 3
				|| (ip.length() > 0 && ip.startsWith("0"))
				|| Integer.parseInt(ip) > 255) {
			return false;
		}
		return true;
	}

	/**
	 * 给定一个字符串，返回可能构成的IP地址集合 "25525511135" --> ["255.255.11.135",
	 * "255.255.111.35"]
	 * 
	 * @param s
	 * @return
	 */
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<String>();
		restoreIPHelper(s, res, 0, "", 0);
		return res;
	}

	/**
	 * 解析IP辅助函数
	 * 
	 * @param ip
	 * @param res
	 * @param start
	 * @param str
	 * @param cnt
	 */
	private void restoreIPHelper(String ip, List<String> res, int start,
			String str, int cnt) {
		if (cnt > 4)
			return;
		if (cnt == 4 && start == ip.length()) {
			res.add(str);
			return;
		}

		for (int i = 1; i < 4; i++) {
			if (start + i > ip.length())
				return;
			String item = ip.substring(start, start + i);
			if (item.length() > 0 && item.startsWith("0")
					|| Integer.parseInt(item) >= 256)
				continue;
			System.out.println(str + item);
			restoreIPHelper(ip, res, start + i, cnt == 3 ? str + item + ""
					: str + item + ".", cnt + 1);
		}
	}

	private void cartesianProductHelper(List<String> res,
			Map<Integer, List<String>> map, int[] digits, int index,
			StringBuilder sb) {
		if (index == digits.length) {// 三个数组的笛卡尔积，index到3就可以加到结果中
			res.add(new String(sb.toString()));
			return;
		} else {
			List<String> currList = map.get(digits[index]); // 当前数组
			for (int i = 0; i < currList.size(); i++) {
				sb.append(currList.get(i));
				cartesianProductHelper(res, map, digits, index + 1, sb); // index前移保证找到下一个数组
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	/**
	 * 计算多个数组的笛卡尔积
	 * 
	 * @return
	 */
	public List<String> cartesianProduct() {
		List<String> res = new ArrayList<String>();
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		map.put(1,
				new ArrayList<String>(Arrays.asList(new String[] { "1", "2" })));
		map.put(2,
				new ArrayList<String>(Arrays.asList(new String[] { "3", "4",
						"5" })));
		map.put(3, new ArrayList<String>(Arrays.asList(new String[] { "6" })));
		int[] level = { 1, 2, 3 };
		cartesianProductHelper(res, map, level, 0, new StringBuilder());
		return res;
	}

	private static int total = 0;

	/**
	 * 八皇后问题 行岔开以后，对列进行全排列，并保证没有对角线的情况
	 * 
	 * @param n
	 * @return
	 */
	public int totalNQueens(int n) {
		int[] cols = new int[n];
		for (int i = 0; i < n; i++) {
			cols[i] = i + 1;
		}
		// cols = {1,2,3,4}

		QueensHelper(n, cols, new ArrayList<Integer>(), new HashSet<Integer>());
		return total;
	}

	private void QueensHelper(int n, int[] cols, List<Integer> list,
			HashSet<Integer> set) {
		if (list.size() == n && !idDiagonal(list)) {
			total++;
			// System.out.println(list + " " + total);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (set.contains(cols[i]))
				continue;
			set.add(cols[i]);
			list.add(cols[i]);

			QueensHelper(n, cols, list, set);
			set.remove(cols[i]);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * 判断list中的元素是否在对角线上
	 * 
	 * @param list
	 * @return
	 */
	private boolean idDiagonal(List<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (Math.abs(i - j) == Math.abs(list.get(i) - list.get(j)))
					return true;
			}
		}
		return false;
	}

	/**
	 * 二维数组包含了X和O，将X包围的O全部换成X
	 * 
	 * @param board
	 */
	public void SurroundedRegion(char[][] board) {
		if (board.length == 0 || board[0].length == 0)
			return;
		int row = board.length, col = board[0].length;

		// 利用bfs将边界上的O先设置为*，*表示不需要被改为X的O
		for (int i = 0; i < row; i++) {
			srModify(board, i, 0, row, col); // 处理第0列
			if (col > 1) {
				srModify(board, i, col - 1, row, col); // 处理最后一列
			}
		}

		for (int j = 1; j < row - 1; j++) {
			srModify(board, 0, j, row, col); // 处理第0行
			if (row > 1) {
				srModify(board, row - 1, j, row, col); // 处理最后一行
			}
		}

		// 将剩下的O都改为X，将*都还原为O
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = (board[i][j] == '*') ? 'O' : 'X';
			}
		}

	}

	/**
	 * bfs, 将O先设置为*
	 * 
	 * @param board
	 * @param i
	 * @param j
	 * @param row
	 * @param col
	 */
	private void srModify(char[][] board, int i, int j, int row, int col) {
		if (board[i][j] == 'O') {
			board[i][j] = '*';
			if (i - 1 > 0)
				srModify(board, i - 1, j, row, col);
			if (i + 1 < row)
				srModify(board, i + 1, j, row, col);
			if (j - 1 > 0)
				srModify(board, i, j - 1, row, col);
			if (j + 1 < col)
				srModify(board, i, j + 1, row, col);
		}
	}

	public static void main(String[] args) {
		BacktrackingSeries t = new BacktrackingSeries();
		int[] nums = new int[] { 1, 2, 3 };
		int target = 7;
		int[] cans = new int[] { 2, 3, 6, 7 };
		// System.out.println(t.combinationSumOnlyOnce(cans, target));
		// System.out.println(t.combinationSumUnlimitedTimes(cans, target));
		// int k = 2, n = 9;
		// System.out.println(t.combinationSumHasKEles(k, n));
		// System.out.println(t.combine(n, k));
		// System.out.println(t.permuteWithUnique(nums));
		// System.out.println(t.permuteWithDuplicates(nums));
		// System.out.println(t.getPermutation(4,4));
		System.out.println(t.subsets(nums));
		// System.out.println(t.subsetsWithDuplicates(nums));
		// System.out.println(t.restoreIPAddress("25525511135"));
	}
}
