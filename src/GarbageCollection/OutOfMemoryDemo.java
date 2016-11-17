package GarbageCollection;

import java.util.ArrayList;
import java.util.List;

/*
 * ͨ�������ڴ���ص����������������OutOfMemory�쳣
 * */
public class OutOfMemoryDemo {
	static class OOMObject {
	}

	/*
	 * Args: -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
	 * java�������ʹ������Args�����г���
	 */
	public static void HeapOOM() {
		List<OOMObject> list = new ArrayList<OutOfMemoryDemo.OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}
	}

	/*
	 * VM Args: -Xss128k
	 * java�����ջ�ͱ��ط���ջ���
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
