package DesignPattern;


/*
 *[行为模式]
 * 模板模式
 * 模板模式确定了方法的执行顺序，定义了子类方法的默认实现。在父类中定义为final，不允许子类覆盖。
 * 模板模式包含了两个角色：
 * 抽象类（AbstractClass）：实现了模板方法，定义了算法的骨架。
 * 具体类（ConcreteClass)：实现抽象类中的抽象方法，来完成完整的算法。
 * jdk中的InputStream、OutputStream、Reader、Writer类都使用了模板模式
 * */
abstract class HouseTemplate{
	
	//父类方法调用子类方法，依赖子类实现，顺序不可变化，模板方法本身定义了执行顺序，模板方法不可变
	//抽象类定义整个流程骨架
	public final void buildHouse(){
		buildFoundation();
		buildPillars();
		buildWalls();
		buildWindows();
		System.out.println("House is built...");
	}
	
	public void buildFoundation(){
		System.out.println("Building foundation...");
	}
	
	//默认实现
	public void buildWindows(){
		System.out.println("Building Glass Windows...");
	}
	
	//由子类实现的方法
	public abstract void buildWalls();
	public abstract void buildPillars();
}

class WoodenHouse extends HouseTemplate{

	@Override
	public void buildWalls() {
		System.out.println("Building Wooden Walls...");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with Wood...");
	}
}

class GlassHouse extends HouseTemplate{
	
	@Override
	public void buildWalls() {
		System.out.println("Building Glass Walls...");
	}

	@Override
	public void buildPillars() {
		System.out.println("Building Pillars with Glass...");
	}
}

public class TemplatePattern {
	public static void main(String[] args){
		HouseTemplate houseType = new WoodenHouse();
		houseType.buildHouse();
		System.out.println("----------------------");
		houseType = new GlassHouse();
		houseType.buildHouse();
	}
}
