package Series;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MathSeries {

	/**
	 * 258. Add Digits
	 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
	 */
	public int addDigits(int num) {
        //int res = (int) (num - 9 * Math.floor((num - 1) / 9));
		//return res;
		while(num >= 10) {
			num = num % 10 + num / 10;
		}
		return num;
    }
	
	/**
	 * 给一个用数组表示的数字加一，并返回这个数字的数组表示
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
        int carry = 0, plus = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i] = digits[i] + carry + plus;
			plus = 0; // plus只能加一次
			if (digits[i] > 9) { // 超过9进位
				carry = 1;
				digits[i] -= 10;
			} else {
				carry = 0;
			}
		}
		if (carry == 1) { // 处理最高位的情况
			int[] newDigits = new int[digits.length + 1];
			newDigits[0] = 1;
			System.arraycopy(digits, 0, newDigits, 1, digits.length);
			return newDigits;
		}
		return digits;
    }
	
	/**
	 * 118. Pascal's Triangle, 输入5,得到如下三角形：
	 *     [1],
	 *    [1,1],
	 *   [1,2,1],
	 *  [1,3,3,1],
	 * [1,4,6,4,1]
	 * @param numRows
	 * @return
	 */
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return res;
		for (int i = 0; i < numRows; i++) { // 外层循环控制行
			List<Integer> row = new ArrayList<Integer>();
			for(int j = 0; j < i+1; j++) { // 内层循环控制行标
				if(j == 0 || j == i){
					row.add(1);
				} else {
					row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j)); 
				}
			}
			res.add(row);
		}
		return res;
    }
	
	/**
	 * 172. Factorial Trailing Zeroes
	 * 判断n的阶乘尾部有多少个0，就是判断n！包含多少个5
	 * @param n
	 * @return
	 */
    public int trailingZeroes(int n) {
        int cnt = 0;
		while(n > 0) {
			cnt += n / 5;
			n /= 5;
		}
		return cnt;
    }
	
	/**
	 * 判断一个数是不是2的幂
	 * @param n
	 * @return
	 */
	public boolean isPowerOfTwo(int n) {
        if(n == 0 || n == Integer.MIN_VALUE) // 2的幂只包含一个1
            return false;
        n = (n & (n-1));
        return n == 0 ? true : false;
    }
	
	/**
	 * 判断一个数是不是3的幂
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree(int n) {
        // 小于最大int值的3的幂是：log以3为底的Integer.MAX_VALUE，得出来用int截取得到整数19，然后maxPow3就是3^19 
        int maxPow3 = (int) Math.pow(3, (int) (Math.log(Integer.MAX_VALUE) / Math.log(3)));
        // n是3的幂，那么一定是3^19的约数
		return (n > 0 && maxPow3 % n == 0);
    }
	
	/**
	 * 判断一个数是不是四的幂
	 * @param num
	 * @return
	 */
    public boolean isPowerOfFour(int num) {
        int cnt = 0, n = num;
        n = (n & (n-1));
        while(num > 0) {
            num = num >>> 1;
            cnt++;
        }
        return (n == 0) && (cnt % 2 == 1) ? true : false;
    }
	
    /**
     * 202. Happy Number
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     * ---> 19 is a happy number
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        if (n == 0)
			return false;
		Set<Integer> set = new HashSet<Integer>();
		int res = 0;
		while(true) {
			while(n > 0){
				int cur = n % 10; // 依次取出各位
				res += cur * cur; // 
				n = n / 10;
			}
			if(res == 1){
				return true;
			}
			if(set.contains(res)) { // 如果之前出现过，就会陷入循环，永远得不到1的结果，一定是错误的
				return false;
			}
			set.add(res); // 将结果加入集合
			n = res; // 更新n和res
			res = 0;
		}
    }
    
    /**
     * 223. Rectangle Area
     * 两个重叠的长方形，计算去除重叠以后的面积
     * @return
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
		int area2 = (G - E) * (H - F);
		
		// 计算出两个长方形的交叉边
		int overlap = 0;
		int left = Math.max(A, E);
		int right = Math.min(C, G);
		int bottom = Math.max(B, F);
		int top = Math.min(D, H);
		
		// 确保有交叉，计算出overlap
		if(right > left && top > bottom)
			overlap = (right - left) * (top - bottom);
		
		return area1 + area2 - overlap;
    }
    
    /**
     * 263. Ugly Number
     * 质因数只包含2,3,5的正整数叫做ugly number
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if(num == 0)
            return false;
        while(num % 5 == 0)
            num /= 5;
        while(num % 3 == 0) 
            num /= 3;
        while(num % 2 == 0)
            num /= 2;
        return num == 1;
    }
	
	/**
	 * 质因数分解
	 * @param m
	 * @param n
	 */
	public void prim(int m, int n) {
		if(m >= n) {
			while(m % n != 0) n++; // n不能整除m且n比m小，就给因子n++
			m /= n; // 除以当前因子
			prim(m, n); // 递归
			System.out.print(n + "*");
		}
	}
	
	/**
	 * 打印从1到最大的n位数
	 * @param n
	 */
	public void outPutOneToMaxNDigits(int n) {
        // 用nlist表示数n,nlist[0]表示n的最低位
        ArrayList<Integer> nlist = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            nlist.add(0);
        }
        increment(nlist);
    }
 
    /**
     * 使数字每次+1然后输出
     * @param nlist
     */
    private void increment(ArrayList<Integer> nlist) {
        int carrybit = 0;
        boolean end = false;
        while (true) {
            for (int i = nlist.size() - 1; i >= 0; i--) {
                int digit = nlist.get(i);
                int sum = digit + carrybit;
                if (i == (nlist.size() - 1)) {
                    sum += 1;
                }
                if (sum >= 10) {
                    // 最高位产生进位，达到最大值，停止输出
                    if (i == 0) {
                        end = true;
                    }
                    sum = sum - 10;
                    carrybit = 1;
                } else {
                    carrybit = 0;
                }
                nlist.set(i, sum);
            }
            output(nlist);
            if (end) {
                break;
            }
        }
    }
 
    /**
     * 输出数字，将高位的0舍掉
     * @param nlist
     */
    private void output(ArrayList<Integer> nlist) {
        Iterator<Integer> ite = nlist.iterator();
        int num;
        // 找到第一个为0的位置
        boolean first = false;
        while (ite.hasNext()) {
            if (first) {
                System.out.print(ite.next());
                continue;
            }
            if ((num = ite.next()) != 0) {
                first = true;
                System.out.print(num);
            }
        }
        System.out.println();
    }
	
	/**
	 * 从1到k整数中1出现的个数
	 * @param k
	 * @return
	 */
	public int countDigitOne(int n) {
		int count = 0, factor = 1, k = n;
		while (k > 0) {
			int num = k / 10, curr = k % 10, amount; // num表示有多少个curr位，curr是当前位
			if (curr == 0) { // 当前位不贡献
				amount = 0;
			} else if (curr == 1) { // 当前位是1，取决于低位，贡献低位个
				amount = n % factor + 1;
			} else { // 当前位大于1，可以贡献factor个
				amount = factor;
			}
			count += num * factor + amount; // 当前位除了贡献num个步长factor以外（高位），还贡献散值（本位）
			
			//更新factor和n
			factor *= 10;
			k /= 10;
		}
		return count;
	}
	
	/**
	 * 179. Largest Number
	 * [3, 30, 34, 5, 9] -> 9534330.
	 * 给定一个数组，返回这个数组可能构成的最大的数
	 * @param nums
	 * @return
	 */
	public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0)
			return "";

		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strs[i] = String.valueOf(nums[i]);
		}

		// 重定义String的排序规则
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String s1 = o1 + o2;
				String s2 = o2 + o1;
				return s1.compareTo(s2);
			}
		};
		
		// 给String数组升序排序
		Arrays.sort(strs, comp);
		System.out.println(Arrays.toString(strs));
		//[30, 3, 34, 5, 9]

		// 最大的str都是以0开头
		if(strs[strs.length-1].charAt(0) == '0') {
			return "0";
		}
		
		StringBuilder sb = new StringBuilder();
		for (String s : strs)
			sb.insert(0, s);
		return sb.toString();
    }
	
	/**
	 * 26进制转换，数字转字母
	 * 1 -> A
     * 2 -> B
     * ...
     * 26 -> Z
     * 27 -> AA
	 * @param n
	 * @return
	 */
    public String convertToTitle(int n) {
		StringBuilder res = new StringBuilder();
		while (n > 0) {
			n--; // A是从1开始的
			res.insert(0, (char) (n % 26 + 'A'));
			n /= 26;
		}
		return res.toString();
     }
	
    /**
     * 26进制转换，字母转数字
     * AC->29
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int res = 0, scale = 1; // scale表示进制，该题是26进制
        for(int i = s.length() - 1; i >= 0; i--) { // 由后向前遍历
        	int num = s.charAt(i) - 'A' + 1;
        	res += num * scale;
        	scale *= 26;
        }
        return res;
    }
    
	/**
	 * 将十进制转为n进制
	 * @param num: 十进制数
	 * @param n: n进制
	 */
	public void transform(int num, int n) {
		int arr[] = new int[100];
		int len = 0;
		while (num != 0) { //当输入的数不为0时循环执行求余和赋值
			arr[len++] = num % n;
			num = num / n;
		}
		for (int i = len-1; i >= 0; i--) { // 从后往前输出
			if (arr[i] >= 9) {
				System.out.print((char) (arr[i] - 10 + 'A')); // 超过9从A开始输出
			} else {
				System.out.print(arr[i] + "");
			}
		}
	}
	
	/**
	 * 反转一个整形数
	 * @param x
	 * @return
	 */
	public int reverseInteger(int x) {
		int flag = (x > 0) ? 1 : -1; // 首先处理符号
		x = Math.abs(x);
		long res = 0L;
		while (x > 0) {
			res *= 10;
			res += x % 10;
			x /= 10;
		}

		return res > Integer.MAX_VALUE ? 0 : (int) (res * flag);
	}
	
	/**
	 * 二分法求x的n次方，递归
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		if(n == 0) return 1.0; // 任何数的0次方都是1，0的0次方没有意义，也输出1
		if(x == 0 && n < 0) return 0.0; // 0的负数次方会导致除数是0,需要特殊处理
		if(n == Integer.MIN_VALUE) { // 如果指数为最小负值，应该都输出0，除非1的任何次方都是1
			if(Math.abs(x) == 1)
				return 1.0;
			else
				return 0.0;
		}
		// 接下来是正常情况，如果指数是负数，就反转指数，并求底数的倒数
		if(n < 0) { 
			n = -n;
			x = 1/x;
		}
		return (n%2 == 0) ? myPow(x*x, n/2) : x*myPow(x*x, n/2); // 递归求，分奇数偶数
	}
	
	/**
	 * 计算x的开方，二分查找
	 * @param x
	 * @return
	 */
    public int mySqrt(int x) {
        if(x == 0 || x == 1) return x;
		int lo = 1, hi = x;
		while(lo < hi) {
			int mid = lo + (hi - lo)/2;
			if(mid * mid < x) {
				lo = mid + 1;
			} else if(mid * mid == x){
				return mid;
			}
			else {
				hi = mid;
			}
		}
		return lo-1;
    }
    
    /**
     * 1， 罗马数字共有7个，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）
	 * 2， 重复次数：一个罗马数字最多重复3次。
	 * 3， 右加左减：在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
	 * 4， 左减的数字有限制，仅限于I、X、C，且放在大数的左边只能用一个。V 和 X 左边的小数字只能用Ⅰ。L 和 C 左边的小数字只能用X。D 和 M 左 边的小数字只能用C。
     * @param s
     * @return
     */
	public int romanToInt(String s) {
		int nums[] = new int[s.length()];
		// 先将字符置换成数字
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'M':
				nums[i] = 1000;
				break;
			case 'D':
				nums[i] = 500;
				break;
			case 'C':
				nums[i] = 100;
				break;
			case 'L':
				nums[i] = 50;
				break;
			case 'X':
				nums[i] = 10;
				break;
			case 'V':
				nums[i] = 5;
				break;
			case 'I':
				nums[i] = 1;
				break;
			}
		}
		int sum = 0;
		//XIX,对于AB，如果A小于B，就-A，如果A>=B，就+A
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] < nums[i + 1]) {
				sum -= nums[i];
			} else {
				sum += nums[i]; 
			}
		}
		// 最后一位肯定是+的
		return sum + nums[nums.length - 1];
	}
    
	/**
	 * 给定一个int值，返回对应的罗马字母
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {
        // 这张表是根据罗马字母的规则得到的：V和 X左边的小数字只能用I。L和 C左边的小数字只能用X，D和 M左边的小数字只能用C。
        // 左小于右，就用右减左；左大于等于右，就右加左。
		int[] values = { 1000, 900, 500, 400,  100,   90,  50,  40,   10,   9,    5,   4,    1 };
		String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			while (num >= values[i]) {
				num -= values[i];
				sb.append(strs[i]);
			}
		}
		return sb.toString();
    }
	
	/**
	 * 计算比给定的数小的素数的个数，使用的是筛选法
	 * @param n
	 * @return
	 */
	public static int countPrimes(int n) {
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true; // isPrime=true表示是素数
		}

		for (int i = 2; i * i < n; i++) { // 从2遍历到根号n
			if (!isPrime[i]) { // 不是素数的跳过
				continue;
			}
			for (int j = i * i; j < n; j += i) { // 从素数平方开始到n，i的倍数的全部不是素数
				isPrime[j] = false;
			}
		}

		int count = 0;
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) { // 重新遍历表格，累加是素数的个数
				count++;
			}
		}
		return count;
	}
	
	
	
	/**
	 * 不用乘除取余做除法, 数字类问题一定要注意符号和越界的问题
	 * @param dividend
	 * @param divisor
	 * @return
	 */
    public int divide(int dividend, int divisor) {
    	int sign = 1; // 首先须要做一个正负号的判断
    	if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
    		sign = -1;
    	}
    	// abs特别注意：Integer.MIN_VALUE绝对值以后会越界，因此需要转为long型
    	long ldend = Math.abs((long)(dividend)); // 将除数和被除数都转为long正数
    	long ldsor = Math.abs((long)(divisor));
    	if(ldsor == 0) return Integer.MAX_VALUE; // 除数不能为0
    	if(ldend == 0 || ldend < ldsor) return 0; // 被除数小于除数的情况
    	
    	long res = 0L;
    	while(ldend >= ldsor) { // 除数大于等于被除数
			int shift = 0;
			while (ldend > (ldsor << shift + 1)) { // 被除数可以左移多少位
				shift++;
			}
			ldend -= ldsor << shift;
			res += (1 << shift);
    	}
    	res = (sign == -1) ? -res : res; // 处理符号
    	res = Math.min(Integer.MAX_VALUE, res); // 处理越界
    	res = Math.max(Integer.MIN_VALUE, res);
    	return (int)res;
    }
    
	public static void main(String[] args) {
		MathSeries t = new MathSeries();
		//int ones = t.countDigitOne(21); // 1 10 11 12 13 14 15 16 17 18 19 21
		int[] nums = new int[]{3, 30, 34, 5, 9};
		//System.out.println(t.divide(-1, 1));
		/*System.out.println(Math.abs(Integer.MAX_VALUE));
		System.out.println(Math.abs(Integer.MIN_VALUE + 1));
		System.out.println(Math.abs(Integer.MIN_VALUE));*/
		//System.out.println(t.titleToNumber("ABA"));
		//System.out.println(t.divide(17, 3));
		//System.out.println(a + " " + i);
		System.out.println(t.trailingZeroes(10));
	}
}
