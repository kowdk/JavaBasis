package ExtendsAndPolymorphism;

class Cow{
	private double weight;
	private String prop = "Outer class prop";
	private static String staticProp = "outter class static prop";
	public Cow(){}
	public Cow(double weight)
	{
		this.weight = weight;
	}
	
	/*
	 * ��̬�ڲ���
	 * */
	static class staticCow{
		private static String staticProp = "static inner class static prop";
		private String prop = "static inner class prop";	
	}
	
	/*
	 * �Ǿ�̬�ڲ���
	 * */
	class CowLeg{
		private double length;
		private String color;
		private String prop = "Inner class prop";
		public CowLeg(){}
		public CowLeg(double length, String color)
		{
			this.length = length;
			this.color = color;
		}
		public void info()
		{
			String prop = "Local prop";
			System.out.println("CowLeg length = [" + length + "] , color = [" + color +"]");
			System.out.println("Cow Weight = [" + weight + "]");
			//�ڲ������ͨ���ⲿ����������������Ա
			System.out.println("Outer class prop = [" + Cow.this.prop + "] , Inner class prop = [" + this.prop + "] , Local prop = [" + prop + "]");
		}
	}
	
	public void test()
	{
		CowLeg cl = new CowLeg(10.0, "red");
		cl.info();
		/*
		 * �ڲ����˽�����Ա���ͨ���½��ڲ������
		 * */
		System.out.println("Inner class private prop: " + new CowLeg().prop);
	}
	
	public void accessInnerClass()
	{
		System.out.println(staticCow.staticProp);
		System.out.println(new staticCow().prop);
	}
}

public class InClassDemo {

	public static void main(String[] args)
	{
		Cow c = new Cow(20.0);
		c.test();
		c.accessInnerClass();
		/*
		 * ���ⲿ������ʹ���ڲ���ķ���������new,����ʱ�ͱ���Ҫ���ڲ��౻���õķ�����public����
		 * */
		Cow.CowLeg in = new Cow(20.0).new CowLeg(10.0, "red");
		in.info();
	}
}
