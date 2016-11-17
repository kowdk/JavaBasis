package Series;

import java.util.HashMap;
import java.util.Map;

public class CharacterHashSeries {

	/**
	 * 387. First Unique Character in a String
	 * 在字符串中找到第一个不重复的字符
	 * @param s
	 * @return
	 */
	public int firstUniqChar(String s) {
        if(s == null || s.length() == 0)
            return -1;
        int[] cnt = new int[26];
        char[] cs = s.toCharArray();
        
        for(int i = 0; i < cs.length; i++) {
            cnt[cs[i]-'a']++; // 先遍历一遍字符串，为字符进行次数累加
        }
        
        for(int i = 0; i < cs.length; i++) {
            if(cnt[cs[i]-'a'] == 1) { // 再遍历字符串，发现的第一个次数为1的字符，立即返回
                return i;
            }
        }
        
        return -1;
    }
	
	/**
	 * 删除字符串中所有重复出现的字符
	 * @param str
	 * @return
	 */
	public String removeDuplicatesFromString(String str){
		boolean[] seen = new boolean[26];
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(!seen[ch-'a']){
				seen[ch-'a'] = true;
				sb.append(ch);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 在s中删除p中出现的所有字符
	 * @param s
	 * @param p
	 * @return
	 */
	public String removeCharsInOtherString(String s, String p){
		boolean[] seen = new boolean[26];
		StringBuilder sb = new StringBuilder();
		for(char ch : p.toCharArray()) {
			seen[ch-'a'] = true;
		}
		for(char ch : s.toCharArray()){
			if(!seen[ch-'a']){
				sb.append(ch);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 242. Valid Anagram
	 * 判断两个单词是否互为变位词，即单词中出现的字母相同，字母出现的次数也相同
	 * @param args
	 */
	public boolean isAnagram(String s, String t) {
		if(s == null || t == null)
			return s.equals(t);
		if(s.length() != t.length()) // 长度不同不可能为变位词
			return false;
		
		int[] table = new int[26]; // char hash
		int len = s.length();
		for(int i = 0; i < len; i++) { // s的char被加了，在t中又会被减，因此最后应该都是0
			table[s.charAt(i) - 'a']++;
			table[t.charAt(i) - 'a']--;
		}
		for(int i : table) {
			if(i != 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断两个字符串是不是同构的，同构的是指s中的字符可以被t中的字符替换。abb,egg
	 * @param s
	 * @param t
	 * @return
	 */
    public boolean isIsomorphic(String s, String t) {
    	if(s == null && t == null)
    		return true;
    	if(s.length() != t.length())
    		return false;
    	
    	final int tableSize = 26;
    	int len = s.length();
    	int[] tableS = new int[tableSize];
    	int[] tableT = new int[tableSize];
    	//遍历第一次用于计数
    	for(int i = 0; i < len; i++) { //record the position for char in s and t
    		tableS[s.charAt(i) - 'a'] = i;
    		tableT[t.charAt(i) - 'a'] = i;
    	}
    	//遍历第二次用于判断
    	for(int i = 0; i < len; i++) {
    		if(tableS[s.charAt(i) - 'a'] != tableT[t.charAt(i) - 'a']) // the pos of char in s and t should be the same
    			return false;
    	}
    	return true;
    }
	
    /**
     * 判断一个字符串是否遵从一个字符模式
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
    	if(pattern == null || pattern.length() == 0) {
    		return false;
    	}
    	
    	String[] words = str.split(" ");
    	char[] cs = pattern.toCharArray();
    	if(words.length != cs.length) {
    		return false;
    	}
    	Map<Character, String> map = new HashMap<Character, String>();
    	
    	//abb ---- xu tao tao
    	for(int i = 0; i < words.length; i++) {
    		String word = words[i];
    		char ch = cs[i];
    		if(map.containsKey(ch)){ // map contains ch
    			if(!map.get(ch).equals(word)){
    				return false;
    			}
    		} else { // map do not contains ch
    			if(map.containsValue(word)) { // 这一步不处理会导致ab和xxx，xxx返回true
    				return false;
    			}
    			else {
    				map.put(ch, word);
    			}
    		}
    	}
    	return true;
    }
    
    /**
     * 389. Find the Difference
     * t比s多了一个字符，找出来
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        if(s.length() == 0)
            return t.charAt(0);
        int[] seen = new int[26];
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();
        char res = 'a';
        for(int i = 0; i < ts.length; i++) {
            seen[ts[i] - 'a']++;
        }
        for(int i = 0; i < ss.length; i++) {
            seen[ss[i] - 'a']--;
        }
        for(int i = 0; i < seen.length; i++) {
            if(seen[i] > 0){
                res = (char)(i + 'a');
                break;
            }
        }
        return res;
    }
    
    /**
     * 383. Ransom Note
     * 后面的字符串是否能够覆盖前面的字符串
     * canConstruct("a", "b") -> false
     * canConstruct("aa", "ab") -> false
     * canConstruct("aa", "aab") -> true
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote == "")
            return true;
        if(ransomNote.length() > magazine.length())
            return false;
        int[] table = new int[26];
        char[] rs = ransomNote.toCharArray();
        char[] ms = magazine.toCharArray();
        
        for(int i = 0; i < rs.length; i++) {
            table[rs[i] - 'a']++;
        }
        for(int i = 0; i < ms.length; i++) {
            table[ms[i] - 'a']--;
        }
        for(int i : table) {
            if(i > 0)
                return false;
        }
        return true;
    }
    
    
	public static void main(String[] args) {
		CharacterHashSeries tester = new CharacterHashSeries();
		//String str = "abaccdeff";
		//String p = "defg";
		//System.out.println(tester.findFirstNotRepeatedChar(str));
		System.out.println(tester.findTheDifference("abc", "abcd"));
		//System.out.println(tester.removeCharsInOtherString(str, p));
		/*String s = "live";
		String t = "evil";
		System.out.println(tester.isAnagram(s, t));*/
		
		/*String pattern = "abb";
		String str1 = "egg add add";
		String str2 = "add add pig";
		System.out.println(tester.wordPattern(pattern, str1));
		System.out.println(tester.wordPattern(pattern, str2));*/
		//System.out.println(tester.isIsomorphic("add", "egg"));
	}
}
