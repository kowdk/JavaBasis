package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/*
 * [��Ϊģʽ]
 * �۲���ģʽ��������������״̬�Ľ����۲��ߣ������ӵĶ�����������ߡ������߰����˴����Ĺ۲��߼����Լ���״̬��
 * ��������Ҫ�ṩ�۲���ע��۲��Լ���ȡ���۲��Լ��ķ�����ͬʱҪ�й㲥�۲��ߵķ�������ʾ�Լ��ĸ��¡�
 * �۲�������Ҫ�ṩһ����������ʾ�۲�����������ͬʱʹ�����������֪ͨ�۲����Լ������ߵı仯��
 * MVC���ʹ���˹۲���ģʽ��Model�൱�������ߣ���View�൱�ڹ۲��ߡ�
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
		System.out.println(this.name + "�۲⵽Subject��״̬�ǣ�" + this.sub.getState());
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
