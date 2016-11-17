package Base;

/**
 * java的静态代理类
 * */

interface Subject{
	public void doSth();
}

class SubjectImpl implements Subject{

	@Override
	public void doSth() {
		System.out.println("The SubjectImpl really does sth...");
	}
}

class SubjectProxy implements Subject{

	Subject subImpl = new SubjectImpl(); // 静态代理组合一个原对象，然后使用该对象执行方法
	@Override
	public void doSth() {
		subImpl.doSth();
	}
}

public class StaticProxy {
	public static void main(String[] args) {
		Subject sub = new SubjectProxy();
		sub.doSth();
	}
}
