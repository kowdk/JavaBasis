package DesignPattern;

/*
 *[行为模式]
 * 中间者模式
 * 中间者模式为系统内对象之间的通讯提供媒介
 * 当对象之间通讯很复杂，就使用通讯的中间件来管理整个通讯，包含了以下的角色：
 * 抽象中介者：定义好同事类对象到中介者对象的接口，用于各个同事类之间的通信。一般包括一个或几个抽象的事件方法，并由子类去实现。
 * 中介者实现类：从抽象中介者继承而来，实现抽象中介者中定义的事件方法。从一个同事类接收消息，然后通过消息影响其他同时类。
 * 同事类：如果一个对象会影响其他的对象，同时也会被其他对象影响，那么这两个对象称为同事类。
 */
abstract class AbstractColleague {
	protected int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// 注意这里的参数不再是同事类，而是一个中介者
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

	// 处理A对B的影响
	public void AaffectB() {
		int number = A.getNumber();
		B.setNumber(number * 100);
	}

	// 处理B对A的影响
	public void BaffectA() {
		int number = B.getNumber();
		A.setNumber(number / 100);
	}
}

/** 把原来处理对象关系的代码重新封装到一个中介类中，通过这个中介类来处理对象间的关系 */
public class MediatorPatternEasy {
	public static void main(String[] args) {
		AbstractColleague collA = new ColleagueA();
		AbstractColleague collB = new ColleagueB();

		AbstractMediator am = new Mediator(collA, collB);

		System.out.println("==========通过设置A影响B==========");
		collA.setNumber(1000, am);
		System.out.println("collA的number值为：" + collA.getNumber());
		System.out.println("collB的number值为A的100倍：" + collB.getNumber());

		System.out.println("==========通过设置B影响A==========");
		collB.setNumber(1000, am);
		System.out.println("collB的number值为：" + collB.getNumber());
		System.out.println("collA的number值为B的0.01倍：" + collA.getNumber());

	}

}
