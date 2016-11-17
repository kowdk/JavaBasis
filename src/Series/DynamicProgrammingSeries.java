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
	 * 一共n层楼梯，每次走1层或2层，有多少种不同的走法，斐波那契
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
	 * nums中相邻的元素不能相加，计算最大和值
	 * 198. House Robber
	 * [5, 2, 7, 3, 6, 9]
	 * dp数组
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
 		int[][] dp = new int[nums.length + 1][2]; // dp[i][0]表示不抢nums[i-1],dp[i][1]表示抢nums[i-1]
		for (int i = 1; i <= nums.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]); // [i][0]表示不抢nums[i]，保持上个dp的最大值
			dp[i][1] = dp[i - 1][0] + nums[i - 1]; // [i][1]表示抢nums[i]，更新符合条件的最大值
		}
		return Math.max(dp[nums.length][0], dp[nums.length][1]); // dp[i]抢与不抢代表了当前可能的最大值
	}
	
	/**
	 * 给定硬币数组和需要找的数量，计算最小的需要找的硬币个数
	 * 322. Coin Change
	 * coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int minCoins(int[] coins, int amount) {
		// dp[i]表示当前amount最少需要找几次
		int[] dp = new int[amount + 1];
		// init dp
		dp[0] = 0; // 0元找0次
		for(int i = 1; i <= amount; i++) {
			dp[i] = -1; // dp置初值为-1
		}
		// refresh dp
		for(int i = 1; i <= amount; i++) { // 外层遍历了从1到待找钱11
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < coins.length; j++) { // 内层遍历零钱集合1,2,5
				if(i >= coins[j] && dp[i - coins[j]] != -1) { // 如果当前amount大于coin[j]并且之前找钱有好方案，就叠加好方案
					min = Math.min(min, dp[i - coins[j]] + 1);
				}
			}
			dp[i] = min == Integer.MAX_VALUE ? -1 : min;
		}
		// return the last element in dp
		return dp[amount];
	}
	
	/**
	 * 给出不同解码方法的个数
	 * 91. Decode Ways
	 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
	 * 'A' -> 1
	 * 'B' -> 2
	 * ...
	 * 'Z' -> 26
	 * Given an encoded message containing digits, determine the total number of ways to decode it.
	 * "12" -> AB、L，2
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
        dp[0] = 1; // ""只有一种解码方法
        dp[1] = 1; // 一个字符只有一种解码方法
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
	 * 在s中找出唯一序列t的个数
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
        for(int i = 0; i < dp[0].length; i++) { // 空串能且仅能被匹配一次
        	dp[0][i] = 1;
        }
        // refresh dp array
        for(int i = 1; i < dp.length; i++) {
        	for(int j = 1; j < dp[0].length; j++) {
        		if(t.charAt(i - 1) == s.charAt(j - 1)) { // dp[i-1][j-1]表示没有s[j-1]和t[i-1]时的情况情况，再加上有t[i-1]的情况
        			dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
        		} else { // 当前字符不相等，延续上一次匹配的情况
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
					dp[i][j] = dp[i - 1][j - 1]; // 不需要修改
				} else {
					//上、左和左上找一个最小的，+1表示改动一次
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
	 * 判断s3是否能成为s1和s2的和，s1和s2可以互相插入，但是各自顺序不能发生变化
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
	 * 最长公共子序列
	 * 动归
	 * sa[i-1]和sb[j-1]的子问题：
	 * sa[i-1] = sb[j-1], 子问题就是sa[i-2]和sb[j-2]的最长公共子序列+1
	 * sa[i-1] != sb[j-1], 子问题就是sa[i-2]和sb[j-1]以及sa[i-1]和sb[j-2]的最长公共子序列
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
					dp[i][j] = 0; // 空串和sa，sb没有公共子序列
				} else {
					if(sa.charAt(i-1) == sb.charAt(j-1)) {
						dp[i][j] = dp[i-1][j-1] + 1; // 有相等的加一个
					} else {
						dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]); //没有相等的保持目前为止最长的公共子序列长度
					}
				}
			}
		}
		return dp[lenA][lenB];
	}
    
	/**
	 * 最长公共子串
	 * 动归，从后往前，考虑i到i-1的拆解
	 * dp[i][j]保存了sa[i-1]和sb[j-1]最长公共子串的长度
	 * 最长公共子串，对于i来说，如果sa[i]!=sb[j]，长度就为0；否则就是dp[i-1][j-1]+1
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
			dp[0][i] = 0; // 空串和sa没有公共子串
		}
		for(int j = 0; j <= lenB; j++) {
			dp[j][0] = 0; // 空串和sb没有公共子串
		}
		// refresh dp
		for(int i = 1; i <= lenA; i++) {
			for(int j = 1; j <= lenB; j++) {
				if(sa.charAt(i-1) == sb.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1; // 增加一个的公共子串
				} else {
					dp[i][j] = 0; // 无公共子串
				}
				// 利用max来保持当前最长公共子串的长度
				max = Math.max(dp[i][j], max);
			}
		}
		return max;
	}
	
	/**
	 * 300. Longest Increasing Subsequence
	 * 求最长的递增子序列
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int nums[]) {
    	int[] dp = new int[nums.length];
    	Arrays.fill(dp, 1);
    	for(int i = 1; i < dp.length; i++) {
    		for(int j = 0; j < i; j++) {
    			// dp[i]记录的是到i为止最长的递增序列数，
    			if(nums[i] > nums[j]) { // 如果出现i的比前面的大，就取它或者前面的递增序列数+1的较大值
    				dp[i] = Math.max(dp[i], dp[j] + 1);
    			}
    		}
    	}
    	return dp[dp.length-1];
    }
	
	/**
	 * 求最长的回文子序列
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
	 * 连续子数组的最大和
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int max = 0;
		for(int i = 1; i < nums.length; i++) { // dp[i]保持了当前最大的和值
			dp[i] = Math.max(dp[i-1] + nums[i], nums[i]); // 如果加上nums[i]还不如nums[i]本身大，就用后者
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
	/**
	 * 返回一个数组最大和值的递增子序列，递增子序列需要n*n的
	 * @param nums
	 * @return
	 */
	public int LISwithMaxSum(int[] nums){
		int[] dp = new int[nums.length];
		int max = 0;
		dp[0] = nums[0];
		for(int i = 1; i < nums.length; i++) {
			dp[i] = nums[i]; // dp[i]记录了到i为止最大的递增序列的和值
			for(int j = 0; j < i; j++) { // 遍历i之前的所有值
				if(nums[j] < nums[i]) { // 如果递增
					dp[i] = Math.max(dp[j] + nums[i], dp[i]); // 给一个当前最大和值
					max = Math.max(max, dp[i]); //更新当前最大和值max，用于返回
				}
			}
		}
		
		return max;
	}
	
	/**
	 * 10. Regular Expression Matching
	 * '.' Matches any single character.
	 * '*' Matches zero or more of the preceding element.
	 * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
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
					dp[i][j] = dp[i][j - 2]; // 首先承接这个字符和*无贡献的情况
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
	 * isMatch("aa","a") → false
	 * isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false
	 * isMatch("aa", "*") → true
	 * isMatch("aa", "a*") → true
	 * isMatch("ab", "?*") → true
	 * isMatch("aab", "c*a*b") → false
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
            if(p.charAt(j-1)=='*'){ //因为*可以匹配空串
                dp[0][j] = true;
            } else {
                break;
            }
        }
        // i是s的指针，j是p的指针
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
            	//不是通配符，只要上一个字符匹配且当前位匹配就行，x==x或者x==?
            	if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
            		dp[i][j] = dp[i-1][j-1];
            	}
            	// 是通配符，只需要s除了前一位被匹配，s的当前位被*匹配就可以了；或者p除了当前位就可以匹配s了，通配符就匹配一个“”就可以了
                // s: xayl, p:x?y*, dp[i-1][j]表示xay与x?y*是否匹配，dp[i][j-1]表示xayl与x?y是否匹配
            	else if (p.charAt(j-1) == '*') { 
            		dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
	
	/**
	 * 判断s是否能分割成wordDict中的单词
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean wordBreak(String s, Set<String> wordDict) {
        if(s == null || s.length() == 0) {
			return false;
		}
		int n = s.length();
		boolean[] dp = new boolean[n+1]; // dp[i]表示s.subString(0,i);
		dp[0] = true; // ""默认是存在的
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= i; j++) {
				String curr = s.substring(j, i); // 双层循环找到当前的子串
				if(dp[j] && wordDict.contains(curr)) { // 必须是它之前的是true而且当前被字典包含
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
    }
	
	/**
	 * 给定s和一个字典，返回s能够分割成的不同语句
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
		// dp从后往前，key是s的下标，list是对应子问题的结果，用map来做dp数组
        Map<Integer, List<String>> validMap = new HashMap<Integer, List<String>>();

        // dp初始化
        List<String> l = new ArrayList<String>();
        l.add("");
        validMap.put(s.length(), l); 

        // dp更新
        for(int i = s.length() - 1; i >= 0; i--) { // 从最后一个字符开始
            List<String> values = new ArrayList<String>(); // 记录当前的结果
            for(int j = i + 1; j <= s.length(); j++) { // j从i+1开始到结尾
            	String curr = s.substring(i, j); // 卡出相应的substring
                if (dict.contains(curr)) { // dict要包含curr
                    for(String word : validMap.get(j)) { // word是之后的
                        values.add(curr + (word.isEmpty() ? "" : " ") + word); // curr是当前卡出来的
                    }
                }
            }
            validMap.put(i, values); // 当前位置跟新map
        }
        // 返回结果
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
