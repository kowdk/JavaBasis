package CollectionAndGenericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类
 * @author xutao
 *
 * @param <T>
 */
class Fruit<T> {
	//就把T想成一个String，Integer就行
	//T、E、K、V等都只是一个记号，T表示Type，E表示Element等
	private T info;
	public Fruit(T info)
	{
		this.info = info;
	}
	public void setInfo(T info)
	{
		this.info = info;
	}
	public T getInfo()
	{
		return this.info;
	}
}

class Pair<M, V> {

    private M key;
    private V value;

    public Pair(M key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 泛型方法
     * @param key
     */
    public void setKey(M key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
    public M getKey()   { return key; }
    public V getValue() { return value; }
}


class A{
	
}

class B extends A {
	
}

class C extends A {
	
}

class D extends B {
	
}

public class GenericityDemo {
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
	
	public static <T extends A> List<T> intersection(List<T> list1, List<T> list2) {
		
		return null;
	}

	public static void main(String[] args)
	{
		Fruit<String> fs = new Fruit<String>("apple");
		System.out.println(fs.getInfo());
		Fruit<Integer> fi = new Fruit<Integer>(1);
		System.out.println(fi.getInfo());
		//很好地说明了不存在泛型类，只是在初始化的时候限定了参数类型
		System.out.println(fs.getClass() == fi.getClass());//true,都是object
		//更不存在泛型子类，String是Object的子类，但是List<String>却不是List<Object>的子类
		List<String> l = new ArrayList<String>();
		List<Object> o = new ArrayList<Object>();
		System.out.println(l.getClass() == o.getClass());//true
		
		List<B> list1 = new ArrayList<B>();
		List<B> list2 = new ArrayList<B>();
		intersection(list1, list2);
		
		Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        boolean same = GenericityDemo.compare(p1, p2);
	}
}
