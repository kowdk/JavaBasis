package DesignPattern;

/* 
 * [创建模式]
 * 工厂方法接口 */
interface ComputerFactoryInterface
{	
	Computer createComputer();
}

/* 子类对应的工厂方法，通过构造函数传参，通过组合获得子类对象，重写父类工厂方法返回该子类 */
class PCFactory implements ComputerFactoryInterface{
	private PC pc = null;
	
	public PCFactory(String ram, String cpu) {
		// TODO Auto-generated constructor stub
		pc = new PC(ram, cpu);
	}
	@Override
	public Computer createComputer() {
		// TODO Auto-generated method stub
		return pc;
	}
}

class ServerFactory implements ComputerFactoryInterface{
	private Server server = null;
	
	public ServerFactory(String ram, String cpu) {
		// TODO Auto-generated constructor stub
		server = new Server(ram, cpu);
	}
	
	@Override
	public Computer createComputer() {
		// TODO Auto-generated method stub
		return server;
	}
}

/* 工厂方法对于客户类的接口 */
class AbstractComputerFactory{
	public static Computer getComputer(ComputerFactoryInterface cf)
	{
		return cf.createComputer();
	}
}

/* 
 * 客户使用类，统一的接口，不依赖于传入的参数来决定返回对象，接口和工厂方法的接口不变。
 * 通过增加子类的工厂方法，来获得子类对象，实际参数通过构造函数传入
 * 但是工厂方法不适用于很多参数的情况，这样在客户类处容易出错，比如必须确保参数顺序，必须传入null等问题
 *  */
public class AbstractFactoryPattern {
	public static void main(String[] args){
		Computer server = AbstractComputerFactory.getComputer(new ServerFactory("2 GB", "2.4 GHz"));
		Computer pc = AbstractComputerFactory.getComputer(new PCFactory("16 GB", "2.9 GHz"));
		
		System.out.println("AbstractFactory Server Config::"+server);
		System.out.println("AbstractFactory PC Config::"+pc);
	}
}
