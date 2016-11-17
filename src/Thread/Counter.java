package Thread;

interface Counter {

	public int getValue();

	public int increment();

}

class SimulatedCas {
	private int value;
	private static SimulatedCas instance = null;

	private SimulatedCas(){};
	
	public static SimulatedCas getInstance(){
		if(instance == null) {
			synchronized(SimulatedCas.class) {
				if(instance == null) {
					instance = new SimulatedCas();
				}
			}
		}
		
		return instance;
	}
	
	public synchronized int getValue() {
		return value;
	}

	/**
	 * Cas: ����Ϊλ�� V Ӧ�ð���ֵ A�����������ֵ���� B �ŵ����λ�ã� ���򣬲�Ҫ���ĸ�λ�ã�ֻ���������λ�����ڵ�ֵ����
	 * */
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (value == expectedValue) {
			value = newValue;
		}
		return oldValue;
	}

	public synchronized boolean compareAndSet(int expectedValue, int newValue) {
		return (expectedValue == compareAndSwap(expectedValue, newValue));
	}
}

class CasCounter implements Counter{
	private SimulatedCas cas = null;
	
	public CasCounter(){
		cas = SimulatedCas.getInstance();
	}

	public int getValue() {
		return cas.getValue();
	}

	public int increment() {
		int oldValue = cas.getValue();

		while (cas.compareAndSwap(oldValue, oldValue + 1) != oldValue) {
			oldValue = cas.getValue();
		}

		return oldValue + 1;
	}
}

class SynCounter implements Counter{
	private int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized int increment() {
		return ++value;
	}
	
	public synchronized int decrement() {
		return --value;
	}
}