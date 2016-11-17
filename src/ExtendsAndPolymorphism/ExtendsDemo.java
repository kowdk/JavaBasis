package ExtendsAndPolymorphism;

import java.io.IOException;

/*
 * 创建一个对象时，首先调用静态初始化块，然后调用初始化块，然后调用构造器。
 * 对于类属性显式初始化和用静态化块初始化，按序执行。
 * */
class BaseClass {
	{
		System.out.println("父类的初始化块");
	}
	static {
		System.out.println("父类的静态初始化块");
	}
	public static int b = 1;
	public BaseClass() {
		System.out.println("父类构造器");
	}
	public int a = 1;
	public BaseClass(int a) {
		System.out.println("父类被重载的构造器");
	}

	public static void overrideStaticMethod()
	{
		System.out.println("父类类方法");
	}
	
	public void baseMethod() {
		System.out.println("父类普通方法");
	}

	public void overrideMethod() {
		System.out.println("父类被重写的方法");
	}
}

class MiddleClass extends BaseClass {
	public int a = 2;
	public static int c = 2;
	// public static int b = 2;
	{
		System.out.println("中间类的初始化块");
	}
	static {
		System.out.println("中间类的静态初始化块");
	}

	public MiddleClass() {
		System.out.println("中间类构造器");
	}

	public MiddleClass(int a) {
		System.out.println("中间类被重载的构造器");
	}

	public void middleMethod() {
		System.out.println("中间类普通方法");
	}

	@Override
	public void overrideMethod() {
		System.out.println("中间类被重写的方法");
	}
}

class SubClass extends MiddleClass {
	public int a = 3;
	public static int c = 3;
	public static int b = 3;
	{
		System.out.println("子类的初始化块");
		c = 4;
	}

	static {
		System.out.println("子类的静态初始化块");
	}

	public SubClass() {
		System.out.println("子类构造器");
	}
	
	public SubClass(int a) {
		// this();
		System.out.println("子类被重载的构造器");
	}

	public static void overrideStaticMethod()
	{
		System.out.println("子类重写的类方法");
	}
	
	public void subMethod() {
		System.out.println("子类普通方法");
	}

	@Override
	public void overrideMethod() {
		System.out.println("子类重写的方法");
	}
}

class X
{
	Y b = new Y();
	/*{
		System.out.println("here");
	}*/
	X(){System.out.println("X");}
}

class Y
{
	Y(){System.out.println("Y");}
}

class Z extends X
{
	Z(){System.out.println("Z");}
	Y b = new Y();
}


class Base
{
	int i;
	Base()
	{
		add(1);
		System.out.println("In base constructor: " + i);
	}
	void add(int v)
	{
		i+=v;
		System.out.println("In base add: " + i);
	}
}

class Sub extends Base
{
	Sub()
	{
		add(2);
		System.out.println("In sub constructor: " + i);
	}
	@Override
	void add(int v)
	{
		i+=v*2;
		System.out.println("In sub add: " + i);
	}
}

public class ExtendsDemo {
	private static void go(Sub b)
	{
		b.add(8);
	}
	public static void main(String[] args) throws IOException {
		System.out.println("----main---");
		//Sub s = new Sub();
		//go(s);//6+2*8
		//go((Base)s);//还是执行了sub的add方法
		
		//Base b = new Sub();
		//go((Sub)b);
		
		/*Base b = new Base();
		go((Sub)b);//运行时异常 ClassCast异常
*/		
		/*SubClass sub = new SubClass();
		sub.overrideMethod();
		System.out.println(sub.c);*/
		/*  父类的静态初始化块
			中间类的静态初始化块
			子类的静态初始化块
			父类的初始化块
			父类构造器
			中间类的初始化块
			中间类构造器
			子类的初始化块
			子类被重载的构造器
			子类重写的方法
			4
		*/
		
		/*MiddleClass mc = new SubClass(3);
		mc.overrideMethod();
		System.out.println(mc.c);*/	
		/*  父类的静态初始化块
			中间类的静态初始化块
			子类的静态初始化块
			父类的初始化块
			父类构造器
			中间类的初始化块
			中间类构造器
			子类的初始化块
			子类被重载的构造器
			子类重写的方法
			2
		 * */
		
		BaseClass bc = new SubClass(6);
		new SubClass(6); // 静态属性和初始化块只会被加载一次
		bc.overrideMethod(); // 父类被重载的方法
		bc.baseMethod(); // 父类未被重载的方法不体现多态
		System.out.println(bc.a); // 父类属性不体现多态
		//new Z();
		//静态变量先行，然后是初始化块或者变量定义、再然后是构造器；先父类后子类。
	}
}
