package DesignPattern;


/*
 * [�ṹģʽ]
 * �Ž�ģʽ:�����󲿷���ʵ�ֲ��ַ��룬ʹ���Ƕ����Զ����ı仯��
 * ͨ��������ϵķ�ʽ��Bridgeģʽ��������ɫ֮��ļ̳й�ϵ��Ϊ����ϵĹ�ϵ���Ӷ�ʹ�����߿��Ը��Զ����ı仯
 * �Ž�ģʽ����Ӧ�Զ�ά�ȵı仯
 * */

/**�����ӿڼ�ʹ����ϵķ�ʽ�����������Žӡ�*/
abstract class AbstractRoad{
	AbstractCar aCar;
	void run(){};
}

abstract class AbstractCar{
	void run(){};
}

/** person�������ӵ���һ��ά�� */
abstract class AbstractPerson{
	AbstractRoad road;
	void run(){};
}

class Man extends AbstractPerson{
	@Override
	void run(){
		super.run();
		System.out.print("���˿���");
		road.run();
	}
}

class Woman extends AbstractPerson{
	@Override
	void run(){
		super.run();
		System.out.print("Ů�˿���");
		road.run();
	}
}

class Street extends AbstractRoad{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		aCar.run();
		System.out.println("�������ֵ���ʻ");
	}
}
class SpeedWay extends AbstractRoad{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		aCar.run();
		System.out.println("�ڸ��ٹ�·��ʻ");
	}
}
class Sedan extends AbstractCar{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.print("С����");
	}
}
class Bus extends AbstractCar{
	@Override
	void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.print("������");
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
