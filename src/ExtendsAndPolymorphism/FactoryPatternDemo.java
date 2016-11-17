package ExtendsAndPolymorphism;

/*
 * �򵥵Ĺ���ģʽ
 * */

//�ӿ�
interface Output
{
	int MAX_CACHE_LINE=2;
	void out();
	void getData(String msg);
}

//�ӿڵ�ʵ����
class Printer implements Output
{
	private String[] data = new String[MAX_CACHE_LINE];
	private int dataNum = 0;
	@Override
	public void out() {
		// TODO Auto-generated method stub
		while(dataNum > 0)
		{
			System.out.println("Printer1 is printing : " + data[0]);
			System.arraycopy(data, 1, data, 0, --dataNum);
		}
	}

	@Override
	public void getData(String msg) {
		// TODO Auto-generated method stub
		if(dataNum >= MAX_CACHE_LINE)
		{
			System.out.println("The list is full");
		}
		else
		{
			data[dataNum++] = msg;
		}
	}
	
}

//ʹ������ֻʹ�ýӿ�ʵ��
class Computer
{
	private Output out;
	public Computer(Output out)
	{
		this.out = out;
	}
	public void keyIn(String msg)
	{
		out.getData(msg);
	}
	public void print()
	{
		out.out();
	}
}

public class FactoryPatternDemo {
	
	//�൱��spring.xml����ʹ���ĸ�ʵ����
	public Output getOutput()
	{
		return new Printer();
	}
	
	public static void main(String[] args)
	{
		FactoryPatternDemo fc = new FactoryPatternDemo();
		Computer c = new Computer(fc.getOutput());
		c.keyIn("msg1");
		c.keyIn("msg2");
		c.keyIn("msg3");
		c.print();
	}
}
