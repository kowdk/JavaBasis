package GarbageCollection;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/*
 * ʵ��չʾ����������á������ú������á�
 * ������ֻ�����ڴ治����ʱ��Ž����������գ������ò����ڴ��Ƿ���㶼������������ա�
 * 
 * */

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return "Person[ name=" + name + " age=" + age + " ]";
	}
}

public class ReferenceDemo {
	public static void main(String[] args)
	{
		String str = new String("���ڴ�����ַ���");
		SoftReference sf = new SoftReference(str);
		WeakReference wr = new WeakReference(str);
		
		str = null;
		System.out.println(sf.get());
		System.out.println(wr.get());
		
		//֪ͨϵͳִ����������
		System.gc();
		System.runFinalization();
		System.out.println(sf.get());
		System.out.println(wr.get());
		
		SoftReference<Person>[] sr = new SoftReference[100];
		for(int i=0; i<sr.length; i++)
		{
			sr[i] = new SoftReference<Person>(new Person("����"+i, (i+1)*4/100));
		}
		System.out.println(sr[1].get());
		System.gc();
		System.runFinalization();
		System.out.println(sr[1].get());
	}
}
