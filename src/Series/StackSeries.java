package Series;

import java.util.Arrays;
import java.util.Stack;
public class StackSeries {
	
	/**
	 * 栈的压入弹出序列，压入栈的数组没有重复元素，判断出栈的数组是否是一个正确的出栈顺序
	 * @param inStack = [1,2,3,4]
	 * @param outStack = [2,4,3,1]
	 * @return
	 */
	public boolean isStackOrder(int[] inStack, int[] outStack) {
		if (inStack.length == 0 || outStack.length == 0)
			return false;
		if (inStack.length != outStack.length)
			return false;
		Stack<Integer> stack = new Stack<Integer>(); // 使用stack来完全模拟in和out的动作
		for (int i = 0, j = 0; i < inStack.length; i++) {
			stack.push(inStack[i]); // 先压入栈
			while (!stack.isEmpty() && stack.peek() == outStack[j]) { // 和出栈比较，相同则出栈
				stack.pop();
				j++;
			}
		}
		return stack.isEmpty() ? true : false; // 如果完全一致最后栈应该是空的
	}
	
	/**
	 * 计算四则运算，没有括号，有空格，输入没有异常情况
	 * 227. Basic Calculator II
	 * e.g. 3-2*2+6/2
	 * @param s
	 * @return
	 */
	public int calculate2(String s) {
	   if(s == null || s.length() == 0) {
		   return 0;
	   }
	   s = s.trim().replace(" ", ""); // 去除空格的干扰
	   int curr = 0;
	   char sign = '+'; // sign默认是+号，用来更新当前的
	   Stack<Integer> stack = new Stack<Integer>(); // stack用于存储带符号的当前值
	   for(int i = 0; i < s.length(); i++) {
		   char c = s.charAt(i);
		   if(Character.isDigit(c)) { // 一直计算数字
			   curr = curr * 10 + c - '0'; // 数字不止一位
		   }
		   if(!Character.isDigit(c) || i == s.length() - 1) { // 当前符号，判断栈顶和上一个符号
			   if(sign == '+') {
				   stack.push(curr);
			   } else if(sign == '-') { // +-最后一并处理，1-2当做1+(-2)
				   stack.push(-curr);
			   } else if(sign == '*') {
				   stack.push(stack.pop() * curr);
			   } else if(sign == '/') { // *和/立即处理，并将结果压回
				   stack.push(stack.pop() / curr);
			   }
			   sign = c; // 更新sign
			   curr = 0; // curr置0
		   }
	   }
	   int res = 0;
	   while(!stack.isEmpty()) { // stack存储的都是可以直接做加法的数据，乘除已经优先处理过
		   res += stack.pop();
	   }
	   return res;
	}
	
	/**
	 * 224. Basic Calculator
	 * 计算算式，只有加减法和括号
	 * "(1+(4+5+2)-3)+(6+8)" = 23
	 * @param s
	 * @return
	 */
	public int calculate(String s) {
		int len = s.length();
		int sign = 1; // sign表示正负
		int result = 0; // result始终保持当前的结果
		Stack<Integer> stack = new Stack<Integer>(); // 栈用于符号和括号当前结果的保存
		for (int i = 0; i < len; i++) {
			if (Character.isDigit(s.charAt(i))) { // 一次数字的计算
				int sum = s.charAt(i) - '0';
				while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) { // 数字可能不止一位
					sum = sum * 10 + s.charAt(i + 1) - '0';
					i++;
				}
				result += sum * sign; 
			} else if (s.charAt(i) == '+') {
				sign = 1;
			} else if (s.charAt(i) == '-') {
				sign = -1;
			} else if (s.charAt(i) == '(') {
				stack.push(result); // 遇上左括号先压入当前值
				stack.push(sign); // 再压入符号
				result = 0; // result已经保存，result置0
				sign = 1;   // 括号内sign肯定是+
			} else if (s.charAt(i) == ')') {
				int currSign = stack.pop();
				result = result * currSign + stack.pop(); // pop两次，第一次是正负号，第二次是之前保存的值
			}
		}
		return result;
    }
	
	/**
	 * 在直方图中求最大的矩阵面积，heights表示高度，宽度均为1
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
