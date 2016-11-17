/**
 * 
 */
package CollectionAndGenericity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;

public class CollectionsDemo {

	private static List<Integer> list = new ArrayList<Integer>();

	private static List<Integer> copyFromArrayToList(int[] src) {
		List<Integer> list = null;
		if (src == null)
			return list;
		list = new ArrayList(src.length);
		for (int i = 0; i < src.length; i++) {
			list.add(src[i]);
		}
		return list;
	}

	private static final int REVERSE_THRESHOLD = 18;

	private static void rotate(List<?> list, int off){
		int size = list.size();
		if(size == 0)
			return ;
		int mid = -off%size;
		if(mid < 0)
			mid+=size;
		if(mid == 0)
			return ;
		
		reverse(list.subList(0, mid));
		reverse(list.subList(mid, size));
		reverse(list);
	}
	
	private static void reverse(List<?> list) {
		int size = list.size();
		if (size < REVERSE_THRESHOLD || list instanceof RandomAccess) {
			for (int i = 0, mid = size / 2, j = size - 1; i < mid; i++, j--)
				swap(list, i, j);
		} else {
			ListIterator fwd = list.listIterator();
			ListIterator rev = list.listIterator(size);
			for(int i=0, mid=list.size()/2; i<mid; i++)
			{
				Object tmp = fwd.next();
				fwd.set(rev.previous());
				rev.set(tmp);
			}
		}
	}
	
	//rnd.nextInt(n)方法返回0至n-1的随机数
	private static void shuffle(List<?> list, Random rnd)
	{
		int size = list.size();
		//由于nextInt是返回从0到该数，所以从大到小去做
		for(int i=size; i>1; i--)
			swap(list, i-1, rnd.nextInt(i));
	}
		
	private static void swap(Object[] obj, int i, int j)
	{
		Object tmp = obj[i];
		obj[i] = obj[j];
		obj[j] = tmp;
	}	
	
	private static void swap(List<?> list, int i, int j)
	{
		final List l = list;
		l.set(j, l.set(i, l.get(j)));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] iArr = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		list = copyFromArrayToList(iArr);
		System.out.println(list.toString());
		rotate(list, -2);
		System.out.println(list.toString());
		
	}

}
