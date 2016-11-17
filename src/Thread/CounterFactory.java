package Thread;

interface CounterInterface{
	Counter createCounter();
}

class SynCounterFactory implements CounterInterface{

	private SynCounter counter = null;
	
	public SynCounterFactory() {
		this.counter = new SynCounter();
	}
	
	@Override
	public Counter createCounter() {
		// TODO Auto-generated method stub
		return counter;
	}
	
}

class CasCounterFactory implements CounterInterface {

	private CasCounter counter = null;
	
	public CasCounterFactory() {
		this.counter = new CasCounter();
	}
	
	@Override
	public Counter createCounter() {
		// TODO Auto-generated method stub
		return counter;
	}
	 
}

public class CounterFactory{
	public static Counter getCounter(CounterInterface ci){
		return ci.createCounter();
	}
}