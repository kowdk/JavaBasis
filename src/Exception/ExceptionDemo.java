package Exception;

public class ExceptionDemo {
	
	static void test1() throws Exception
	{
		throw new Exception();//��throw�ͱ���catch����throws Exception��������
	}
	
	static void test2()
	{
		//��throw�ͱ���catch����throws Exception��������
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
