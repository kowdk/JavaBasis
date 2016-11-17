package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/*
 *[行为模式]
 * 中间者模式
 * 中间者模式为系统内对象之间的通讯提供媒介
 * 当对象之间通讯很复杂，就使用通讯的中间件来管理整个通讯，包含了以下的角色：
 * 抽象中介者：定义好同事类对象到中介者对象的接口，用于各个同事类之间的通信。一般包括一个或几个抽象的事件方法，并由子类去实现。
 * 中介者实现类：从抽象中介者继承而来，实现抽象中介者中定义的事件方法。从一个同事类接收消息，然后通过消息影响其他同时类。
 * 同事类：如果一个对象会影响其他的对象，同时也会被其他对象影响，那么这两个对象称为同事类。
 */

interface ChatMediator{
	
	//中介者维护一个user列表，将消息发送到其他的user里
	public void sendMessage(String msg, User user);
	
	//将user注册到中介者上
	void addUser(User user);
	
}

class ChatMediatorImpl implements ChatMediator{

	private List<User> users;
	
	public ChatMediatorImpl(){
		this.users = new ArrayList<User>();
	}
	
	@Override
	public void sendMessage(String msg, User user) {
		for(User u : users)
		{
			if(!u.equals(user))
				u.receive(msg);
		}
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		this.users.add(user);
	}	
}

abstract class User{
	protected String name;
	protected ChatMediator mediator;
	 
	public User(ChatMediator mediator, String name)
	{
		this.mediator = mediator;
		this.name = name;
	}
	
	public abstract void send(String msg);
	public abstract void receive(String msg);
	
}

class UserImpl extends User{

	public UserImpl(ChatMediator mediator, String name) {
		super(mediator, name);
	}

	@Override
	public void send(String msg) {
		System.out.println(this.name + " sending msg: " + msg);
		this.mediator.sendMessage(msg, this);
	}

	@Override
	public void receive(String msg) {
		System.out.println(this.name + " received msg: " + msg);
	}
	
}

public class MediatorPattern {
	public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Pankaj");
        User user2 = new UserImpl(mediator, "Lisa");
        User user3 = new UserImpl(mediator, "Saurabh");
        User user4 = new UserImpl(mediator, "David");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);
         
        user1.send("Hi All");
         
    }
}
