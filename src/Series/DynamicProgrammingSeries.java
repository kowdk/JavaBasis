package Series;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamicProgrammingSeries {

	/**
	 * 70. Climbing Stairs
	 * һ��n��¥�ݣ�ÿ����1���2�㣬�ж����ֲ�ͬ���߷���쳲�����
	 * @param n
	 * @return
	 */
	public int climbStairs(int n) {
        int[] nums = new int[n];
        if(n == 1) return 1;
        if(n == 2) return 2;
        
        nums[0] = 1;
        nums[1] = 2;
        for(int i = 2; i < n; i++) {
        	nums[i] = nums[i-1] + nums[i-2];
        }
        
        return nums[n-1];
    }
	
	/**
	 * nums�����ڵ�Ԫ�ز�����ӣ���������ֵ
	 * 198. House Robber
	 * [5, 2, 7, 3, 6, 9]
	 * dp����
	 * 0 0
	 * 0 5
	 * 5 2
	 * 5 12
	 * 12 8
	 * 12 18
	 * 18 21
	 * @param nums
	 * @return
	 */
	public int rob1(int[] nums) {
		if(nums.length == 0) return 0;
 		int[][] dp = new int[nums.length + 1][2]; // dp[i][0]��ʾ����nums[i-1],dp[i][1]��ʾ��nums[i-1]
		for (int i = 1; i <= nums.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]); // [i][0]��ʾ����nums[i]�������ϸ�dp�����ֵ
			dp[i][1] = dp[i - 1][0] + nums[i - 1]; // [i][1]��ʾ��nums[i]�����·������������ֵ
		}
		return Math.max(dp[nums.length][0], dp[nums.length][1]); // dp[i]���벻�������˵�ǰ���ܵ����ֵ
	}
	
	/**
	 * ����Ӳ���������Ҫ�ҵ�������������С����Ҫ�ҵ�Ӳ�Ҹ���
	 * 322. Coin Change
	 * coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int minCoins(int[] coins, int amount) {
		// dp[i]��ʾ��ǰamount������Ҫ�Ҽ���
		int[] dp = new int[amount + 1];
		// init dp
		dp[0] = 0; // 0Ԫ��0��
		for(int i = 1; i <= amount; i++) {
			dp[i] = -1; // dp�ó�ֵΪ-1
		}
		// refresh dp
		for(int i = 1; i <= amount; i++) { // �������˴�1������Ǯ11
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < coins.length; j++) { // �ڲ������Ǯ����1,2,5
				if(i >= coins[j] && dp[i - coins[j]] != -1) { // �����ǰamount����coin[j]����֮ǰ��Ǯ�к÷������͵��Ӻ÷���
					min = Math.min(min, dp[i - coins[j]] + 1);
				}
			}
			dp[i] = min == Integer.MAX_VALUE ? -1 : min;
		}
		// return the last element in dp
		return dp[amount];
	}
	
	/**
	 * ������ͬ���뷽���ĸ���
	 * 91. Decode Ways
	 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
	 * 'A' -> 1
	 * 'B' -> 2
	 * ...
	 * 'Z' -> 26
	 * Given an encoded message containing digits, determine the total number of ways to decode it.
	 * "12" -> AB��L��2
	 * @param s
	 * @return
	 */
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) 
        	return 0;
        if(s.startsWith("0")) return 0;
        // create dp
        int[] dp = new int[s.length() + 1];
        // init dp
        dp[0] = 1; // ""ֻ��һ�ֽ��뷽��
        dp[1] = 1; // һ���ַ�ֻ��һ�ֽ��뷽��
        // refresh dp, 127
        for(int i = 2; i <= s.length(); i++) {
        	int first = Integer.valueOf(s.substring(i-1, i)); // 2,7
        	int second = Integer.valueOf(s.substring(i-2, i)); // 12,27
        	if(first >= 1 && first <= 9) {
        		dp[i] += dp[i-1];
        	} 
        	if(second >= 10 && second <= 26) {
        		dp[i] += dp[i-2];
        	}	
        }
        // return dp[len]
        return dp[s.length()];
    }
	
    /**
	 * 115. Distinct Subsequences
	 * ��s���ҳ�Ψһ����t�ĸ���
	 * S = "rabbbit", T = "rabbit" -> 3
	 * S = "abdacgblc", T = "abc" -> 4
	 * @param s
	 * @param t
	 * @return
	 */
    public int numDistinct(String s, String t) {
        if(s.length() == 0 || t.length() == 0) 
        	return 0;
        // create dp array
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        // init dp array
        for(int i = 0; i < dp[0].length; i++) { // �մ����ҽ��ܱ�ƥ��һ��
        	dp[0][i] = 1;
        }
        // refresh dp array
        for(int i = 1; i < dp.length; i++) {
        	for(int j = 1; j < dp[0].length; j++) {
        		if(t.charAt(i - 1) == s.charAt(j - 1)) { // dp[i-1][j-1]��ʾû��s[j-1]��t[i-1]ʱ�����������ټ�����t[i-1]�����
        			dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
        		} else { // ��ǰ�ַ�����ȣ�������һ��ƥ������
        			dp[i][j] = dp[i][j-1];
        		}
        	}
        }
        // return the result
        return dp[t.length()][s.length()];
    }
    
    /**
     * 72. Edit Distance
     * @param s
     * @param p
     * @return
     */
    public int minDistance(String s, String p) {
		// exception handling
		if (s.length() == 0 && p.length() == 0)
			return 0;
		if (s.length() == 0)
			return p.length();
		if (p.length() == 0)
			return s.length();
		char[] ss = s.toCharArray();
		char[] ps = p.toCharArray();
		// init dp
		int m = s.length(), n = p.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= n; j++) {
			dp[0][j] = j;
		}
		// refresh dp
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (ss[i-1] == ps[j-1]) {
					dp[i][j] = dp[i - 1][j - 1]; // ����Ҫ�޸�
				} else {
					//�ϡ����������һ����С�ģ�+1��ʾ�Ķ�һ��
					dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j], dp[i][j - 1]) + 1);
				}
			}
		}
		return dp[m][n];
	}
    
    
    /**
	 * 97. Interleaving String
	 * s1:aab
	 * s2:axy
	 * s3:aaxaby -> true
	 * s3:aabayx -> false
	 * �ж�s3�Ƿ��ܳ�Ϊs1��s2�ĺͣ�s1��s2���Ի�����룬���Ǹ���˳���ܷ����仯
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
    public boolean isInterleave(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null)
			return false;
		int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
		if (len1 == 0)
			return s2.equals(s3);
		if (len2 == 0)
			return s1.equals(s3);
		if (len1 + len2 != len3)
			return false;
		char[] cs1 = s1.toCharArray();
		char[] cs2 = s2.toCharArray();
		char[] cs3 = s3.toCharArray();
		boolean[][] dp = new boolean[len1 + 1][len2 + 1];
		
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				int k = i + j - 1;
				if (i == 0 && j == 0) { // init dp[0][0]
					dp[i][j] = true;
				} else if (i == 0) {
					if (cs2[j - 1] == cs3[k]) { // init dp[0][j]
						dp[i][j] = dp[i][j - 1];
					}
				} else if (j == 0) {
					if (cs1[i - 1] == cs3[k]) { // init dp[i][0]
						dp[i][j] = dp[i - 1][j];
					}
				} else { // refresh dp
					dp[i][j] = (cs1[i - 1] == cs3[k] ? dp[i - 1][j] : false)
							|| (cs2[j - 1] == cs3[k] ? dp[i][j - 1] : false);
				}
			}
		}
		return dp[len1][len2];
    }
    
    /**
	 * �����������
	 * ����
	 * sa[i-1]��sb[j-1]�������⣺
	 * sa[i-1] = sb[j-1], ���������sa[i-2]��sb[j-2]�������������+1
	 * sa[i-1] != sb[j-1], ���������sa[i-2]��sb[j-1]�Լ�sa[i-1]��sb[j-2]�������������
	 * */
	public static int longestCommonSubsequence(String sa, String sb) {
		if(sa == null || sb == null) {
			return 0;
		}
		int lenA = sa.length();
		int lenB = sb.length();
		int[][] dp = new int[lenA+1][lenB+1];
		// dp init and refresh
		for(int i = 0; i <= lenA; i++) {
			for(int j = 0; j <= lenB; j++) {
				if(i == 0 || j == 0) {
					dp[i][j] = 0; // �մ���sa��sbû�й���������
				} else {
					if(sa.charAt(i-1) == sb.charAt(j-1)) {
						dp[i][j] = dp[i-1][j-1] + 1; // ����ȵļ�һ��
					} else {
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]); //û����ȵı���ĿǰΪֹ��Ĺ��������г���
					}
				}
			}
		}
		return dp[lenA][lenB];
	}
    
	/**
	 * ������Ӵ�
	 * ���飬�Ӻ���ǰ������i��i-1�Ĳ��
	 * dp[i][j]������sa[i-1]��sb[j-1]������Ӵ��ĳ���
	 * ������Ӵ�������i��˵�����sa[i]!=sb[j]�����Ⱦ�Ϊ0���������dp[i-1][j-1]+1
	 * */	
	public static int longestCommonSubString(String sa, String sb){
		if(sa == null || sb == null)
			return 0;
		int max = 0;
		int lenA = sa.length();
		int lenB = sb.length();
		// init dp
		int[][] dp = new int[lenA+1][lenB+1];
		for(int i = 0; i <= lenA; i++) {
			dp[0][i] = 0; // �մ���saû�й����Ӵ�
		}
		for(int j = 0; j <= lenB; j++) {
			dp[j][0] = 0; // �մ���sbû�й����Ӵ�
		}
		// refresh dp
		for(int i = 1; i <= lenA; i++) {
			for(int j = 1; j <= lenB; j++) {
				if(sa.charAt(i-1) == sb.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1; // ����һ���Ĺ����Ӵ�
				} else {
					dp[i][j] = 0; // �޹����Ӵ�
				}
				// ����max�����ֵ�ǰ������Ӵ��ĳ���
				max = Math.max(dp[i][j], max);
			}
		}
		return max;
	}
	
	/**
	 * 300. Longest Increasing Subsequence
	 * ����ĵ���������
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int nums[]) {
    	int[] dp = new int[nums.length];
    	Arrays.fill(dp, 1);
    	for(int i = 1; i < dp.length; i++) {
    		for(int j = 0; j < i; j++) {
    			// dp[i]��¼���ǵ�iΪֹ��ĵ�����������
    			if(nums[i] > nums[j]) { // �������i�ı�ǰ��Ĵ󣬾�ȡ������ǰ��ĵ���������+1�Ľϴ�ֵ
    				dp[i] = Math.max(dp[i], dp[j] + 1);
    			}
    		}
    	}
    	return dp[dp.length-1];
    }
	
	/**
	 * ����Ļ���������
	 * @param str
	 * @return
	 */
	public static int findLPS(char[] str) {
		// create dp
		int strLen = str.length;// 6
		int[][] dp = new int[strLen][strLen];
		// init dp
		for (int i = 0; i < strLen; i++) {
			dp[i][i] = 1;
		}
		// refresh dp
		for (int len = 2; len <= strLen; len++) { // len==[2,6]
			int diff = len - 1; // j-i is the same for Diagonal line..
			for (int i = 0; i < strLen - len + 1; i++) {
				int j = i + diff; // dp[i][j] is in the proper diagonal line..
				if (len == 2 && str[i] == str[j]) { // aa or bb or etc..
					dp[i][j] = 2;
				} else if (str[i] == str[j]) { // aba or abcba or etc..
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); // others..
				}
			}
		}
		//System.out.println(Arrays.deepToString(dp));
		return dp[0][strLen - 1];
	}
	
	/**
	 * ���������������
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int max = 0;
		for(int i = 1; i < nums.length; i++) { // dp[i]�����˵�ǰ���ĺ�ֵ
			dp[i] = Math.max(dp[i-1] + nums[i], nums[i]); // �������nums[i]������nums[i]����󣬾��ú���
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
	/**
	 * ����һ����������ֵ�ĵ��������У�������������Ҫn*n��
	 * @param nums
	 * @return
	 */
	public int LISwithMaxSum(int[] nums){
		int[] dp = new int[nums.length];
		int max = 0;
		dp[0] = nums[0];
		for(int i = 1; i < nums.length; i++) {
			dp[i] = nums[i]; // dp[i]��¼�˵�iΪֹ���ĵ������еĺ�ֵ
			for(int j = 0; j < i; j++) { // ����i֮ǰ������ֵ
				if(nums[j] < nums[i]) { // �������
					dp[i] = Math.max(dp[j] + nums[i], dp[i]); // ��һ����ǰ����ֵ
					max = Math.max(max, dp[i]); //���µ�ǰ����ֵmax�����ڷ���
				}
			}
		}
		
		return max;
	}
	
	/**
	 * 10. Regular Expression Matching
	 * '.' Matches any single character.
	 * '*' Matches zero or more of the preceding element.
	 * isMatch("aa","a") �� false
     * isMatch("aa","aa") �� true
     * isMatch("aaa","aa") �� false
     * isMatch("aa", "a*") �� true
     * isMatch("aa", ".*") �� true
     * isMatch("ab", ".*") �� true
     * isMatch("aab", "c*a*b") �� true
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		// exception handling
		if (s.length() == 0 && p.length() == 0)
			return true;
		// create dp and init dp status
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		// handle patterns like a* or a*b* etc.
		for (int i = 1; i <= n; i++) {
			if (p.charAt(i - 1) == '*') {
				dp[0][i] = dp[0][i - 2];
			}
		}
		// refresh dp status
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') { 
					dp[i][j] = dp[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					dp[i][j] = dp[i][j - 2]; // ���ȳн�����ַ���*�޹��׵����
					if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
						dp[i][j] |= dp[i - 1][j];
					}
				} else {
					dp[i][j] = false;
				}
			}
		}
		// return the right bottom corner
		return dp[m][n];
	}
	
	/**
	 * 44. Wildcard Matching
	 * '?' Matches any single character.
	 * '*' Matches any sequence of characters (including the empty sequence).
	 * isMatch("aa","a") �� false
	 * isMatch("aa","aa") �� true
	 * isMatch("aaa","aa") �� false
	 * isMatch("aa", "*") �� true
	 * isMatch("aa", "a*") �� true
	 * isMatch("ab", "?*") �� true
	 * isMatch("aab", "c*a*b") �� false
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isWildcardMatch(String s, String p) {
        int m=s.length(), n=p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i=1; i<=m; i++) {
            dp[i][0] = false;
        }
        for(int j=1; j<=n; j++) {
            if(p.charAt(j-1)=='*'){ //��Ϊ*����ƥ��մ�
                dp[0][j] = true;
            } else {
                break;
            }
        }
        // i��s��ָ�룬j��p��ָ��
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
            	//����ͨ�����ֻҪ��һ���ַ�ƥ���ҵ�ǰλƥ����У�x==x����x==?
            	if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
            		dp[i][j] = dp[i-1][j-1];
            	}
            	// ��ͨ�����ֻ��Ҫs����ǰһλ��ƥ�䣬s�ĵ�ǰλ��*ƥ��Ϳ����ˣ�����p���˵�ǰλ�Ϳ���ƥ��s�ˣ�ͨ�����ƥ��һ�������Ϳ�����
                // s: xayl, p:x?y*, dp[i-1][j]��ʾxay��x?y*�Ƿ�ƥ�䣬dp[i][j-1]��ʾxayl��x?y�Ƿ�ƥ��
            	else if (p.charAt(j-1) == '*') { 
            		dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
	
	/**
	 * �ж�s�Ƿ��ָܷ��wordDict�еĵ���
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean wordBreak(String s, Set<String> wordDict) {
        if(s == null || s.length() == 0) {
			return false;
		}
		int n = s.length();
		boolean[] dp = new boolean[n+1]; // dp[i]��ʾs.subString(0,i);
		dp[0] = true; // ""Ĭ���Ǵ��ڵ�
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= i; j++) {
				String curr = s.substring(j, i); // ˫��ѭ���ҵ���ǰ���Ӵ�
				if(dp[j] && wordDict.contains(curr)) { // ��������֮ǰ����true���ҵ�ǰ���ֵ����
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
    }
	
	/**
	 * ����s��һ���ֵ䣬����s�ܹ��ָ�ɵĲ�ͬ���
	 * s = "catsanddog",
	 * dict = ["cat", "cats", "and", "sand", "dog"].
	 * A solution is ["cats and dog", "cat sand dog"].
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public List<String> wordBreakII(String s, Set<String> wordDict) {
		return dfs(s, wordDict, new HashMap<String, LinkedList<String>>());
	}
	
	public List<String> dfs(String s, Set<String> dict, HashMap<String, LinkedList<String>> map){
    	if(map.containsKey(s)){
    		return map.get(s);
    	}
    	LinkedList<String> list = new LinkedList<String>();
    	if(s.length() == 0) {
    		list.add("");
    		return list;
    	}
    	for(String word : dict) {
    		if(s.startsWith(word)) {
    			List<String> subList = dfs(s.substring(word.length()), dict, map);
    			for(String subWord : subList) {
    				list.add(word + (subWord.isEmpty() ? "" : " ") + subWord);
    			}
    		}
    	}
    	map.put(s, list);
    	return list;
    }
	
	public List<String> wordBreakIIUsingMap(String s, Set<String> dict) {
		// dp�Ӻ���ǰ��key��s���±꣬list�Ƕ�Ӧ������Ľ������map����dp����
        Map<Integer, List<String>> validMap = new HashMap<Integer, List<String>>();

        // dp��ʼ��
        List<String> l = new ArrayList<String>();
        l.add("");
        validMap.put(s.length(), l); 

        // dp����
        for(int i = s.length() - 1; i >= 0; i--) { // �����һ���ַ���ʼ
            List<String> values = new ArrayList<String>(); // ��¼��ǰ�Ľ��
            for(int j = i + 1; j <= s.length(); j++) { // j��i+1��ʼ����β
            	String curr = s.substring(i, j); // ������Ӧ��substring
                if (dict.contains(curr)) { // dictҪ����curr
                    for(String word : validMap.get(j)) { // word��֮���
                        values.add(curr + (word.isEmpty() ? "" : " ") + word); // curr�ǵ�ǰ��������
                    }
                }
            }
            validMap.put(i, values); // ��ǰλ�ø���map
        }
        // ���ؽ��
        return validMap.get(0);
    }
	
	public static void main(String[] args) {
		DynamicProgrammingSeries tester = new DynamicProgrammingSeries();
		/*String s = "123";
		System.out.println(tester.numDecodings(s));*/
		/*String s1 = "axyz", s2 = "aab", s3 = "aaxabyz";
		System.out.println(tester.isInterleave(s1,s2,s3));*/
		/*String s = "xaylmz";
		String p = "x?y*z";
		System.out.println(tester.isWildcardMatch(s, p));*/
		/*String s = "xutao";
		Set<String> dict = new HashSet<String>();
		dict.add("xu");
		dict.add("ut");
		dict.add("tao");
		System.out.println(tester.wordBreak(s, dict));*/
		String s = "catsanddog";
		Set<String> dict = new HashSet<String>();
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		List<String> res = tester.wordBreakIIUsingMap(s, dict);
		System.out.println(res);
	}

}
