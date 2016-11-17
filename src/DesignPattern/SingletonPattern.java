package DesignPattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * [创建模式]
 * 单例模式最关心的是对象创建的次数和对象何时被创建。单例模式的特点：
 * 1. 私有构造器，限制外部类初始化。
 * 2. 私有静态成员变量。
 * 3. 公有静态方法返回上述私有静态成员变量。
 * 
 * 单例模式的目的：
 * 希望对象只创建一个实例，并且提供一个全局的访问点。
 * */

/* 在类加载的时候就初始化对象，是恶汉式的，不会出现并发的问题
 * 因为一个类在一个classloader中只会被初始化一次，是由JVM保证的，但是不能保证多classloader甚至是多jvm的情况
 * */
class EagerInitializedSingleton {
	private EagerInitializedSingleton() {
	};

	private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

	public static EagerInitializedSingleton getInstance() {
		return instance;
	}
}

/*
 * 在多线程的环境下，多个线程同时进入这个单例类时，就会实例化多个对象，因此不是线程安全的
 * 当线程1执行到1时，cpu切换到线程2，线程2进入得到了一个instance，然后切回线程1，线程1以为instance还是null，所以又得到了一个instance
 */
class LazyInitializedSingleton {
	private LazyInitializedSingleton() {
	};

	private static LazyInitializedSingleton instance = null;

	public static LazyInitializedSingleton getInstance() {
		if (instance == null)// 1
		{
			instance = new LazyInitializedSingleton();// 2
		}
		return instance;
	}
}

/* 使用synchronized关键字确保线程同步，但是性能不够 */
class SynchronizedSinglenton {
	private SynchronizedSinglenton() {
	};

	private static SynchronizedSinglenton instance = null;

	public static synchronized SynchronizedSinglenton getInstance() {
		if (instance == null) {
			instance = new SynchronizedSinglenton();
		}
		return instance;
	}
}

/* 使用双重锁定检查来保证只有第一次产生实例的时候才会进入同步代码块来减少代码开销 */
class DoubleCheckedSinglenton {
	private DoubleCheckedSinglenton() {
	};

	private static DoubleCheckedSinglenton instance = null;

	public static DoubleCheckedSinglenton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckedSinglenton.class) { //线程1获得了锁线程2就进不去了
				if (instance == null) {
					// 在java1.5以前，java编译器允许乱序执行，有可能线程一产生一个未初始化的对象，线程二直接拿去用造成错误
					instance = new DoubleCheckedSinglenton();
					/*
					 * 上述语句可以被分解为:
					 * memory = allocate(); //分配内存空间
					 * ctorInstance(memory); //初始化对象
					 * instance = memory; // 将instance指向分配的内存地址
					 * 一旦后两句发生了重排序，将会造成instance指向一个未初始化的内存空间，错误！因此要阻止这种重排序。
					 * */
				}
			}
		}
		return instance;
	}
}

/*
 * 使用私有内部类并且实现序列化接口
 * */
class HelperSinglenton implements Serializable {

	private static final long serialVersionUID = -7604766932017737115L;

	private HelperSinglenton() {
	}

	private static class SinglentonHolder {
		static final HelperSinglenton instance = new HelperSinglenton();
	}
	
	public static HelperSinglenton getInstance()
	{
		return SinglentonHolder.instance;
	}
	
	private Object readResolve() {
        return getInstance();
    }
}

/*使用反射打破单例模式*/
public class SingletonPattern {
	public static void main(String[] args) {
		/*EagerInitializedSingleton instance1 = EagerInitializedSingleton.getInstance();
		EagerInitializedSingleton instance2 = null;
		
		try{
			Constructor[] cs = EagerInitializedSingleton.class.getDeclaredConstructors();
			for(Constructor c : cs)
			{
				c.setAccessible(true);
				instance2 = (EagerInitializedSingleton) c.newInstance();
				break;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}*/
		
		HelperSinglenton instance1 = HelperSinglenton.getInstance();
		HelperSinglenton instance2 = null;
		
		ObjectOutput out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(
			        "filename.ser"));
			out.writeObject(instance1);
	        out.close();
	        
	        ObjectInput in = new ObjectInputStream(new FileInputStream(
	                "filename.ser"));
	        instance2 = (HelperSinglenton) in.readObject();
	        in.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		System.out.println(instance1.hashCode());
		System.out.println(instance2.hashCode());
	}
}
