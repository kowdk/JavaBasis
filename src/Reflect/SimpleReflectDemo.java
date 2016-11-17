package Reflect;

import java.lang.reflect.Constructor;

/*
 * 通过反射调用有参的构造函数
 * 
 * */
public class SimpleReflectDemo {
	public static void main(String[] args) throws Exception
	{
		Class<?> clazz = Class.forName("javax.swing.JFrame");
		Constructor con = clazz.getConstructor(String.class);
		Object obj = con.newInstance("测试窗口");
		System.out.println(obj);
	}
}
