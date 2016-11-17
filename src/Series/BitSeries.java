package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BitSeries {

	
	/**
	 * 191. Number of 1 Bits
	 * 计算给定的整形数的二进制表示中1的个数
	 * @param n
	 * @return
	 */
	public int count1Bits(int n){
		int cnt = 0;
		while(n != 0) {
			cnt++;
			n = n & (n-1); // n&(n-1)去除n最右侧的1
		}
		return cnt;
	}
	
	/**
	 * 231. Power of Two
	 * 判断一个数字是不是2的次方
	 * @param n
	 * @return
	 */
    public boolean isPowerOfTwo(int n) {
    	if(n == 0 || n == Integer.MIN_VALUE)
            return false;
        n = (n & (n-1)); // n&(n-1)表示去除最右边的1
        return n == 0 ? true : false;
    }
	
	/**
	 * 136. Single Number
	 * 数组中其它数字均出现两次，有一个数字只出现一次，找到只出现一次的数字
	 * 
	 * @param nums
	 * @return
	 */
	public int findSingleNumberOthersTwice(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int res = 0;
		for (int num : nums) {
			res ^= num;
		}
		return res;
	}

	/**
	 * 数组中其它数字均出现三次，有一个数字只出现一次，找到只出现一次的数字
	 * @param nums
	 * @return
	 */
	public int findSingleNumberOthersThreeTimes(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int res = 0, mask = 1;
		while (mask != 0) { // 1作为掩码需要一直左移直到为0
			int bitCnt = 0;
			for (int num : nums) {
				if ((mask & num) != 0) {
					bitCnt++; // 累加该位一共有多少个1，如果该位是不属于结果的，那么cnt应该是3的倍数
				}
			}
			if (bitCnt % 3 != 0) { //bitCnt is not multiple of 3, the res must contains this bit.
				res |= mask; // |=用于赋值
			}
			mask = mask << 1;
		}
		return res;
	}

	/**
	 * 数组中其它数字均出现两次，有两个数字只出现了一次，找到这两个数字
	 * @param nums
	 * @return
	 */
	public int[] findTwoSingleNumberOthersTwice(int[] nums) {
		int[] res = new int[2];
		if (nums == null || nums.length == 0)
			return res;
		
		// xor all nums to obtain mask
		int mask = 0;
		for(int num : nums) {
			mask ^= num;
		}
		
		// mask = mask & (-mask);
		// 找到mask最右侧的1
		int rightBit = 0, p = 1;
		while(p != 0) {
			if((p & mask) != 0){
				rightBit |= p;
				break;
			}
			p <<= 1;
		}
		//a & (-a) is the first right bit of a
		//int rightBit = mask & (-mask);
		
		// split nums into two subarray, each has one single number
		for(int num : nums) {
			if((rightBit & num) != 0) {
				res[0] ^= num;
			} else {
				res[1] ^= num;
			}
		}
		
		return res;
	}

	/**
	 * 找出从0-n的数组中缺少的唯一数字
	 * @param nums
	 * @return
	 */
	public int findMissingNumber(int[] nums) {
        int res = 0, i;
        for(i = 0; i < nums.length; i++) { 
        	res = res ^ i ^ nums[i]; // use index for help
        }
        res = res ^ i;
        return res;
    }
	
	/**
	 * 计算符合0 <= m <= n <= 2147483647的[m,n]之间的所有数字与的结果
	 * @param m
	 * @param n
	 * @return
	 */
    public int rangeBitwiseAnd(int m, int n) {
        int step = 1;
        while(m != n) {
        	m = m >> 1;
        	n = n >> 1;
			step = step << 1;
        }
        return m * step;
    }
	
    /**
     * 找出所有重复出现的长度为10的DNA序列
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaUsingBitmap(String s) { // 只有ATGC四种字符，因此可以用两bit来表示一个字符
    	Set<Integer> traval = new HashSet<>();
    	Set<Integer> repeat = new HashSet<>();
    	List<String> res = new ArrayList<>();
    	char[] map = new char[26]; // bitmap
    	map['A' - 'A'] = 0;
    	map['C' - 'A'] = 1;
    	map['G' - 'A'] = 2;
    	map['T' - 'A'] = 3;
    	
    	for(int i = 0; i < s.length() - 9; i++) {
    		int curr = 0;
    		for(int j = i; j < i + 10; j++) {
    			curr <<= 2;
    			curr |= map[s.charAt(j) - 'A'];
    		}
    		if((!traval.add(curr)) && repeat.add(curr)) {
    			res.add(s.substring(i, i+10));
    		}
     	}
    	return res;
    }
    
    /**
     * 不使用加减法完成两个Integer的相加
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
    	if (a == 0) return b;
    	if (b == 0) return a;

    	while (b != 0) {
    		int carry = a & b; // 将a和b的进位保存起来
    		a = a ^ b; // a和b的非进位加赋值给a
    		b = carry << 1; // 进位赋值给b
    	}
    	
    	return a;
    }
    
    /**
     * 计算从0到num，每个数字二进制1出现的次数
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
    	int pow = 1;
    	
    	for(int i = 1, j = 0; i <= num; i++, j++) {
    		if(i == pow) {
    			pow *= 2;
    			j = 0;
    		}
    		res[i] = res[j] + 1; 
    	}
    	
    	return res;
    }
    
	public static void main(String[] args) {
		BitSeries tester = new BitSeries();
		int[] nums = new int[] { 0, 1, 2, 3, 5};
		//System.out.println(tester.findSingleNumberOthersTwice(nums));
		//System.out.println(tester.findSingleNumberOthersThreeTimes(nums));
		//System.out.println(Arrays.toString(tester.findTwoSingleNumberOthersTwice(nums)));
		//System.out.println(tester.findMissingNumber(nums));
		System.out.println(tester.rangeBitwiseAnd(2, 7));
		//System.out.println(Arrays.toString(tester.countBits(16)));
	}

}
