package DesignPattern;

/*
 *[��Ϊģʽ]
 * ״̬ģʽ
 * ��������Լ����ڲ�״̬���ı�����Ϊ��
 * ״̬ģʽ������������ɫ��Context �� State��State�����˳���ӿں;���ʵ��State�࣬Context��ͨ����State����������л���Ϊ.
 * Context��Ҳʵ����State�ӿڣ�ͨ������һ�����еľ���״̬����ͨ����״ִ̬�����Ӧ�ķ���.
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
