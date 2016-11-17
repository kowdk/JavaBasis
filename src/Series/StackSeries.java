package Series;

import java.util.Arrays;
import java.util.Stack;
public class StackSeries {
	
	/**
	 * ջ��ѹ�뵯�����У�ѹ��ջ������û���ظ�Ԫ�أ��жϳ�ջ�������Ƿ���һ����ȷ�ĳ�ջ˳��
	 * @param inStack = [1,2,3,4]
	 * @param outStack = [2,4,3,1]
	 * @return
	 */
	public boolean isStackOrder(int[] inStack, int[] outStack) {
		if (inStack.length == 0 || outStack.length == 0)
			return false;
		if (inStack.length != outStack.length)
			return false;
		Stack<Integer> stack = new Stack<Integer>(); // ʹ��stack����ȫģ��in��out�Ķ���
		for (int i = 0, j = 0; i < inStack.length; i++) {
			stack.push(inStack[i]); // ��ѹ��ջ
			while (!stack.isEmpty() && stack.peek() == outStack[j]) { // �ͳ�ջ�Ƚϣ���ͬ���ջ
				stack.pop();
				j++;
			}
		}
		return stack.isEmpty() ? true : false; // �����ȫһ�����ջӦ���ǿյ�
	}
	
	/**
	 * �����������㣬û�����ţ��пո�����û���쳣���
	 * 227. Basic Calculator II
	 * e.g. 3-2*2+6/2
	 * @param s
	 * @return
	 */
	public int calculate2(String s) {
	   if(s == null || s.length() == 0) {
		   return 0;
	   }
	   s = s.trim().replace(" ", ""); // ȥ���ո�ĸ���
	   int curr = 0;
	   char sign = '+'; // signĬ����+�ţ��������µ�ǰ��
	   Stack<Integer> stack = new Stack<Integer>(); // stack���ڴ洢�����ŵĵ�ǰֵ
	   for(int i = 0; i < s.length(); i++) {
		   char c = s.charAt(i);
		   if(Character.isDigit(c)) { // һֱ��������
			   curr = curr * 10 + c - '0'; // ���ֲ�ֹһλ
		   }
		   if(!Character.isDigit(c) || i == s.length() - 1) { // ��ǰ���ţ��ж�ջ������һ������
			   if(sign == '+') {
				   stack.push(curr);
			   } else if(sign == '-') { // +-���һ������1-2����1+(-2)
				   stack.push(-curr);
			   } else if(sign == '*') {
				   stack.push(stack.pop() * curr);
			   } else if(sign == '/') { // *��/���������������ѹ��
				   stack.push(stack.pop() / curr);
			   }
			   sign = c; // ����sign
			   curr = 0; // curr��0
		   }
	   }
	   int res = 0;
	   while(!stack.isEmpty()) { // stack�洢�Ķ��ǿ���ֱ�����ӷ������ݣ��˳��Ѿ����ȴ����
		   res += stack.pop();
	   }
	   return res;
	}
	
	/**
	 * 224. Basic Calculator
	 * ������ʽ��ֻ�мӼ���������
	 * "(1+(4+5+2)-3)+(6+8)" = 23
	 * @param s
	 * @return
	 */
	public int calculate(String s) {
		int len = s.length();
		int sign = 1; // sign��ʾ����
		int result = 0; // resultʼ�ձ��ֵ�ǰ�Ľ��
		Stack<Integer> stack = new Stack<Integer>(); // ջ���ڷ��ź����ŵ�ǰ����ı���
		for (int i = 0; i < len; i++) {
			if (Character.isDigit(s.charAt(i))) { // һ�����ֵļ���
				int sum = s.charAt(i) - '0';
				while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) { // ���ֿ��ܲ�ֹһλ
					sum = sum * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				result += sum * sign; 
			} else if (s.charAt(i) == '+') {
				sign = 1;
			} else if (s.charAt(i) == '-') {
				sign = -1;
			} else if (s.charAt(i) == '(') {
				stack.push(result); // ������������ѹ�뵱ǰֵ
				stack.push(sign); // ��ѹ�����
				result = 0; // result�Ѿ����棬result��0
				sign = 1;   // ������sign�϶���+
			} else if (s.charAt(i) == ')') {
				int currSign = stack.pop();
				result = result * currSign + stack.pop(); // pop���Σ���һ���������ţ��ڶ�����֮ǰ�����ֵ
			}
		}
		return result;
    }
	
	/**
	 * ��ֱ��ͼ�������ľ��������heights��ʾ�߶ȣ���Ⱦ�Ϊ1
	 * @param heights
	 * @return
	 */
	public int largestRectangleArea(int[] heights) {
		heights = Arrays.copyOf(heights, heights.length + 1);
        int maxRect = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < heights.length; ++i) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int rect = heights[stack.pop()] * (stack.isEmpty() ? i : (i-stack.peek()-1));
                maxRect = Math.max(maxRect, rect);
            }
            stack.push(i);
        }
        return maxRect;
	}
	
	public static void main(String[] args) {
		StackSeries t = new StackSeries();
		int res = t.calculate("(1+(4+5+2)-3)+(6+8)");
		System.out.println(res);
	}
}
