package ExtendsAndPolymorphism;

abstract class Device{
	private String name;
	public Device(){}
	public Device(String name)
	{
		this.name = name;
	}
	public abstract double getPrice();
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
}

public class AnonymousInnerClassDemo {
	private static String name = "outerProp";
	public void testDevice(Device d)
	{
		System.out.println("Device = [" + d.getName() +"] , Price = [" + d.getPrice() + "]");
	}
	public static void main(String[] args)
	{	
		final String nameInMain = "outerProp";
		AnonymousInnerClassDemo test = new AnonymousInnerClassDemo();
		test.testDevice(new Device("computer")
		{
			@Override
			public double getPrice() {
				// TODO Auto-generated method stub
				return 8000.0;
			}
		});
		
		Device d = new Device(){
			//匿名内部类可以复写父类的非抽象方法
			@Override
			public String getName()
			{
				//访问外部类的类变量，必须加上static
				System.out.println(AnonymousInnerClassDemo.name);
				//访问外部类的局部变量，该局部变量必须加上final
				System.out.println(nameInMain);
				return "keyboard";
			}
			//匿名内部类必须实现父类的抽象方法
			@Override
			public double getPrice() {
				// TODO Auto-generated method stub
				return 100.0;
			}	
		};
		test.testDevice(d);
	}
}
