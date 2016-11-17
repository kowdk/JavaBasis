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

	//subject维护了一个观察者的列表
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

	//接受observer的访问，传回新消息
	@Override
	public Object getUpdate(Observer obj) {
		// TODO Auto-generated method stub
		return this.msg;
	}

	//每次发布信息都会通知所有注册的observer
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

	//从主题者那里获取信息
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
		Observer obj1 = new MyTopicSubscriber("观察者1");
		Observer obj2 = new MyTopicSubscriber("观察者2");
		
		topic.register(obj1);
		topic.register(obj2);
		
		obj1.setSubject(topic);
		obj2.setSubject(topic);
		
		//obj1.update();
		/*
		 * topic广播之后，subject首先改变自己的状态并告知所有observer自己已经改变，这需要调用observer的update方法，
		 * 而observer需要调用subject的getUpdate方法来获得改变的信息。
		 * 你中有我和我中有你的状态，二者需要通讯
		 * */
		topic.postMessage("New Msg...");
		
		/*Observer obj3 = new MyTopicSubscriber("obj3");
		topic.register(obj3);
		obj3.setSubject(topic);
		obj3.update();*/
	}
}
