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
			//�����ڲ�����Ը�д����ķǳ��󷽷�
			@Override
			public String getName()
			{
				//�����ⲿ�����������������static
				System.out.println(AnonymousInnerClassDemo.name);
				//�����ⲿ��ľֲ��������þֲ������������final
				System.out.println(nameInMain);
				return "keyboard";
			}
			//�����ڲ������ʵ�ָ���ĳ��󷽷�
			@Override
			public double getPrice() {
				// TODO Auto-generated method stub
				return 100.0;
			}	
		};
		test.testDevice(d);
	}
}
