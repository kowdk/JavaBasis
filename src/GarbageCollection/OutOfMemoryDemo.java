package GarbageCollection;

import java.util.ArrayList;
import java.util.List;

/*
 * 通过设置内存相关的虚拟机参数来发掘OutOfMemory异常
 * */
public class OutOfMemoryDemo {
	static class OOMObject {
	}

	/*
	 * Args: -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
	 * java堆溢出，使用上述Args来运行程序
	 */
	public static void HeapOOM() {
		List<OOMObject> list = new ArrayList<OutOfMemoryDemo.OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}

	/*
	 * VM Args: -Xss128k
	 * java虚拟机栈和本地方法栈溢出
	 * */
	public static void VMStackSOF() throws Throwable {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length: " + oom.length);
			throw e;
		}
	}

	public static void main(String[] args) throws Throwable {
		VMStackSOF();
	}
}

class JavaVMStackSOF {
	public int length = 1;

	public void stackLeak() {
		length++;
		stackLeak();
	}
}
