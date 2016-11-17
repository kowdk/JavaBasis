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

interface Subject {
	public void register(Observer obj);

	public void unregister(Observer obj);

	public void notifyObservers();

	public Object getUpdate(Observer obj);
}

interface Observer {
	public void update();

	public void setSubject(Subject sub);
}

class MyTopic implements Subject {

	//subjectά����һ���۲��ߵ��б�
	private List<Observer> observers;
	private String msg;
	private boolean changed;
	private final Object MUTEX = new Object();

	public MyTopic() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList<Observer>();
	}

	@Override
	public void register(Observer obj) {
		// TODO Auto-generated method stub
		if (obj == null)
			throw new NullPointerException("Null Observer...");
		synchronized (MUTEX) {
			if (!observers.contains(obj))
				observers.add(obj);
		}
	}

	@Override
	public void unregister(Observer obj) {
		// TODO Auto-generated method stub
		synchronized (MUTEX) {
			observers.remove(obj);
		}
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		List<Observer> observerLocal = null;
		synchronized (MUTEX) {
			if (!changed)
				return;
			observerLocal = new ArrayList<Observer>(this.observers);
			this.changed = false;
		}
		for (Observer obj : observerLocal) {
			obj.update();
		}
	}

	//����observer�ķ��ʣ���������Ϣ
	@Override
	public Object getUpdate(Observer obj) {
		// TODO Auto-generated method stub
		return this.msg;
	}

	//ÿ�η�����Ϣ����֪ͨ����ע���observer
	public void postMessage(String msg) {
		System.out.println("Message Posted to the topic: " + msg);
		this.msg = msg;
		this.changed = true;
		notifyObservers();
	}
}

class MyTopicSubscriber implements Observer {
	private String name;
	private Subject topic;

	public MyTopicSubscriber(String nm) {
		this.name = nm;
	}

	//�������������ȡ��Ϣ
	@Override
	public void update() {
		// TODO Auto-generated method stub
		String msg = (String) topic.getUpdate(this);
		if (msg == null) {
			System.out.println(name + ":: No new msg...");
		} else {
			System.out.println(name + ":: New msg...");
		}
	}

	@Override
	public void setSubject(Subject sub) {
		// TODO Auto-generated method stub
		this.topic = sub;
	}
}

public class ObserverPattern {
	public static void main(String[] args) {
		MyTopic topic = new MyTopic();
		Observer obj1 = new MyTopicSubscriber("�۲���1");
		Observer obj2 = new MyTopicSubscriber("�۲���2");
		
		topic.register(obj1);
		topic.register(obj2);
		
		obj1.setSubject(topic);
		obj2.setSubject(topic);
		
		//obj1.update();
		/*
		 * topic�㲥֮��subject���ȸı��Լ���״̬����֪����observer�Լ��Ѿ��ı䣬����Ҫ����observer��update������
		 * ��observer��Ҫ����subject��getUpdate��������øı����Ϣ��
		 * �������Һ����������״̬��������ҪͨѶ
		 * */
		topic.postMessage("New Msg...");
		
		/*Observer obj3 = new MyTopicSubscriber("obj3");
		topic.register(obj3);
		obj3.setSubject(topic);
		obj3.update();*/
	}
}
