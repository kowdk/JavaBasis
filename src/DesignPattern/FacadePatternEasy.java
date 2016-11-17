package DesignPattern;

/**
 * Facade类其实相当于A、B、C模块的外观界面，有了这个Facade类
 * 那么客户端就不需要亲自调用子系统中的A、B、C模块了
 * 也不需要知道系统内部的实现细节，甚至都不需要知道A、B、C模块的存在
 * 客户端只需要跟Facade类交互就好了
 * 从而更好地实现了客户端和子系统中A、B、C模块的解耦，让客户端更容易地使用系统
 * */


/** 子系统角色1 */
class SubClass1 {
	public void registration() {
		System.out.println("Registration...");
	}
}

/** 子系统角色2 */
class SubClass2 {
	public void see() {
		System.out.println("See the doctor in clinic...");
	}
}

/** 子系统角色3 */
class SubClass3 {
	public void pick() {
		System.out.println("Pick up the medicine...");
	}
}

/** 门面角色 */
class Facade {
	public void Emergency() {
		new SubClass2().see();
	}

	public void General() {
		new SubClass1().registration();
		new SubClass2().see();
		new SubClass3().pick();
	}
}

/** 客户 */
public class FacadePatternEasy {
	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.Emergency();
		facade.General();
	}
}
