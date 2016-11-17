package DesignPattern;


/**
 * [行为模式]
 * 解释者模式：解释者类似于一个语法解释器，包含角色：解释引擎、抽象语句以及具体语句实现
 * */

/** 解释器引擎 */
class InterpreterContext {
	 
    public String getBinaryFormat(int i){
        return Integer.toBinaryString(i);
    }
     
    public String getHexadecimalFormat(int i){
        return Integer.toHexString(i);
    }
}

/** 抽象语句 */
interface Expression {
    String interpret(InterpreterContext ic);
}

/** 语句的具体实现 */
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

/** 客户类 */
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
