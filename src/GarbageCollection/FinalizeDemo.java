package GarbageCollection;

public class FinalizeDemo {
	
	private static FinalizeDemo tf = null;
	public void info()
	{
		System.out.println("������Դ�����finalize����");
	}
	
	protected void finalize() throws Throwable
	{
		super.finalize();
		FinalizeDemo.tf = this;
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		tf = new FinalizeDemo();
		//֪ͨϵͳ������Դ����
		tf = null;
		System.gc();
		Thread.sleep(2000);
		//System.runFinalization();
		tf.info();
		
		tf = null;
		System.gc();
		Thread.sleep(2000);
		tf.info();
	}
}
