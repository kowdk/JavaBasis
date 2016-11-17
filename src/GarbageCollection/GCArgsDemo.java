package GarbageCollection;

public class GCArgsDemo {
	
	private static final int _1MB = 1024 * 1024;
	
	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * ���ڴ�����20M�����ܶ�̬��չ��YoungGen10M��Eden:s0:s1�ı���Ϊ8:1:1
	 * �������all4ʱ��eden�Ų��£�minorGC����Ҳ�Ų��£���˷��䵣����all1-all3�Ƶ�oldgen
	 * */
	public static void testAllocation(){
		byte[] all1,all2,all3,all4;
		all1 = new byte[2 * _1MB];
		all2 = new byte[2 * _1MB];
		all3 = new byte[2 * _1MB];
		all4 = new byte[2 * _1MB];//һ��9M��younggen�ڴ棬�����ᴥ��minorGC
	}
	
	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
	 * ���ڴ�����20M�����ܶ�̬��չ��YoungGen10M��Eden:s0:s1�ı���Ϊ8:1:1,Pre��������ֱ����oldgen����
	 * �����ֱ�ӱ��Ƶ�OldGen
	 * */
	public static void testPretenureSizeThreshold(){
		byte[] all;
		all = new byte[4 * _1MB];
	}
	
	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 * ���ڴ�����20M�����ܶ�̬��չ��YoungGen10M��Eden:s0:s1�ı���Ϊ8:1:1,Pre��������ֱ����oldgen����
	 * 
	 * */
	public static void testTenuringThreshold(){
		byte[] all1, all2, all3;
		all1 = new byte[_1MB / 4];
		all2 = new byte[4 * _1MB];
		all3 = new byte[4 * _1MB];
		all3 = null;
		all3 = new byte[4 * _1MB];
	}
	
	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 * ���ڴ�����20M�����ܶ�̬��չ��YoungGen10M��Eden:s0:s1�ı���Ϊ8:1:1,Pre��������ֱ����oldgen����
	 * 
	 * */
	public static void testHandlePromotion(){
		byte[] all1, all2, all3, all4, all5, all6, all7;
		all1 = new byte[2 * _1MB];
		all2 = new byte[2 * _1MB];
		all3 = new byte[2 * _1MB];
		all4 = new byte[2 * _1MB];
		all5 = new byte[2 * _1MB];
		all6 = new byte[2 * _1MB];
		/*all4 = null;
		all5 = null;
		all6 = null;*/
		all7 = new byte[2 * _1MB];
	}
	
	public static void main(String[] args)
	{
		//testAllocation();
		//testPretenureSizeThreshold();
		//testTenuringThreshold();
		testHandlePromotion();
	}
}
