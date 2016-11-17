package ExtendsAndPolymorphism;

/*
 * 继承与组合，继承就是is a的关系，而组合是has a的关系
 * 继承是子类继承父类的方法和属性，组合是在类中嵌入另一个类的引用。
 * 1.继承的耦合度高，必须继承可继承的方法和属性，而组合可以只挑选感兴趣的方法和属性，也可以在调用者的类中修改被调用者的方法但不会影响被调用者，耦合度较低。
 * 2.继承将父类的方法都暴露在调用者面前，而组合可以对特定方法设置访问权限，封装性更强。
 * 3.在单元测试的时候，组合因为解耦的原因更好分开测，而继承必须测试父类，是额外的工作。
 * 继承是说“我父亲在家里给我帮了很大的忙”，组合是说“我请了个老头在我家里干活”。
 * */

class Animal
{   
	private void beat()
	{
		System.out.println("心脏在不停的跳动");
	}
	
	public void breath()
	{
		this.beat();
		System.out.println("我正在呼吸");
	}
}

class WolfInherit extends Animal
{	
	public void run()
	{
		System.out.println("我可以在陆地上奔跑");
	}
}

class BirdInherit extends Animal
{
	public void fly()
	{
		System.out.println("我可以在天空中自由的翱翔");
	}
}

class WolfCombine
{
	private Animal a = new Animal();
	public void run()
	{
		System.out.println("我可以在陆地上奔跑");
	}
	
	public void breath()
	{
		a.breath();
	}
}

class BirdCombine
{
	private Animal a = new Animal();
	
	public void fly()
	{
		System.out.println("我可以在天空中自由的翱翔");
	}
	
	public void breath()
	{
		a.breath();
	}
}

public class InheritanceAndCompositionDemo {
	public static void main(String[] args)
	{
		//使用继承的方式让bird和wolf可以呼吸
		BirdInherit bi = new BirdInherit();
		bi.fly();
		bi.breath();
		
		//使用组合的方式，设置Animal为private可以保证Animal中的任何public方法都无法被访问到，而继承就不行，breath就被访问到了
		Animal a = new BirdInherit();
		a.breath();
		
		/*WolfInherit wi = new WolfInherit();
		wi.run();
		wi.breath();*/
		
		//使用组合的方式让bird和wolf可以呼吸
		/*BirdCombine bc = new BirdCombine();
		bc.fly();
		bc.breath();
		WolfCombine wc = new WolfCombine();
		wc.run();
		wc.breath();*/
	}
}

