package GarbageCollection;

public class GCArgsDemo {
	
	private static final int _1MB = 1024 * 1024;
	
	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	 * 堆内存申请20M，不能动态扩展，YoungGen10M，Eden:s0:s1的比例为8:1:1
	 * 当申请第all4时，eden放不下，minorGC回收也放不下，因此分配担保将all1-all3移到oldgen
	 * */
	public static void testAllocation(){
		byte[] all1,all2,all3,all4;
		all1 = new byte[2 * _1MB];
		all2 = new byte[2 * _1MB];
		all3 = new byte[2 * _1MB];
		all4 = new byte[2 * _1MB];//一共9M的younggen内存，不够会触发minorGC
	}
	
	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
	 * 堆内存申请20M，不能动态扩展，YoungGen10M，Eden:s0:s1的比例为8:1:1,Pre对象设置直接在oldgen分配
	 * 大对象直接被移到OldGen
	 * */
	public static void testPretenureSizeThreshold(){
		byte[] all;
		all = new byte[4 * _1MB];
	}
	
	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
	 * 堆内存申请20M，不能动态扩展，YoungGen10M，Eden:s0:s1的比例为8:1:1,Pre对象设置直接在oldgen分配
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
	 * 堆内存申请20M，不能动态扩展，YoungGen10M，Eden:s0:s1的比例为8:1:1,Pre对象设置直接在oldgen分配
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
