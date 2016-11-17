package Base;

public class StringAndStringBuilderDemo {
	/*
	 * ==判断两个变量是否指向同一块内存地址，equals判断两个变量的值是否相同。
	 * 
	 * String类为什么是字符串常量？？ 1. 字符串常量可以提供String pool，如果命中就不会在堆上开辟新内存了。
	 * 当使用双引号初始化一个String对象时，先在String pool中查找，如果值相同就返回对该值的引用；如果找不到就在pool中建一个。
	 * 但是当使用new的时候，就会直接在堆中除了pool的地方新建一块内存，可以使用intern把它放到堆里面。 2.
	 * socket通讯，SQL都需要字符串常量来确保安全。 3. 反射中的classLoder方法需要字符串常量提供指定的类。 4.
	 * 由于字符串是常量，所以它在被创建的时候hashcode就会被计算并缓存，这样在hashmap中作key非常快
	 * 
	 * String类是字符串常量，字符序列不可变；StringBuilder是字符串变量，字符序列是可变的。
	 * String类内部实现也会调用StringBuilder，在产生新的字符串以后，旧的字符串会失活，等待被回收，造成大量的堆栈垃圾
	 * StringBuffer是线程安全的，而StringBuilder不是，因此后者的性能更好。
	 */

	private static void stringBuilderTest() {
		StringBuilder sb = new StringBuilder();
		sb.append("java");
		sb.insert(0, "Hello ");
		sb.replace(5, 6, ",");
		sb.reverse();
		sb.setLength(5);
		System.out.println(sb.toString());
	}

	private static void stringTest() {
		String str1 = "aabb";
		String str2 = "aabb";
		String str3 = "aa" + "bb";
		String a = "aa";
		String b = "bb";
		String str5 = a + b;
		String str4 = new String("aabb");
		String str6 = str4.intern();
		/* str1-str3在编译时就被保存在常量池中，且只会保存一个 */
		System.out.println(str1 == str2);// true
		System.out.println(str1 == str3);// true
		System.out.println(str1 == str4);// false
		System.out.println(str1 == str5);// false
		System.out.println(str1 == str6);// true
	}

	public static void stringBuilderChange(StringBuilder str) {
		str.append("World");
	}

	private static void stringChange(String str) {
		str = "Hello World";
	}

	public static void main(String[] args) {
		stringTest();
		// StringBuilder sb = new StringBuilder("Hello ");
		// String str = "Hello";
		// stringChange(str);
		// System.out.println(str);
	}
}
