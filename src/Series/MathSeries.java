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
	 * ��һ���������ʾ�����ּ�һ��������������ֵ������ʾ
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
        int carry = 0, plus = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i] = digits[i] + carry + plus;
			plus = 0; // plusֻ�ܼ�һ��
			if (digits[i] > 9) { // ����9��λ
				carry = 1;
				digits[i] -= 10;
			} else {
				carry = 0;
			}
		}
		if (carry == 1) { // �������λ�����
			int[] newDigits = new int[digits.length + 1];
			newDigits[0] = 1;
			System.arraycopy(digits, 0, newDigits, 1, digits.length);
			return newDigits;
		}
		return digits;
    }
	
	/**
	 * 118. Pascal's Triangle, ����5,�õ����������Σ�
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
		for (int i = 0; i < numRows; i++) { // ���ѭ��������
			List<Integer> row = new ArrayList<Integer>();
			for(int j = 0; j < i+1; j++) { // �ڲ�ѭ�������б�
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
	 * �ж�n�Ľ׳�β���ж��ٸ�0�������ж�n���������ٸ�5
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
	 * �ж�һ�����ǲ���2����
	 * @param n
	 * @return
	 */
	public boolean isPowerOfTwo(int n) {
        if(n == 0 || n == Integer.MIN_VALUE) // 2����ֻ����һ��1
            return false;
        n = (n & (n-1));
        return n == 0 ? true : false;
    }
	
	/**
	 * �ж�һ�����ǲ���3����
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree(int n) {
        // С�����intֵ��3�����ǣ�log��3Ϊ�׵�Integer.MAX_VALUE���ó�����int��ȡ�õ�����19��Ȼ��maxPow3����3^19 
        int maxPow3 = (int) Math.pow(3, (int) (Math.log(Integer.MAX_VALUE) / Math.log(3)));
        // n��3���ݣ���ôһ����3^19��Լ��
		return (n > 0 && maxPow3 % n == 0);
    }
	
	/**
	 * �ж�һ�����ǲ����ĵ���
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
				int cur = n % 10; // ����ȡ����λ
				res += cur * cur; // 
				n = n / 10;
			}
			if(res == 1){
				return true;
			}
			if(set.contains(res)) { // ���֮ǰ���ֹ����ͻ�����ѭ������Զ�ò���1�Ľ����һ���Ǵ����
				return false;
			}
			set.add(res); // ��������뼯��
			n = res; // ����n��res
			res = 0;
		}
    }
    
    /**
     * 223. Rectangle Area
     * �����ص��ĳ����Σ�����ȥ���ص��Ժ�����
     * @return
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
		int area2 = (G - E) * (H - F);
		
		// ��������������εĽ����
		int overlap = 0;
		int left = Math.max(A, E);
		int right = Math.min(C, G);
		int bottom = Math.max(B, F);
		int top = Math.min(D, H);
		
		// ȷ���н��棬�����overlap
		if(right > left && top > bottom)
			overlap = (right - left) * (top - bottom);
		
		return area1 + area2 - overlap;
    }
    
    /**
     * 263. Ugly Number
     * ������ֻ����2,3,5������������ugly number
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
	 * �������ֽ�
	 * @param m
	 * @param n
	 */
	public void prim(int m, int n) {
		if(m >= n) {
			while(m % n != 0) n++; // n��������m��n��mС���͸�����n++
			m /= n; // ���Ե�ǰ����
			prim(m, n); // �ݹ�
			System.out.print(n + "*");
		}
	}
	
	/**
	 * ��ӡ��1������nλ��
	 * @param n
	 */
	public void outPutOneToMaxNDigits(int n) {
        // ��nlist��ʾ��n,nlist[0]��ʾn�����λ
        ArrayList<Integer> nlist = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            nlist.add(0);
        }
        increment(nlist);
    }
 
    /**
     * ʹ����ÿ��+1Ȼ�����
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
                    // ���λ������λ���ﵽ���ֵ��ֹͣ���
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
     * ������֣�����λ��0���
     * @param nlist
     */
    private void output(ArrayList<Integer> nlist) {
        Iterator<Integer> ite = nlist.iterator();
        int num;
        // �ҵ���һ��Ϊ0��λ��
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
	 * ��1��k������1���ֵĸ���
	 * @param k
	 * @return
	 */
	public int countDigitOne(int n) {
		int count = 0, factor = 1, k = n;
		while (k > 0) {
			int num = k / 10, curr = k % 10, amount; // num��ʾ�ж��ٸ�currλ��curr�ǵ�ǰλ
			if (curr == 0) { // ��ǰλ������
				amount = 0;
			} else if (curr == 1) { // ��ǰλ��1��ȡ���ڵ�λ�����׵�λ��
				amount = n % factor + 1;
			} else { // ��ǰλ����1�����Թ���factor��
				amount = factor;
			}
			count += num * factor + amount; // ��ǰλ���˹���num������factor���⣨��λ����������ɢֵ����λ��
			
			//����factor��n
			factor *= 10;
			k /= 10;
		}
		return count;
	}
	
	/**
	 * 179. Largest Number
	 * [3, 30, 34, 5, 9] -> 9534330.
	 * ����һ�����飬�������������ܹ��ɵ�������
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

		// �ض���String���������
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String s1 = o1 + o2;
				String s2 = o2 + o1;
				return s1.compareTo(s2);
			}
		};
		
		// ��String������������
		Arrays.sort(strs, comp);
		System.out.println(Arrays.toString(strs));
		//[30, 3, 34, 5, 9]

		// ����str������0��ͷ
		if(strs[strs.length-1].charAt(0) == '0') {
			return "0";
		}
		
		StringBuilder sb = new StringBuilder();
		for (String s : strs)
			sb.insert(0, s);
		return sb.toString();
    }
	
	/**
	 * 26����ת��������ת��ĸ
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
			n--; // A�Ǵ�1��ʼ��
			res.insert(0, (char) (n % 26 + 'A'));
			n /= 26;
		}
		return res.toString();
     }
	
    /**
     * 26����ת������ĸת����
     * AC->29
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int res = 0, scale = 1; // scale��ʾ���ƣ�������26����
        for(int i = s.length() - 1; i >= 0; i--) { // �ɺ���ǰ����
        	int num = s.charAt(i) - 'A' + 1;
        	res += num * scale;
        	scale *= 26;
        }
        return res;
    }
    
	/**
	 * ��ʮ����תΪn����
	 * @param num: ʮ������
	 * @param n: n����
	 */
	public void transform(int num, int n) {
		int arr[] = new int[100];
		int len = 0;
		while (num != 0) { //�����������Ϊ0ʱѭ��ִ������͸�ֵ
			arr[len++] = num % n;
			num = num / n;
		}
		for (int i = len-1; i >= 0; i--) { // �Ӻ���ǰ���
			if (arr[i] >= 9) {
				System.out.print((char) (arr[i] - 10 + 'A')); // ����9��A��ʼ���
			} else {
				System.out.print(arr[i] + "");
			}
		}
	}
	
	/**
	 * ��תһ��������
	 * @param x
	 * @return
	 */
	public int reverseInteger(int x) {
		int flag = (x > 0) ? 1 : -1; // ���ȴ������
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
	 * ���ַ���x��n�η����ݹ�
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		if(n == 0) return 1.0; // �κ�����0�η�����1��0��0�η�û�����壬Ҳ���1
		if(x == 0 && n < 0) return 0.0; // 0�ĸ����η��ᵼ�³�����0,��Ҫ���⴦��
		if(n == Integer.MIN_VALUE) { // ���ָ��Ϊ��С��ֵ��Ӧ�ö����0������1���κδη�����1
			if(Math.abs(x) == 1)
				return 1.0;
			else
				return 0.0;
		}
		// ��������������������ָ���Ǹ������ͷ�תָ������������ĵ���
		if(n < 0) { 
			n = -n;
			x = 1/x;
		}
		return (n%2 == 0) ? myPow(x*x, n/2) : x*myPow(x*x, n/2); // �ݹ��󣬷�����ż��
	}
	
	/**
	 * ����x�Ŀ��������ֲ���
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
     * 1�� �������ֹ���7������I��1����V��5����X��10����L��50����C��100����D��500����M��1000��
	 * 2�� �ظ�������һ��������������ظ�3�Ρ�
	 * 3�� �Ҽ�������ڽϴ���������ֵ��ұ߼��Ͻ�С���������֣���ʾ�����ּ�С���֡��ڽϴ���������ֵ���߼��Ͻ�С���������֣���ʾ�����ּ�С���֡�
	 * 4�� ��������������ƣ�������I��X��C���ҷ��ڴ��������ֻ����һ����V �� X ��ߵ�С����ֻ���â�L �� C ��ߵ�С����ֻ����X��D �� M �� �ߵ�С����ֻ����C��
     * @param s
     * @return
     */
	public int romanToInt(String s) {
		int nums[] = new int[s.length()];
		// �Ƚ��ַ��û�������
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
		//XIX,����AB�����AС��B����-A�����A>=B����+A
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] < nums[i + 1]) {
				sum -= nums[i];
			} else {
				sum += nums[i]; 
			}
		}
		// ���һλ�϶���+��
		return sum + nums[nums.length - 1];
	}
    
	/**
	 * ����һ��intֵ�����ض�Ӧ��������ĸ
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {
        // ���ű��Ǹ���������ĸ�Ĺ���õ��ģ�V�� X��ߵ�С����ֻ����I��L�� C��ߵ�С����ֻ����X��D�� M��ߵ�С����ֻ����C��
        // ��С���ң������Ҽ�������ڵ����ң����Ҽ���
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
	 * ����ȸ�������С�������ĸ�����ʹ�õ���ɸѡ��
	 * @param n
	 * @return
	 */
	public static int countPrimes(int n) {
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; i++) {
			isPrime[i] = true; // isPrime=true��ʾ������
		}

		for (int i = 2; i * i < n; i++) { // ��2����������n
			if (!isPrime[i]) { // ��������������
				continue;
			}
			for (int j = i * i; j < n; j += i) { // ������ƽ����ʼ��n��i�ı�����ȫ����������
				isPrime[j] = false;
			}
		}

		int count = 0;
		for (int i = 2; i < n; i++) {
			if (isPrime[i]) { // ���±�������ۼ��������ĸ���
				count++;
			}
		}
		return count;
	}
	
	
	
	/**
	 * ���ó˳�ȡ��������, ����������һ��Ҫע����ź�Խ�������
	 * @param dividend
	 * @param divisor
	 * @return
	 */
    public int divide(int dividend, int divisor) {
    	int sign = 1; // ������Ҫ��һ�������ŵ��ж�
    	if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
    		sign = -1;
    	}
    	// abs�ر�ע�⣺Integer.MIN_VALUE����ֵ�Ժ��Խ�磬�����ҪתΪlong��
    	long ldend = Math.abs((long)(dividend)); // �������ͱ�������תΪlong����
    	long ldsor = Math.abs((long)(divisor));
    	if(ldsor == 0) return Integer.MAX_VALUE; // ��������Ϊ0
    	if(ldend == 0 || ldend < ldsor) return 0; // ������С�ڳ��������
    	
    	long res = 0L;
    	while(ldend >= ldsor) { // �������ڵ��ڱ�����
			int shift = 0;
			while (ldend > (ldsor << shift + 1)) { // �������������ƶ���λ
				shift++;
			}
			ldend -= ldsor << shift;
			res += (1 << shift);
    	}
    	res = (sign == -1) ? -res : res; // �������
    	res = Math.min(Integer.MAX_VALUE, res); // ����Խ��
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
