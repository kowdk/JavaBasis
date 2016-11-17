package Base;

public class PrimitiveDemo {
	
	/*i++ and ++i��i��ʵ����������ֻ����������ֵ��ǰ���Ǵ�ӡi�����ߴ�ӡi+1*/
	private static void plusTest()
	{
		int x = 0;
		int y = x++ + ++x;
		System.out.println(x + " " + y);//x == 2, y == 2
		int i = 0;
		i = i++ + ++i;
		System.out.println("i = " + i);//i == 2
		
		int m=0, k=0;
		for(; k<10; k++)
		{
			//m = m++;//0
			m = ++m;//10
		}
		System.out.println("m = " + m + ";k = " + k);
		
		int n = 0;
		n = ++n + n++ + n++;
		System.out.println("n = " + n);//1+1+2=4
		
		n = 0;
		n = n++ + n++ + ++n;
		System.out.println("n = " + n);//0+1+3=4
	}
	
	public static void ternaryOperatorTest()
	{
		//��Ŀ��������ҽ�ϵģ����Եȼ���b = false ? false : ((true == true) ? false : true);
		boolean b = false ? false : true == true ? false : true;
		boolean c = false ? false : false == true ? false : true;
		System.out.println(b);
		System.out.println(c);
	}
	
	public static void typeCastTest()
	{
		short s = 1;
		//s = s + 1;//�����ԭ�����ڱ��ʽ����������������1��int
		s += 1; //+=û������
		
		int a = 0;
		System.out.println("value is: " + ((a < 0) ? 10.9 : 9));//9.0����������������
		
		char x = 'a';
		int i = 10;
		System.out.println(false ? i : x);//��������������
		System.out.println(false ? 10 : x);//���������ͱ���
	}
	
	public static void shiftOperatorTest()
	{
		int num = 32;
		System.out.println(num >> 32);//��λ������Ҫ�Ƚ���ģ32���㣬�ȼ���num>>0
		System.out.println(num >> 8);
		System.out.println(num >> 2);
	}
	
	public static void main(String[] args)
	{
		//plusTest();
		//ternaryOperatorTest();
		//typeCastTest();
		shiftOperatorTest();
	}
}
