package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/*
 *[��Ϊģʽ]
 * �м���ģʽ
 * �м���ģʽΪϵͳ�ڶ���֮���ͨѶ�ṩý��
 * ������֮��ͨѶ�ܸ��ӣ���ʹ��ͨѶ���м������������ͨѶ�����������µĽ�ɫ��
 * �����н��ߣ������ͬ��������н��߶���Ľӿڣ����ڸ���ͬ����֮���ͨ�š�һ�����һ���򼸸�������¼���������������ȥʵ�֡�
 * �н���ʵ���ࣺ�ӳ����н��߼̳ж�����ʵ�ֳ����н����ж�����¼���������һ��ͬ���������Ϣ��Ȼ��ͨ����ϢӰ������ͬʱ�ࡣ
 * ͬ���ࣺ���һ�������Ӱ�������Ķ���ͬʱҲ�ᱻ��������Ӱ�죬��ô�����������Ϊͬ���ࡣ
 */

interface ChatMediator{
	
	//�н���ά��һ��user�б�����Ϣ���͵�������user��
	public void sendMessage(String msg, User user);
	
	//��userע�ᵽ�н�����
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
