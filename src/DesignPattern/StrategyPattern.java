/**
 * 
 */
package DesignPattern;

/**
 * [行为模式]
 * 策略模式：让算法和对象分开来，使得算法可以独立于使用它的客户而变化，由下面的三个部分组成：
 * 环境类(Context):用一个ConcreteStrategy对象来配置。维护一个对Strategy对象的引用。可定义一个接口来让Strategy访问它的数据。
 * 抽象策略类(Strategy):定义所有支持的算法的公共接口。 Context使用这个接口来调用某ConcreteStrategy定义的算法。、
 * 具体策略类(ConcreteStrategy):以Strategy接口实现某具体算法。
 */

/**抽象策略类：Strategy */
interface IStrategy{
	public void operate();
}

/**三个具体策略类：ConcreteStrategy */
class BackDoorStrategy implements IStrategy {
    @Override
    public void operate() {
         System.out.println("找乔国老帮忙，让吴国太给孙权施加压力，使孙权不能杀刘备");
    }
}

class GivenGreenLightStrategy implements IStrategy {
    @Override
    public void operate() {
         System.out.println("求吴国太开个绿灯，放行");
    }
}

class BlackEnemyStrategy implements IStrategy {
    @Override
    public void operate() {
         System.out.println("孙夫人断后，挡住追兵");
    }
}

/** 环境类:Context */
class Context {
    private IStrategy strategy;
    //构造函数，要你使用哪个妙计
    public Context(IStrategy strategy){
         this.strategy = strategy;
    }
    public void setStrategy(IStrategy strategy){
         this.strategy = strategy;
    }
    public void operate(){
         this.strategy.operate();
    }
}

/** 策略使用者 */
public class StrategyPattern {
	
	public static void main(String[] args) {
		Context context;
		System.out.println("----------刚到吴国使用第一个锦囊---------------");
		context = new Context(new BackDoorStrategy());
		context.operate();
		System.out.println();
		
		System.out.println("----------刘备乐不思蜀使用第二个锦囊---------------");
		context.setStrategy(new GivenGreenLightStrategy());
		context.operate();
		System.out.println();
		
		System.out.println("----------孙权的追兵来了，使用第三个锦囊---------------");
		context.setStrategy(new BlackEnemyStrategy());
		context.operate();
		System.out.println();
	}
}
