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
	 * ȥ���ַ����ж���Ŀո�ȥ����ͷβ�Ŀո�
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
	 * ���ַ����еĿո��滻Ϊ20%���Ӻ���ǰ
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
	 * 1->11->21->1211,���ַ���
	 * 
	 * @param n
	 * @return
	 */
	public String countAndSay(int n) {
		StringBuilder prev = new StringBuilder("1");
		StringBuilder curr = null;

		for (int i = 1; i < n; i++) { // ��1��n����n-1��
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
	 * ����t��s�е�һ�γ��ֵ�λ�ã����s������t���򷵻�-1
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
	 * �ַ�����zigzagת��
	 * ���룺PAYPALISHIRING
	 * P   A   H   N
	 * A P L S I I G
	 * Y   I   R
	 * �����PAHNAPLSIIGYIR
	 * @param s
	 * @param nRows
	 * @return
	 */
	public String convert(String s, int nRows) {
        if(nRows <= 1) return s;
        char[] str = s.toCharArray();
        String result = "";
        int cycle = 2 * nRows - 2; // ���������
        for(int i = 0; i < nRows; ++i) { // ���д���
            for(int j = i; j < s.length(); j = j + cycle) {
               result = result + str[j]; // ������������
               int secondJ = (j - i) + cycle - i; // ��������е��±� 
               if(i != 0 && i != nRows-1 && secondJ < s.length()) // ֻ�г��˵�һ�к����һ�вŻ��ж�����
                   result = result + str[secondJ];
            }
        }
        return result;
    }
	
	/**
	 * 128. Longest Consecutive Sequence
	 * �����������
	 * [100, 4, 200, 1, 3, 2]��[1, 2, 3, 4]. Return its length: 4.
	 * @param nums
	 * @return
	 */
	public int longestConsecutive(int[] nums) {
		int max = 0;
		// key��Ԫ��ֵ��value�ǵ�ǰ����������ֳ���
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) { // �������ظ�Ԫ��
				int lo = map.containsKey(nums[i] - 1) ? map.get(nums[i] - 1)
						: 0; // ��ǰԪ�ص�ǰһ��Ԫ�������
				int hi = map.containsKey(nums[i] + 1) ? map.get(nums[i] + 1)
						: 0; // ��ǰԪ�ص���һ��Ԫ�������
				int curr = lo + hi + 1;
				max = Math.max(max, curr); // ������󳤶�

				map.put(nums[i], curr); // ����ǰֵ������ȴ���hash

				map.put(nums[i] - lo, curr); // ���µ�ǰ������ȵ���߽�
				map.put(nums[i] + hi, curr); // ���µ�ǰ������ȵ��ұ߽�
			}
		}
		return max;
	}

	/**
	 * 316. Remove Duplicate Letters
	 * ɾ���ַ����е��ظ���ĸ,����ԭ�ַ��������˳�򲻱䣬�����ֵ���ֵ��С������ cbacdcbc->acdb
	 * "bcabc" -> "abc"
	 * "cbacdcbc" -> "acdb"
	 * @param s
	 * @return
	 */
	public String removeDuplicateLetters(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}

		int[] cnt = new int[26]; // cnt��¼�ַ����ִ���
		boolean[] visited = new boolean[26]; // �ַ��Ƿ񱻷��ʹ�
		char[] cs = s.toCharArray();
		for (char c : cs) { // ���Ƚ�cntװ��
			cnt[c - 'a'] += 1;
		}

		Stack<Character> stack = new Stack<Character>(); // ��¼���ظ��ַ�
		for (char c : cs) {
			int i = c - 'a';
			cnt[i]--; // ÿ���Ƚ�cnt--��ʾ������һ����Ԫ�أ�����cnt��ȫ��Ϊ0
			if (visited[i]) // visited�Ƿ�Ϊ����ջ��Ԫ��һһ��Ӧ
				continue;

			while (!stack.isEmpty() && c < stack.peek()
					&& cnt[stack.peek() - 'a'] > 0) { // ��ջ���ַ�С����ջ���ַ����ֹ���˵�����滹�У�������Գ�ջ
				visited[stack.pop() - 'a'] = false; // ����ջԪ�ػ���Ϊδ����
			}

			stack.push(c);
			visited[i] = true; // ����visited��ȫ��Ϊtrue
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.insert(0, stack.pop());
		}
		return sb.toString();
	}

	/**
	 * ��ת�ַ���
	 * 
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		if (s == null || s.equals("")) // ���sΪ��ָ����߳���Ϊ��
			return s;

		char[] cs = reverse(s.toCharArray(), 0, s.length() - 1); // �Ƚ��ַ������巴ת
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
	 * �ַ�������ĺϷ����Ŷ�
	 * ")()())" -> "()()", return 4
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<Integer>(); // stack���ڴ�����������±�������ű���
		int maxLen = 0;
		stack.push(-1); // ��ֹ()����߽�������ڸ���maxLen��ʱ���ж������Լ�
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			// �����ŲŻ��ж��Ƿ�Ϸ���peek>0��֤���±���Ч��ջ���������ţ��ҵ�һ���Ϸ���
			if (cs[i] == ')' && stack.peek() >= 0 && cs[stack.peek()] == '(') {
				stack.pop();
				maxLen = Math.max(maxLen, i - stack.peek()); // ����maxlen
			} else { 
				stack.push(i);
			}
		}
		return maxLen;
	}

	/**
	 * ��s���һ�����ʵĳ���
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
	 * ���ַ��������������Ӵ�
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String pre = strs[0]; //��str[0]ΪĬ��prefix
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(pre) != 0) { //ֻҪ��ǰstr������preΪͷ��pre�ͼ�1
				pre = pre.substring(0, pre.length() - 1);
			}
		}
		return pre;
	}

	

	/**
	 * �ж�һ���ַ����ǲ��ǺϷ����֣��Ϸ����ְ�����ѧ���뷨��С��
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValidNumber(String s) {
		if (s == null || s.equals(""))
			return false;
		s = s.trim(); // ɾ���ո�
		if (s.length() == 0)
			return false;

		boolean numberSeen = false; // ��ʶ����
		boolean pointSeen = false; // ��ʶС����
		boolean eSeen = false; // ��ʶE
		boolean numberAfterE = true; // ��ʶE���������
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) { // ����
				numberSeen = true;
				numberAfterE = true;
			} else if (s.charAt(i) == '.') {
				if (eSeen || pointSeen) { // e���治����С���㣬С���㲻���ظ�����
					return false;
				}
				pointSeen = true; // ��ʶС����
			} else if (s.charAt(i) == 'e') {
				if (eSeen || !numberSeen) { // e�����ظ����֣�e֮ǰ����û������
					return false;
				}
				eSeen = true;
				numberAfterE = false; // ����e���¿�ʼ����
			} else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
				if (i != 0 && s.charAt(i - 1) != 'e') { // +-ֻ�ܳ������ַ���ͷ����e֮��
					return false;
				}
			} else { // ����e��+-��.�����������ֱ�ӷ���false
				return false;
			}
		}
		return numberSeen && numberAfterE;
	}

	/**
	 * �ַ���ת����
	 * @param str
	 * @return
	 */
	public int atoi(String str) {
		if(str == null || str.length() == 0) {
			return 0;
		}
		int sign = 1, i = 0;
		long res = 0;
		// ����Stringǰ��Ŀո�
		while (i < str.length() && str.charAt(i) == ' ') {
			i++;
		}
		if(i == str.length()) return 0;
		// ����������
		if (str.charAt(i) == '+') {
			sign = 1;
			i++;
		} else if (str.charAt(i) == '-') {
			sign = -1;
			i++;
		}
		if(i == str.length()) return 0;
		// ��������
		while(i < str.length()) {
			if(Character.isDigit(str.charAt(i))){
				res = res * 10 + str.charAt(i) - '0';
				i++;
				// Խ�紦��
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
	 * Stringתfloat
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public float atof(String str) throws Exception {
		double res = 0.0;
		int i = 0, cnt = 0;
		boolean flag = true;

		// strΪ��
		if (str == null) {
			return (float) res;
		}

		// ȥ������Ŀո�
		while (i < str.length() && str.charAt(i) == ' ') {
			i++;
		}
		if (i == str.length()) {
			throw new NumberFormatException("String can not be only space");
		}

		// ����+-��
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

		// ����С����֮ǰ��
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

		// ����С����֮��
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

		// ��������
		return (float) (res * (flag ? 1.0 : -1.0));
	}

	/**
	 * ��Stringģ��������
	 * 
	 * @param args
	 */
	public String addString(String num1, String num2) {
		// �쳣����Ĵ���
		if (num1 == null || num1.length() == 0)
			return num2;
		if (num2 == null || num2.length() == 0)
			return num1;

		// ��ÿ��String���ǵ�����ģ��ӷ��ɵ�λ����λ�Ĺ���
		int i = num1.length() - 1, j = num2.length() - 1;
		int k = Math.max(i, j);
		int[] res = new int[k + 1]; // res���ڴ�ż����е�����
		int sum = 0, carry = 0;
		while (i >= 0 || j >= 0) {
			int addend1 = i >= 0 ? (num1.charAt(i) - '0') : 0; // ���i��һЩ������0������
			i--;
			int addend2 = j >= 0 ? (num2.charAt(j) - '0') : 0; // ���j��һЩ������0������
			j--;
			sum = addend1 + addend2 + carry;
			res[k--] = sum % 10; // ȡ���ǵ�ǰλ
			carry = sum / 10; // �����ǽ�λ
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
	 * ���ַ���ģ������ŵĴ������
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
	 * ��Stringģ��������
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String subString(String num1, String num2) {
		int len1 = num1.length(), len2 = num2.length();
		if (len1 > len2) { // num1��num2��
			return subCore(num1, num2);
		} else if (len1 < len2) { // num1��num2С��-��������
			return "-" + subCore(num2, num1);
		} else {
			int comp = num1.compareTo(num2);
			if (comp > 0) { // num1��num2��
				return subCore(num1, num2);
			} else if (comp < 0) { // num1��num2С��-��������
				return "-" + subCore(num2, num1);
			} else { // ������ȷ���0
				return "0";
			}
		}
	}

	/**
	 * ��㺯����֤��num1����num2
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
			diff = d1 + 10 - borrow - d2; // ������d1��10��ȥ֮ǰ����λ�ļ�ȥ����d2
			res[i] = diff % 10;
			borrow = diff < 10 ? 1 : 0; // diffС��10����Ҫ��һλ
			i--;
			j--;
		}
		i = 0;
		while (res[i] == 0) { // i��λ�Ժ�ֱ����Ϊ0Ϊֹ��ʼ���
			i++;
		}
		StringBuilder sb = new StringBuilder();
		while (i < res.length) {
			sb.append(res[i++]);
		}
		return sb.toString();
	}

	public String subSignedString(String num1, String num2) {
		int sign1 = 1, sign2 = 1; // Ĭ������ֵ
		if (num1.charAt(0) == '-') {
			sign1 = -1;
			num1 = num1.substring(1, num1.length());
		}
		if (num2.charAt(0) == '-') {
			sign2 = -1;
			num2 = num2.substring(1, num2.length());
		}

		if (sign1 == sign2) { // ����������ķ������
			if (sign1 == 1) { // �������������ֱ�����
				return subString(num1, num2);
			} else { // ���Ǹ��������ǰ
				return subString(num2, num1);
			}
		} else { // ���Ų���
			if (sign1 == 1) { // ������ȥ����
				return addString(num1, num2);
			} else { // ������ȥ����
				return "-" + addString(num1, num2);
			}
		}
	}

	/**
	 * ���ַ���ģ��������
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
				int p1 = i + j; // p1��ʾ�������ڵ���һλ
				int p2 = i + j + 1; // p2��ʾ�������ڵĵ�ǰλ
				int multi = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int sum = multi + pos[p2]; // ��ǰλ+�˻�
				pos[p1] += sum / 10; // ����һλ�ӽ�λ
				pos[p2] = sum % 10; // ��ǰλ������
			}
		}
		// System.out.println(Arrays.toString(pos));
		StringBuilder sb = new StringBuilder();
		for (int i : pos) {
			if (!(sb.length() == 0 && i == 0)) // ����ǰ�����õ�0
				sb.append(i);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}

	/**
	 * ��s�а���t�������ַ�����С����
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

			// ��ʱ�ҵ���һ�����������Ĵ���
			while (counter == 0) {
				if (end - start < minLen) {
					minStart = start;
					minLen = end - start;
				}
				m[cs[start]]++; // ��map�����cs[start]
				// ����s�е��ַ���map��ʼֵΪ0��end����--��start����++�����ᳬ��0.
				// ����t�е��ַ���map��ʼֵΪ1��end����--��start����++���ص�1���Ϳ��Ը���counter
				if (m[cs[start]] > 0)
					counter++;
				start++;
			}
		}
		if (minLen != Integer.MAX_VALUE) // minLen�����˱仯
			return s.substring(minStart, minStart + minLen);
		return "";
	}

	/**
	 * 3. Longest Substring Without Repeating Characters
	 * sliding window��O(n)time��O(n)space
	 * ��Ĳ������ظ��ַ����Ӵ�
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
				j = Math.max(j, map.get(key) + 1); // ָ��j��¼��һ�β��ظ��Ӵ��Ŀ�ʼ������pww��jָ��w
			}
			map.put(key, i);
			maxLen = Math.max(maxLen, i - j + 1); // �ڲ������ظ�������¸�����󳤶ȣ�����pww��i������2
		}
		return maxLen;
	}
	
	public int lengthOfLongestSubstring3(String s) {
		int[] map = new int[128];
		int counter = 0, begin = 0, end = 0, len = 0;
		char[] cs = s.toCharArray();
		while (end < s.length()) {
			if (map[cs[end]] > 0) { // ��������ظ��ַ���cnt��++
				counter++;
			}
			map[cs[end]]++;
			end++;

			while (counter > 0) { // ѭ������ֱ��cnt==0����ʾû���ظ��ַ�
				if (map[cs[begin]] > 1) {
					counter--;
				}
				map[cs[begin]]--;
				begin++; // ���ظ��ľ���Ҫ��beginǰ��
			}
			len = Math.max(len, end - begin); // ����len�ĳ���
		}
		return len;
	}

	/**
	 * ����S��L�����д���ϳɵĴ�����ʼλ�ã�L�еĴ�������ͬ��
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
		int len = words[0].length(); // ÿ���ַ����ĳ���
		int cnt = words.length; // L���ж��ٸ��ַ���

		Map<String, Integer> map = new HashMap<String, Integer>(); // ��map�������ַ���������ִ���
		for (String tmp : words) {
			map.put(tmp, map.containsKey(tmp) ? map.get(tmp) + 1 : 1);
		}

		int threshold = s.length() - len * cnt; // �����ܳ��������
		// ����s
		for (int i = 0; i <= threshold; i++) { // i��һ��һ����ǰ�ƶ�
			Map<String, Integer> copy = new HashMap<String, Integer>(map);
			// ÿǰ��һλ����j����ÿ��word����ÿ��word����Ϊlen����ʾ��ǰ�ĵڼ���word
			for (int j = 0; j < cnt; j++) {
				String str = s.substring(i + j * len, i + j * len + len);
				if(copy.containsKey(str)) {
					int count = copy.get(str);
					if(count == 1) copy.remove(str); // ������1��ʱ����map��ɾ��
					else copy.put(str, count-1); // cnt--�����ظ���ɾһ��
					if(copy.isEmpty()) {
						res.add(i); // ����ѿ���������wordsȫ��ɾ���ˣ���˵����ǰ��i��һ����ʼ�ڵ�
						break;
					}
				} else { // ���������˳��ô�ѭ��������iǰ��
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
