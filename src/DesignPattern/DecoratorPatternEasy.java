package DesignPattern;

/** 被装饰者类 */
abstract class Hamburger {
	protected String name;

	public String getName() {
		return name;
	}

	public abstract int getPrice();
}

/** 被装饰者类基础状态 */
class ChickenBurger extends Hamburger{
	public ChickenBurger(){
		name = "chicken burger";
	}
	
	@Override
	public int getPrice() {
		return 10;
	}	
}

/** 装饰者抽象基类 */
abstract class Condiment extends Hamburger{
	public abstract String getName();
}

/** 装饰者实现类 */
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

/** 装饰者实现类 */
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

/** 客户类 */
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
