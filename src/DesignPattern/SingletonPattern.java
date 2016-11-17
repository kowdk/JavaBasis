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
 * [����ģʽ]
 * ����ģʽ����ĵ��Ƕ��󴴽��Ĵ����Ͷ����ʱ������������ģʽ���ص㣺
 * 1. ˽�й������������ⲿ���ʼ����
 * 2. ˽�о�̬��Ա������
 * 3. ���о�̬������������˽�о�̬��Ա������
 * 
 * ����ģʽ��Ŀ�ģ�
 * ϣ������ֻ����һ��ʵ���������ṩһ��ȫ�ֵķ��ʵ㡣
 * */

/* ������ص�ʱ��ͳ�ʼ�������Ƕ�ʽ�ģ�������ֲ���������
 * ��Ϊһ������һ��classloader��ֻ�ᱻ��ʼ��һ�Σ�����JVM��֤�ģ����ǲ��ܱ�֤��classloader�����Ƕ�jvm�����
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
 * �ڶ��̵߳Ļ����£�����߳�ͬʱ�������������ʱ���ͻ�ʵ�������������˲����̰߳�ȫ��
 * ���߳�1ִ�е�1ʱ��cpu�л����߳�2���߳�2����õ���һ��instance��Ȼ���л��߳�1���߳�1��Ϊinstance����null�������ֵõ���һ��instance
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

/* ʹ��synchronized�ؼ���ȷ���߳�ͬ�����������ܲ��� */
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

/* ʹ��˫�������������ֻ֤�е�һ�β���ʵ����ʱ��Ż����ͬ������������ٴ��뿪�� */
class DoubleCheckedSinglenton {
	private DoubleCheckedSinglenton() {
	};

	private static DoubleCheckedSinglenton instance = null;

	public static DoubleCheckedSinglenton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckedSinglenton.class) { //�߳�1��������߳�2�ͽ���ȥ��
				if (instance == null) {
					// ��java1.5��ǰ��java��������������ִ�У��п����߳�һ����һ��δ��ʼ���Ķ����̶߳�ֱ����ȥ����ɴ���
					instance = new DoubleCheckedSinglenton();
					/*
					 * ���������Ա��ֽ�Ϊ:
					 * memory = allocate(); //�����ڴ�ռ�
					 * ctorInstance(memory); //��ʼ������
					 * instance = memory; // ��instanceָ�������ڴ��ַ
					 * һ�������䷢���������򣬽������instanceָ��һ��δ��ʼ�����ڴ�ռ䣬�������Ҫ��ֹ����������
					 * */
				}
			}
		}
		return instance;
	}
}

/*
 * ʹ��˽���ڲ��ಢ��ʵ�����л��ӿ�
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

/*ʹ�÷�����Ƶ���ģʽ*/
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
