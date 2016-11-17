package GarbageCollection;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/*
 * 实例展示对象的软引用、弱引用和虚引用。
 * 软引用只有在内存不够的时候才进行垃圾回收，弱引用不管内存是否充足都会进行垃圾回收。
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
		String str = new String("堆内存对象字符串");
		SoftReference sf = new SoftReference(str);
		WeakReference wr = new WeakReference(str);
		
		str = null;
		System.out.println(sf.get());
		System.out.println(wr.get());
		
		//通知系统执行垃圾回收
		System.gc();
		System.runFinalization();
		System.out.println(sf.get());
		System.out.println(wr.get());
		
		SoftReference<Person>[] sr = new SoftReference[100];
		for(int i=0; i<sr.length; i++)
		{
			sr[i] = new SoftReference<Person>(new Person("名字"+i, (i+1)*4/100));
		}
		System.out.println(sr[1].get());
		System.gc();
		System.runFinalization();
		System.out.println(sr[1].get());
	}
}
