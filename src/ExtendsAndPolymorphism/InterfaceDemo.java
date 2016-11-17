package ExtendsAndPolymorphism;

import java.util.AbstractList;
import java.util.HashMap;

interface Playable{
	void play();
}

interface Bounceable{
	void play();
}

interface Rollable extends Playable, Bounceable{
	Ball ball = new Ball("PingPang");//ball在接口中属性自动加上public static final
}

class Ball implements Rollable{
	private String name;
	public String getName(){
		return name;
	}

	public Ball(String name){
		this.name = name;
	}

	public void play(){
		// ball = new Ball("Football");
		System.out.println(ball.getName());
	}
}

public class InterfaceDemo extends AbstractList {
	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
