package ExtendsAndPolymorphism;

interface Teachable {
	public void work();
}

class Programmer {
	private String name;
	public Programmer(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void work() {
		System.out.println("Programmer [" + name + "] is working!");
	}
}

class TeachableProgrammer extends Programmer {
	public TeachableProgrammer(String name) {
		super(name);
	}

	private void teach() {
		System.out.println("Teacher [" + super.getName() + "] is teaching!");
	}

	//ʹ���ڲ�������ⲿ���˽�з���
	private class Closure implements Teachable {
		@Override
		public void work() {
			// TODO Auto-generated method stub
			teach();
		}
	}

	//Ȼ�󷵻�һ���ڲ�����󣬻ص���TeachableProgrammer�ķ���
	public Teachable getCallBack() {
		return new Closure();
	}
}

public class ClosureClassDemo {
	public static void main(String[] args) {
		/*ʹ�ñհ������Programmer��Teachable������ǩ��������*/
		TeachableProgrammer p = new TeachableProgrammer("xutao");
		p.work();
		p.getCallBack().work();
	}
}
