package ExtendsAndPolymorphism;

/*
 * �������ģʽ
 * */

interface Command
{
	void process(int[] target);
}

class PrintCommand implements Command
{
	@Override
	public void process(int[] target) {
		// TODO Auto-generated method stub
		for(int i : target)
		{
			System.out.println(target[i]);
		}
	}
}

class ProcessArray
{
	//ֻ���ýӿڣ�������ʵ����
	public void process(int[] target, Command cmd)
	{
		cmd.process(target);
	}
}

public class CommandPatternDemo {
	public static void main(String[] args)
	{
		ProcessArray pa = new ProcessArray();
		int[] target = {1,2,3,4};
		pa.process(target, new PrintCommand());
	}
}
