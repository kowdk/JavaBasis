package CollectionAndGenericity;

import java.util.ArrayList;
import java.util.Collection;

//下面的两个接口是等价的，上面使用了类型通配符，下面的使用了泛型方法
interface MyCollectionCard<E>
{
	boolean containsAll(Collection<?> c);
	boolean addAll(Collection<? extends E> c);
}

// 泛型方法
interface MyCollectionMethod<E>
{
	<T> boolean containsAll(Collection<T> c);
	<T extends E> boolean addAll(Collection<T> c);
}

public class GenericMethodDemo {
	static <T> void fromArrayToCollection(T[] a, Collection<T> c)
	{
		for(T i : a)
		{
			c.add(i);
		}
	}
	
	static <T> void fromCollectionToCollection(Collection<? extends T> a, Collection<T> c)
	{
		for(T i : a)
		{
			c.add(i);
		}
	}
	
	public static void main(String[] args)
	{
		String[] str = new String[10];
		Collection<Object> b = new ArrayList<Object>();
		Collection<String> c = new ArrayList<String>();
		fromArrayToCollection(str, c);
		fromCollectionToCollection(c, b);
	}
}
