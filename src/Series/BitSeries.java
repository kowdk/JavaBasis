package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BitSeries {

	
	/**
	 * 191. Number of 1 Bits
	 * ����������������Ķ����Ʊ�ʾ��1�ĸ���
	 * @param n
	 * @return
	 */
	public int count1Bits(int n){
		int cnt = 0;
		while(n != 0) {
			cnt++;
			n = n & (n-1); // n&(n-1)ȥ��n���Ҳ��1
		}
		return cnt;
	}
	
	/**
	 * 231. Power of Two
	 * �ж�һ�������ǲ���2�Ĵη�
	 * @param n
	 * @return
	 */
    public boolean isPowerOfTwo(int n) {
    	if(n == 0 || n == Integer.MIN_VALUE)
            return false;
        n = (n & (n-1)); // n&(n-1)��ʾȥ�����ұߵ�1
        return n == 0 ? true : false;
    }
	
	/**
	 * 136. Single Number
	 * �������������־��������Σ���һ������ֻ����һ�Σ��ҵ�ֻ����һ�ε�����
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
	 * �������������־��������Σ���һ������ֻ����һ�Σ��ҵ�ֻ����һ�ε�����
	 * @param nums
	 * @return
	 */
	public int findSingleNumberOthersThreeTimes(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int res = 0, mask = 1;
		while (mask != 0) { // 1��Ϊ������Ҫһֱ����ֱ��Ϊ0
			int bitCnt = 0;
			for (int num : nums) {
				if ((mask & num) != 0) {
					bitCnt++; // �ۼӸ�λһ���ж��ٸ�1�������λ�ǲ����ڽ���ģ���ôcntӦ����3�ı���
				}
			}
			if (bitCnt % 3 != 0) { //bitCnt is not multiple of 3, the res must contains this bit.
				res |= mask; // |=���ڸ�ֵ
			}
			mask = mask << 1;
		}
		return res;
	}

	/**
	 * �������������־��������Σ�����������ֻ������һ�Σ��ҵ�����������
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
		// �ҵ�mask���Ҳ��1
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
	 * �ҳ���0-n��������ȱ�ٵ�Ψһ����
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
	 * �������0 <= m <= n <= 2147483647��[m,n]֮�������������Ľ��
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
     * �ҳ������ظ����ֵĳ���Ϊ10��DNA����
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaUsingBitmap(String s) { // ֻ��ATGC�����ַ�����˿�������bit����ʾһ���ַ�
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
     * ��ʹ�üӼ����������Integer�����
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
    	if (a == 0) return b;
    	if (b == 0) return a;

    	while (b != 0) {
    		int carry = a & b; // ��a��b�Ľ�λ��������
    		a = a ^ b; // a��b�ķǽ�λ�Ӹ�ֵ��a
    		b = carry << 1; // ��λ��ֵ��b
    	}
    	
    	return a;
    }
    
    /**
     * �����0��num��ÿ�����ֶ�����1���ֵĴ���
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
