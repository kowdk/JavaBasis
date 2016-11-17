package DesignPattern;

/**
 * Facade����ʵ�൱��A��B��Cģ�����۽��棬�������Facade��
 * ��ô�ͻ��˾Ͳ���Ҫ���Ե�����ϵͳ�е�A��B��Cģ����
 * Ҳ����Ҫ֪��ϵͳ�ڲ���ʵ��ϸ�ڣ�����������Ҫ֪��A��B��Cģ��Ĵ���
 * �ͻ���ֻ��Ҫ��Facade�ཻ���ͺ���
 * �Ӷ����õ�ʵ���˿ͻ��˺���ϵͳ��A��B��Cģ��Ľ���ÿͻ��˸����׵�ʹ��ϵͳ
 * */


/** ��ϵͳ��ɫ1 */
class SubClass1 {
	public void registration() {
		System.out.println("Registration...");
	}
}

/** ��ϵͳ��ɫ2 */
class SubClass2 {
	public void see() {
		System.out.println("See the doctor in clinic...");
	}
}

/** ��ϵͳ��ɫ3 */
class SubClass3 {
	public void pick() {
		System.out.println("Pick up the medicine...");
	}
}

/** �����ɫ */
class Facade {
	public void Emergency() {
		new SubClass2().see();
	}

	public void General() {
		new SubClass1().registration();
		new SubClass2().see();
		new SubClass3().pick();
	}
}

/** �ͻ� */
public class FacadePatternEasy {
	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.Emergency();
		facade.General();
	}
}
