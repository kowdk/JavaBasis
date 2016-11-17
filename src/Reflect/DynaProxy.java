package Reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Dog{
	public void run();
	public void info();
}

class GunDog implements Dog
{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Dog can run");
	}

	@Override
	public void info() {
		// TODO Auto-generated method stub
		System.out.println("Dog's info");
	}
}

class DogUtil
{
	public static void method1(){
		System.out.println("Method 1");
	}
	public static void method2(){
		System.out.println("Method 2");
	}
}

class AnInvocationHandler implements InvocationHandler
{
	private Object target;
	public AnInvocationHandler(Object target)
	{
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		DogUtil.method1();
		Object result = method.invoke(target, args);
		DogUtil.method2();
		return result;
	}
}

class MyProxyFactory 
{
	public static Object getProxy(Object target)
	{
		AnInvocationHandler handler = new AnInvocationHandler(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}

public class DynaProxy {
	public static void main(String[] args) throws Exception {
		Dog target = new GunDog();
		//dog是动态代理对象，是通过对象来获取一个dog的动态代理
		Dog dog = (Dog) MyProxyFactory.getProxy(target);
		dog.info();
		dog.run();
	}
}
