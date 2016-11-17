package Base;

public class StringAndStringBuilderDemo {
	/*
	 * ==�ж����������Ƿ�ָ��ͬһ���ڴ��ַ��equals�ж�����������ֵ�Ƿ���ͬ��
	 * 
	 * String��Ϊʲô���ַ����������� 1. �ַ������������ṩString pool��������оͲ����ڶ��Ͽ������ڴ��ˡ�
	 * ��ʹ��˫���ų�ʼ��һ��String����ʱ������String pool�в��ң����ֵ��ͬ�ͷ��ضԸ�ֵ�����ã�����Ҳ�������pool�н�һ����
	 * ���ǵ�ʹ��new��ʱ�򣬾ͻ�ֱ���ڶ��г���pool�ĵط��½�һ���ڴ棬����ʹ��intern�����ŵ������档 2.
	 * socketͨѶ��SQL����Ҫ�ַ���������ȷ����ȫ�� 3. �����е�classLoder������Ҫ�ַ��������ṩָ�����ࡣ 4.
	 * �����ַ����ǳ������������ڱ�������ʱ��hashcode�ͻᱻ���㲢���棬������hashmap����key�ǳ���
	 * 
	 * String�����ַ����������ַ����в��ɱ䣻StringBuilder���ַ����������ַ������ǿɱ�ġ�
	 * String���ڲ�ʵ��Ҳ�����StringBuilder���ڲ����µ��ַ����Ժ󣬾ɵ��ַ�����ʧ��ȴ������գ���ɴ����Ķ�ջ����
	 * StringBuffer���̰߳�ȫ�ģ���StringBuilder���ǣ���˺��ߵ����ܸ��á�
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
		/* str1-str3�ڱ���ʱ�ͱ������ڳ������У���ֻ�ᱣ��һ�� */
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
