package Base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Java�Ķ�̬������
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
		//�ڴ˿�������������ǿ�������ڷ���ִ��ǰ�󣬵�����־����������ƣ�Ҳ�����ڴ˴�ʵ�ֻ��棬Ҳ������Ԫģʽ��
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
		// ���ر���������ֱ�ӷ���
		DynSubject subject = new DynSubjectImpl();
		// ����һ����ǿ�Ķ���
		DynSubject sub = (DynSubject) ProxyFactory.getProxy(subject);
		sub.doSth();
	}
}
