package Base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ValuePropagationDemo {
	/*
	 * java�Ĳ������ݶ���ֵ���ݣ����ܸı���ֵ����
	 * ����swap(int a, int b)��˵����������swapջ����ab�����ǲ�û�иı�mainջ����ab��
	 * ����swap(Datawrap dw)��˵���������˶��ڴ��е�Datawrap�����ֵ��
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
