package Reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
	void walk();

	void sayhello(String name);
}

class GunPerson implements Person {

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("I can walk.");
	}

	@Override
	public void sayhello(String name) {
		// TODO Auto-generated method stub
		System.out.println("I can say hello.");
	}

}

class MyInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("methods: " + method);
		if (args != null) {
			for (Object obj : args) {
				System.out.println(obj);
			}
		} else {
			System.out.println("no args");
		}
		return null;
	}
}

public class ProxyDemo {
	public static void main(String[] args) throws Exception {
		
		 InvocationHandler handler = new MyInvocationHandler();
		 //��֪��person�������µĵڶ�����������������������ִ��handler�����invoke���� 
		 Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new
		 Class[]{Person.class}, handler);
		 
		 //����ľ��Ƕ�̬������ 
		 Person target = new GunPerson(); 
		 Person ps = (Person) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
		 ps.walk();
		 ps.sayhello("xt");
	}
}
