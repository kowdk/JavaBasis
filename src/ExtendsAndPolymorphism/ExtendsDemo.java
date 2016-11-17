package ExtendsAndPolymorphism;

import java.io.IOException;

/*
 * ����һ������ʱ�����ȵ��þ�̬��ʼ���飬Ȼ����ó�ʼ���飬Ȼ����ù�������
 * ������������ʽ��ʼ�����þ�̬�����ʼ��������ִ�С�
 * */
class BaseClass {
	{
		System.out.println("����ĳ�ʼ����");
	}
	static {
		System.out.println("����ľ�̬��ʼ����");
	}
	public static int b = 1;
	public BaseClass() {
		System.out.println("���๹����");
	}
	public int a = 1;
	public BaseClass(int a) {
		System.out.println("���౻���صĹ�����");
	}

	public static void overrideStaticMethod()
	{
		System.out.println("�����෽��");
	}
	
	public void baseMethod() {
		System.out.println("������ͨ����");
	}

	public void overrideMethod() {
		System.out.println("���౻��д�ķ���");
	}
}

class MiddleClass extends BaseClass {
	public int a = 2;
	public static int c = 2;
	// public static int b = 2;
	{
		System.out.println("�м���ĳ�ʼ����");
	}
	static {
		System.out.println("�м���ľ�̬��ʼ����");
	}

	public MiddleClass() {
		System.out.println("�м��๹����");
	}

	public MiddleClass(int a) {
		System.out.println("�м��౻���صĹ�����");
	}

	public void middleMethod() {
		System.out.println("�м�����ͨ����");
	}

	@Override
	public void overrideMethod() {
		System.out.println("�м��౻��д�ķ���");
	}
}

class SubClass extends MiddleClass {
	public int a = 3;
	public static int c = 3;
	public static int b = 3;
	{
		System.out.println("����ĳ�ʼ����");
		c = 4;
	}

	static {
		System.out.println("����ľ�̬��ʼ����");
	}

	public SubClass() {
		System.out.println("���๹����");
	}
	
	public SubClass(int a) {
		// this();
		System.out.println("���౻���صĹ�����");
	}

	public static void overrideStaticMethod()
	{
		System.out.println("������д���෽��");
	}
	
	public void subMethod() {
		System.out.println("������ͨ����");
	}

	@Override
	public void overrideMethod() {
		System.out.println("������д�ķ���");
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
		//go((Base)s);//����ִ����sub��add����
		
		//Base b = new Sub();
		//go((Sub)b);
		
		/*Base b = new Base();
		go((Sub)b);//����ʱ�쳣 ClassCast�쳣
*/		
		/*SubClass sub = new SubClass();
		sub.overrideMethod();
		System.out.println(sub.c);*/
		/*  ����ľ�̬��ʼ����
			�м���ľ�̬��ʼ����
			����ľ�̬��ʼ����
			����ĳ�ʼ����
			���๹����
			�м���ĳ�ʼ����
			�м��๹����
			����ĳ�ʼ����
			���౻���صĹ�����
			������д�ķ���
			4
		*/
		
		/*MiddleClass mc = new SubClass(3);
		mc.overrideMethod();
		System.out.println(mc.c);*/	
		/*  ����ľ�̬��ʼ����
			�м���ľ�̬��ʼ����
			����ľ�̬��ʼ����
			����ĳ�ʼ����
			���๹����
			�м���ĳ�ʼ����
			�м��๹����
			����ĳ�ʼ����
			���౻���صĹ�����
			������д�ķ���
			2
		 * */
		
		BaseClass bc = new SubClass(6);
		new SubClass(6); // ��̬���Ժͳ�ʼ����ֻ�ᱻ����һ��
		bc.overrideMethod(); // ���౻���صķ���
		bc.baseMethod(); // ����δ�����صķ��������ֶ�̬
		System.out.println(bc.a); // �������Բ����ֶ�̬
		//new Z();
		//��̬�������У�Ȼ���ǳ�ʼ������߱������塢��Ȼ���ǹ��������ȸ�������ࡣ
	}
}
