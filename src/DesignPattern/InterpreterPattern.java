package DesignPattern;


/**
 * [��Ϊģʽ]
 * ������ģʽ��������������һ���﷨��������������ɫ���������桢��������Լ��������ʵ��
 * */

/** ���������� */
class InterpreterContext {
	 
    public String getBinaryFormat(int i){
        return Integer.toBinaryString(i);
    }
     
    public String getHexadecimalFormat(int i){
        return Integer.toHexString(i);
    }
}

/** ������� */
interface Expression {
    String interpret(InterpreterContext ic);
}

/** ���ľ���ʵ�� */
class intToBinaryExpression implements Expression{

	private int i;
	public intToBinaryExpression(int i) {
		this.i = i;
	}
	
	@Override
	public String interpret(InterpreterContext ic) {
		return ic.getBinaryFormat(i);
	}
}

class intToHexExpression implements Expression{

	private int i;
	public intToHexExpression(int i){
		this.i = i;
	}
	@Override
	public String interpret(InterpreterContext ic) {
		return ic.getHexadecimalFormat(i);
	}
	
}

/** �ͻ��� */
public class InterpreterPattern {
	public static void main(String[] args) {
		int i = 28;
		InterpreterContext ic = new InterpreterContext();
		
		Expression ex = new intToBinaryExpression(i);
		System.out.println(ex.interpret(ic));
		
		ex = new intToHexExpression(i);
		System.out.println(ex.interpret(ic));
		
	}
}
