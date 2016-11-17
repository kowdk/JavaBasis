package Base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Java的动态代理类
 * */

interface DynSubject{
	public void doSth();
}

class DynSubjectImpl implements DynSubject{
	@Override
	public void doSth() {
		System.out.println("The subject implement really does sth...");
	}
}

class ProxyHandler implements InvocationHandler{

	private Object target = null;
	
	public ProxyHandler(Object target){
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//在此可以做方法的增强，可以在方法执行前后，调用日志、做事务控制；也可以在此处实现缓存，也就是享元模式。
		Object result = null;
		//before method you can do sth...
		result = method.invoke(target, args);
		//after method you can do sth...
		return result;
	}
}

class ProxyFactory{
	public static Object getProxy(Object target){
		ProxyHandler handler = new ProxyHandler(target);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}

public class DynamicProxy {
	
	public static void main(String[] args) {
		// 拦截被代理对象的直接访问
		DynSubject subject = new DynSubjectImpl();
		// 返回一个增强的对象
		DynSubject sub = (DynSubject) ProxyFactory.getProxy(subject);
		sub.doSth();
	}
}
