package Base;

import java.util.ArrayList;
import java.util.List;

/*
 * 包装类是对基本数据类型的包装，基本数据类型操作简单，而包装类可以用在Object这个体系下
 * 包装类和自动数据类型提供自动装箱和自动拆箱功能
 * */
public class WrapperClassDemo {
	
	private static void doSth(Character c){
		
	}
	public static void main(String[] args)
	{
		int i = 1;
		char c = 'a';
		
		doSth(new Character(c));
		List<Integer> list = new ArrayList<Integer>();
		
		//自动装箱
		int j = i+1;
		list.add(j);
		
		//自动拆箱
		Integer in = new Integer(5);
		j = j + in;
		in = null;
	}
}
