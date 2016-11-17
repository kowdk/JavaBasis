package DesignPattern;

/*
 * [����ģʽ]
 * ��������ģʽ�����һ�����������࣬�ͻ���ʹ��ʱ�������뷵�ض�Ӧ������
 * 
 * */

abstract class Computer {

	public String ram;
	public String cpu;

	public abstract String getARM();

	public abstract String getCPU();

	public String toString() {
		return "RAM = " + this.getARM() + " CPU = " + this.getCPU();
	}
}

class PC extends Computer {
	public PC(String ram, String cpu) {
		super();
		this.ram = ram;
		this.cpu = cpu;
	}

	@Override
	public String getARM() {
		// TODO Auto-generated method stub
		return this.ram;
	}

	@Override
	public String getCPU() {
		// TODO Auto-generated method stub
		return this.cpu;
	}
}

class Server extends Computer {
	public Server(String ram, String cpu) {
		super();
		this.ram = ram;
		this.cpu = cpu;
	}

	@Override
	public String getARM() {
		// TODO Auto-generated method stub
		return this.ram;
	}

	@Override
	public String getCPU() {
		// TODO Auto-generated method stub
		return this.cpu;
	}
}

/*
 * ���������࣬ ����������������ء�
 * ���ǲ����Ͽ���-�պ�ԭ�򣺶����޸���˵������ʵ���ǹرյģ�����������˵������ʵ���ǿ��ŵ�
 * 
 * */
class ComputerFactory {
	public static Computer getComputer(String type, String ram, String cpu) {
		if(type.equalsIgnoreCase("PC")) return new PC(ram, cpu);
		else if(type.equalsIgnoreCase("Server")) return new Server(ram, cpu);
		return null;
	}
}

/* �ͻ��� */
public class FactoryPattern {
	public static void main(String[] args) {
		Computer pc = ComputerFactory.getComputer("pc","2 GB","2.4 GHz");
        Computer server = ComputerFactory.getComputer("server","16 GB","2.9 GHz");
        System.out.println("Factory PC Config::"+pc);
        System.out.println("Factory Server Config::"+server);
	}
}
