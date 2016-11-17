package Base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ValuePropagationDemo {
	/*
	 * java的参数传递都是值传递，不能改变数值本身。
	 * 对于swap(int a, int b)来说，它交换了swap栈区的ab，但是并没有改变main栈区的ab。
	 * 对于swap(Datawrap dw)来说，它交换了堆内存中的Datawrap对象的值。
	 * */
	
	public void swap(int a, int b)
	{
		int tmp = 0;
		tmp = a;
		a = b;
		b = tmp;
		//System.out.println("In the func: "a + " " + b);
	}
	
	public void swap(DataWrap dw)
	{
		int tmp = 0;
		tmp = dw.a;
		dw.a = dw.b;
		dw.b = tmp;
		dw = null;
		//System.out.println("In the func: " + dw. a + " " + dw.b);
	}
	
	public static void main(String[] args)
	{
		DataWrap dw = new DataWrap(1, 2);
		int a = 1, b = 2;
		System.out.println(dw.a + " " + dw.b);
		new ValuePropagationDemo().swap(dw);
		System.out.println(dw.a + " " + dw.b);
	}
}

class DataWrap
{
	public int a;
	public int b;
	public DataWrap(int a, int b)
	{
		this.a = a;
		this.b = b;
	}
	
	List<String> list1 = new LinkedList<String>();
	List<String> list2 = new ArrayList<String>();
}
