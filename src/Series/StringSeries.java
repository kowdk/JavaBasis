package Series;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class StringSeries {

	/**
	 * 去除字符串中多余的空格，去除串头尾的空格
	 * 
	 * @param str
	 * @return
	 */
	public String removeBlank(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char[] cs = str.toCharArray();
		int lo = 0, hi = 0;
		while (cs[hi] == ' ')
			hi++;
		while (hi < cs.length) {
			if (cs[hi] != ' ' || hi < cs.length - 1 && cs[hi + 1] != ' ') {
				cs[lo++] = cs[hi++];
			} else {
				hi++;
			}
		}

		return new String(cs, 0, lo);
	}

	/**
	 * 将字符串中的空格替换为20%，从后向前
	 * 
	 * @param str
	 * @return
	 */
	public String replaceBlank(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		char[] cs = str.toCharArray();
		int len = cs.length;
		int blankCnt = 0;
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == ' ') {
				blankCnt++;
			}
		}
		int newLen = len + blankCnt * 2;
		char[] newCs = new char[newLen];
		System.arraycopy(cs, 0, newCs, 0, len);
		int lo = len - 1, hi = newLen - 1;
		while (lo >= 0 && hi > lo) {
			if (newCs[lo] == ' ') {
				newCs[hi--] = '0';
				newCs[hi--] = '2';
				newCs[hi--] = '%';
			} else {
				newCs[hi--] = newCs[lo];
			}
			--lo;
		}
		return new String(newCs);
	}

	/**
	 * 1->11->21->1211,数字符串
	 * 
	 * @param n
	 * @return
	 */
	public String countAndSay(int n) {
		StringBuilder prev = new StringBuilder("1");
		StringBuilder curr = null;

		for (int i = 1; i < n; i++) { // 从1到n迭代n-1次
			curr = new StringBuilder();
			int count = 1;
			char say = prev.charAt(0);
			for (int j = 1; j < prev.length(); j++) {
				if (prev.charAt(j) == say) {
					count++;
				} else {
					curr.append(count).append(say);
					count = 1;
					say = prev.charAt(j);
				}
			}
			curr.append(count).append(say);
			prev = curr;
		}
		return curr.toString();
	}

	/**
	 * 28. Implement strStr()
	 * 返回t在s中第一次出现的位置，如果s不包含t，则返回-1
	 * @param s
	 * @param t
	 * @return
	 */
	public int strStr(String s, String t) {
		if(t.length() > s.length()) return -1;
		if(t.length() == 0) return 0;
		char c = t.charAt(0);
		for(int i = 0; i < s.length(); i++) { // abcdbce bce
			while(i < s.length() && s.charAt(i) != c) {
				i++;
			}
			if(i < s.length()) {
				int j = i + 1;
				int end = j + t.length() - 1;
				if(end > s.length()) return -1; // abcd cde
				for(int k = 1; j < end && t.charAt(k) == s.charAt(j); k++, j++);
				if(j == end) {
					return i;
				}
			}
		}
		return -1; // abcd efg
	}
	
	/**
	 * 6. ZigZag Conversion
	 * 字符串的zigzag转换
	 * 输入：PAYPALISHIRING
	 * P   A   H   N
	 * A P L S I I G
	 * Y   I   R
	 * 输出：PAHNAPLSIIGYIR
	 * @param s
	 * @param nRows
	 * @return
	 */
	public String convert(String s, int nRows) {
        if(nRows <= 1) return s;
        char[] str = s.toCharArray();
        String result = "";
        int cycle = 2 * nRows - 2; // 计算出周期
        for(int i = 0; i < nRows; ++i) { // 逐行处理
            for(int j = i; j < s.length(); j = j + cycle) {
               result = result + str[j]; // 正常的周期列
               int secondJ = (j - i) + cycle - i; // 计算多余列的下标 
               if(i != 0 && i != nRows-1 && secondJ < s.length()) // 只有除了第一行和最后一行才会有多余列
                   result = result + str[secondJ];
            }
        }
        return result;
    }
	
	/**
	 * 128. Longest Consecutive Sequence
	 * 最长的连续序列
	 * [100, 4, 200, 1, 3, 2]，[1, 2, 3, 4]. Return its length: 4.
	 * @param nums
	 * @return
	 */
	public int longestConsecutive(int[] nums) {
		int max = 0;
		// key是元素值，value是当前最长的连续数字长度
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) { // 不处理重复元素
				int lo = map.containsKey(nums[i] - 1) ? map.get(nums[i] - 1)
						: 0; // 当前元素的前一个元素最长序列
				int hi = map.containsKey(nums[i] + 1) ? map.get(nums[i] + 1)
						: 0; // 当前元素的下一个元素最长序列
				int curr = lo + hi + 1;
				max = Math.max(max, curr); // 更新最大长度

				map.put(nums[i], curr); // 将当前值的最长长度存入hash

				map.put(nums[i] - lo, curr); // 更新当前的最长长度的左边界
				map.put(nums[i] + hi, curr); // 更新当前的最长长度的右边界
			}
		}
		return max;
	}

	/**
	 * 316. Remove Duplicate Letters
	 * 删除字符串中的重复字母,保持原字符串的相对顺序不变，返回字典序值最小的序列 cbacdcbc->acdb
	 * "bcabc" -> "abc"
	 * "cbacdcbc" -> "acdb"
	 * @param s
	 * @return
	 */
	public String removeDuplicateLetters(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}

		int[] cnt = new int[26]; // cnt记录字符出现次数
		boolean[] visited = new boolean[26]; // 字符是否被访问过
		char[] cs = s.toCharArray();
		for (char c : cs) { // 首先将cnt装满
			cnt[c - 'a'] += 1;
		}

		Stack<Character> stack = new Stack<Character>(); // 记录不重复字符
		for (char c : cs) {
			int i = c - 'a';
			cnt[i]--; // 每次先将cnt--表示消耗了一个该元素，最终cnt将全部为0
			if (visited[i]) // visited是否为真与栈内元素一一对应
				continue;

			while (!stack.isEmpty() && c < stack.peek()
					&& cnt[stack.peek() - 'a'] > 0) { // 比栈顶字符小而且栈顶字符出现过，说明后面还有，这个可以出栈
				visited[stack.pop() - 'a'] = false; // 将出栈元素回退为未访问
			}

			stack.push(c);
			visited[i] = true; // 最终visited将全部为true
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}
		return sb.toString();
	}

	/**
	 * 反转字符串
	 * 
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		if (s == null || s.equals("")) // 如果s为空指针或者长度为空
			return s;

		char[] cs = reverse(s.toCharArray(), 0, s.length() - 1); // 先将字符串整体反转
		int lo = 0, hi = 0;
		for (int i = 0; i < s.length(); i++) {
			if (cs[i] != ' ') {
				cs[hi++] = cs[i];
			} else if (i > 0 && cs[i - 1] != ' ') {
				reverse(cs, lo, hi - 1);
				cs[hi++] = ' ';
				lo = hi;
			}
		}
		reverse(cs, lo, hi - 1);
		return new String(cs, 0, cs[hi - 1] == ' ' ? hi - 1 : hi);
	}

	private char[] reverse(char[] cs, int lo, int hi) {
		while (lo < hi) {
			char tmp = cs[lo];
			cs[lo++] = cs[hi];
			cs[hi--] = tmp;
		}
		return cs;
	}

	/**
	 * 32. Longest Valid Parentheses
	 * 字符串中最长的合法括号对
	 * ")()())" -> "()()", return 4
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<Integer>(); // stack用于存放括号所在下标而非括号本身
		int maxLen = 0;
		stack.push(-1); // 防止()这类边界情况，在更新maxLen的时候有东西可以减
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			// 右括号才会判断是否合法，peek>0保证了下标有效，栈顶是左括号，找到一个合法对
			if (cs[i] == ')' && stack.peek() >= 0 && cs[stack.peek()] == '(') {
				stack.pop();
				maxLen = Math.max(maxLen, i - stack.peek()); // 更新maxlen
			} else { 
				stack.push(i);
			}
		}
		return maxLen;
	}

	/**
	 * 求s最后一个单词的长度
	 * @param s
	 * @return
	 */
	public int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		StringBuilder sb = new StringBuilder();
		int len = 0;
		char[] cs = s.trim().toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (i == cs.length - 1) {
				return sb.length() + 1;
			}
			if (cs[i] != ' ') {
				sb.append(cs[i]);
			} else {
				len = sb.length();
				sb = new StringBuilder();
			}
		}
		return len;
    }
	
	/**
	 * 求字符串数组的最长公共子串
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String pre = strs[0]; //以str[0]为默认prefix
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(pre) != 0) { //只要当前str不是以pre为头，pre就减1
				pre = pre.substring(0, pre.length() - 1);
			}
		}
		return pre;
	}

	

	/**
	 * 判断一个字符串是不是合法数字，合法数字包括科学输入法、小数
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValidNumber(String s) {
		if (s == null || s.equals(""))
			return false;
		s = s.trim(); // 删除空格
		if (s.length() == 0)
			return false;

		boolean numberSeen = false; // 标识数字
		boolean pointSeen = false; // 标识小数点
		boolean eSeen = false; // 标识E
		boolean numberAfterE = true; // 标识E后面的数字
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) { // 数字
				numberSeen = true;
				numberAfterE = true;
			} else if (s.charAt(i) == '.') {
				if (eSeen || pointSeen) { // e后面不能有小数点，小数点不能重复出现
					return false;
				}
				pointSeen = true; // 标识小数点
			} else if (s.charAt(i) == 'e') {
				if (eSeen || !numberSeen) { // e不能重复出现，e之前不能没有数字
					return false;
				}
				eSeen = true;
				numberAfterE = false; // 遇到e重新开始计算
			} else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				if (i != 0 && s.charAt(i - 1) != 'e') { // +-只能出现在字符串头或者e之后
					return false;
				}
			} else { // 出现e、+-、.、数字以外的直接返回false
				return false;
			}
		}
		return numberSeen && numberAfterE;
	}

	/**
	 * 字符串转数字
	 * @param str
	 * @return
	 */
	public int atoi(String str) {
		if(str == null || str.length() == 0) {
			return 0;
		}
		int sign = 1, i = 0;
		long res = 0;
		// 处理String前面的空格
		while (i < str.length() && str.charAt(i) == ' ') {
			i++;
		}
		if(i == str.length()) return 0;
		// 处理正负号
		if (str.charAt(i) == '+') {
			sign = 1;
			i++;
		} else if (str.charAt(i) == '-') {
			sign = -1;
			i++;
		}
		if(i == str.length()) return 0;
		// 计算数字
		while(i < str.length()) {
			if(Character.isDigit(str.charAt(i))){
				res = res * 10 + str.charAt(i) - '0';
				i++;
				// 越界处理
				if(sign == 1 && res >= Integer.MAX_VALUE)
					return Integer.MAX_VALUE;
				if(sign == -1 && (-res) <= Integer.MIN_VALUE)
					return Integer.MIN_VALUE;
			} else {
				return sign * (int)res;
			}
		}
		return sign * (int)res;
	}
	
	/**
	 * String转float
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public float atof(String str) throws Exception {
		double res = 0.0;
		int i = 0, cnt = 0;
		boolean flag = true;

		// str为空
		if (str == null) {
			return (float) res;
		}

		// 去除多余的空格
		while (i < str.length() && str.charAt(i) == ' ') {
			i++;
		}
		if (i == str.length()) {
			throw new NumberFormatException("String can not be only space");
		}

		// 处理+-号
		if (str.charAt(i) == '+') {
			flag = true;
			i++;
		} else if (str.charAt(i) == '-') {
			flag = false;
			i++;
		}
		if (i == str.length()) {
			throw new NumberFormatException("string can not be alone with +/-");
		}

		// 处理小数点之前的
		while (i < str.length() && str.charAt(i) != '.') {
			if (Character.isDigit(str.charAt(i))) {
				res = res * 10.0 + str.charAt(i) - '0';
				i++;
				cnt++;
				/*
				 * if(cnt == 8) { return (float)(Math.round(res * (flag ? 1.0 :
				 * -1.0))); }
				 */
			} else {
				return (float) (res * (flag ? 1.0 : -1.0));
			}
		}
		if (i++ == str.length())
			return (float) res;
		double divisor = 10;

		// 处理小数点之后
		while (i < str.length()) {
			if (Character.isDigit(str.charAt(i))) {
				res = res + (str.charAt(i) - '0') / divisor;
				divisor *= 10.0;
				i++;
				cnt++;
				/*
				 * if(cnt == 8) { return (float)(res * (flag ? 1.0 : -1.0)); }
				 */
			} else {
				return (float) (res * (flag ? 1.0 : -1.0));
			}
		}

		// 正常返回
		return (float) (res * (flag ? 1.0 : -1.0));
	}

	/**
	 * 用String模拟大数相加
	 * 
	 * @param args
	 */
	public String addString(String num1, String num2) {
		// 异常情况的处理
		if (num1 == null || num1.length() == 0)
			return num2;
		if (num2 == null || num2.length() == 0)
			return num1;

		// 对每个String都是倒序处理，模拟加法由低位到高位的过程
		int i = num1.length() - 1, j = num2.length() - 1;
		int k = Math.max(i, j);
		int[] res = new int[k + 1]; // res用于存放计算中的数据
		int sum = 0, carry = 0;
		while (i >= 0 || j >= 0) {
			int addend1 = i >= 0 ? (num1.charAt(i) - '0') : 0; // 如果i短一些，就用0来补齐
			i--;
			int addend2 = j >= 0 ? (num2.charAt(j) - '0') : 0; // 如果j短一些，就用0来补齐
			j--;
			sum = addend1 + addend2 + carry;
			res[k--] = sum % 10; // 取余是当前位
			carry = sum / 10; // 除数是进位
		}
		// System.out.println(Arrays.toString(res));
		StringBuilder sb = new StringBuilder();
		if (carry != 0)
			sb.append(carry);
		for (int num : res) {
			sb.append(num);
		}
		return sb.toString();
	}

	/**
	 * 用字符串模拟带符号的大数相加
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String addSignedString(String num1, String num2) {
		int sign1 = 1, sign2 = 1;
		if (num1.charAt(0) == '-') {
			sign1 = -1;
			num1 = num1.substring(1, num1.length());
		}
		if (num2.charAt(0) == '-') {
			sign2 = -1;
			num2 = num2.substring(1, num2.length());
		}

		if (sign1 == sign2) {
			String res = addString(num1, num2);
			return sign1 == -1 ? ("-" + res) : res;
		} else {
			if (sign1 == 1) {
				return subString(num1, num2);
			} else {
				return subString(num2, num1);
			}
		}
	}

	/**
	 * 用String模拟大数相减
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String subString(String num1, String num2) {
		int len1 = num1.length(), len2 = num2.length();
		if (len1 > len2) { // num1比num2大
			return subCore(num1, num2);
		} else if (len1 < len2) { // num1比num2小，-反向输入
			return "-" + subCore(num2, num1);
		} else {
			int comp = num1.compareTo(num2);
			if (comp > 0) { // num1比num2大
				return subCore(num1, num2);
			} else if (comp < 0) { // num1比num2小，-反向输入
				return "-" + subCore(num2, num1);
			} else { // 二者相等返回0
				return "0";
			}
		}
	}

	/**
	 * 外层函数保证了num1大于num2
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	private String subCore(String num1, String num2) {
		int i = num1.length() - 1, j = num2.length() - 1;
		int[] res = new int[i + 1];
		int diff = 0, borrow = 0;
		while (i >= 0) {
			int d1 = num1.charAt(i) - '0';
			int d2 = (j >= 0) ? (num2.charAt(j) - '0') : 0;
			diff = d1 + 10 - borrow - d2; // 被减数d1借10减去之前被借位的减去减数d2
			res[i] = diff % 10;
			borrow = diff < 10 ? 1 : 0; // diff小于10了需要借一位
			i--;
			j--;
		}
		i = 0;
		while (res[i] == 0) { // i复位以后，直到不为0为止开始输出
			i++;
		}
		StringBuilder sb = new StringBuilder();
		while (i < res.length) {
			sb.append(res[i++]);
		}
		return sb.toString();
	}

	public String subSignedString(String num1, String num2) {
		int sign1 = 1, sign2 = 1; // 默认是正值
		if (num1.charAt(0) == '-') {
			sign1 = -1;
			num1 = num1.substring(1, num1.length());
		}
		if (num2.charAt(0) == '-') {
			sign2 = -1;
			num2 = num2.substring(1, num2.length());
		}

		if (sign1 == sign2) { // 如果两个数的符号相等
			if (sign1 == 1) { // 如果都是正数，直接相减
				return subString(num1, num2);
			} else { // 都是负数，后减前
				return subString(num2, num1);
			}
		} else { // 符号不等
			if (sign1 == 1) { // 正数减去负数
				return addString(num1, num2);
			} else { // 负数减去正数
				return "-" + addString(num1, num2);
			}
		}
	}

	/**
	 * 用字符串模拟大数相乘
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		if (num1 == null || num2 == null)
			return null;
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int p1 = i + j; // p1表示乘数所在的上一位
				int p2 = i + j + 1; // p2表示乘数所在的当前位
				int multi = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int sum = multi + pos[p2]; // 当前位+乘积
				pos[p1] += sum / 10; // 给上一位加进位
				pos[p2] = sum % 10; // 当前位留余数
			}
		}
		// System.out.println(Arrays.toString(pos));
		StringBuilder sb = new StringBuilder();
		for (int i : pos) {
			if (!(sb.length() == 0 && i == 0)) // 跳过前面无用的0
				sb.append(i);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}

	/**
	 * 求s中包含t中所有字符的最小窗口
	 * 76. Minimum Window Substring
	 * S = "ADOBECODEBANC"
	 * T = "ABC"
	 * =="BANC"
	 * @param s
	 * @param t
	 * @return
	 */
	public String minWindow(String s, String t) {
		int[] m = new int[128];
		// Statistic for count of char in t
		for (Character c : t.toCharArray())
			m[c]++;
		// counter represents the number of chars of t to be found in s.
		int start = 0, end = 0, counter = t.length(), minStart = 0, minLen = Integer.MAX_VALUE;
		char[] cs = s.toCharArray();

		// Move end to find a valid window.
		while (end < s.length()) {
			// If char in s exists in t, decrease counter
			if (m[cs[end]] > 0)
				counter--;
			// Decrease m[s[end]]. If char does not exist in t, m[s[end]] will
			// be negative.
			m[cs[end]]--;
			end++;

			// 此时找到了一个符合条件的窗口
			while (counter == 0) {
				if (end - start < minLen) {
					minStart = start;
					minLen = end - start;
				}
				m[cs[start]]++; // 向map中添加cs[start]
				// 对于s中的字符，map起始值为0，end遍历--，start遍历++，不会超过0.
				// 对于t中的字符，map起始值为1，end遍历--，start遍历++，回到1，就可以更新counter
				if (m[cs[start]] > 0)
					counter++;
				start++;
			}
		}
		if (minLen != Integer.MAX_VALUE) // minLen发生了变化
			return s.substring(minStart, minStart + minLen);
		return "";
	}

	/**
	 * 3. Longest Substring Without Repeating Characters
	 * sliding window，O(n)time，O(n)space
	 * 最长的不包含重复字符的子串
	 * "pwwkew"->"wke"
	 * "abcabcbb"->"abc"
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring1(String s) {
		int n = s.length();
		int res = 0;
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j <= n; j++) {
				if(allUnique(s, i, j)) {
					res = Math.max(res, j-i);
				}
			}
		}
		return res;
	}
	
	public boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
		for(int i = start; i < end; i++) {
			if(set.contains(s.charAt(i))) return false;
			set.add(s.charAt(i));
		}
		return true;
	}

	public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

	public int lengthOfLongestSubstring4(String s) {
		if (s == null || s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int maxLen = 0;
		for (int i = 0, j = 0; i < s.length(); i++) {
			char key = s.charAt(i);
			if (map.containsKey(key)) {
				j = Math.max(j, map.get(key) + 1); // 指针j记录下一次不重复子串的开始，例如pww，j指向w
			}
			map.put(key, i);
			maxLen = Math.max(maxLen, i - j + 1); // 在不出现重复的情况下更新最大长度，例如pww，i更新至2
		}
		return maxLen;
	}
	
	public int lengthOfLongestSubstring3(String s) {
		int[] map = new int[128];
		int counter = 0, begin = 0, end = 0, len = 0;
		char[] cs = s.toCharArray();
		while (end < s.length()) {
			if (map[cs[end]] > 0) { // 如果含有重复字符，cnt就++
				counter++;
			}
			map[cs[end]]++;
			end++;

			while (counter > 0) { // 循环处理直到cnt==0，表示没有重复字符
				if (map[cs[begin]] > 1) {
					counter--;
				}
				map[cs[begin]]--;
				begin++; // 有重复的就需要将begin前移
			}
			len = Math.max(len, end - begin); // 更新len的长度
		}
		return len;
	}

	/**
	 * 返回S中L的所有串组合成的串的起始位置，L中的串长度相同。
	 * 30. Substring with Concatenation of All Words
	 * s: "barfoothefoobarman"
	 * words: ["foo", "bar"]
	 * You should return the indices: [0,9].
	 * @param S
	 * @param L
	 * @return
	 */
	public List<Integer> findSubString(String s, String[] words) {
		List<Integer> res = new ArrayList<Integer>();
		if (s == null || words == null || words.length == 0)
			return res;
		int len = words[0].length(); // 每个字符串的长度
		int cnt = words.length; // L中有多少个字符串

		Map<String, Integer> map = new HashMap<String, Integer>(); // 用map来保存字符串及其出现次数
		for (String tmp : words) {
			map.put(tmp, map.containsKey(tmp) ? map.get(tmp) + 1 : 1);
		}

		int threshold = s.length() - len * cnt; // 不可能超过这个数
		// 遍历s
		for (int i = 0; i <= threshold; i++) { // i是一个一个往前移动
			Map<String, Integer> copy = new HashMap<String, Integer>(map);
			// 每前进一位，用j卡出每个word来，每个word步长为len，表示向前的第几个word
			for (int j = 0; j < cnt; j++) {
				String str = s.substring(i + j * len, i + j * len + len);
				if(copy.containsKey(str)) {
					int count = copy.get(str);
					if(count == 1) copy.remove(str); // 计数是1的时候，在map中删除
					else copy.put(str, count-1); // cnt--对于重复的删一个
					if(copy.isEmpty()) {
						res.add(i); // 如果把拷贝过来的words全部删除了，就说明当前的i是一个开始节点
						break;
					}
				} else { // 不包含，退出该次循环，导致i前移
					break;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		StringSeries tester = new StringSeries();
		//String s = "    hello    world!  xutao";
		//int n = 3;

		// t.longestValidParentheses("(())");
		// System.out.println(t.lengthOfLongestSubstring("pww"));
		//String num1 = "90";
		//String num2 = "88";
		// System.out.println(t.addString(num1, num2));
		// System.out.println(t.subString(num1, num2));
		
		/*String s = "ADOBECODEBANC";
		String t = "ABC";
		System.out.println(tester.minWindow(s, t));*/
		
		//String S = "barfoothefoobarman";
		//String[] L = {"bar", "foo"};
		//System.out.println(t.findSubstring(S, L));
		//System.out.println(tester.atoi("1"));
		
		/*String s = "bcabc";
		System.out.println(tester.removeDuplicateLetters(s));*/
		
		String s = ")()())";
		System.out.println(tester.longestValidParentheses(s));
	}

}
