package Base;

/**
 * java�ľ�̬������
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

	Subject subImpl = new SubjectImpl(); // ��̬�������һ��ԭ����Ȼ��ʹ�øö���ִ�з���
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
