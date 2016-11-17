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
	 * Combination Sum I:�����Ӽ����⣬Ԫ�ؿ��ظ�ȡ candidate set [2, 3, 6, 7] and target 7
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
			combinationSumHelper(cans, remain - cans[i], res, list, i); // �����ظ�ȡԪ�أ����Ի��Ǵ�i��ʼ
			list.remove(list.size() - 1);
		}

	}

	/**
	 * Combination Sum II:�����Ӽ����⣬Ԫ��ֻ��ȡһ��
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
	 * Combination Sum III:��1��9ѡȡ���е�k�����ֵ���ϣ�ʹ����k������֮�͵���n�����ֲ����ظ�
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
		if (remain == 0 && list.size() == k) { // �����Ӽ��ĳ����Ǻ�Ϊtarget
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
	 * 77. Combinations���г�����1-n����k�����ֵ��Ӽ�
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
	 * 46. Permutations���г�nums���������ֵ�ȫ����,nums���ظ� [1,2,3] ---> [1,2,3], [1,3,2],
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

		if (list.size() == nums.length) { // ȫ���еĳ����ǳ���Ϊnums.length
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) { // ȫ����ÿ�ζ���0��ʼ����Ϊ����23��ʱ�򻹱���ʹ��1
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
	 * 47. Permutations II���г�nums���������ֵ�ȫ����,nums���ظ� [1,1,2] ---> [1,1,2],
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

		for (int i = 0; i < nums.length; i++) { // ������Ҫʹ��֮ǰ��Խ�������֣����Դ�0��ʼ
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
	 * ��1-n�����������У�ȡ��k��
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

		for (int i = pos; i < s.length(); i++) { // ��Ҫÿ�ζ���ǰ�ƣ���˴�pos��ʼ
			String tmp = s.substring(pos, i + 1);
			System.out.println(tmp);
			if (isValidPalindrome(s, pos, i)) { // ��Ҫ�ж�һ���Ӵ��Ƿ�Ϊ���Ĵ�
				list.add(s.substring(pos, i + 1));
				partitionHelper(res, s, list, i + 1); // �����ظ�����˴�i+1��ǰ�ƶ�
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
	 * �г�nums�������Ӽ� [1,2,3]---> [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], []
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
		res.add(new ArrayList<Integer>(list)); // ��ȫ�Ӽ���û���κ�����
		// }

		for (int i = pos; i < nums.length; i++) { // �Ӽ�������ȡ֮ǰ��ֵ
			list.add(nums[i]);
			System.out.println(list);
			subsetsHelper(res, nums, list, i + 1); // �����ظ��������Ҫ��ǰ�ƶ�
			list.remove(list.size() - 1);
		}
	}

	/**
	 * �г�nums���Ӽ���nums�����ظ����֣��ظ����ֶ�ʹ����Ԫ�ض�Ӧ��boolean��������ʶ�Ƿ�ʹ�ù���
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
		for (int i = pos; i < nums.length; i++) { // Ԫ��ֻ��ǰ���ʣ�1,12,123,13,132֮��1�Ѿ�������ϣ���������һ�����־ͳ���2��
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
	 * ��board����������㿪ʼ�����������������ߣ�����word�Ƿ���board������
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
		// ����ÿһ���㶼������ȱ���������word
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
	 * wordSearch�ĸ�������
	 * 
	 * @param board
	 * @param start
	 *            ��ʾword���ж��ַ���λ��
	 * @param i
	 *            ��ʾboard���ַ���
	 * @param j
	 *            ��ʾboard���ַ���
	 * @param word
	 * @return
	 */
	private boolean wordSearchHelper(char[][] board, int i, int j, String word,
			int curr) {
		// curr��word��Խ�磬�����ݹ�
		if (curr == word.length())
			return true;

		// i��j��������Խ�磬�����ݹ�
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
			return false;

		// ���board��ǰ�ַ�û�б����ʹ���word�е�ǰ�ַ����
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
		// board��ǰ�ַ���word�е�ǰ�ַ������
		return false;
	}

	/**
	 * �ھ�����Ѱ�����·����ÿ����������������ƶ����ƶ��ı�׼����һ�����ֵ���ڵ���ǰһ����
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
		// �Ծ�����ÿһ����ֵ������һ��
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
		// ��������˾���߽磬�ݹ����
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return 0;
		}

		// ���δ������һ���㣬�ݹ����
		if (matrix[i][j] <= pre) {
			return 0;
		}

		// cache�����˸õ����ȣ�����������ֱ�����
		if (cache[i][j] > 0) {
			return cache[i][j];
		} else { // �õ����û�б������
			int cur = matrix[i][j]; // ��¼��ǰ��ֵ
			int tempMax = 0; // tempMax���ڼ���õ����ȣ������������ĸ������������
			tempMax = Math.max(findSmallAround(i - 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i + 1, j, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j - 1, matrix, cache, cur),
					tempMax);
			tempMax = Math.max(findSmallAround(i, j + 1, matrix, cache, cur),
					tempMax);
			cache[i][j] = ++tempMax; // ����cache����
			return tempMax;
		}
	}

	/**
	 * ���Թ����⣬�Թ����Ͻ�Ϊ��ڣ����½�Ϊ���ڣ�0��ʾ�����ߣ�1��ʾ�ϰ�
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
	 * ��ӡ·��
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
	 * �Թ����⸨������
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
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) { // ����߳���ͷ��ش���
			return false;
		}

		if (i == matrix.length - 1 && j == matrix[0].length - 1) { // �ߵ����ڴ��ɹ�����
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
	 * 200. Number of Islands ��������йµ��ĸ�����1��ʾ�µ���0��ʾˮ��������ȫ��ˮ���µ���ָ��ˮ��Χ��1���� 11110 |
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
					islandsHelper(grid, i, j, m, n); // һ��ǰ̽���ۼ�
					islands++;
				}
			}
		}
		return islands;
	}

	private void islandsHelper(char[][] grid, int i, int j, int m, int n) {
		if (i < 0 || i >= m || j < 0 || j >= n)
			return; // ���i��jԽ�磬�򷵻�
		if (grid[i][j] == '0')
			return; // ���̽�⵽�ڵ���0������
		grid[i][j] = '0'; // ����ǰ�ڵ���Ϊ0��������ǰ̽
		islandsHelper(grid, i - 1, j, m, n);
		islandsHelper(grid, i + 1, j, m, n);
		islandsHelper(grid, i, j - 1, m, n);
		islandsHelper(grid, i, j + 1, m, n);
	}

	/**
	 * ��������ķ�ʽ����IP
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
	 * �ж�.��item�Ƿ���ȷ
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
	 * ����һ���ַ��������ؿ��ܹ��ɵ�IP��ַ���� "25525511135" --> ["255.255.11.135",
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
	 * ����IP��������
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
		if (index == digits.length) {// ��������ĵѿ�������index��3�Ϳ��Լӵ������
			res.add(new String(sb.toString()));
			return;
		} else {
			List<String> currList = map.get(digits[index]); // ��ǰ����
			for (int i = 0; i < currList.size(); i++) {
				sb.append(currList.get(i));
				cartesianProductHelper(res, map, digits, index + 1, sb); // indexǰ�Ʊ�֤�ҵ���һ������
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	/**
	 * ����������ĵѿ�����
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
	 * �˻ʺ����� �в��Ժ󣬶��н���ȫ���У�����֤û�жԽ��ߵ����
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
	 * �ж�list�е�Ԫ���Ƿ��ڶԽ�����
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
	 * ��ά���������X��O����X��Χ��Oȫ������X
	 * 
	 * @param board
	 */
	public void SurroundedRegion(char[][] board) {
		if (board.length == 0 || board[0].length == 0)
			return;
		int row = board.length, col = board[0].length;

		// ����bfs���߽��ϵ�O������Ϊ*��*��ʾ����Ҫ����ΪX��O
		for (int i = 0; i < row; i++) {
			srModify(board, i, 0, row, col); // �����0��
			if (col > 1) {
				srModify(board, i, col - 1, row, col); // �������һ��
			}
		}

		for (int j = 1; j < row - 1; j++) {
			srModify(board, 0, j, row, col); // �����0��
			if (row > 1) {
				srModify(board, row - 1, j, row, col); // �������һ��
			}
		}

		// ��ʣ�µ�O����ΪX����*����ԭΪO
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = (board[i][j] == '*') ? 'O' : 'X';
			}
		}

	}

	/**
	 * bfs, ��O������Ϊ*
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
