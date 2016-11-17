package Base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDemo {
	public static void main(String[] args) {
		Object[] obj1 = new Object[] { 1, 2, 3 };
		Object[] obj2 = new Object[] { 4, 5, 6 };

		System.out.println(Arrays.toString(Add(obj1, 7)));
		System.out.println(Arrays.toString(Add(obj1, obj2)));

		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");

		String[] strArr = list.toArray(new String[list.size()]);
		System.out.println(Arrays.toString(strArr));

		/*
		 * java中数组拷贝有如下方法: 
		 * 1. Arrays.copyOf(source, source.length);
		 * 2. Arrays.copyOfRange(source, source.length-3, source.length);
		 * 3. System.arraycopy(source, 0, dst, 0, source.length);
		 * 4. (new int[]{1,2,3}).clone;
		 */
		Integer[] iArr = new Integer[] { 1, 2, 3, 4, 5 };
		Integer[] iRes = copyFullArrayUsingClone(iArr);
		System.out.println(Arrays.toString(iRes));

		String[] sArr = new String[] { "q", "w", "e" };
		String[] sRes = copyFullArrayUsingClone(sArr);
		System.out.println(Arrays.toString(sRes));
	}

	// 实现了泛型方法
	private static <T> T[] copyFullArrayUsingClone(T[] source) {
		return source.clone();
	}

	private static int[] copyFullArrayUsingSystem(int[] source) {
		int[] tmp = new int[source.length];
		System.arraycopy(source, 0, tmp, 0, source.length);
		return tmp;
	}

	public static Object[] Add(Object[] obj, Object... args) {
		Object[] newArr = new Object[obj.length + args.length];
		System.arraycopy(obj, 0, newArr, 0, obj.length);

		for (int i = 0; i < args.length; i++) {
			newArr[obj.length + i] = args[i];
		}

		return newArr;
	}
}
