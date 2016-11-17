package Base;

import java.util.Arrays;

/*
 * final关键字可以修饰类、变量和方法，表示被修饰的对象不可改变
 * final成员变量：包括类属性和实例属性，都必须显式初始化，系统不会隐式赋初值
 * 不能对final修饰的形参赋值
 * 
 * */
public class FinalDemo {
	
	//常量可以用final修饰基本数据类型
	private final String BASE_DIR = "d://blabla";
	final int a = 6;
	final String str;
	final int c;
	final static double d;
	{
		str = "hello";
		//a = 9;声明的时候已经给了初始值，因此在初始化块中不能赋值
	}
	
	static
	{
		d = 1.1;
	}
	
	public FinalDemo()
	{
		c = 5;
	}
	
	//普通方法不能对final成员变量赋初值和指定初始值
	public void changeFinal()
	{
		//d = 1.2;
		//str = "hi";
	}
	
	public static void main(String[] args)
	{
		//但是final修饰引用类型变量时，只保证这个引用的地址不变，但对象本身可以被改变
		final int[] iArr = {1,2,3,4};
		Arrays.sort(iArr);
		iArr[1] = 0;
		
		//这样的赋值是错误的
		//iArr = null;
	}
}

//////////////////////////////////////////////////////////////////
class FinalMethod
{
	//final方法可以重载，与重写无关
	public final void PublicFinalMethod(){};
	public final void PublicFinalMethod(String str){};
	private final void PrivateFinalMethod(){};
	protected final void ProtectedFinalMethod(){};
}

class Sub extends FinalMethod
{
	//不能重写父类的final方法
	//public void PublicFinalMethod(){}
	//protected void ProtectedFinalMethod(){};
	//private方法不能为子类所用，所以完全没关系的两个方法，只是名字相同
	private void PrivateFinalMethod(){};
}
//////////////////////////////////////////////////////////////////////

//final类不能被继承
final class FinalClass{}
//class sub extends FinalClass{}

/*
 * 设计不可变类：
 * 1. 使用private final修饰该属性。
 * 2. 提供带参数的构造器。
 * 3. 不提供setter方法，只提供getter方法。
 * 4. 重写hashcode和equals方法。
 * 设计不可变类时，注意引用类型的属性，如果引用类型属性的类时可变的，可以使用匿名对象的方法保护该属性所引用的对象不会被修改。
*/


