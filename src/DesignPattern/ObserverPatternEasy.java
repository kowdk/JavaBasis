package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/*
 * [行为模式]
 * 观察者模式：监视其他对象状态的叫做观察者，被监视的对象叫做主题者。主题者包括了大量的观察者监视自己的状态。
 * 主题者需要提供观察者注册观察自己和取消观察自己的方法，同时要有广播观察者的方法来提示自己的更新。
 * 观察者则需要提供一个主题对象表示观察这个主题对象，同时使用这个对象来通知观察者自己主题者的变化。
 * MVC框架使用了观察者模式，Model相当于主题者，而View相当于观察者。
 * */

class ASubject {
	private List<ObserverInter> observers = new ArrayList<ObserverInter>();
	private int state = 0;
	
	public int getState(){
		return state;
	}
	
	public void setState(int state){
		this.state = state;
		notifyAllObservers(this.observers);
	}
	
	public void register(ObserverInter o){
		observers.add(o);
	}

	private void notifyAllObservers(List<ObserverInter> observers) {
		for(ObserverInter o : observers) {
			o.update();
		}
	}
}

interface ObserverInter{
	public void update();
}

class ObserverConcreate implements ObserverInter{
	private ASubject sub = null;
	private String name = "";
	
	public ObserverConcreate(ASubject sub, String name){
		this.sub = sub;
		this.name = name;
	}
	
	@Override
	public void update() {
		System.out.println(this.name + "观测到Subject的状态是：" + this.sub.getState());
	}
}

public class ObserverPatternEasy {
	public static void main(String[] args) {
		ASubject s = new ASubject();
		
		ObserverConcreate obs1 = new ObserverConcreate(s, "xu");
		ObserverConcreate obs2 = new ObserverConcreate(s, "yin");
		s.register(obs1);
		s.register(obs2);
		
		s.setState(10);
		s.setState(15);
	}
}
