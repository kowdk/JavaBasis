package DesignPattern;

/*
 *[��Ϊģʽ]
 * �м���ģʽ
 * �м���ģʽΪϵͳ�ڶ���֮���ͨѶ�ṩý��
 * ������֮��ͨѶ�ܸ��ӣ���ʹ��ͨѶ���м������������ͨѶ�����������µĽ�ɫ��
 * �����н��ߣ������ͬ��������н��߶���Ľӿڣ����ڸ���ͬ����֮���ͨ�š�һ�����һ���򼸸�������¼���������������ȥʵ�֡�
 * �н���ʵ���ࣺ�ӳ����н��߼̳ж�����ʵ�ֳ����н����ж�����¼���������һ��ͬ���������Ϣ��Ȼ��ͨ����ϢӰ������ͬʱ�ࡣ
 * ͬ���ࣺ���һ�������Ӱ�������Ķ���ͬʱҲ�ᱻ��������Ӱ�죬��ô�����������Ϊͬ���ࡣ
 */
abstract class AbstractColleague {
	protected int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// ע������Ĳ���������ͬ���࣬����һ���н���
	public abstract void setNumber(int number, AbstractMediator am);
}

class ColleagueA extends AbstractColleague {

	public void setNumber(int number, AbstractMediator am) {
		this.number = number;
		am.AaffectB();
	}
}

class ColleagueB extends AbstractColleague {

	@Override
	public void setNumber(int number, AbstractMediator am) {
		this.number = number;
		am.BaffectA();
	}
}

abstract class AbstractMediator {
	protected AbstractColleague A;
	protected AbstractColleague B;

	public AbstractMediator(AbstractColleague a, AbstractColleague b) {
		A = a;
		B = b;
	}

	public abstract void AaffectB();

	public abstract void BaffectA();

}

class Mediator extends AbstractMediator {

	public Mediator(AbstractColleague a, AbstractColleague b) {
		super(a, b);
	}

	// ����A��B��Ӱ��
	public void AaffectB() {
		int number = A.getNumber();
		B.setNumber(number * 100);
	}

	// ����B��A��Ӱ��
	public void BaffectA() {
		int number = B.getNumber();
		A.setNumber(number / 100);
	}
}

/** ��ԭ����������ϵ�Ĵ������·�װ��һ���н����У�ͨ������н�������������Ĺ�ϵ */
public class MediatorPatternEasy {
	public static void main(String[] args) {
		AbstractColleague collA = new ColleagueA();
		AbstractColleague collB = new ColleagueB();

		AbstractMediator am = new Mediator(collA, collB);

		System.out.println("==========ͨ������AӰ��B==========");
		collA.setNumber(1000, am);
		System.out.println("collA��numberֵΪ��" + collA.getNumber());
		System.out.println("collB��numberֵΪA��100����" + collB.getNumber());

		System.out.println("==========ͨ������BӰ��A==========");
		collB.setNumber(1000, am);
		System.out.println("collB��numberֵΪ��" + collB.getNumber());
		System.out.println("collA��numberֵΪB��0.01����" + collA.getNumber());

	}

}
