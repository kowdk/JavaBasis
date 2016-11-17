package DesignPattern;


/*
 * [结构模式]
 * 桥接模式:将抽象部分与实现部分分离，使它们都可以独立的变化。
 * 通过对象组合的方式，Bridge模式把两个角色之间的继承关系改为了耦合的关系，从而使这两者可以各自独立的变化
 * 桥接模式可以应对多维度的变化
 * */

/**两个接口间使用组合的方式来完成这个“桥接”*/
abstract class AbstractRoad{
	AbstractCar aCar;
	void run(){};
}

abstract class AbstractCar{
	void run(){};
}

/** person就是增加的那一个维度 */
abstract class AbstractPerson{
	AbstractRoad road;
	void run(){};
}

class Man extends AbstractPerson{
	@Override
	void run(){
		super.run();
		System.out.print("男人开着");
		road.run();
	}
}

class Woman extends AbstractPerson{
	@Override
	void run(){
		super.run();
		System.out.print("女人开着");
		road.run();
	}
}

class Street extends AbstractRoad{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		aCar.run();
		System.out.println("在市区街道行驶");
	}
}
class SpeedWay extends AbstractRoad{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		aCar.run();
		System.out.println("在高速公路行驶");
	}
}
class Sedan extends AbstractCar{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.print("小汽车");
	}
}
class Bus extends AbstractCar{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.print("公交车");
	}
}

public class BridgePattern {
	public static void main(String[] args){
		AbstractRoad speedWay = new SpeedWay();
		speedWay.aCar = new Sedan();
		speedWay.run();
		
		AbstractPerson man = new Man();
		AbstractRoad street = new Street();
		street.aCar = new Bus();
		man.road = street;
		man.run();
	}
}
