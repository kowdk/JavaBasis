package DesignPattern;

/*
 *[行为模式]
 * 状态模式
 * 对象根据自己的内部状态来改变其行为。
 * 状态模式包含了两个角色：Context 和 State，State包括了抽象接口和具体实现State类，Context则通过对State类的引用来切换行为.
 * Context类也实现了State接口，通过保持一个现有的具体状态，并通过该状态执行其对应的方法.
 * */

interface State{
	public void doAction();
}

class TVStartState implements State{
	@Override
	public void doAction() {
		System.out.println("TV is Started...");
	}
}

class TVCloseState implements State{
	@Override
	public void doAction() {
		System.out.println("TV is closed...");
	}
}

class TVContext implements State{

	private State tvState;
	
	@Override
	public void doAction() {
		this.tvState.doAction();
	}

	public State getTvState() {
		return tvState;
	}

	public void setTvState(State tvState) {
		this.tvState = tvState;
	}
}

public class StatePattern {
	public static void main(String[] args) {
		TVContext context = new TVContext();
		State onState = new TVStartState();
		State offState = new TVCloseState();
		
		context.setTvState(onState);
		context.doAction();
		
		context.setTvState(offState);
		context.doAction();
	}
}
