/**
 * 
 */
package DesignPattern;

/**
 * [��Ϊģʽ]
 * ����ģʽ�����㷨�Ͷ���ֿ�����ʹ���㷨���Զ�����ʹ�����Ŀͻ����仯�������������������ɣ�
 * ������(Context):��һ��ConcreteStrategy���������á�ά��һ����Strategy��������á��ɶ���һ���ӿ�����Strategy�����������ݡ�
 * ���������(Strategy):��������֧�ֵ��㷨�Ĺ����ӿڡ� Contextʹ������ӿ�������ĳConcreteStrategy������㷨����
 * ���������(ConcreteStrategy):��Strategy�ӿ�ʵ��ĳ�����㷨��
 */

/**��������ࣺStrategy */
interface IStrategy{
	public void operate();
}

/**������������ࣺConcreteStrategy */
class BackDoorStrategy implements IStrategy {
    @Override
    public void operate() {
         System.out.println("���ǹ��ϰ�æ�������̫����Ȩʩ��ѹ����ʹ��Ȩ����ɱ����");
    }
}

class GivenGreenLightStrategy implements IStrategy {
    @Override
    public void operate() {
         System.out.println("�����̫�����̵ƣ�����");
    }
}

class BlackEnemyStrategy implements IStrategy {
    @Override
    public void operate() {
         System.out.println("����˶Ϻ󣬵�ס׷��");
    }
}

/** ������:Context */
class Context {
    private IStrategy strategy;
    //���캯����Ҫ��ʹ���ĸ����
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

/** ����ʹ���� */
public class StrategyPattern {
	
	public static void main(String[] args) {
		Context context;
		System.out.println("----------�յ����ʹ�õ�һ������---------------");
		context = new Context(new BackDoorStrategy());
		context.operate();
		System.out.println();
		
		System.out.println("----------�����ֲ�˼��ʹ�õڶ�������---------------");
		context.setStrategy(new GivenGreenLightStrategy());
		context.operate();
		System.out.println();
		
		System.out.println("----------��Ȩ��׷�����ˣ�ʹ�õ���������---------------");
		context.setStrategy(new BlackEnemyStrategy());
		context.operate();
		System.out.println();
	}
}
