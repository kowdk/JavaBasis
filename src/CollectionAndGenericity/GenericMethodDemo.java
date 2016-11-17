package CollectionAndGenericity;

import java.util.ArrayList;
import java.util.Collection;

//����������ӿ��ǵȼ۵ģ�����ʹ��������ͨ����������ʹ���˷��ͷ���
interface MyCollectionCard<E>
{
	boolean containsAll(Collection<?> c);
	boolean addAll(Collection<? extends E> c);
}

// ���ͷ���
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
