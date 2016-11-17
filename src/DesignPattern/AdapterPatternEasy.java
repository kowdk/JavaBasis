package DesignPattern;

/** 
 * [结构模式]
 * 适配器模式：适用于一个对象可能需要多个适配器的情况。又称变压器模式，使得原本接口不匹配而无法一起工作的两个类能够一起工作。
 * 适配器模式希望复用一些现存的类，但是又与使用环境不一致，在代码复用和类库迁移方面特别有用。
 * 在jdk中，asList、java.io.InputStreamReader(InputStream)以及java.io.OutputStreamWriter(OutputStream)都使用适配器模式。
 * 适配器模式有类的适配器模式，extends那个被适配类；也可以以组合的方式将被适配类关联进来。
 **/

class Adaptee{
	public void specificFunc(){
		System.out.println("既有接口，需要被适配");
	}
}

interface StandardInterface{
	public void standardFunc();
}

class StandardClass implements StandardInterface {

	@Override
	public void standardFunc() {
		System.out.println("标准接口的标准实现");
	}
	
}

class Adapter implements StandardInterface{
	private Adaptee adaptee; // 组合需要被适配的现有方法
	
	public Adapter(Adaptee tee){
		this.adaptee = tee;
	}
	
	@Override
	public void standardFunc() { //标准接口
		this.adaptee.specificFunc();//现有实现，获得了代码复用
	}
}

public class AdapterPatternEasy {
	public static void main(String[] args) {
		StandardClass sc = new StandardClass();
		sc.standardFunc();
		
		Adapter sc1 = new Adapter(new Adaptee());
		sc1.standardFunc();
	}
}
