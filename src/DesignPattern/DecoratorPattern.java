package DesignPattern;

/*
 * [结构模式]
 * 修饰模式：用于在运行中修改对象的功能，但已经创建的对象不受影响，广泛应用于java IO中。
 * 装饰者与被装饰者拥有共同的超类，继承的目的是继承类型，而不是行为。
 * 非常灵活，当新的car类型加进来时，可以增加其功能。
 * 缺点是使用了大量相同的decorator对象
 * */

/*被装饰者类*/
interface Car
{
	public void info();
}

/*被装饰者类的初始状态*/
class BasicCar implements Car
{
	@Override
	public void info() {
		System.out.print("Basic car... ");
	}
}

/*装饰者类*/
class DecoratorCar implements Car
{
	protected Car car;
	
	public DecoratorCar(Car car) {
		this.car = car;
	}
	
	@Override
	public void info() {
		car.info();
	}
}

/*所有的新车类型都继承装饰类*/
class SportsCar extends DecoratorCar
{
	public SportsCar(Car car) {
		super(car);
	}
	
	public void info()
	{
		car.info();
		System.out.print(" Adding sports car feature...");
	}
}

class LuxuryCar extends DecoratorCar
{
	public LuxuryCar(Car car) {
		super(car);
	}
	
	public void info()
	{
		car.info();
		System.out.print(" Adding luxury car feature...");
	}
}

public class DecoratorPattern {

	public static void main(String[] args) {
		Car sports = new SportsCar(new BasicCar());
		sports.info();
		System.out.println();
		
		//每一层new都可以为当前对象“装饰”新的功能
		Car sportsLuxuryCar = new LuxuryCar(new SportsCar(new BasicCar()));
		sportsLuxuryCar.info();
	}
}
