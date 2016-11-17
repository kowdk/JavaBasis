package DesignPattern;

/* 
 * [����ģʽ]
 * ���������ӿ� */
interface ComputerFactoryInterface
{	
	Computer createComputer();
}

/* �����Ӧ�Ĺ���������ͨ�����캯�����Σ�ͨ����ϻ�����������д���๤���������ظ����� */
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

/* �����������ڿͻ���Ľӿ� */
class AbstractComputerFactory{
	public static Computer getComputer(ComputerFactoryInterface cf)
	{
		return cf.createComputer();
	}
}

/* 
 * �ͻ�ʹ���࣬ͳһ�Ľӿڣ��������ڴ���Ĳ������������ض��󣬽ӿں͹��������Ľӿڲ��䡣
 * ͨ����������Ĺ���������������������ʵ�ʲ���ͨ�����캯������
 * ���ǹ��������������ںܶ����������������ڿͻ��ദ���׳����������ȷ������˳�򣬱��봫��null������
 *  */
public class AbstractFactoryPattern {
	public static void main(String[] args){
		Computer server = AbstractComputerFactory.getComputer(new ServerFactory("2 GB", "2.4 GHz"));
		Computer pc = AbstractComputerFactory.getComputer(new PCFactory("16 GB", "2.9 GHz"));
		
		System.out.println("AbstractFactory Server Config::"+server);
		System.out.println("AbstractFactory PC Config::"+pc);
	}
}
