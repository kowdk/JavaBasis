package DesignPattern;

import java.util.HashMap;

/*
 * [结构模式]
 * 享元模式：当一个类需要创建大量对象或者创建对象时间很长时，为了减少内存损耗，可以分享对象，考虑缓存，在一个复杂的系统中大量的节省内存空间。
 * 该模式下的类对象有内部属性和外部属性，内部属性由类自己完成，外部属性由客户类提供。
 * 数据库连接池、线程池都是享元模式的应用，StringPool是典型的享元模式，包装类的valueOf也使用享元模式，使用私有的静态内部类提供cache数组来完成拆箱封箱。
 * 享元模式当缓存的对象数量特别大时，就会很耗性能，因此要在时间和内存之间做一个均衡
 * 当有大量的内部属性时，享元模式的工厂类会很冗杂。
 * */

/**
 * 浏览器接口
 * @author xutao
 *
 */
interface Browser {
	public void showBrower();
}

/**
 * 浏览器实现类
 * @author xutao
 *
 */
class ConcreteBrowser implements Browser {
	private String name = "";

	public ConcreteBrowser(String name) {
		this.name = name;
	}

	@Override
	public void showBrower() {
		System.out.println("Browser name : " + name);
	}
}

class FlyWeightFactory {
	private HashMap<String, Object> map = new HashMap<String, Object>(); //缓存池

	public FlyWeightFactory() {
	}

	public Browser getFlyWeight(Object obj) {
		Browser bws = (Browser) map.get(obj);
		if (bws == null) { //没有缓存再创建
			bws = new ConcreteBrowser(String.valueOf(obj));
			map.put(String.valueOf(obj), bws);
		}
		return bws;
	}

	public int getBrowserPoolSize() {
		return map.size();
	}
}

public class FlyWeightPatternEasy {
	public static void main(String[] args) {
		FlyWeightFactory fac = new FlyWeightFactory();
		Browser b1 = fac.getFlyWeight("google chrome");
		Browser b2 = fac.getFlyWeight("google chrome");
		Browser b3 = fac.getFlyWeight("google chrome");
		Browser b4 = fac.getFlyWeight("360");
		
		b1.showBrower();
		b2.showBrower();
		b3.showBrower();
		b4.showBrower();
		
		System.out.println(fac.getBrowserPoolSize());//2
	}
}
