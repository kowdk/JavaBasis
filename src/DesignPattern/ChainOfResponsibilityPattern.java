package DesignPattern;

/**
 * [��Ϊģʽ]
 * ������ģʽ���ж������ͬ��һ��������д�����Щ����ʹ����ʽ�洢�ṹ���γ�һ������ÿ������֪���Լ�����һ������
 * һ�������������д����������һЩ�����󽫶��󴫵ݸ���һ������Ҳ�����ڴ˶����Ͻ�������Ĵ�������������
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

/** �������� */
interface DispenseChain{
	//������һ��������
	public void setNextChain(DispenseChain chain);
	//��Ǯ�ķ���
	public void dispense(Currency c);
}

/** ���崦���ߣ���50�� */
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

/** ���崦���ߣ���20�� */
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

/** ���崦���ߣ���10�� */
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

/** ʹ������ */
public class ChainOfResponsibilityPattern {
	
	private DispenseChain dollar50Chain;
	
	//ʵ�����зǳ��ؼ������������ã����������������ڲ�����ʹ��
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
