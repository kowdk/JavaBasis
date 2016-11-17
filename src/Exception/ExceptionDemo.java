package Exception;

public class ExceptionDemo {
	
	static void test1() throws Exception
	{
		throw new Exception();//有throw就必须catch或者throws Exception给调用者
	}
	
	static void test2()
	{
		//有throw就必须catch或者throws Exception给调用者
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void caller()
	{
		try {
			test1();
			test2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		
	}
}
