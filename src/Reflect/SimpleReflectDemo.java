package Reflect;

import java.lang.reflect.Constructor;

/*
 * ͨ����������вεĹ��캯��
 * 
 * */
public class SimpleReflectDemo {
	public static void main(String[] args) throws Exception
	{
		Class<?> clazz = Class.forName("javax.swing.JFrame");
		Constructor con = clazz.getConstructor(String.class);
		Object obj = con.newInstance("���Դ���");
		System.out.println(obj);
	}
}
