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

	//使用内部类调用外部类的私有方法
	private class Closure implements Teachable {
		@Override
		public void work() {
			// TODO Auto-generated method stub
			teach();
		}
	}

	//然后返回一个内部类对象，回调了TeachableProgrammer的方法
	public Teachable getCallBack() {
		return new Closure();
	}
}

public class ClosureClassDemo {
	public static void main(String[] args) {
		/*使用闭包解决了Programmer和Teachable有重名签名的问题*/
		TeachableProgrammer p = new TeachableProgrammer("xutao");
		p.work();
		p.getCallBack().work();
	}
}
