package Base;

public class PrimitiveDemo {
	
	/*i++ and ++i，i都实现了自增，只不过该语句的值，前者是打印i，后者打印i+1*/
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
		//三目运算符是右结合的，所以等价于b = false ? false : ((true == true) ? false : true);
		boolean b = false ? false : true == true ? false : true;
		boolean c = false ? false : false == true ? false : true;
		System.out.println(b);
		System.out.println(c);
	}
	
	public static void typeCastTest()
	{
		short s = 1;
		//s = s + 1;//错误的原因在于表达式进行了类型提升，1是int
		s += 1; //+=没有问题
		
		int a = 0;
		System.out.println("value is: " + ((a < 0) ? 10.9 : 9));//9.0，变量，类型提升
		
		char x = 'a';
		int i = 10;
		System.out.println(false ? i : x);//变量，类型提升
		System.out.println(false ? 10 : x);//常量，类型保持
	}
	
	public static void shiftOperatorTest()
	{
		int num = 32;
		System.out.println(num >> 32);//移位操作符要先进行模32运算，等价于num>>0
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
