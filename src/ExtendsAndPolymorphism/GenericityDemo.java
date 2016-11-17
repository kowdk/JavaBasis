package ExtendsAndPolymorphism;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

class A<T extends Number>
{
	
}

public class GenericityDemo {
	
	//泛型方法 参数匹配<> 调用时src应该是dst的类型或者子类
	private static <T> void fromArrToCollection(T[] src, Collection<T> dst)
	{
		for(T o : src)
		{
			dst.add(o);
		}
	}
	
	private static void TreeSetSuper(){
		TreeSet<String> tsWithObject = new TreeSet<String>(new Comparator<Object>()
				{
					@Override
					public int compare(Object arg0, Object arg1) {
						// TODO Auto-generated method stub
						return -(arg0.hashCode() - arg1.hashCode());
					}	
				});
		tsWithObject.add("xutao");
		tsWithObject.add("ab");
		System.out.println(tsWithObject);
		
		TreeSet<String> tsWithString = new TreeSet<String>(new Comparator<String>(){

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}});
		
		tsWithString.add("aa");
		tsWithString.add("bb");
		System.out.println(tsWithString);
	}
	
	public static void main(String[] args)
	{
		//TreeSetSuper();
		/*String[] str = {"aa", "bb", "cc"};
		Integer[] in = new Integer[100];
		String[] a = new String[100];
		Collection<String> c = new ArrayList<String>();
		Collection<Object> o = new ArrayList<Object>();
		fromArrToCollection(in, c);
		fromArrToCollection(str, o);
		System.out.println(o);*/
		
		/*List<Shape> list = new ArrayList<Shape>();
		list.add(new Circle());
		list.add(new Rectangle());
		Canvas c = new Canvas();
		c.drawAll(list);*/
		
		A<Integer> ai = new A<Integer>();
		A<Double> ad = new A<Double>();
	}
}
