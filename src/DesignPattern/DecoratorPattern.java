package DesignPattern;

/*
 * [�ṹģʽ]
 * ����ģʽ���������������޸Ķ���Ĺ��ܣ����Ѿ������Ķ�����Ӱ�죬�㷺Ӧ����java IO�С�
 * װ�����뱻װ����ӵ�й�ͬ�ĳ��࣬�̳е�Ŀ���Ǽ̳����ͣ���������Ϊ��
 * �ǳ������µ�car���ͼӽ���ʱ�����������书�ܡ�
 * ȱ����ʹ���˴�����ͬ��decorator����
 * */

/*��װ������*/
interface Car
{
	public void info();
}

/*��װ������ĳ�ʼ״̬*/
class BasicCar implements Car
{
	@Override
	public void info() {
		System.out.print("Basic car... ");
	}
}

/*װ������*/
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

/*���е��³����Ͷ��̳�װ����*/
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
		
		//ÿһ��new������Ϊ��ǰ����װ�Ρ��µĹ���
		Car sportsLuxuryCar = new LuxuryCar(new SportsCar(new BasicCar()));
		sportsLuxuryCar.info();
	}
}
