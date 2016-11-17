package DesignPattern;

/** ��װ������ */
abstract class Hamburger {
	protected String name;

	public String getName() {
		return name;
	}

	public abstract int getPrice();
}

/** ��װ���������״̬ */
class ChickenBurger extends Hamburger{
	public ChickenBurger(){
		name = "chicken burger";
	}
	
	@Override
	public int getPrice() {
		return 10;
	}	
}

/** װ���߳������ */
abstract class Condiment extends Hamburger{
	public abstract String getName();
}

/** װ����ʵ���� */
class DecorateLettuce extends Condiment{
	
	Hamburger ham;
	
	public DecorateLettuce(Hamburger ham) {
		this.ham = ham;
	}
	
	@Override
	public String getName() {
		return ham.getName() + " adding Lettuce...";
	}

	@Override
	public int getPrice() {
		return ham.getPrice() + 2;
	}
}

/** װ����ʵ���� */
class DecorateEgg extends Condiment{
	Hamburger ham;

	public DecorateEgg(Hamburger ham) {
		this.ham = ham;
	}
	
	@Override
	public String getName() {
		return ham.getName() + " adding egg...";
	}

	@Override
	public int getPrice() {
		return ham.getPrice() + 1;
	}
}

/** �ͻ��� */
public class DecoratorPatternEasy {
	public static void main(String[] args) {
		Hamburger ham = new ChickenBurger();
		System.out.println(ham.getName());
		System.out.println(ham.getPrice());
		
		ham = new DecorateEgg(new ChickenBurger());
		System.out.println(ham.getName());
		System.out.println(ham.getPrice());
		
		ham = new DecorateLettuce(new DecorateEgg(new ChickenBurger()));
		System.out.println(ham.getName());
		System.out.println(ham.getPrice());
	}
}
