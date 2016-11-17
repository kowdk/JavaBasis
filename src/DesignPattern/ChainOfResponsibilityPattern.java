package DesignPattern;

/**
 * [行为模式]
 * 责任链模式：有多个对象共同对一个任务进行处理，这些对象使用链式存储结构，形成一个链，每个对象知道自己的下一个对象，
 * 一个对象对任务进行处理，可以添加一些操作后将对象传递个下一个任务。也可以在此对象上结束任务的处理，并结束任务。
 */

class Currency
{
	private int amount;
	public Currency(int amount){
		this.amount = amount;
	}
	
	public int getAmount(){
		return amount;
	}
}

/** 抽象处理者 */
interface DispenseChain{
	//设置下一个责任链
	public void setNextChain(DispenseChain chain);
	//找钱的方法
	public void dispense(Currency c);
}

/** 具体处理者，找50块 */
class Dollar50Chain implements DispenseChain{
	
	private DispenseChain nextChain;
	@Override
	public void setNextChain(DispenseChain chain) {
		// TODO Auto-generated method stub
		this.nextChain = chain;
	}

	@Override
	public void dispense(Currency cur) {
		// TODO Auto-generated method stub
		if(cur.getAmount() >= 50){
			int num = cur.getAmount() / 50;
			int remainder = cur.getAmount() % 50;
			System.out.println("Dollar50 dispense: " + num);
			if(remainder!=0)
			this.nextChain.dispense(new Currency(remainder));
		}else{
			this.nextChain.dispense(cur);
		}
	}
}

/** 具体处理者，找20块 */
class Dollar20Chain implements DispenseChain{
	
	private DispenseChain nextChain;
	@Override
	public void setNextChain(DispenseChain chain) {
		// TODO Auto-generated method stub
		this.nextChain = chain;
	}

	@Override
	public void dispense(Currency cur) {
		// TODO Auto-generated method stub
		if(cur.getAmount() >= 20){
			int num = cur.getAmount() / 20;
			int remainder = cur.getAmount() % 20;
			System.out.println("Dollar20 dispense: " + num);
			if(remainder!=0)
			this.nextChain.dispense(new Currency(remainder));
		}else{
			this.nextChain.dispense(cur);
		}
	}
}

/** 具体处理者，找10块 */
class Dollar10Chain implements DispenseChain{
	
	private DispenseChain nextChain;
	@Override
	public void setNextChain(DispenseChain chain) {
		// TODO Auto-generated method stub
		this.nextChain = chain;
	}

	@Override
	public void dispense(Currency cur) {
		// TODO Auto-generated method stub
		if(cur.getAmount() >= 10){
			int num = cur.getAmount() / 10;
			int remainder = cur.getAmount() % 10;
			System.out.println("Dollar10 dispense: " + num);
			if(remainder!=0)
			this.nextChain.dispense(new Currency(remainder));
		}else{
			this.nextChain.dispense(cur);
		}
	}
}

/** 使用者类 */
public class ChainOfResponsibilityPattern {
	
	private DispenseChain dollar50Chain;
	
	//实用类中非常关键的责任链设置，最外层的责任链用于测试类使用
	public ChainOfResponsibilityPattern(){
		dollar50Chain = new Dollar50Chain();
		DispenseChain dollar20Chain = new Dollar20Chain();
		DispenseChain dollar10Chain = new Dollar10Chain();
		
		dollar50Chain.setNextChain(dollar20Chain);
		dollar20Chain.setNextChain(dollar10Chain);
	}
	
	public static void main(String[] args){
		ChainOfResponsibilityPattern chain = new ChainOfResponsibilityPattern();
		Currency cur = new Currency(360);
		chain.dollar50Chain.dispense(cur);
	}
}
